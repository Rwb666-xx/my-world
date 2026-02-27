package org.dorm.model.service.impl;

import org.dorm.model.dao.DormitoryDAO;
import org.dorm.model.dao.impl.DormitoryDAOImpl;
import org.dorm.model.entity.Dormitory;
import org.dorm.model.service.DormitoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 宿舍服务实现类
 */
public class DormitoryServiceImpl implements DormitoryService {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryServiceImpl.class);

    private DormitoryDAO dormitoryDAO = new DormitoryDAOImpl();

    @Override
    public List<Dormitory> getAllDormitories() {
        return dormitoryDAO.findAll();
    }

    @Override
    public Dormitory getDormitoryById(String dormitoryId) {
        return dormitoryDAO.findByDormitoryId(dormitoryId);
    }

    @Override
    public boolean addDormitory(Dormitory dormitory) {
        try {
            // 检查宿舍ID是否已存在
            if (dormitoryDAO.findByDormitoryId(dormitory.getDormitoryId()) != null) {
                logger.warn("宿舍ID已存在：{}", dormitory.getDormitoryId());
                return false;
            }

            return dormitoryDAO.addDormitory(dormitory);
        } catch (Exception e) {
            logger.error("添加宿舍失败", e);
            return false;
        }
    }

    @Override
    public boolean updateDormitory(Dormitory dormitory) {
        return dormitoryDAO.updateDormitory(dormitory);
    }

    @Override
    public boolean deleteDormitory(String dormitoryId) {
        return dormitoryDAO.deleteDormitory(dormitoryId);
    }
}