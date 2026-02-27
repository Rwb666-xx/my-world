package org.dorm.controller;

import org.dorm.model.entity.Dormitory;
import org.dorm.model.service.DormitoryService;
import org.dorm.model.service.impl.DormitoryServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 宿舍控制器
 * 处理宿舍相关的业务逻辑
 */
public class DormitoryController {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryController.class);

    private DormitoryService dormitoryService = new DormitoryServiceImpl();

    /**
     * 获取所有宿舍
     * @return 宿舍列表
     */
    public List<Dormitory> getAllDormitories() {
        try {
            List<Dormitory> dormitories = dormitoryService.getAllDormitories();
            logger.info("获取所有宿舍成功：{}栋", dormitories.size());
            return dormitories;
        } catch (Exception e) {
            logger.error("获取所有宿舍失败", e);
            throw new RuntimeException("获取宿舍列表失败", e);
        }
    }

    /**
     * 根据宿舍ID查找宿舍
     * @param dormitoryId 宿舍ID
     * @return 宿舍信息
     */
    public Dormitory getDormitoryById(String dormitoryId) {
        try {
            Dormitory dormitory = dormitoryService.getDormitoryById(dormitoryId);
            if (dormitory != null) {
                logger.info("查找宿舍成功：{}", dormitoryId);
            } else {
                logger.warn("未找到宿舍：{}", dormitoryId);
            }
            return dormitory;
        } catch (Exception e) {
            logger.error("查找宿舍失败：{}", dormitoryId, e);
            throw new RuntimeException("查找宿舍失败", e);
        }
    }

    /**
     * 添加宿舍
     * @param dormitory 宿舍信息
     * @return 是否添加成功
     */
    public boolean addDormitory(Dormitory dormitory) {
        try {
            boolean result = dormitoryService.addDormitory(dormitory);
            if (result) {
                logger.info("添加宿舍成功：{}", dormitory.getDormitoryId());
            } else {
                logger.warn("添加宿舍失败：{}", dormitory.getDormitoryId());
            }
            return result;
        } catch (Exception e) {
            logger.error("添加宿舍失败：{}", dormitory.getDormitoryId(), e);
            return false;
        }
    }

    /**
     * 更新宿舍信息
     * @param dormitory 宿舍信息
     * @return 是否更新成功
     */
    public boolean updateDormitory(Dormitory dormitory) {
        try {
            boolean result = dormitoryService.updateDormitory(dormitory);
            if (result) {
                logger.info("更新宿舍成功：{}", dormitory.getDormitoryId());
            } else {
                logger.warn("更新宿舍失败：{}", dormitory.getDormitoryId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新宿舍失败：{}", dormitory.getDormitoryId(), e);
            return false;
        }
    }

    /**
     * 删除宿舍
     * @param dormitoryId 宿舍ID
     * @return 是否删除成功
     */
    public boolean deleteDormitory(String dormitoryId) {
        try {
            boolean result = dormitoryService.deleteDormitory(dormitoryId);
            if (result) {
                logger.info("删除宿舍成功：{}", dormitoryId);
            } else {
                logger.warn("删除宿舍失败：{}", dormitoryId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除宿舍失败：{}", dormitoryId, e);
            return false;
        }
    }
}