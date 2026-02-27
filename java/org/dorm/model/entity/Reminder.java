package org.dorm.model.entity;

import java.util.Date;

/**
 * 提醒信息实体类
 * 用于存储需要提醒的事项，如未缴费、未处理违纪等
 */
public class Reminder {
    private String reminderId;      // 提醒ID，唯一标识
    private String studentId;       // 关联学生ID
    private String studentName;     // 学生姓名
    private String type;            // 提醒类型：缴费提醒、违纪处理提醒
    private String title;           // 提醒标题
    private String content;         // 提醒内容
    private String priority;        // 优先级：高、中、低
    private String status;          // 状态：待处理、已处理、已忽略
    private Date createTime;        // 创建时间
    private Date handleTime;        // 处理时间
    private String handler;         // 处理人

    // 构造函数
    public Reminder() {}

    public Reminder(String reminderId, String studentId, String studentName, String type,
                   String title, String content, String priority, String status,
                   Date createTime, Date handleTime, String handler) {
        this.reminderId = reminderId;
        this.studentId = studentId;
        this.studentName = studentName;
        this.type = type;
        this.title = title;
        this.content = content;
        this.priority = priority;
        this.status = status;
        this.createTime = createTime;
        this.handleTime = handleTime;
        this.handler = handler;
    }

    // Getter和Setter方法
    public String getReminderId() {
        return reminderId;
    }

    public void setReminderId(String reminderId) {
        this.reminderId = reminderId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getHandleTime() {
        return handleTime;
    }

    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    @Override
    public String toString() {
        return "Reminder{" +
                "reminderId='" + reminderId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", priority='" + priority + '\'' +
                ", status='" + status + '\'' +
                ", createTime=" + createTime +
                ", handleTime=" + handleTime +
                ", handler='" + handler + '\'' +
                '}';
    }
}