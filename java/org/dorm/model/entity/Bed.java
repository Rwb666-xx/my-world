package org.dorm.model.entity;

/**
 * 床位实体类
 * 用于存储床位基本信息
 */
public class Bed {
    private String bedId;        // 床位号，唯一标识
    private String dormId;       // 所属宿舍号
    private String status;       // 占用状态：空闲/占用
    private String studentId;    // 关联学生学号

    // 构造函数
    public Bed() {}

    public Bed(String bedId, String dormId, String status, String studentId) {
        this.bedId = bedId;
        this.dormId = dormId;
        this.status = status;
        this.studentId = studentId;
    }

    // Getter和Setter方法
    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    public String getDormId() {
        return dormId;
    }

    public void setDormId(String dormId) {
        this.dormId = dormId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Override
    public String toString() {
        return "Bed{" +
                "bedId='" + bedId + '\'' +
                ", dormId='" + dormId + '\'' +
                ", status='" + status + '\'' +
                ", studentId='" + studentId + '\'' +
                '}';
    }
}