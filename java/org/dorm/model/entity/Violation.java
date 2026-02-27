package org.dorm.model.entity;

import java.util.Date;

/**
 * 违纪记录实体类
 * 用于存储学生的违纪信息
 */
public class Violation {
    private String violationId;  // 违纪记录ID，唯一标识
    private String studentId;    // 关联学号
    private String description;  // 违纪行为描述
    private Date violationDate;  // 违纪发生日期
    private String penalty;      // 处罚结果

    // 构造函数
    public Violation() {}

    public Violation(String violationId, String studentId, String description,
                    Date violationDate, String penalty) {
        this.violationId = violationId;
        this.studentId = studentId;
        this.description = description;
        this.violationDate = violationDate;
        this.penalty = penalty;
    }

    // Getter和Setter方法
    public String getViolationId() {
        return violationId;
    }

    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getViolationDate() {
        return violationDate;
    }

    public void setViolationDate(Date violationDate) {
        this.violationDate = violationDate;
    }

    public String getPenalty() {
        return penalty;
    }

    public void setPenalty(String penalty) {
        this.penalty = penalty;
    }

    @Override
    public String toString() {
        return "Violation{" +
                "violationId='" + violationId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", description='" + description + '\'' +
                ", violationDate=" + violationDate +
                ", penalty='" + penalty + '\'' +
                '}';
    }
}