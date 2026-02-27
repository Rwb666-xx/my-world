package org.dorm.controller;

import org.dorm.model.entity.Payment;
import org.dorm.model.service.PaymentService;
import org.dorm.model.service.impl.PaymentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 缴费控制器
 * 处理缴费相关的业务逻辑
 */
public class PaymentController {
    private static final Logger logger = LoggerFactory.getLogger(PaymentController.class);

    private PaymentService paymentService = new PaymentServiceImpl();

    /**
     * 获取所有缴费记录
     * @return 缴费记录列表
     */
    public List<Payment> getAllPayments() {
        try {
            List<Payment> payments = paymentService.getAllPayments();
            logger.info("获取所有缴费记录成功：{}条", payments.size());
            return payments;
        } catch (Exception e) {
            logger.error("获取所有缴费记录失败", e);
            throw new RuntimeException("获取缴费记录列表失败", e);
        }
    }

    /**
     * 根据缴费单号查找缴费记录
     * @param paymentId 缴费单号
     * @return 缴费记录
     */
    public Payment getPaymentById(String paymentId) {
        try {
            Payment payment = paymentService.getPaymentById(paymentId);
            if (payment != null) {
                logger.info("查找缴费记录成功：{}", paymentId);
            } else {
                logger.warn("未找到缴费记录：{}", paymentId);
            }
            return payment;
        } catch (Exception e) {
            logger.error("查找缴费记录失败：{}", paymentId, e);
            throw new RuntimeException("查找缴费记录失败", e);
        }
    }

    /**
     * 根据学生ID获取缴费记录
     * @param studentId 学生ID
     * @return 缴费记录列表
     */
    public List<Payment> getPaymentsByStudentId(String studentId) {
        try {
            List<Payment> payments = paymentService.getPaymentsByStudentId(studentId);
            logger.info("获取学生{}的缴费记录成功：{}条", studentId, payments.size());
            return payments;
        } catch (Exception e) {
            logger.error("获取学生缴费记录失败：{}", studentId, e);
            throw new RuntimeException("获取学生缴费记录失败", e);
        }
    }

    /**
     * 添加缴费记录
     * @param payment 缴费记录
     * @return 是否添加成功
     */
    public boolean addPayment(Payment payment) {
        try {
            boolean result = paymentService.addPayment(payment);
            if (result) {
                logger.info("添加缴费记录成功：{}", payment.getPaymentId());
            } else {
                logger.warn("添加缴费记录失败：{}", payment.getPaymentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("添加缴费记录失败：{}", payment.getPaymentId(), e);
            return false;
        }
    }

    /**
     * 更新缴费记录
     * @param payment 缴费记录
     * @return 是否更新成功
     */
    public boolean updatePayment(Payment payment) {
        try {
            boolean result = paymentService.updatePayment(payment);
            if (result) {
                logger.info("更新缴费记录成功：{}", payment.getPaymentId());
            } else {
                logger.warn("更新缴费记录失败：{}", payment.getPaymentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新缴费记录失败：{}", payment.getPaymentId(), e);
            return false;
        }
    }

    /**
     * 删除缴费记录
     * @param paymentId 缴费单号
     * @return 是否删除成功
     */
    public boolean deletePayment(String paymentId) {
        try {
            boolean result = paymentService.deletePayment(paymentId);
            if (result) {
                logger.info("删除缴费记录成功：{}", paymentId);
            } else {
                logger.warn("删除缴费记录失败：{}", paymentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除缴费记录失败：{}", paymentId, e);
            return false;
        }
    }
}