package org.dorm.model.dao.impl;

import org.dorm.model.dao.PaymentDAO;
import org.dorm.model.entity.Payment;
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
 * 缴费数据访问实现类
 */
public class PaymentDAOImpl implements PaymentDAO {
    private static final Logger logger = LoggerFactory.getLogger(PaymentDAOImpl.class);

    @Override
    public List<Payment> findAll() {
        String sql = "SELECT payment_id, student_id, amount, payment_date, semester, status FROM payment ORDER BY payment_date DESC";
        List<Payment> payments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setStudentId(rs.getString("student_id"));
                payment.setAmount(rs.getBigDecimal("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setSemester(rs.getString("semester"));
                payment.setStatus(rs.getString("status"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            logger.error("获取所有缴费记录失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return payments;
    }

    @Override
    public Payment findByPaymentId(String paymentId) {
        String sql = "SELECT payment_id, student_id, amount, payment_date, semester, status FROM payment WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setStudentId(rs.getString("student_id"));
                payment.setAmount(rs.getBigDecimal("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setSemester(rs.getString("semester"));
                payment.setStatus(rs.getString("status"));
                return payment;
            }
        } catch (SQLException e) {
            logger.error("查找缴费记录失败：{}", paymentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return null;
    }

    @Override
    public List<Payment> findByStudentId(String studentId) {
        String sql = "SELECT payment_id, student_id, amount, payment_date, semester, status FROM payment WHERE student_id = ? ORDER BY payment_date DESC";
        List<Payment> payments = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Payment payment = new Payment();
                payment.setPaymentId(rs.getString("payment_id"));
                payment.setStudentId(rs.getString("student_id"));
                payment.setAmount(rs.getBigDecimal("amount"));
                payment.setPaymentDate(rs.getDate("payment_date"));
                payment.setSemester(rs.getString("semester"));
                payment.setStatus(rs.getString("status"));
                payments.add(payment);
            }
        } catch (SQLException e) {
            logger.error("获取学生缴费记录失败：{}", studentId, e);
        } finally {
            closeResources(conn, stmt, rs);
        }

        return payments;
    }

    @Override
    public boolean addPayment(Payment payment) {
        String sql = "INSERT INTO payment (payment_id, student_id, amount, payment_date, semester, status) VALUES (?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, payment.getPaymentId());
            stmt.setString(2, payment.getStudentId());
            stmt.setBigDecimal(3, payment.getAmount());
            stmt.setDate(4, new java.sql.Date(payment.getPaymentDate().getTime()));
            stmt.setString(5, payment.getSemester());
            stmt.setString(6, payment.getStatus());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("添加缴费记录失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updatePayment(Payment payment) {
        String sql = "UPDATE payment SET student_id = ?, amount = ?, payment_date = ?, semester = ?, status = ? WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, payment.getStudentId());
            stmt.setBigDecimal(2, payment.getAmount());
            stmt.setDate(3, new java.sql.Date(payment.getPaymentDate().getTime()));
            stmt.setString(4, payment.getSemester());
            stmt.setString(5, payment.getStatus());
            stmt.setString(6, payment.getPaymentId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新缴费记录失败：{}", payment.getPaymentId(), e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deletePayment(String paymentId) {
        String sql = "DELETE FROM payment WHERE payment_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, paymentId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("删除缴费记录失败：{}", paymentId, e);
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