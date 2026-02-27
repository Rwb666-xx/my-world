package org.dorm.model.entity;

import java.util.Date;

/**
 * 维修申请实体类
 * 用于存储学生的宿舍设施维修申请信息
 */
public class RepairApplication {
    private String applyId;      // 申请单号，唯一标识
    private String studentId;    // 申请人学号
    private String faultLocation;// 故障位置
    private String faultDesc;    // 故障描述
    private String contactPhone; // 联系电话
    private Date applyTime;      // 申请时间
    private String status;       // 处理状态：待受理/已受理/维修中/已完成
    private String handler;      // 处理人
    private Date finishTime;     // 完成时间

    // 构造函数
    public RepairApplication() {}

    public RepairApplication(String applyId, String studentId, String faultLocation,
                           String faultDesc, String contactPhone, Date applyTime,
                           String status, String handler, Date finishTime) {
        this.applyId = applyId;
        this.studentId = studentId;
        this.faultLocation = faultLocation;
        this.faultDesc = faultDesc;
        this.contactPhone = contactPhone;
        this.applyTime = applyTime;
        this.status = status;
        this.handler = handler;
        this.finishTime = finishTime;
    }

    // Getter和Setter方法
    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getFaultLocation() {
        return faultLocation;
    }

    public void setFaultLocation(String faultLocation) {
        this.faultLocation = faultLocation;
    }

    public String getFaultDesc() {
        return faultDesc;
    }

    public void setFaultDesc(String faultDesc) {
        this.faultDesc = faultDesc;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    @Override
    public String toString() {
        return "RepairApplication{" +
                "applyId='" + applyId + '\'' +
                ", studentId='" + studentId + '\'' +
                ", faultLocation='" + faultLocation + '\'' +
                ", status='" + status + '\'' +
                ", applyTime=" + applyTime +
                '}';
    }
}