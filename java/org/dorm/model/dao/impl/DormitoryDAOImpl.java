package org.dorm.model.dao.impl;

import org.dorm.model.dao.DormitoryDAO;
import org.dorm.model.entity.Dormitory;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 宿舍数据访问实现类
 */
public class DormitoryDAOImpl implements DormitoryDAO {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryDAOImpl.class);

    @Override
    public Dormitory findByDormitoryId(String dormitoryId) {
        String sql = "SELECT dorm_id, building, floor, capacity, manager_name, manager_phone FROM dormitory WHERE dorm_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitoryId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryId(rs.getString("dorm_id"));
                dormitory.setBuilding(rs.getString("building"));
                dormitory.setFloor(rs.getInt("floor"));
                dormitory.setTotalBeds(rs.getInt("capacity"));
                dormitory.setManagerName(rs.getString("manager_name"));
                dormitory.setManagerPhone(rs.getString("manager_phone"));
                return dormitory;
            }
        } catch (SQLException e) {
            logger.error("查找宿舍失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public List<Dormitory> findAll() {
        String sql = "SELECT dorm_id, building, floor, capacity, manager_name, manager_phone FROM dormitory ORDER BY dorm_id";
        List<Dormitory> dormitories = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryId(rs.getString("dorm_id"));
                dormitory.setBuilding(rs.getString("building"));
                dormitory.setFloor(rs.getInt("floor"));
                dormitory.setTotalBeds(rs.getInt("capacity"));
                dormitory.setManagerName(rs.getString("manager_name"));
                dormitory.setManagerPhone(rs.getString("manager_phone"));
                dormitories.add(dormitory);
            }
        } catch (SQLException e) {
            logger.error("查找所有宿舍失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return dormitories;
    }

    @Override
    public boolean addDormitory(Dormitory dormitory) {
        String sql = "INSERT INTO dormitory (dorm_id, building, floor, capacity, manager_name, manager_phone) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitory.getDormitoryId());
            stmt.setString(2, dormitory.getBuilding());
            stmt.setInt(3, dormitory.getFloor());
            stmt.setInt(4, dormitory.getTotalBeds());
            stmt.setString(5, dormitory.getManagerName());
            stmt.setString(6, dormitory.getManagerPhone());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("添加宿舍失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateDormitory(Dormitory dormitory) {
        String sql = "UPDATE dormitory SET building = ?, floor = ?, capacity = ?, manager_name = ?, manager_phone = ? WHERE dorm_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitory.getBuilding());
            stmt.setInt(2, dormitory.getFloor());
            stmt.setInt(3, dormitory.getTotalBeds());
            stmt.setString(4, dormitory.getManagerName());
            stmt.setString(5, dormitory.getManagerPhone());
            stmt.setString(6, dormitory.getDormitoryId());

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("更新宿舍失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteDormitory(String dormitoryId) {
        String sql = "DELETE FROM dormitory WHERE dorm_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, dormitoryId);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            logger.error("删除宿舍失败", e);
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
                logger.warn("关闭ResultSet失败", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.warn("关闭PreparedStatement失败", e);
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                logger.warn("关闭Connection失败", e);
            }
        }
    }
}