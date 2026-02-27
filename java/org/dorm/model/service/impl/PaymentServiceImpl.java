package org.dorm.model.service.impl;

import org.dorm.model.dao.PaymentDAO;
import org.dorm.model.dao.impl.PaymentDAOImpl;
import org.dorm.model.entity.Payment;
import org.dorm.model.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 缴费服务实现类
 */
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public List<Payment> getAllPayments() {
        return paymentDAO.findAll();
    }

    @Override
    public Payment getPaymentById(String paymentId) {
        return paymentDAO.findByPaymentId(paymentId);
    }

    @Override
    public List<Payment> getPaymentsByStudentId(String studentId) {
        return paymentDAO.findByStudentId(studentId);
    }

    @Override
    public boolean addPayment(Payment payment) {
        try {
            // 检查缴费单号是否已存在
            if (paymentDAO.findByPaymentId(payment.getPaymentId()) != null) {
                logger.warn("缴费单号已存在：{}", payment.getPaymentId());
                return false;
            }

            return paymentDAO.addPayment(payment);
        } catch (Exception e) {
            logger.error("添加缴费记录失败", e);
            return false;
        }
    }

    @Override
    public boolean updatePayment(Payment payment) {
        return paymentDAO.updatePayment(payment);
    }

    @Override
    public boolean deletePayment(String paymentId) {
        return paymentDAO.deletePayment(paymentId);
    }
}