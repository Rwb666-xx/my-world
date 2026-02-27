package org.dorm.model.dao;

import org.dorm.model.entity.Announcement;
import java.util.List;

/**
 * 公告数据访问对象接口
 */
public interface AnnouncementDAO {

    /**
     * 获取所有激活状态的公告
     * @return 公告列表
     */
    List<Announcement> findAllActive();

    /**
     * 获取所有公告（包括停用的）
     * @return 公告列表
     */
    List<Announcement> findAll();

    /**
     * 根据ID获取公告
     * @param announcementId 公告ID
     * @return 公告对象
     */
    Announcement findById(String announcementId);

    /**
     * 添加新公告
     * @param announcement 公告对象
     * @return 是否成功
     */
    boolean insert(Announcement announcement);

    /**
     * 更新公告信息
     * @param announcement 公告对象
     * @return 是否成功
     */
    boolean update(Announcement announcement);

    /**
     * 删除公告
     * @param announcementId 公告ID
     * @return 是否成功
     */
    boolean delete(String announcementId);

    /**
     * 更新公告状态
     * @param announcementId 公告ID
     * @param status 新状态
     * @return 是否成功
     */
    boolean updateStatus(String announcementId, String status);

    /**
     * 根据分类获取公告
     * @param category 分类
     * @return 公告列表
     */
    List<Announcement> findByCategory(String category);

    /**
     * 获取最新的N条公告
     * @param limit 数量限制
     * @return 公告列表
     */
    List<Announcement> findLatest(int limit);
}