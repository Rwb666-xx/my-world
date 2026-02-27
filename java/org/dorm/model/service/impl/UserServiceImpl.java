package org.dorm.model.service.impl;

import org.dorm.model.dao.UserDAO;
import org.dorm.model.dao.impl.UserDAOImpl;
import org.dorm.model.entity.User;
import org.dorm.model.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用户业务逻辑服务实现类
 */
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User login(String userId, String password) {
        if (userId == null || userId.trim().isEmpty()) {
            logger.warn("登录失败：用户名为空");
            return null;
        }
        if (password == null || password.trim().isEmpty()) {
            logger.warn("登录失败：密码为空");
            return null;
        }

        User user = userDAO.login(userId.trim(), password.trim());
        if (user != null) {
            logger.info("用户登录成功：{}", userId);
        } else {
            logger.warn("用户登录失败：用户名或密码错误 - {}", userId);
        }
        return user;
    }

    @Override
    public boolean register(User user) {
        if (user == null || user.getUserId() == null || user.getUserId().trim().isEmpty()) {
            logger.warn("注册失败：用户信息不完整");
            return false;
        }

        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            logger.warn("注册失败：密码不能为空");
            return false;
        }

        if (isUserExists(user.getUserId())) {
            logger.warn("注册失败：用户名已存在 - {}", user.getUserId());
            return false;
        }

        boolean result = userDAO.addUser(user);
        if (result) {
            logger.info("用户注册成功：{}", user.getUserId());
        } else {
            logger.error("用户注册失败：{}", user.getUserId());
        }
        return result;
    }

    @Override
    public boolean changePassword(String userId, String oldPassword, String newPassword) {
        if (userId == null || oldPassword == null || newPassword == null) {
            logger.warn("修改密码失败：参数不完整");
            return false;
        }

        if (newPassword.trim().isEmpty()) {
            logger.warn("修改密码失败：新密码不能为空");
            return false;
        }

        // 验证旧密码
        User user = userDAO.login(userId, oldPassword);
        if (user == null) {
            logger.warn("修改密码失败：旧密码错误 - {}", userId);
            return false;
        }

        // 更新密码
        user.setPassword(newPassword.trim());
        boolean result = userDAO.updateUser(user);
        if (result) {
            logger.info("密码修改成功：{}", userId);
        } else {
            logger.error("密码修改失败：{}", userId);
        }
        return result;
    }

    @Override
    public boolean isUserExists(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return false;
        }
        return userDAO.findByUserId(userId.trim()) != null;
    }

    @Override
    public User getUserById(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            return null;
        }
        return userDAO.findByUserId(userId.trim());
    }
}