package org.dorm.model.service;

import org.dorm.model.entity.Announcement;
import java.util.List;

/**
 * 公告业务逻辑服务接口
 */
public interface AnnouncementService {

    /**
     * 获取所有激活状态的公告
     * @return 公告列表
     */
    List<Announcement> getAllActiveAnnouncements();

    /**
     * 获取所有公告（管理员查看）
     * @return 公告列表
     */
    List<Announcement> getAllAnnouncements();

    /**
     * 根据ID获取公告
     * @param announcementId 公告ID
     * @return 公告对象
     */
    Announcement getAnnouncementById(String announcementId);

    /**
     * 发布新公告
     * @param announcement 公告对象
     * @return 是否成功
     */
    boolean publishAnnouncement(Announcement announcement);

    /**
     * 更新公告
     * @param announcement 公告对象
     * @return 是否成功
     */
    boolean updateAnnouncement(Announcement announcement);

    /**
     * 删除公告
     * @param announcementId 公告ID
     * @return 是否成功
     */
    boolean deleteAnnouncement(String announcementId);

    /**
     * 激活公告
     * @param announcementId 公告ID
     * @return 是否成功
     */
    boolean activateAnnouncement(String announcementId);

    /**
     * 停用公告
     * @param announcementId 公告ID
     * @return 是否成功
     */
    boolean deactivateAnnouncement(String announcementId);

    /**
     * 根据分类获取公告
     * @param category 分类
     * @return 公告列表
     */
    List<Announcement> getAnnouncementsByCategory(String category);

    /**
     * 获取最新的N条公告
     * @param limit 数量限制
     * @return 公告列表
     */
    List<Announcement> getLatestAnnouncements(int limit);

    /**
     * 获取重要公告（紧急通知和重要通知）
     * @return 公告列表
     */
    List<Announcement> getImportantAnnouncements();

    /**
     * 生成公告ID
     * @return 新公告ID
     */
    String generateAnnouncementId();
}