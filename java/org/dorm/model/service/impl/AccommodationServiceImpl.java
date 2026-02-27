package org.dorm.model.service.impl;

import org.dorm.model.dao.AccommodationDAO;
import org.dorm.model.dao.impl.AccommodationDAOImpl;
import org.dorm.model.entity.Accommodation;
import org.dorm.model.service.AccommodationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 住宿分配服务实现
 */
public class AccommodationServiceImpl implements AccommodationService {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationServiceImpl.class);
    private AccommodationDAO accommodationDAO = new AccommodationDAOImpl();

    @Override
    public List<Accommodation> getAllAccommodations() {
        try {
            List<Accommodation> accommodations = accommodationDAO.findAll();
            logger.info("获取所有住宿信息成功：{}条记录", accommodations.size());
            return accommodations;
        } catch (Exception e) {
            logger.error("获取所有住宿信息失败", e);
            throw new RuntimeException("获取住宿信息失败", e);
        }
    }

    @Override
    public Accommodation getAccommodationByStudentId(String studentId) {
        try {
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
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

    @Override
    public boolean assignBed(String studentId, String bedId) {
        try {
            // 验证学生是否存在且未分配床位
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
            if (accommodation == null) {
                logger.warn("学生不存在：{}", studentId);
                return false;
            }

            if (accommodation.getBedId() != null) {
                logger.warn("学生已分配床位：{} -> {}", studentId, accommodation.getBedId());
                return false;
            }

            boolean result = accommodationDAO.assignBed(studentId, bedId);
            if (result) {
                logger.info("分配床位成功：学生{} -> 床位{}", studentId, bedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("分配床位异常：学生{} -> 床位{}", studentId, bedId, e);
            return false;
        }
    }

    @Override
    public boolean changeBed(String studentId, String newBedId) {
        try {
            // 验证学生是否存在且已分配床位
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
            if (accommodation == null) {
                logger.warn("学生不存在：{}", studentId);
                return false;
            }

            if (accommodation.getBedId() == null) {
                logger.warn("学生未分配床位：{}", studentId);
                return false;
            }

            if (accommodation.getBedId().equals(newBedId)) {
                logger.warn("新床位与当前床位相同：{}", newBedId);
                return false;
            }

            boolean result = accommodationDAO.changeBed(studentId, newBedId);
            if (result) {
                logger.info("调换床位成功：学生{} -> 新床位{}", studentId, newBedId);
            }
            return result;
        } catch (Exception e) {
            logger.error("调换床位异常：学生{} -> 新床位{}", studentId, newBedId, e);
            return false;
        }
    }

    @Override
    public boolean checkout(String studentId) {
        try {
            // 验证学生是否存在且已分配床位
            Accommodation accommodation = accommodationDAO.findByStudentId(studentId);
            if (accommodation == null) {
                logger.warn("学生不存在：{}", studentId);
                return false;
            }

            if (accommodation.getBedId() == null) {
                logger.warn("学生未分配床位：{}", studentId);
                return false;
            }

            boolean result = accommodationDAO.checkout(studentId);
            if (result) {
                logger.info("退宿成功：学生{}", studentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("退宿异常：学生{}", studentId, e);
            return false;
        }
    }

    @Override
    public List<String> getAvailableBeds() {
        try {
            List<String> availableBeds = accommodationDAO.findAvailableBeds();
            logger.info("获取空闲床位成功：{}个", availableBeds.size());
            return availableBeds;
        } catch (Exception e) {
            logger.error("获取空闲床位失败", e);
            throw new RuntimeException("获取空闲床位失败", e);
        }
    }

    @Override
    public List<Accommodation> searchAccommodations(String searchText, String searchType) {
        try {
            List<Accommodation> allAccommodations = accommodationDAO.findAll();
            List<Accommodation> filteredAccommodations;

            if (searchText == null || searchText.trim().isEmpty()) {
                filteredAccommodations = allAccommodations;
            } else {
                String searchLower = searchText.toLowerCase().trim();
                filteredAccommodations = allAccommodations.stream()
                    .filter(accommodation -> {
                        switch (searchType) {
                            case "学号":
                                return accommodation.getStudentId() != null &&
                                       accommodation.getStudentId().toLowerCase().contains(searchLower);
                            case "姓名":
                                return accommodation.getStudentName() != null &&
                                       accommodation.getStudentName().toLowerCase().contains(searchLower);
                            case "宿舍":
                                return accommodation.getDormitoryId() != null &&
                                       accommodation.getDormitoryId().toLowerCase().contains(searchLower);
                            default: // "全部"
                                return (accommodation.getStudentId() != null &&
                                        accommodation.getStudentId().toLowerCase().contains(searchLower)) ||
                                       (accommodation.getStudentName() != null &&
                                        accommodation.getStudentName().toLowerCase().contains(searchLower)) ||
                                       (accommodation.getDormitoryId() != null &&
                                        accommodation.getDormitoryId().toLowerCase().contains(searchLower));
                        }
                    })
                    .collect(Collectors.toList());
            }

            logger.info("搜索住宿信息成功，类型：{}，关键词：{}，结果：{}条记录",
                       searchType, searchText, filteredAccommodations.size());
            return filteredAccommodations;
        } catch (Exception e) {
            logger.error("搜索住宿信息异常，类型：{}，关键词：{}", searchType, searchText, e);
            throw new RuntimeException("搜索住宿信息失败", e);
        }
    }
}