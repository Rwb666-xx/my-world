package org.dorm.model.dao;

import org.dorm.model.entity.Reminder;

import java.util.List;

/**
 * 提醒数据访问接口
 */
public interface ReminderDAO {

    /**
     * 获取所有提醒
     * @return 提醒列表
     */
    List<Reminder> findAll();

    /**
     * 根据提醒ID查找提醒
     * @param reminderId 提醒ID
     * @return 提醒信息
     */
    Reminder findByReminderId(String reminderId);

    /**
     * 根据学生ID获取提醒列表
     * @param studentId 学生ID
     * @return 提醒列表
     */
    List<Reminder> findByStudentId(String studentId);

    /**
     * 根据状态获取提醒列表
     * @param status 状态
     * @return 提醒列表
     */
    List<Reminder> findByStatus(String status);

    /**
     * 根据类型获取提醒列表
     * @param type 类型
     * @return 提醒列表
     */
    List<Reminder> findByType(String type);

    /**
     * 获取未处理的提醒
     * @return 未处理提醒列表
     */
    List<Reminder> findUnprocessedReminders();

    /**
     * 添加提醒
     * @param reminder 提醒信息
     * @return 是否添加成功
     */
    boolean addReminder(Reminder reminder);

    /**
     * 更新提醒
     * @param reminder 提醒信息
     * @return 是否更新成功
     */
    boolean updateReminder(Reminder reminder);

    /**
     * 删除提醒
     * @param reminderId 提醒ID
     * @return 是否删除成功
     */
    boolean deleteReminder(String reminderId);

    /**
     * 批量删除已处理的提醒
     * @return 删除的记录数
     */
    int deleteProcessedReminders();
}