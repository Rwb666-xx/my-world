package org.dorm.model.service.impl;

import org.dorm.model.dao.ReminderDAO;
import org.dorm.model.dao.StudentDAO;
import org.dorm.model.dao.ViolationDAO;
import org.dorm.model.dao.PaymentDAO;
import org.dorm.model.dao.impl.ReminderDAOImpl;
import org.dorm.model.dao.impl.StudentDAOImpl;
import org.dorm.model.dao.impl.ViolationDAOImpl;
import org.dorm.model.dao.impl.PaymentDAOImpl;
import org.dorm.model.entity.Reminder;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Violation;
import org.dorm.model.entity.Payment;
import org.dorm.model.service.ReminderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 提醒服务实现类
 */
public class ReminderServiceImpl implements ReminderService {
    private static final Logger logger = LoggerFactory.getLogger(ReminderServiceImpl.class);

    private ReminderDAO reminderDAO = new ReminderDAOImpl();
    private StudentDAO studentDAO = new StudentDAOImpl();
    private ViolationDAO violationDAO = new ViolationDAOImpl();
    private PaymentDAO paymentDAO = new PaymentDAOImpl();

    @Override
    public List<Reminder> getAllReminders() {
        return reminderDAO.findAll();
    }

    @Override
    public Reminder getReminderById(String reminderId) {
        return reminderDAO.findByReminderId(reminderId);
    }

    @Override
    public List<Reminder> getRemindersByStudentId(String studentId) {
        return reminderDAO.findByStudentId(studentId);
    }

    @Override
    public List<Reminder> getRemindersByStatus(String status) {
        return reminderDAO.findByStatus(status);
    }

    @Override
    public List<Reminder> getRemindersByType(String type) {
        return reminderDAO.findByType(type);
    }

    @Override
    public List<Reminder> getUnprocessedReminders() {
        return reminderDAO.findUnprocessedReminders();
    }

    @Override
    public boolean addReminder(Reminder reminder) {
        return reminderDAO.addReminder(reminder);
    }

    @Override
    public boolean updateReminder(Reminder reminder) {
        return reminderDAO.updateReminder(reminder);
    }

    @Override
    public boolean deleteReminder(String reminderId) {
        return reminderDAO.deleteReminder(reminderId);
    }

    @Override
    public int deleteProcessedReminders() {
        return reminderDAO.deleteProcessedReminders();
    }

    @Override
    public int checkUnpaidStudents() {
        int count = 0;
        try {
            // 获取所有学生
            List<Student> students = studentDAO.findAll();

            for (Student student : students) {
                // 检查该学生的缴费记录
                List<Payment> payments = paymentDAO.findByStudentId(student.getStudentId());

                boolean hasUnpaid = false;
                for (Payment payment : payments) {
                    if ("未缴".equals(payment.getStatus())) {
                        hasUnpaid = true;
                        break;
                    }
                }

                if (hasUnpaid) {
                    // 检查是否已有提醒
                    List<Reminder> existingReminders = reminderDAO.findByStudentId(student.getStudentId());
                    boolean alreadyExists = false;
                    for (Reminder reminder : existingReminders) {
                        if ("缴费提醒".equals(reminder.getType()) && "待处理".equals(reminder.getStatus())) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    if (!alreadyExists) {
                        // 创建新的缴费提醒
                        Reminder reminder = new Reminder();
                        reminder.setReminderId("REMINDER_" + UUID.randomUUID().toString().substring(0, 8));
                        reminder.setStudentId(student.getStudentId());
                        reminder.setStudentName(student.getName());
                        reminder.setType("缴费提醒");
                        reminder.setTitle("住宿费未缴提醒");
                        reminder.setContent("学生 " + student.getName() + " (" + student.getStudentId() + ") 存在未缴纳的住宿费用，请及时处理。");
                        reminder.setPriority("高");
                        reminder.setStatus("待处理");
                        reminder.setCreateTime(new Date());

                        if (addReminder(reminder)) {
                            count++;
                            logger.info("为学生 {} 生成缴费提醒", student.getStudentId());
                        }
                    }
                }
            }

            logger.info("缴费提醒检查完成，共生成 {} 条新提醒", count);
        } catch (Exception e) {
            logger.error("检查未缴费学生失败", e);
        }

        return count;
    }

    @Override
    public int checkUnprocessedViolations() {
        int count = 0;
        try {
            // 获取所有违纪记录
            List<Violation> violations = violationDAO.findAll();

            for (Violation violation : violations) {
                // 检查是否需要处理（假设有处理状态，这里简化判断）
                // 在实际应用中，可以根据具体业务规则判断违纪是否需要处理
                if (violation.getPenalty() == null || violation.getPenalty().trim().isEmpty()) {
                    // 检查是否已有提醒
                    List<Reminder> existingReminders = reminderDAO.findByStudentId(violation.getStudentId());
                    boolean alreadyExists = false;
                    for (Reminder reminder : existingReminders) {
                        if ("违纪处理提醒".equals(reminder.getType()) && "待处理".equals(reminder.getStatus())) {
                            alreadyExists = true;
                            break;
                        }
                    }

                    if (!alreadyExists) {
                        // 创建新的违纪处理提醒
                        Reminder reminder = new Reminder();
                        reminder.setReminderId("REMINDER_" + UUID.randomUUID().toString().substring(0, 8));
                        reminder.setStudentId(violation.getStudentId());
                        reminder.setStudentName(getStudentNameById(violation.getStudentId()));
                        reminder.setType("违纪处理提醒");
                        reminder.setTitle("违纪记录处理提醒");
                        reminder.setContent("学生 " + getStudentNameById(violation.getStudentId()) + " (" + violation.getStudentId() + ") 存在未处理的违纪记录：" +
                                          violation.getDescription() + "，请及时处理。");
                        reminder.setPriority("中");
                        reminder.setStatus("待处理");
                        reminder.setCreateTime(new Date());

                        if (addReminder(reminder)) {
                            count++;
                            logger.info("为学生 {} 生成违纪处理提醒", violation.getStudentId());
                        }
                    }
                }
            }

            logger.info("违纪处理提醒检查完成，共生成 {} 条新提醒", count);
        } catch (Exception e) {
            logger.error("检查未处理违纪记录失败", e);
        }

        return count;
    }

    @Override
    public int refreshAllReminders() {
        try {
            // 删除所有旧提醒
            int deletedCount = deleteProcessedReminders();
            logger.info("删除了 {} 条已处理的提醒", deletedCount);

            // 重新检查并生成提醒
            int paymentReminders = checkUnpaidStudents();
            int violationReminders = checkUnprocessedViolations();

            int total = paymentReminders + violationReminders;
            logger.info("提醒刷新完成，共生成 {} 条新提醒", total);

            return total;
        } catch (Exception e) {
            logger.error("刷新提醒失败", e);
            return 0;
        }
    }

    /**
     * 根据学生ID获取学生姓名
     */
    private String getStudentNameById(String studentId) {
        try {
            Student student = studentDAO.findByStudentId(studentId);
            return student != null ? student.getName() : "未知学生";
        } catch (Exception e) {
            logger.error("获取学生姓名失败：{}", studentId, e);
            return "未知学生";
        }
    }
}