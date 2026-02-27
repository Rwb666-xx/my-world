package org.dorm.controller;

import org.dorm.model.entity.User;
import org.dorm.model.service.UserService;
import org.dorm.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 登录控制器
 * 处理用户登录相关的业务逻辑
 */
public class LoginController {
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private UserService userService = new UserServiceImpl();

    /**
     * 用户登录验证
     * @param userId 用户名
     * @param password 密码
     * @return 登录结果：null表示登录失败，否则返回用户对象
     */
    public User login(String userId, String password) {
        if (userId == null || userId.trim().isEmpty()) {
            logger.warn("登录失败：用户名为空");
            return null;
        }

        if (password == null || password.trim().isEmpty()) {
            logger.warn("登录失败：密码为空");
            return null;
        }

        try {
            User user = userService.login(userId.trim(), password.trim());
            if (user != null) {
                logger.info("用户登录成功：{} ({})", userId, user.getUserType());
                return user;
            } else {
                logger.warn("用户登录失败：用户名或密码错误 - {}", userId);
                return null;
            }
        } catch (Exception e) {
            logger.error("登录过程中发生异常：{}", userId, e);
            return null;
        }
    }

    /**
     * 检查用户名是否存在
     * @param userId 用户名
     * @return 是否存在
     */
    public boolean isUserExists(String userId) {
        return userService.isUserExists(userId);
    }

    /**
     * 修改密码
     * @param userId 用户名
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return 是否修改成功
     */
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        try {
            boolean result = userService.changePassword(userId, oldPassword, newPassword);
            if (result) {
                logger.info("密码修改成功：{}", userId);
            } else {
                logger.warn("密码修改失败：{}", userId);
            }
            return result;
        } catch (Exception e) {
            logger.error("修改密码过程中发生异常：{}", userId, e);
            return false;
        }
    }
}