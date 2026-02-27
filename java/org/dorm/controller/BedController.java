package org.dorm.controller;

import org.dorm.model.entity.Bed;
import org.dorm.model.service.BedService;
import org.dorm.model.service.impl.BedServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 床位控制器
 * 处理床位相关的业务逻辑
 */
public class BedController {
    private static final Logger logger = LoggerFactory.getLogger(BedController.class);

    private BedService bedService = new BedServiceImpl();

    /**
     * 获取所有床位
     * @return 床位列表
     */
    public List<Bed> getAllBeds() {
        try {
            List<Bed> beds = bedService.getAllBeds();
            logger.info("获取所有床位成功：{}个", beds.size());
            return beds;
        } catch (Exception e) {
            logger.error("获取所有床位失败", e);
            throw new RuntimeException("获取床位列表失败", e);
        }
    }

    /**
     * 根据床位ID查找床位
     * @param bedId 床位ID
     * @return 床位
     */
    public Bed getBedById(String bedId) {
        try {
            Bed bed = bedService.getBedById(bedId);
            if (bed != null) {
                logger.info("查找床位成功：{}", bedId);
            } else {
                logger.warn("未找到床位：{}", bedId);
            }
            return bed;
        } catch (Exception e) {
            logger.error("查找床位失败：{}", bedId, e);
            throw new RuntimeException("查找床位失败", e);
        }
    }

    /**
     * 获取可用床位
     * @return 可用床位列表
     */
    public List<Bed> getAvailableBeds() {
        try {
            List<Bed> beds = bedService.getAvailableBeds();
            logger.info("获取可用床位成功：{}个", beds.size());
            return beds;
        } catch (Exception e) {
            logger.error("获取可用床位失败", e);
            throw new RuntimeException("获取可用床位失败", e);
        }
    }

    /**
     * 根据宿舍ID获取床位
     * @param dormId 宿舍ID
     * @return 床位列表
     */
    public List<Bed> getBedsByDormId(String dormId) {
        try {
            List<Bed> beds = bedService.getBedsByDormId(dormId);
            logger.info("获取宿舍{}的床位成功：{}个", dormId, beds.size());
            return beds;
        } catch (Exception e) {
            logger.error("获取宿舍床位失败：{}", dormId, e);
            throw new RuntimeException("获取宿舍床位失败", e);
        }
    }

    /**
     * 添加床位
     * @param bed 床位
     * @return 是否添加成功
     */
    public boolean addBed(Bed bed) {
        try {
            boolean result = bedService.addBed(bed);
            if (result) {
                logger.info("添加床位成功：{}", bed.getBedId());
            } else {
                logger.warn("添加床位失败：{}", bed.getBedId());
            }
            return result;
        } catch (Exception e) {
            logger.error("添加床位失败：{}", bed.getBedId(), e);
            return false;
        }
    }

    /**
     * 更新床位
     * @param bed 床位
     * @return 是否更新成功
     */
    public boolean updateBed(Bed bed) {
        try {
            boolean result = bedService.updateBed(bed);
            if (result) {
                logger.info("更新床位成功：{}", bed.getBedId());
            } else {
                logger.warn("更新床位失败：{}", bed.getBedId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新床位失败：{}", bed.getBedId(), e);
            return false;
        }
    }

    /**
     * 删除床位
     * @param bedId 床位ID
     * @return 是否删除成功
     */
    public boolean deleteBed(String bedId) {
        try {
            boolean result = bedService.deleteBed(bedId);
            if (result) {
                logger.info("删除床位成功：{}", bedId);
            } else {
                logger.warn("删除床位失败：{}", bedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除床位失败：{}", bedId, e);
            return false;
        }
    }
}