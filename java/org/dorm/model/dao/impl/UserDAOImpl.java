package org.dorm.model.dao.impl;

import org.dorm.model.dao.UserDAO;
import org.dorm.model.entity.User;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 用户数据访问实现类
 */
public class UserDAOImpl implements UserDAO {
    private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

    @Override
    public User findByUserId(String userId) {
        String sql = "SELECT user_id, password, user_type FROM user WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                return user;
            }
        } catch (SQLException e) {
            logger.error("查找用户失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public User login(String userId, String password) {
        String sql = "SELECT user_id, password, user_type FROM user WHERE user_id = ? AND password = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);
            stmt.setString(2, password);
            rs = stmt.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setUserId(rs.getString("user_id"));
                user.setPassword(rs.getString("password"));
                user.setUserType(rs.getString("user_type"));
                logger.info("用户登录成功: {}", userId);
                return user;
            }
        } catch (SQLException e) {
            logger.error("用户登录验证失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public boolean addUser(User user) {
        String sql = "INSERT INTO user (user_id, password, user_type) VALUES (?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUserId());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getUserType());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("添加用户失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateUser(User user) {
        String sql = "UPDATE user SET password = ?, user_type = ? WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getUserType());
            stmt.setString(3, user.getUserId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新用户失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteUser(String userId) {
        String sql = "DELETE FROM user WHERE user_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, userId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("删除用户失败", e);
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