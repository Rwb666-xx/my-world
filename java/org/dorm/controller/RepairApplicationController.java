package org.dorm.controller;

import org.dorm.model.entity.RepairApplication;
import org.dorm.model.service.RepairApplicationService;
import org.dorm.model.service.impl.RepairApplicationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 维修申请控制器
 * 处理维修申请相关的业务逻辑
 */
public class RepairApplicationController {
    private static final Logger logger = LoggerFactory.getLogger(RepairApplicationController.class);
    private RepairApplicationService repairService = new RepairApplicationServiceImpl();

    /**
     * 提交维修申请
     * @param application 维修申请对象
     * @return 是否提交成功
     */
    public boolean submitRepairApplication(RepairApplication application) {
        if (application == null) {
            logger.warn("提交维修申请失败：申请信息为空");
            return false;
        }

        try {
            boolean result = repairService.submitRepairApplication(application);
            if (result) {
                logger.info("维修申请提交成功：{}", application.getApplyId());
            } else {
                logger.error("维修申请提交失败");
            }
            return result;
        } catch (Exception e) {
            logger.error("提交维修申请过程中发生异常", e);
            return false;
        }
    }

    /**
     * 获取学生自己的维修申请记录
     * @param studentId 学生学号
     * @return 维修申请列表
     */
    public List<RepairApplication> getStudentApplications(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            logger.warn("获取学生维修申请失败：学生学号为空");
            return List.of();
        }

        try {
            List<RepairApplication> applications = repairService.getStudentApplications(studentId.trim());
            logger.info("获取学生维修申请成功：{} - {}条记录", studentId, applications.size());
            return applications;
        } catch (Exception e) {
            logger.error("获取学生维修申请过程中发生异常：{}", studentId, e);
            return List.of();
        }
    }

    /**
     * 获取所有维修申请（管理员功能）
     * @return 维修申请列表
     */
    public List<RepairApplication> getAllApplications() {
        try {
            List<RepairApplication> applications = repairService.getAllApplications();
            logger.info("获取所有维修申请成功：{}条记录", applications.size());
            return applications;
        } catch (Exception e) {
            logger.error("获取所有维修申请过程中发生异常", e);
            return List.of();
        }
    }

    /**
     * 获取待处理的维修申请
     * @return 维修申请列表
     */
    public List<RepairApplication> getPendingApplications() {
        try {
            List<RepairApplication> applications = repairService.getPendingApplications();
            logger.info("获取待处理维修申请成功：{}条记录", applications.size());
            return applications;
        } catch (Exception e) {
            logger.error("获取待处理维修申请过程中发生异常", e);
            return List.of();
        }
    }

    /**
     * 更新维修申请状态（管理员功能）
     * @param applyId 申请单号
     * @param status 新状态
     * @param handler 处理人
     * @return 是否更新成功
     */
    public boolean updateApplicationStatus(String applyId, String status, String handler) {
        if (applyId == null || status == null) {
            logger.warn("更新维修申请状态失败：参数不完整");
            return false;
        }

        try {
            boolean result = repairService.updateApplicationStatus(applyId.trim(), status, handler);
            if (result) {
                logger.info("维修申请状态更新成功：{} -> {}", applyId, status);
            } else {
                logger.error("维修申请状态更新失败：{}", applyId);
            }
            return result;
        } catch (Exception e) {
            logger.error("更新维修申请状态过程中发生异常：{}", applyId, e);
            return false;
        }
    }

    /**
     * 完成维修申请（管理员功能）
     * @param applyId 申请单号
     * @return 是否完成成功
     */
    public boolean completeRepairApplication(String applyId) {
        if (applyId == null || applyId.trim().isEmpty()) {
            logger.warn("完成维修申请失败：申请单号为空");
            return false;
        }

        try {
            boolean result = repairService.completeRepairApplication(applyId.trim());
            if (result) {
                logger.info("维修申请完成成功：{}", applyId);
            } else {
                logger.error("维修申请完成失败：{}", applyId);
            }
            return result;
        } catch (Exception e) {
            logger.error("完成维修申请过程中发生异常：{}", applyId, e);
            return false;
        }
    }

    /**
     * 生成申请单号
     * @param studentId 学生学号
     * @return 申请单号
     */
    public String generateApplyId(String studentId) {
        return repairService.generateApplyId(studentId);
    }
}