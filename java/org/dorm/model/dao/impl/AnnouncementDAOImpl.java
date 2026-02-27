package org.dorm.model.dao.impl;

import org.dorm.model.dao.AnnouncementDAO;
import org.dorm.model.entity.Announcement;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 公告数据访问实现类
 */
public class AnnouncementDAOImpl implements AnnouncementDAO {
    private static final Logger logger = LoggerFactory.getLogger(AnnouncementDAOImpl.class);

    @Override
    public List<Announcement> findAllActive() {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE status = 'active' ORDER BY publish_time DESC";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("获取所有激活公告失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
    }

    @Override
    public List<Announcement> findAll() {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement ORDER BY publish_time DESC";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("获取所有公告失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
    }

    @Override
    public Announcement findById(String announcementId) {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcementId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                return announcement;
            }
        } catch (SQLException e) {
            logger.error("根据ID查找公告失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public boolean insert(Announcement announcement) {
        String sql = "INSERT INTO announcement (announcement_id, title, content, publisher, publish_time, status, category) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcement.getAnnouncementId());
            stmt.setString(2, announcement.getTitle());
            stmt.setString(3, announcement.getContent());
            stmt.setString(4, announcement.getPublisher());
            stmt.setTimestamp(5, new Timestamp(announcement.getPublishTime().getTime()));
            stmt.setString(6, announcement.getStatus());
            stmt.setString(7, announcement.getCategory());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("公告插入成功：{}", announcement.getAnnouncementId());
                return true;
            }
        } catch (SQLException e) {
            logger.error("插入公告失败", e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public boolean update(Announcement announcement) {
        String sql = "UPDATE announcement SET title = ?, content = ?, status = ?, category = ? WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcement.getTitle());
            stmt.setString(2, announcement.getContent());
            stmt.setString(3, announcement.getStatus());
            stmt.setString(4, announcement.getCategory());
            stmt.setString(5, announcement.getAnnouncementId());

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("公告更新成功：{}", announcement.getAnnouncementId());
                return true;
            }
        } catch (SQLException e) {
            logger.error("更新公告失败", e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public boolean delete(String announcementId) {
        String sql = "DELETE FROM announcement WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, announcementId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("公告删除成功：{}", announcementId);
                return true;
            }
        } catch (SQLException e) {
            logger.error("删除公告失败", e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public boolean updateStatus(String announcementId, String status) {
        String sql = "UPDATE announcement SET status = ? WHERE announcement_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, status);
            stmt.setString(2, announcementId);

            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                logger.info("公告状态更新成功：{} -> {}", announcementId, status);
                return true;
            }
        } catch (SQLException e) {
            logger.error("更新公告状态失败", e);
        } finally {
            closeResources(conn, stmt, null);
        }
        return false;
    }

    @Override
    public List<Announcement> findByCategory(String category) {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE category = ? AND status = 'active' ORDER BY publish_time DESC";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, category);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("根据分类获取公告失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
    }

    @Override
    public List<Announcement> findLatest(int limit) {
        String sql = "SELECT announcement_id, title, content, publisher, publish_time, status, category FROM announcement WHERE status = 'active' ORDER BY publish_time DESC LIMIT ?";
        List<Announcement> announcements = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, limit);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Announcement announcement = new Announcement();
                announcement.setAnnouncementId(rs.getString("announcement_id"));
                announcement.setTitle(rs.getString("title"));
                announcement.setContent(rs.getString("content"));
                announcement.setPublisher(rs.getString("publisher"));
                announcement.setPublishTime(rs.getTimestamp("publish_time"));
                announcement.setStatus(rs.getString("status"));
                announcement.setCategory(rs.getString("category"));
                announcements.add(announcement);
            }
        } catch (SQLException e) {
            logger.error("获取最新公告失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return announcements;
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