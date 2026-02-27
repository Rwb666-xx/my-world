package org.dorm.model.dao;

import org.dorm.model.entity.User;

/**
 * 用户数据访问接口
 */
public interface UserDAO {
    /**
     * 根据用户名查找用户
     * @param userId 用户名
     * @return 用户对象，如果不存在返回null
     */
    User findByUserId(String userId);

    /**
     * 验证用户登录
     * @param userId 用户名
     * @param password 密码
     * @return 用户对象，如果验证失败返回null
     */
    User login(String userId, String password);

    /**
     * 添加新用户
     * @param user 用户对象
     * @return 是否添加成功
     */
    boolean addUser(User user);

    /**
     * 更新用户信息
     * @param user 用户对象
     * @return 是否更新成功
     */
    boolean updateUser(User user);

    /**
     * 删除用户
     * @param userId 用户名
     * @return 是否删除成功
     */
    boolean deleteUser(String userId);
}