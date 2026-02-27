package org.dorm.model.service;

import org.dorm.model.entity.Reminder;

import java.util.List;

/**
 * 提醒服务接口
 */
public interface ReminderService {

    /**
     * 获取所有提醒
     * @return 提醒列表
     */
    List<Reminder> getAllReminders();

    /**
     * 根据提醒ID获取提醒
     * @param reminderId 提醒ID
     * @return 提醒信息
     */
    Reminder getReminderById(String reminderId);

    /**
     * 根据学生ID获取提醒列表
     * @param studentId 学生ID
     * @return 提醒列表
     */
    List<Reminder> getRemindersByStudentId(String studentId);

    /**
     * 根据状态获取提醒列表
     * @param status 状态
     * @return 提醒列表
     */
    List<Reminder> getRemindersByStatus(String status);

    /**
     * 根据类型获取提醒列表
     * @param type 类型
     * @return 提醒列表
     */
    List<Reminder> getRemindersByType(String type);

    /**
     * 获取未处理的提醒
     * @return 未处理提醒列表
     */
    List<Reminder> getUnprocessedReminders();

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

    /**
     * 检查未缴费学生并生成提醒
     * @return 生成的提醒数量
     */
    int checkUnpaidStudents();

    /**
     * 检查未处理违纪记录并生成提醒
     * @return 生成的提醒数量
     */
    int checkUnprocessedViolations();

    /**
     * 刷新所有提醒（清除旧提醒，重新生成）
     * @return 更新的提醒总数
     */
    int refreshAllReminders();
}