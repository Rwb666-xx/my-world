package org.dorm.model.dao.impl;

import org.dorm.model.dao.AccommodationDAO;
import org.dorm.model.entity.Accommodation;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 住宿分配数据访问实现
 */
public class AccommodationDAOImpl implements AccommodationDAO {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationDAOImpl.class);

    @Override
    public List<Accommodation> findAll() {
        String sql = "SELECT s.student_id, s.name, s.college_name, s.class_name, " +
                    "b.dorm_id, b.bed_id, s.bed_id IS NOT NULL as has_bed, " +
                    "NOW() as checkin_date " +
                    "FROM student s LEFT JOIN bed b ON s.bed_id = b.bed_id " +
                    "ORDER BY s.student_id";

        List<Accommodation> accommodations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Accommodation accommodation = new Accommodation();
                accommodation.setStudentId(rs.getString("student_id"));
                accommodation.setStudentName(rs.getString("name"));
                accommodation.setCollegeName(rs.getString("college_name"));
                accommodation.setClassName(rs.getString("class_name"));
                accommodation.setDormitoryId(rs.getString("dorm_id"));
                accommodation.setBedId(rs.getString("bed_id"));
                accommodation.setCheckinDate(rs.getDate("checkin_date"));

                // 根据是否有床位设置状态
                if (rs.getString("bed_id") != null) {
                    accommodation.setStatus("已入住");
                } else {
                    accommodation.setStatus("待分配");
                }

                accommodations.add(accommodation);
            }

            logger.info("获取住宿信息成功：{}条记录", accommodations.size());

        } catch (SQLException e) {
            logger.error("获取住宿信息失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return accommodations;
    }

    @Override
    public Accommodation findByStudentId(String studentId) {
        String sql = "SELECT s.student_id, s.name, s.college_name, s.class_name, " +
                    "b.dorm_id, b.bed_id, NOW() as checkin_date " +
                    "FROM student s LEFT JOIN bed b ON s.bed_id = b.bed_id " +
                    "WHERE s.student_id = ?";

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Accommodation accommodation = new Accommodation();
                accommodation.setStudentId(rs.getString("student_id"));
                accommodation.setStudentName(rs.getString("name"));
                accommodation.setCollegeName(rs.getString("college_name"));
                accommodation.setClassName(rs.getString("class_name"));
                accommodation.setDormitoryId(rs.getString("dorm_id"));
                accommodation.setBedId(rs.getString("bed_id"));
                accommodation.setCheckinDate(rs.getDate("checkin_date"));

                if (rs.getString("bed_id") != null) {
                    accommodation.setStatus("已入住");
                } else {
                    accommodation.setStatus("待分配");
                }

                return accommodation;
            }

        } catch (SQLException e) {
            logger.error("获取学生住宿信息失败：{}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public boolean assignBed(String studentId, String bedId) {
        // 检查床位是否已被占用
        if (isBedOccupied(bedId)) {
            logger.warn("床位已被占用：{}", bedId);
            return false;
        }

        // 分配床位：更新学生表的bed_id字段
        String sql = "UPDATE student SET bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            stmt.setString(2, studentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("分配床位成功：学生{} -> 床位{}", studentId, bedId);
                return true;
            }

        } catch (SQLException e) {
            logger.error("分配床位失败：学生{} -> 床位{}", studentId, bedId, e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return false;
    }

    @Override
    public boolean changeBed(String studentId, String newBedId) {
        // 检查新床位是否已被占用
        if (isBedOccupied(newBedId)) {
            logger.warn("新床位已被占用：{}", newBedId);
            return false;
        }

        // 调换床位：更新学生表的bed_id字段
        String sql = "UPDATE student SET bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, newBedId);
            stmt.setString(2, studentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("调换床位成功：学生{} -> 新床位{}", studentId, newBedId);
                return true;
            }

        } catch (SQLException e) {
            logger.error("调换床位失败：学生{} -> 新床位{}", studentId, newBedId, e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return false;
    }

    @Override
    public boolean checkout(String studentId) {
        // 退宿：清空学生表的bed_id字段
        String sql = "UPDATE student SET bed_id = NULL WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                logger.info("退宿成功：学生{}", studentId);
                return true;
            }

        } catch (SQLException e) {
            logger.error("退宿失败：学生{}", studentId, e);
        } finally {
            closeResources(conn, stmt, null);
        }

        return false;
    }

    @Override
    public List<String> findAvailableBeds() {
        String sql = "SELECT bed_id FROM bed WHERE bed_id NOT IN (SELECT bed_id FROM student WHERE bed_id IS NOT NULL) ORDER BY bed_id";
        List<String> availableBeds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                availableBeds.add(rs.getString("bed_id"));
            }

            logger.info("获取空闲床位成功：{}个", availableBeds.size());

        } catch (SQLException e) {
            logger.error("获取空闲床位失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return availableBeds;
    }

    /**
     * 检查床位是否已被占用
     */
    private boolean isBedOccupied(String bedId) {
        String sql = "SELECT COUNT(*) FROM student WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            logger.error("检查床位占用状态失败：{}", bedId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return false;
    }

    /**
     * 关闭数据库资源
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("关闭ResultSet失败", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("关闭PreparedStatement失败", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.error("关闭Connection失败", e);
            }
        }
    }
}