package org.dorm.model.service;

import org.dorm.model.entity.Payment;

import java.util.List;

/**
 * 缴费服务接口
 */
public interface PaymentService {
    /**
     * 获取所有缴费记录
     * @return 缴费记录列表
     */
    List<Payment> getAllPayments();

    /**
     * 根据缴费单号获取缴费记录
     * @param paymentId 缴费单号
     * @return 缴费记录
     */
    Payment getPaymentById(String paymentId);

    /**
     * 根据学生ID获取缴费记录
     * @param studentId 学生ID
     * @return 缴费记录列表
     */
    List<Payment> getPaymentsByStudentId(String studentId);

    /**
     * 添加缴费记录
     * @param payment 缴费记录
     * @return 是否添加成功
     */
    boolean addPayment(Payment payment);

    /**
     * 更新缴费记录
     * @param payment 缴费记录
     * @return 是否更新成功
     */
    boolean updatePayment(Payment payment);

    /**
     * 删除缴费记录
     * @param paymentId 缴费单号
     * @return 是否删除成功
     */
    boolean deletePayment(String paymentId);
}