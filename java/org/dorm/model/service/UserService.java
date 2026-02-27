package org.dorm.model.service;

import org.dorm.model.entity.User;

/**
 * 用户业务逻辑服务接口
 */
public interface UserService {
    /**
     * 用户登录验证
     * @param userId 用户名
     * @param password 密码
     * @return 用户对象，如果登录失败返回null
     */
    User login(String userId, String password);

    /**
     * 注册新用户
     * @param user 用户对象
     * @return 是否注册成功
     */
    boolean register(User user);

    /**
     * 修改密码
     * @param userId 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否修改成功
     */
    boolean changePassword(String userId, String oldPassword, String newPassword);

    /**
     * 检查用户名是否存在
     * @param userId 用户名
     * @return 是否存在
     */
    boolean isUserExists(String userId);

    /**
     * 根据用户名获取用户信息
     * @param userId 用户名
     * @return 用户对象
     */
    User getUserById(String userId);
}