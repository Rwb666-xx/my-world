package org.dorm.model.dao.impl;

import org.dorm.model.dao.ViolationDAO;
import org.dorm.model.entity.Violation;
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
 * 违纪数据访问实现类
 */
public class ViolationDAOImpl implements ViolationDAO {
    private static final Logger logger = LoggerFactory.getLogger(ViolationDAOImpl.class);

    @Override
    public List<Violation> findAll() {
        String sql = "SELECT violation_id, student_id, description, violation_date, penalty FROM violation ORDER BY violation_date DESC";
        List<Violation> violations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Violation violation = new Violation();
                violation.setViolationId(rs.getString("violation_id"));
                violation.setStudentId(rs.getString("student_id"));
                violation.setDescription(rs.getString("description"));
                violation.setViolationDate(rs.getDate("violation_date"));
                violation.setPenalty(rs.getString("penalty"));
                violations.add(violation);
            }
        } catch (SQLException e) {
            logger.error("获取所有违纪记录失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return violations;
    }

    @Override
    public Violation findByViolationId(String violationId) {
        String sql = "SELECT violation_id, student_id, description, violation_date, penalty FROM violation WHERE violation_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violationId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Violation violation = new Violation();
                violation.setViolationId(rs.getString("violation_id"));
                violation.setStudentId(rs.getString("student_id"));
                violation.setDescription(rs.getString("description"));
                violation.setViolationDate(rs.getDate("violation_date"));
                violation.setPenalty(rs.getString("penalty"));
                return violation;
            }
        } catch (SQLException e) {
            logger.error("查找违纪记录失败：{}", violationId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Violation> findByStudentId(String studentId) {
        String sql = "SELECT violation_id, student_id, description, violation_date, penalty FROM violation WHERE student_id = ? ORDER BY violation_date DESC";
        List<Violation> violations = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Violation violation = new Violation();
                violation.setViolationId(rs.getString("violation_id"));
                violation.setStudentId(rs.getString("student_id"));
                violation.setDescription(rs.getString("description"));
                violation.setViolationDate(rs.getDate("violation_date"));
                violation.setPenalty(rs.getString("penalty"));
                violations.add(violation);
            }
        } catch (SQLException e) {
            logger.error("获取学生违纪记录失败：{}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return violations;
    }

    @Override
    public boolean addViolation(Violation violation) {
        String sql = "INSERT INTO violation (violation_id, student_id, description, violation_date, penalty) VALUES (?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violation.getViolationId());
            stmt.setString(2, violation.getStudentId());
            stmt.setString(3, violation.getDescription());
            stmt.setDate(4, new java.sql.Date(violation.getViolationDate().getTime()));
            stmt.setString(5, violation.getPenalty());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("添加违纪记录失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateViolation(Violation violation) {
        String sql = "UPDATE violation SET student_id = ?, description = ?, violation_date = ?, penalty = ? WHERE violation_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violation.getStudentId());
            stmt.setString(2, violation.getDescription());
            stmt.setDate(3, new java.sql.Date(violation.getViolationDate().getTime()));
            stmt.setString(4, violation.getPenalty());
            stmt.setString(5, violation.getViolationId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新违纪记录失败：{}", violation.getViolationId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteViolation(String violationId) {
        String sql = "DELETE FROM violation WHERE violation_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, violationId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("删除违纪记录失败：{}", violationId, e);
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