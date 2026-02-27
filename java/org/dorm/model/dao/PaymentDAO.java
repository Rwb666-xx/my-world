package org.dorm.model.dao;

import org.dorm.model.entity.Payment;

import java.util.List;

/**
 * 缴费数据访问接口
 */
public interface PaymentDAO {
    /**
     * 获取所有缴费记录
     * @return 缴费记录列表
     */
    List<Payment> findAll();

    /**
     * 根据缴费单号查找缴费记录
     * @param paymentId 缴费单号
     * @return 缴费记录
     */
    Payment findByPaymentId(String paymentId);

    /**
     * 根据学生ID查找缴费记录
     * @param studentId 学生ID
     * @return 缴费记录列表
     */
    List<Payment> findByStudentId(String studentId);

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