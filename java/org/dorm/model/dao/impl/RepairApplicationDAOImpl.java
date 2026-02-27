package org.dorm.model.dao.impl;

import org.dorm.model.dao.RepairApplicationDAO;
import org.dorm.model.entity.RepairApplication;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 维修申请数据访问实现类
 */
public class RepairApplicationDAOImpl implements RepairApplicationDAO {
    private static final Logger logger = LoggerFactory.getLogger(RepairApplicationDAOImpl.class);

    @Override
    public RepairApplication findByApplyId(String applyId) {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application WHERE apply_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, applyId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                return application;
            }
        } catch (SQLException e) {
            logger.error("查找维修申请失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public List<RepairApplication> findByStudentId(String studentId) {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application WHERE student_id = ? ORDER BY apply_time DESC";
        List<RepairApplication> applications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                applications.add(application);
            }
        } catch (SQLException e) {
            logger.error("按学生查找维修申请失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return applications;
    }

    @Override
    public List<RepairApplication> findAll() {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application ORDER BY apply_time DESC";
        List<RepairApplication> applications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                applications.add(application);
            }
        } catch (SQLException e) {
            logger.error("获取所有维修申请失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return applications;
    }

    @Override
    public List<RepairApplication> findByStatus(String status) {
        String sql = "SELECT apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status, handler, finish_time FROM repair_application WHERE status = ? ORDER BY apply_time DESC";
        List<RepairApplication> applications = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();

            while (rs.next()) {
                RepairApplication application = new RepairApplication();
                application.setApplyId(rs.getString("apply_id"));
                application.setStudentId(rs.getString("student_id"));
                application.setFaultLocation(rs.getString("fault_location"));
                application.setFaultDesc(rs.getString("fault_desc"));
                application.setContactPhone(rs.getString("contact_phone"));
                application.setApplyTime(rs.getTimestamp("apply_time"));
                application.setStatus(rs.getString("status"));
                application.setHandler(rs.getString("handler"));
                application.setFinishTime(rs.getTimestamp("finish_time"));
                applications.add(application);
            }
        } catch (SQLException e) {
            logger.error("按状态查找维修申请失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return applications;
    }

    @Override
    public boolean addRepairApplication(RepairApplication application) {
        String sql = "INSERT INTO repair_application (apply_id, student_id, fault_location, fault_desc, contact_phone, apply_time, status) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, application.getApplyId());
            stmt.setString(2, application.getStudentId());
            stmt.setString(3, application.getFaultLocation());
            stmt.setString(4, application.getFaultDesc());
            stmt.setString(5, application.getContactPhone());
            stmt.setTimestamp(6, new Timestamp(application.getApplyTime().getTime()));
            stmt.setString(7, application.getStatus());

            int rows = stmt.executeUpdate();
            logger.info("维修申请插入成功，影响行数：{}", rows);
            return rows > 0;
        } catch (SQLException e) {
            logger.error("添加维修申请失败", e);
            logger.error("SQL: {}", sql);
            logger.error("参数: applyId={}, studentId={}, faultLocation={}, faultDesc={}, contactPhone={}, applyTime={}, status={}",
                application.getApplyId(), application.getStudentId(), application.getFaultLocation(),
                application.getFaultDesc(), application.getContactPhone(), application.getApplyTime(), application.getStatus());
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateStatus(String applyId, String status, String handler) {
        String sql = "UPDATE repair_application SET status = ?, handler = ? WHERE apply_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, handler);
            stmt.setString(3, applyId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新维修申请状态失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean completeRepair(String applyId, java.util.Date finishTime) {
        String sql = "UPDATE repair_application SET status = 'completed', finish_time = ? WHERE apply_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setTimestamp(1, new Timestamp(finishTime.getTime()));
            stmt.setString(2, applyId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("完成维修申请失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
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
        DatabaseUtil.closeConnection(conn);
    }
}