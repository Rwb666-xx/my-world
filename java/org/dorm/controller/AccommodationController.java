package org.dorm.controller;

import org.dorm.model.entity.Accommodation;
import org.dorm.model.service.AccommodationService;
import org.dorm.model.service.impl.AccommodationServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 住宿分配控制器
 * 处理住宿分配相关的业务逻辑
 */
public class AccommodationController {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationController.class);
    private AccommodationService accommodationService = new AccommodationServiceImpl();

    /**
     * 获取所有住宿信息
     * @return 住宿信息列表
     */
    public List<Accommodation> getAllAccommodations() {
        try {
            List<Accommodation> accommodations = accommodationService.getAllAccommodations();
            logger.info("获取所有住宿信息成功：{}条记录", accommodations.size());
            return accommodations;
        } catch (Exception e) {
            logger.error("获取所有住宿信息失败", e);
            throw new RuntimeException("获取住宿信息失败", e);
        }
    }

    /**
     * 根据学生ID获取住宿信息
     * @param studentId 学生ID
     * @return 住宿信息
     */
    public Accommodation getAccommodationByStudentId(String studentId) {
        try {
            Accommodation accommodation = accommodationService.getAccommodationByStudentId(studentId);
            if (accommodation != null) {
                logger.info("获取学生住宿信息成功：{}", studentId);
            } else {
                logger.warn("未找到学生住宿信息：{}", studentId);
            }
            return accommodation;
        } catch (Exception e) {
            logger.error("获取学生住宿信息失败：{}", studentId, e);
            throw new RuntimeException("获取学生住宿信息失败", e);
        }
    }

    /**
     * 分配床位
     * @param studentId 学生ID
     * @param bedId 床位ID
     * @return 是否成功
     */
    public boolean assignBed(String studentId, String bedId) {
        try {
            boolean result = accommodationService.assignBed(studentId, bedId);
            if (result) {
                logger.info("分配床位成功：学生{} -> 床位{}", studentId, bedId);
            } else {
                logger.warn("分配床位失败：学生{} -> 床位{}", studentId, bedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("分配床位异常：学生{} -> 床位{}", studentId, bedId, e);
            return false;
        }
    }

    /**
     * 调换床位
     * @param studentId 学生ID
     * @param newBedId 新床位ID
     * @return 是否成功
     */
    public boolean changeBed(String studentId, String newBedId) {
        try {
            boolean result = accommodationService.changeBed(studentId, newBedId);
            if (result) {
                logger.info("调换床位成功：学生{} -> 新床位{}", studentId, newBedId);
            } else {
                logger.warn("调换床位失败：学生{} -> 新床位{}", studentId, newBedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("调换床位异常：学生{} -> 新床位{}", studentId, newBedId, e);
            return false;
        }
    }

    /**
     * 退宿
     * @param studentId 学生ID
     * @return 是否成功
     */
    public boolean checkout(String studentId) {
        try {
            boolean result = accommodationService.checkout(studentId);
            if (result) {
                logger.info("退宿成功：学生{}", studentId);
            } else {
                logger.warn("退宿失败：学生{}", studentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("退宿异常：学生{}", studentId, e);
            return false;
        }
    }

    /**
     * 获取空闲床位列表
     * @return 空闲床位ID列表
     */
    public List<String> getAvailableBeds() {
        try {
            List<String> availableBeds = accommodationService.getAvailableBeds();
            logger.info("获取空闲床位成功：{}个", availableBeds.size());
            return availableBeds;
        } catch (Exception e) {
            logger.error("获取空闲床位失败", e);
            throw new RuntimeException("获取空闲床位失败", e);
        }
    }

    /**
     * 根据条件搜索住宿信息
     * @param searchText 搜索文本
     * @param searchType 搜索类型
     * @return 住宿信息列表
     */
    public List<Accommodation> searchAccommodations(String searchText, String searchType) {
        try {
            List<Accommodation> accommodations = accommodationService.searchAccommodations(searchText, searchType);
            logger.info("搜索住宿信息成功，类型：{}，关键词：{}，结果：{}条记录",
                       searchType, searchText, accommodations.size());
            return accommodations;
        } catch (Exception e) {
            logger.error("搜索住宿信息异常，类型：{}，关键词：{}", searchType, searchText, e);
            throw new RuntimeException("搜索住宿信息失败", e);
        }
    }
}