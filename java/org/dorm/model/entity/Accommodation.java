package org.dorm.model.entity;

import java.util.Date;

/**
 * 住宿信息实体类
 * 用于显示学生的住宿分配情况
 */
public class Accommodation {
    private String studentId;
    private String studentName;
    private String collegeName;
    private String className;
    private String dormitoryId;
    private String bedId;
    private Date checkinDate;
    private String status; // "已入住", "待分配", "已退宿"

    public Accommodation() {}

    public Accommodation(String studentId, String studentName, String collegeName,
                        String className, String dormitoryId, String bedId,
                        Date checkinDate, String status) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.collegeName = collegeName;
        this.className = className;
        this.dormitoryId = dormitoryId;
        this.bedId = bedId;
        this.checkinDate = checkinDate;
        this.status = status;
    }

    // Getter and Setter methods
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

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public Date getCheckinDate() {
        return checkinDate;
    }

    public void setCheckinDate(Date checkinDate) {
        this.checkinDate = checkinDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Accommodation{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", dormitoryId='" + dormitoryId + '\'' +
                ", bedId='" + bedId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}