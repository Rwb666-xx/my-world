package org.dorm.controller;

import org.dorm.model.entity.User;
import org.dorm.model.service.UserService;
import org.dorm.model.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户控制器
 * 处理用户相关的业务逻辑
 */
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService = new UserServiceImpl();

    /**
     * 添加新用户（注册）
     * @param user 用户信息
     * @return 是否成功
     */
    public boolean addUser(User user) {
        try {
            boolean result = userService.register(user);
            if (result) {
                logger.info("用户注册成功：{}", user.getUserId());
            } else {
                logger.warn("用户注册失败：{}", user.getUserId());
            }
            return result;
        } catch (Exception e) {
            logger.error("用户注册异常：{}", user.getUserId(), e);
            throw new RuntimeException("用户注册失败", e);
        }
    }


    /**
     * 根据用户ID查找用户
     * @param userId 用户ID
     * @return 用户信息
     */
    public User getUserById(String userId) {
        try {
            User user = userService.getUserById(userId);
            if (user != null) {
                logger.info("查找用户成功：{}", userId);
            } else {
                logger.warn("未找到用户：{}", userId);
            }
            return user;
        } catch (Exception e) {
            logger.error("查找用户异常：{}", userId, e);
            throw new RuntimeException("查找用户失败", e);
        }
    }
}