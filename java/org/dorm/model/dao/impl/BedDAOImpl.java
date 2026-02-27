package org.dorm.model.dao.impl;

import org.dorm.model.dao.BedDAO;
import org.dorm.model.entity.Bed;
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
 * 床位数据访问实现类
 */
public class BedDAOImpl implements BedDAO {
    private static final Logger logger = LoggerFactory.getLogger(BedDAOImpl.class);

    @Override
    public List<Bed> findAll() {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed ORDER BY dorm_id, bed_id";
        List<Bed> beds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                beds.add(bed);
            }
        } catch (SQLException e) {
            logger.error("获取所有床位失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return beds;
    }

    @Override
    public Bed findByBedId(String bedId) {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                return bed;
            }
        } catch (SQLException e) {
            logger.error("查找床位失败：{}", bedId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Bed> findAvailableBeds() {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed WHERE status = '空闲' ORDER BY dorm_id, bed_id";
        List<Bed> beds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                beds.add(bed);
            }
        } catch (SQLException e) {
            logger.error("获取可用床位失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return beds;
    }

    @Override
    public List<Bed> findByDormId(String dormId) {
        String sql = "SELECT bed_id, dorm_id, status, student_id FROM bed WHERE dorm_id = ? ORDER BY bed_id";
        List<Bed> beds = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Bed bed = new Bed();
                bed.setBedId(rs.getString("bed_id"));
                bed.setDormId(rs.getString("dorm_id"));
                bed.setStatus(rs.getString("status"));
                bed.setStudentId(rs.getString("student_id"));
                beds.add(bed);
            }
        } catch (SQLException e) {
            logger.error("获取宿舍床位失败：{}", dormId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return beds;
    }

    @Override
    public boolean addBed(Bed bed) {
        String sql = "INSERT INTO bed (bed_id, dorm_id, status, student_id) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bed.getBedId());
            stmt.setString(2, bed.getDormId());
            stmt.setString(3, bed.getStatus());
            stmt.setString(4, bed.getStudentId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("添加床位失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateBed(Bed bed) {
        String sql = "UPDATE bed SET dorm_id = ?, status = ?, student_id = ? WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bed.getDormId());
            stmt.setString(2, bed.getStatus());
            stmt.setString(3, bed.getStudentId());
            stmt.setString(4, bed.getBedId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新床位失败：{}", bed.getBedId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteBed(String bedId) {
        String sql = "DELETE FROM bed WHERE bed_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("删除床位失败：{}", bedId, e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 关闭数据库资源
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            logger.error("关闭数据库资源失败", e);
        }
    }
}