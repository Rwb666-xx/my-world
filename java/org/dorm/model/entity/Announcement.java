package org.dorm.model.entity;

import java.util.Date;

/**
 * 公告实体类
 * 用于存储管理员发布的公告信息，所有学生都能查看
 */
public class Announcement {
    private String announcementId;     // 公告ID，唯一标识
    private String title;              // 公告标题
    private String content;            // 公告内容
    private String publisher;          // 发布者（管理员ID）
    private Date publishTime;          // 发布时间
    private String status;             // 状态：active-激活，inactive-停用
    private String category;           // 分类：general-一般通知，important-重要通知，emergency-紧急通知

    // 构造函数
    public Announcement() {}

    public Announcement(String announcementId, String title, String content, String publisher, Date publishTime, String status, String category) {
        this.announcementId = announcementId;
        this.title = title;
        this.content = content;
        this.publisher = publisher;
        this.publishTime = publishTime;
        this.status = status;
        this.category = category;
    }

    // Getter和Setter方法
    public String getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(String announcementId) {
        this.announcementId = announcementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId='" + announcementId + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishTime=" + publishTime +
                ", status='" + status + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}