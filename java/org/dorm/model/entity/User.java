package org.dorm.model.entity;

/**
 * 用户实体类
 * 用于存储系统用户信息
 */
public class User {
    private String userId;      // 用户名，唯一标识
    private String password;    // 密码
    private String userType;    // 用户类型：管理员/学生

    // 构造函数
    public User() {}

    public User(String userId, String password, String userType) {
        this.userId = userId;
        this.password = password;
        this.userType = userType;
    }

    // Getter和Setter方法
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }
}