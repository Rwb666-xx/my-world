package org.dorm.model.entity;

import java.util.Date;

/**
 * 学生实体类
 * 用于存储学生基本信息
 */
public class Student {
    private String studentId;      // 学号，唯一标识
    private String password;       // 密码
    private String name;           // 姓名
    private String collegeName;    // 学院名
    private String className;      // 班级号
    private String gender;         // 性别
    private Date birthday;         // 生日
    private String email;          // 电子邮箱
    private String phone;          // 联系电话
    private String bedId;          // 关联床位号

    // 构造函数
    public Student() {}

    public Student(String studentId, String password, String name, String collegeName,
                  String className, String gender, Date birthday, String email,
                  String phone, String bedId) {
        this.studentId = studentId;
        this.password = password;
        this.name = name;
        this.collegeName = collegeName;
        this.className = className;
        this.gender = gender;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.bedId = bedId;
    }

    // Getter和Setter方法
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBedId() {
        return bedId;
    }

    public void setBedId(String bedId) {
        this.bedId = bedId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", name='" + name + '\'' +
                ", collegeName='" + collegeName + '\'' +
                ", className='" + className + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", bedId='" + bedId + '\'' +
                '}';
    }
}