package org.dorm.model.service.impl;

import org.dorm.model.dao.RepairApplicationDAO;
import org.dorm.model.dao.impl.RepairApplicationDAOImpl;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.service.RepairApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 维修申请业务逻辑服务实现类
 */
public class RepairApplicationServiceImpl implements RepairApplicationService {
    private static final Logger logger = LoggerFactory.getLogger(RepairApplicationServiceImpl.class);
    private RepairApplicationDAO repairDAO = new RepairApplicationDAOImpl();

    @Override
    public boolean submitRepairApplication(RepairApplication application) {
        if (application == null) {
            logger.warn("提交维修申请失败：申请信息为空");
            return false;
        }

        if (application.getStudentId() == null || application.getFaultLocation() == null ||
            application.getFaultDesc() == null || application.getContactPhone() == null) {
            logger.warn("提交维修申请失败：必填信息不完整");
            return false;
        }

        // 生成申请单号
        String applyId = generateApplyId(application.getStudentId());
        application.setApplyId(applyId);

        // 设置申请时间和初始状态
        application.setApplyTime(new Date());
        application.setStatus("pending");

        boolean result = repairDAO.addRepairApplication(application);
        if (result) {
            logger.info("维修申请提交成功：{} - {}", applyId, application.getStudentId());
        } else {
            logger.error("维修申请提交失败：{}", application.getStudentId());
        }
        return result;
    }

    @Override
    public List<RepairApplication> getStudentApplications(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            logger.warn("获取学生维修申请失败：学生学号为空");
            return List.of();
        }

        List<RepairApplication> applications = repairDAO.findByStudentId(studentId.trim());
        logger.info("获取学生维修申请成功：{} - {}条记录", studentId, applications.size());
        return applications;
    }

    @Override
    public List<RepairApplication> getAllApplications() {
        List<RepairApplication> applications = repairDAO.findAll();
        logger.info("获取所有维修申请成功：{}条记录", applications.size());
        return applications;
    }

    @Override
    public List<RepairApplication> getPendingApplications() {
        List<RepairApplication> applications = repairDAO.findByStatus("pending");
        logger.info("获取待处理维修申请成功：{}条记录", applications.size());
        return applications;
    }

    @Override
    public boolean updateApplicationStatus(String applyId, String status, String handler) {
        if (applyId == null || status == null) {
            logger.warn("更新维修申请状态失败：参数不完整");
            return false;
        }

        // 验证状态值
        if (!isValidStatus(status)) {
            logger.warn("更新维修申请状态失败：无效的状态值 - {}", status);
            return false;
        }

        boolean result = repairDAO.updateStatus(applyId, status, handler);
        if (result) {
            logger.info("维修申请状态更新成功：{} -> {}", applyId, status);
        } else {
            logger.error("维修申请状态更新失败：{}", applyId);
        }
        return result;
    }

    @Override
    public boolean completeRepairApplication(String applyId) {
        if (applyId == null || applyId.trim().isEmpty()) {
            logger.warn("完成维修申请失败：申请单号为空");
            return false;
        }

        boolean result = repairDAO.completeRepair(applyId.trim(), new Date());
        if (result) {
            logger.info("维修申请完成：{}", applyId);
        } else {
            logger.error("维修申请完成失败：{}", applyId);
        }
        return result;
    }

    @Override
    public String generateApplyId(String studentId) {
        // 生成格式：WX + 年月日 + 学生学号后4位 + 随机数
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        String dateStr = sdf.format(new Date());
        String studentSuffix = studentId.length() >= 4 ?
            studentId.substring(studentId.length() - 4) :
            studentId;
        String randomStr = UUID.randomUUID().toString().substring(0, 4).toUpperCase();

        return "WX" + dateStr + studentSuffix + randomStr;
    }

    /**
     * 验证状态值是否有效
     */
    private boolean isValidStatus(String status) {
        return status.equals("pending") || status.equals("accepted") ||
               status.equals("repairing") || status.equals("completed");
    }
}