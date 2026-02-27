package org.dorm.controller;

import org.dorm.model.entity.Violation;
import org.dorm.model.service.ViolationService;
import org.dorm.model.service.impl.ViolationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 违纪控制器
 * 处理违纪相关的业务逻辑
 */
public class ViolationController {
    private static final Logger logger = LoggerFactory.getLogger(ViolationController.class);

    private ViolationService violationService = new ViolationServiceImpl();

    /**
     * 获取所有违纪记录
     * @return 违纪记录列表
     */
    public List<Violation> getAllViolations() {
        try {
            List<Violation> violations = violationService.getAllViolations();
            logger.info("获取所有违纪记录成功：{}条", violations.size());
            return violations;
        } catch (Exception e) {
            logger.error("获取所有违纪记录失败", e);
            throw new RuntimeException("获取违纪记录列表失败", e);
        }
    }

    /**
     * 根据违纪单号查找违纪记录
     * @param violationId 违纪单号
     * @return 违纪记录
     */
    public Violation getViolationById(String violationId) {
        try {
            Violation violation = violationService.getViolationById(violationId);
            if (violation != null) {
                logger.info("查找违纪记录成功：{}", violationId);
            } else {
                logger.warn("未找到违纪记录：{}", violationId);
            }
            return violation;
        } catch (Exception e) {
            logger.error("查找违纪记录失败：{}", violationId, e);
            throw new RuntimeException("查找违纪记录失败", e);
        }
    }

    /**
     * 根据学生ID获取违纪记录
     * @param studentId 学生ID
     * @return 违纪记录列表
     */
    public List<Violation> getViolationsByStudentId(String studentId) {
        try {
            List<Violation> violations = violationService.getViolationsByStudentId(studentId);
            logger.info("获取学生{}的违纪记录成功：{}条", studentId, violations.size());
            return violations;
        } catch (Exception e) {
            logger.error("获取学生违纪记录失败：{}", studentId, e);
            throw new RuntimeException("获取学生违纪记录失败", e);
        }
    }

    /**
     * 添加违纪记录
     * @param violation 违纪记录
     * @return 是否添加成功
     */
    public boolean addViolation(Violation violation) {
        try {
            boolean result = violationService.addViolation(violation);
            if (result) {
                logger.info("添加违纪记录成功：{}", violation.getViolationId());
            } else {
                logger.warn("添加违纪记录失败：{}", violation.getViolationId());
            }
            return result;
        } catch (Exception e) {
            logger.error("添加违纪记录失败：{}", violation.getViolationId(), e);
            return false;
        }
    }

    /**
     * 更新违纪记录
     * @param violation 违纪记录
     * @return 是否更新成功
     */
    public boolean updateViolation(Violation violation) {
        try {
            boolean result = violationService.updateViolation(violation);
            if (result) {
                logger.info("更新违纪记录成功：{}", violation.getViolationId());
            } else {
                logger.warn("更新违纪记录失败：{}", violation.getViolationId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新违纪记录失败：{}", violation.getViolationId(), e);
            return false;
        }
    }

    /**
     * 删除违纪记录
     * @param violationId 违纪单号
     * @return 是否删除成功
     */
    public boolean deleteViolation(String violationId) {
        try {
            boolean result = violationService.deleteViolation(violationId);
            if (result) {
                logger.info("删除违纪记录成功：{}", violationId);
            } else {
                logger.warn("删除违纪记录失败：{}", violationId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除违纪记录失败：{}", violationId, e);
            return false;
        }
    }
}