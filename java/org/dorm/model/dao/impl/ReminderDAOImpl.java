package org.dorm.model.dao.impl;

import org.dorm.model.dao.ReminderDAO;
import org.dorm.model.entity.Reminder;
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
 * 提醒数据访问实现类
 */
public class ReminderDAOImpl implements ReminderDAO {
    private static final Logger logger = LoggerFactory.getLogger(ReminderDAOImpl.class);

    @Override
    public List<Reminder> findAll() {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("获取所有提醒失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public Reminder findByReminderId(String reminderId) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE reminder_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminderId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                return reminder;
            }
        } catch (SQLException e) {
            logger.error("根据ID查找提醒失败：{}", reminderId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Reminder> findByStudentId(String studentId) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE student_id = ? ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("根据学生ID查找提醒失败：{}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public List<Reminder> findByStatus(String status) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE status = ? ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("根据状态查找提醒失败：{}", status, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public List<Reminder> findByType(String type) {
        String sql = "SELECT reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler FROM reminder WHERE type = ? ORDER BY create_time DESC";
        List<Reminder> reminders = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, type);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Reminder reminder = new Reminder();
                reminder.setReminderId(rs.getString("reminder_id"));
                reminder.setStudentId(rs.getString("student_id"));
                reminder.setStudentName(rs.getString("student_name"));
                reminder.setType(rs.getString("type"));
                reminder.setTitle(rs.getString("title"));
                reminder.setContent(rs.getString("content"));
                reminder.setPriority(rs.getString("priority"));
                reminder.setStatus(rs.getString("status"));
                reminder.setCreateTime(rs.getTimestamp("create_time"));
                reminder.setHandleTime(rs.getTimestamp("handle_time"));
                reminder.setHandler(rs.getString("handler"));
                reminders.add(reminder);
            }
        } catch (SQLException e) {
            logger.error("根据类型查找提醒失败：{}", type, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return reminders;
    }

    @Override
    public List<Reminder> findUnprocessedReminders() {
        return findByStatus("待处理");
    }

    @Override
    public boolean addReminder(Reminder reminder) {
        String sql = "INSERT INTO reminder (reminder_id, student_id, student_name, type, title, content, priority, status, create_time, handle_time, handler) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminder.getReminderId());
            stmt.setString(2, reminder.getStudentId());
            stmt.setString(3, reminder.getStudentName());
            stmt.setString(4, reminder.getType());
            stmt.setString(5, reminder.getTitle());
            stmt.setString(6, reminder.getContent());
            stmt.setString(7, reminder.getPriority());
            stmt.setString(8, reminder.getStatus());
            if (reminder.getCreateTime() != null) {
                stmt.setTimestamp(9, new java.sql.Timestamp(reminder.getCreateTime().getTime()));
            } else {
                stmt.setTimestamp(9, new java.sql.Timestamp(System.currentTimeMillis()));
            }
            if (reminder.getHandleTime() != null) {
                stmt.setTimestamp(10, new java.sql.Timestamp(reminder.getHandleTime().getTime()));
            } else {
                stmt.setTimestamp(10, null);
            }
            stmt.setString(11, reminder.getHandler());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("添加提醒失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateReminder(Reminder reminder) {
        String sql = "UPDATE reminder SET student_id=?, student_name=?, type=?, title=?, content=?, priority=?, status=?, create_time=?, handle_time=?, handler=? WHERE reminder_id=?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminder.getStudentId());
            stmt.setString(2, reminder.getStudentName());
            stmt.setString(3, reminder.getType());
            stmt.setString(4, reminder.getTitle());
            stmt.setString(5, reminder.getContent());
            stmt.setString(6, reminder.getPriority());
            stmt.setString(7, reminder.getStatus());
            if (reminder.getCreateTime() != null) {
                stmt.setTimestamp(8, new java.sql.Timestamp(reminder.getCreateTime().getTime()));
            } else {
                stmt.setTimestamp(8, null);
            }
            if (reminder.getHandleTime() != null) {
                stmt.setTimestamp(9, new java.sql.Timestamp(reminder.getHandleTime().getTime()));
            } else {
                stmt.setTimestamp(9, null);
            }
            stmt.setString(10, reminder.getHandler());
            stmt.setString(11, reminder.getReminderId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("更新提醒失败：{}", reminder.getReminderId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteReminder(String reminderId) {
        String sql = "DELETE FROM reminder WHERE reminder_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, reminderId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("删除提醒失败：{}", reminderId, e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public int deleteProcessedReminders() {
        String sql = "DELETE FROM reminder WHERE status = '已处理'";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected;
        } catch (SQLException e) {
            logger.error("批量删除已处理提醒失败", e);
            return 0;
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