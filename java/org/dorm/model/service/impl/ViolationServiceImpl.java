package org.dorm.model.service.impl;

import org.dorm.model.dao.ViolationDAO;
import org.dorm.model.dao.impl.ViolationDAOImpl;
import org.dorm.model.entity.Violation;
import org.dorm.model.service.ViolationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 违纪服务实现类
 */
public class ViolationServiceImpl implements ViolationService {
    private static final Logger logger = LoggerFactory.getLogger(ViolationServiceImpl.class);
    private ViolationDAO violationDAO = new ViolationDAOImpl();

    @Override
    public List<Violation> getAllViolations() {
        return violationDAO.findAll();
    }

    @Override
    public Violation getViolationById(String violationId) {
        return violationDAO.findByViolationId(violationId);
    }

    @Override
    public List<Violation> getViolationsByStudentId(String studentId) {
        return violationDAO.findByStudentId(studentId);
    }

    @Override
    public boolean addViolation(Violation violation) {
        try {
            // 检查违纪单号是否已存在
            if (violationDAO.findByViolationId(violation.getViolationId()) != null) {
                logger.warn("违纪单号已存在：{}", violation.getViolationId());
                return false;
            }

            return violationDAO.addViolation(violation);
        } catch (Exception e) {
            logger.error("添加违纪记录失败", e);
            return false;
        }
    }

    @Override
    public boolean updateViolation(Violation violation) {
        return violationDAO.updateViolation(violation);
    }

    @Override
    public boolean deleteViolation(String violationId) {
        return violationDAO.deleteViolation(violationId);
    }
}