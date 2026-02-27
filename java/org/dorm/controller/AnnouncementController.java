package org.dorm.controller;

import org.dorm.model.entity.Announcement;
import org.dorm.model.service.AnnouncementService;
import org.dorm.model.service.impl.AnnouncementServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 公告控制器
 * 处理公告相关的业务逻辑
 */
public class AnnouncementController {
    private static final Logger logger = LoggerFactory.getLogger(AnnouncementController.class);
    private AnnouncementService announcementService = new AnnouncementServiceImpl();

    /**
     * 获取所有激活状态的公告（学生查看）
     * @return 公告列表
     */
    public List<Announcement> getAllActiveAnnouncements() {
        try {
            return announcementService.getAllActiveAnnouncements();
        } catch (Exception e) {
            logger.error("获取激活公告失败", e);
            throw new RuntimeException("获取公告失败", e);
        }
    }

    /**
     * 获取所有公告（管理员查看）
     * @return 公告列表
     */
    public List<Announcement> getAllAnnouncements() {
        try {
            return announcementService.getAllAnnouncements();
        } catch (Exception e) {
            logger.error("获取所有公告失败", e);
            throw new RuntimeException("获取公告失败", e);
        }
    }

    /**
     * 根据ID获取公告
     * @param announcementId 公告ID
     * @return 公告对象
     */
    public Announcement getAnnouncementById(String announcementId) {
        try {
            return announcementService.getAnnouncementById(announcementId);
        } catch (Exception e) {
            logger.error("获取公告详情失败：{}", announcementId, e);
            return null;
        }
    }

    /**
     * 发布新公告
     * @param announcement 公告对象
     * @return 是否成功
     */
    public boolean publishAnnouncement(Announcement announcement) {
        if (announcement == null) {
            logger.warn("发布公告失败：公告信息为空");
            return false;
        }

        try {
            boolean result = announcementService.publishAnnouncement(announcement);
            if (result) {
                logger.info("公告发布成功：{}", announcement.getTitle());
            } else {
                logger.error("公告发布失败：{}", announcement.getTitle());
            }
            return result;
        } catch (Exception e) {
            logger.error("发布公告过程中发生异常", e);
            return false;
        }
    }

    /**
     * 更新公告
     * @param announcement 公告对象
     * @return 是否成功
     */
    public boolean updateAnnouncement(Announcement announcement) {
        if (announcement == null) {
            logger.warn("更新公告失败：公告信息为空");
            return false;
        }

        try {
            boolean result = announcementService.updateAnnouncement(announcement);
            if (result) {
                logger.info("公告更新成功：{}", announcement.getAnnouncementId());
            } else {
                logger.error("公告更新失败：{}", announcement.getAnnouncementId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新公告过程中发生异常", e);
            return false;
        }
    }

    /**
     * 删除公告
     * @param announcementId 公告ID
     * @return 是否成功
     */
    public boolean deleteAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("删除公告失败：公告ID为空");
            return false;
        }

        try {
            boolean result = announcementService.deleteAnnouncement(announcementId.trim());
            if (result) {
                logger.info("公告删除成功：{}", announcementId);
            } else {
                logger.error("公告删除失败：{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除公告过程中发生异常", e);
            return false;
        }
    }

    /**
     * 激活公告
     * @param announcementId 公告ID
     * @return 是否成功
     */
    public boolean activateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("激活公告失败：公告ID为空");
            return false;
        }

        try {
            boolean result = announcementService.activateAnnouncement(announcementId.trim());
            if (result) {
                logger.info("公告激活成功：{}", announcementId);
            } else {
                logger.error("公告激活失败：{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("激活公告过程中发生异常", e);
            return false;
        }
    }

    /**
     * 停用公告
     * @param announcementId 公告ID
     * @return 是否成功
     */
    public boolean deactivateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("停用公告失败：公告ID为空");
            return false;
        }

        try {
            boolean result = announcementService.deactivateAnnouncement(announcementId.trim());
            if (result) {
                logger.info("公告停用成功：{}", announcementId);
            } else {
                logger.error("公告停用失败：{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("停用公告过程中发生异常", e);
            return false;
        }
    }

    /**
     * 根据分类获取公告
     * @param category 分类
     * @return 公告列表
     */
    public List<Announcement> getAnnouncementsByCategory(String category) {
        try {
            return announcementService.getAnnouncementsByCategory(category);
        } catch (Exception e) {
            logger.error("获取分类公告失败：{}", category, e);
            throw new RuntimeException("获取分类公告失败", e);
        }
    }

    /**
     * 获取最新的N条公告
     * @param limit 数量限制
     * @return 公告列表
     */
    public List<Announcement> getLatestAnnouncements(int limit) {
        try {
            return announcementService.getLatestAnnouncements(limit);
        } catch (Exception e) {
            logger.error("获取最新公告失败", e);
            throw new RuntimeException("获取最新公告失败", e);
        }
    }

    /**
     * 获取重要公告（紧急通知和重要通知）
     * @return 公告列表
     */
    public List<Announcement> getImportantAnnouncements() {
        try {
            return announcementService.getImportantAnnouncements();
        } catch (Exception e) {
            logger.error("获取重要公告失败", e);
            throw new RuntimeException("获取重要公告失败", e);
        }
    }

    /**
     * 生成公告ID
     * @return 新公告ID
     */
    public String generateAnnouncementId() {
        return announcementService.generateAnnouncementId();
    }
}