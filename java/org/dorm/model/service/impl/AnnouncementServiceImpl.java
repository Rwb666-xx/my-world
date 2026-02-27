package org.dorm.model.service.impl;

import org.dorm.model.dao.AnnouncementDAO;
import org.dorm.model.dao.impl.AnnouncementDAOImpl;
import org.dorm.model.entity.Announcement;
import org.dorm.model.service.AnnouncementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;

/**
 * 公告业务逻辑服务实现类
 */
public class AnnouncementServiceImpl implements AnnouncementService {
    private static final Logger logger = LoggerFactory.getLogger(AnnouncementServiceImpl.class);
    private AnnouncementDAO announcementDAO = new AnnouncementDAOImpl();

    @Override
    public List<Announcement> getAllActiveAnnouncements() {
        try {
            List<Announcement> announcements = announcementDAO.findAllActive();
            logger.info("获取激活公告成功，共{}条", announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("获取激活公告失败", e);
            throw new RuntimeException("获取公告失败", e);
        }
    }

    @Override
    public List<Announcement> getAllAnnouncements() {
        try {
            List<Announcement> announcements = announcementDAO.findAll();
            logger.info("获取所有公告成功，共{}条", announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("获取所有公告失败", e);
            throw new RuntimeException("获取公告失败", e);
        }
    }

    @Override
    public Announcement getAnnouncementById(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("获取公告失败：公告ID为空");
            return null;
        }

        try {
            Announcement announcement = announcementDAO.findById(announcementId.trim());
            if (announcement != null) {
                logger.info("获取公告成功：{}", announcementId);
            } else {
                logger.warn("公告不存在：{}", announcementId);
            }
            return announcement;
        } catch (Exception e) {
            logger.error("获取公告失败：{}", announcementId, e);
            throw new RuntimeException("获取公告失败", e);
        }
    }

    @Override
    public boolean publishAnnouncement(Announcement announcement) {
        if (announcement == null) {
            logger.warn("发布公告失败：公告对象为空");
            return false;
        }

        if (announcement.getTitle() == null || announcement.getTitle().trim().isEmpty()) {
            logger.warn("发布公告失败：公告标题为空");
            return false;
        }

        if (announcement.getContent() == null || announcement.getContent().trim().isEmpty()) {
            logger.warn("发布公告失败：公告内容为空");
            return false;
        }

        if (announcement.getPublisher() == null || announcement.getPublisher().trim().isEmpty()) {
            logger.warn("发布公告失败：发布者为空");
            return false;
        }

        // 设置默认值
        if (announcement.getAnnouncementId() == null || announcement.getAnnouncementId().trim().isEmpty()) {
            announcement.setAnnouncementId(generateAnnouncementId());
        }

        if (announcement.getPublishTime() == null) {
            announcement.setPublishTime(new Date());
        }

        if (announcement.getStatus() == null || announcement.getStatus().trim().isEmpty()) {
            announcement.setStatus("active");
        }

        if (announcement.getCategory() == null || announcement.getCategory().trim().isEmpty()) {
            announcement.setCategory("general");
        }

        try {
            boolean result = announcementDAO.insert(announcement);
            if (result) {
                logger.info("发布公告成功：{} - {}", announcement.getAnnouncementId(), announcement.getTitle());
            } else {
                logger.warn("发布公告失败：{}", announcement.getTitle());
            }
            return result;
        } catch (Exception e) {
            logger.error("发布公告异常", e);
            throw new RuntimeException("发布公告失败", e);
        }
    }

    @Override
    public boolean updateAnnouncement(Announcement announcement) {
        if (announcement == null || announcement.getAnnouncementId() == null) {
            logger.warn("更新公告失败：公告对象或ID为空");
            return false;
        }

        try {
            boolean result = announcementDAO.update(announcement);
            if (result) {
                logger.info("更新公告成功：{}", announcement.getAnnouncementId());
            } else {
                logger.warn("更新公告失败：{}", announcement.getAnnouncementId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新公告异常", e);
            throw new RuntimeException("更新公告失败", e);
        }
    }

    @Override
    public boolean deleteAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("删除公告失败：公告ID为空");
            return false;
        }

        try {
            boolean result = announcementDAO.delete(announcementId.trim());
            if (result) {
                logger.info("删除公告成功：{}", announcementId);
            } else {
                logger.warn("删除公告失败：{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除公告异常", e);
            throw new RuntimeException("删除公告失败", e);
        }
    }

    @Override
    public boolean activateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("激活公告失败：公告ID为空");
            return false;
        }

        try {
            boolean result = announcementDAO.updateStatus(announcementId.trim(), "active");
            if (result) {
                logger.info("激活公告成功：{}", announcementId);
            } else {
                logger.warn("激活公告失败：{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("激活公告异常", e);
            throw new RuntimeException("激活公告失败", e);
        }
    }

    @Override
    public boolean deactivateAnnouncement(String announcementId) {
        if (announcementId == null || announcementId.trim().isEmpty()) {
            logger.warn("停用公告失败：公告ID为空");
            return false;
        }

        try {
            boolean result = announcementDAO.updateStatus(announcementId.trim(), "inactive");
            if (result) {
                logger.info("停用公告成功：{}", announcementId);
            } else {
                logger.warn("停用公告失败：{}", announcementId);
            }
            return result;
        } catch (Exception e) {
            logger.error("停用公告异常", e);
            throw new RuntimeException("停用公告失败", e);
        }
    }

    @Override
    public List<Announcement> getAnnouncementsByCategory(String category) {
        if (category == null || category.trim().isEmpty()) {
            logger.warn("获取分类公告失败：分类为空");
            return announcementDAO.findAllActive();
        }

        try {
            List<Announcement> announcements = announcementDAO.findByCategory(category.trim());
            logger.info("获取{}分类公告成功，共{}条", category, announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("获取分类公告失败", e);
            throw new RuntimeException("获取分类公告失败", e);
        }
    }

    @Override
    public List<Announcement> getLatestAnnouncements(int limit) {
        if (limit <= 0) {
            limit = 10; // 默认获取10条
        }

        try {
            List<Announcement> announcements = announcementDAO.findLatest(limit);
            logger.info("获取最新公告成功，共{}条", announcements.size());
            return announcements;
        } catch (Exception e) {
            logger.error("获取最新公告失败", e);
            throw new RuntimeException("获取最新公告失败", e);
        }
    }

    @Override
    public List<Announcement> getImportantAnnouncements() {
        try {
            // 获取重要通知和紧急通知
            List<Announcement> important = announcementDAO.findByCategory("important");
            List<Announcement> emergency = announcementDAO.findByCategory("emergency");

            important.addAll(emergency);
            logger.info("获取重要公告成功，共{}条", important.size());
            return important;
        } catch (Exception e) {
            logger.error("获取重要公告失败", e);
            throw new RuntimeException("获取重要公告失败", e);
        }
    }

    @Override
    public String generateAnnouncementId() {
        // 生成公告ID格式：ANN + 时间戳
        return "ANN" + System.currentTimeMillis();
    }
}