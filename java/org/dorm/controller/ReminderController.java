package org.dorm.controller;

import org.dorm.model.entity.Reminder;
import org.dorm.model.service.ReminderService;
import org.dorm.model.service.impl.ReminderServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 提醒控制器
 * 处理提醒相关的业务逻辑
 */
public class ReminderController {
    private static final Logger logger = LoggerFactory.getLogger(ReminderController.class);

    private ReminderService reminderService = new ReminderServiceImpl();

    /**
     * 获取所有提醒
     * @return 提醒列表
     */
    public List<Reminder> getAllReminders() {
        try {
            List<Reminder> reminders = reminderService.getAllReminders();
            logger.info("获取所有提醒成功：{}条", reminders.size());
            return reminders;
        } catch (Exception e) {
            logger.error("获取所有提醒失败", e);
            throw new RuntimeException("获取提醒列表失败", e);
        }
    }

    /**
     * 根据提醒ID查找提醒
     * @param reminderId 提醒ID
     * @return 提醒信息
     */
    public Reminder getReminderById(String reminderId) {
        try {
            Reminder reminder = reminderService.getReminderById(reminderId);
            if (reminder != null) {
                logger.info("查找提醒成功：{}", reminderId);
            } else {
                logger.warn("未找到提醒：{}", reminderId);
            }
            return reminder;
        } catch (Exception e) {
            logger.error("查找提醒失败：{}", reminderId, e);
            throw new RuntimeException("查找提醒失败", e);
        }
    }

    /**
     * 根据学生ID获取提醒列表
     * @param studentId 学生ID
     * @return 提醒列表
     */
    public List<Reminder> getRemindersByStudentId(String studentId) {
        try {
            List<Reminder> reminders = reminderService.getRemindersByStudentId(studentId);
            logger.info("获取学生{}的提醒成功：{}条", studentId, reminders.size());
            return reminders;
        } catch (Exception e) {
            logger.error("获取学生提醒失败：{}", studentId, e);
            throw new RuntimeException("获取学生提醒失败", e);
        }
    }

    /**
     * 获取未处理的提醒
     * @return 未处理提醒列表
     */
    public List<Reminder> getUnprocessedReminders() {
        try {
            List<Reminder> reminders = reminderService.getUnprocessedReminders();
            logger.info("获取未处理提醒成功：{}条", reminders.size());
            return reminders;
        } catch (Exception e) {
            logger.error("获取未处理提醒失败", e);
            throw new RuntimeException("获取未处理提醒失败", e);
        }
    }

    /**
     * 添加提醒
     * @param reminder 提醒信息
     * @return 是否添加成功
     */
    public boolean addReminder(Reminder reminder) {
        try {
            boolean result = reminderService.addReminder(reminder);
            if (result) {
                logger.info("添加提醒成功：{}", reminder.getReminderId());
            } else {
                logger.warn("添加提醒失败：{}", reminder.getReminderId());
            }
            return result;
        } catch (Exception e) {
            logger.error("添加提醒失败：{}", reminder.getReminderId(), e);
            return false;
        }
    }

    /**
     * 更新提醒
     * @param reminder 提醒信息
     * @return 是否更新成功
     */
    public boolean updateReminder(Reminder reminder) {
        try {
            boolean result = reminderService.updateReminder(reminder);
            if (result) {
                logger.info("更新提醒成功：{}", reminder.getReminderId());
            } else {
                logger.warn("更新提醒失败：{}", reminder.getReminderId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新提醒失败：{}", reminder.getReminderId(), e);
            return false;
        }
    }

    /**
     * 删除提醒
     * @param reminderId 提醒ID
     * @return 是否删除成功
     */
    public boolean deleteReminder(String reminderId) {
        try {
            boolean result = reminderService.deleteReminder(reminderId);
            if (result) {
                logger.info("删除提醒成功：{}", reminderId);
            } else {
                logger.warn("删除提醒失败：{}", reminderId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除提醒失败：{}", reminderId, e);
            return false;
        }
    }

    /**
     * 检查未缴费学生并生成提醒
     * @return 生成的提醒数量
     */
    public int checkUnpaidStudents() {
        try {
            int count = reminderService.checkUnpaidStudents();
            logger.info("未缴费学生检查完成，生成了{}条提醒", count);
            return count;
        } catch (Exception e) {
            logger.error("检查未缴费学生失败", e);
            return 0;
        }
    }

    /**
     * 检查未处理违纪记录并生成提醒
     * @return 生成的提醒数量
     */
    public int checkUnprocessedViolations() {
        try {
            int count = reminderService.checkUnprocessedViolations();
            logger.info("未处理违纪检查完成，生成了{}条提醒", count);
            return count;
        } catch (Exception e) {
            logger.error("检查未处理违纪记录失败", e);
            return 0;
        }
    }

    /**
     * 批量删除已处理的提醒
     * @return 删除的记录数
     */
    public int deleteProcessedReminders() {
        try {
            int count = reminderService.deleteProcessedReminders();
            logger.info("删除了{}条已处理的提醒", count);
            return count;
        } catch (Exception e) {
            logger.error("删除已处理提醒失败", e);
            return 0;
        }
    }

    /**
     * 刷新所有提醒
     * @return 更新的提醒总数
     */
    public int refreshAllReminders() {
        try {
            int count = reminderService.refreshAllReminders();
            logger.info("提醒刷新完成，共{}条提醒", count);
            return count;
        } catch (Exception e) {
            logger.error("刷新提醒失败", e);
            return 0;
        }
    }
}