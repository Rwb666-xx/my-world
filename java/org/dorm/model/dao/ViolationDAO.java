package org.dorm.model.dao;

import org.dorm.model.entity.Violation;

import java.util.List;

/**
 * 违纪数据访问接口
 */
public interface ViolationDAO {
    /**
     * 获取所有违纪记录
     * @return 违纪记录列表
     */
    List<Violation> findAll();

    /**
     * 根据违纪单号查找违纪记录
     * @param violationId 违纪单号
     * @return 违纪记录
     */
    Violation findByViolationId(String violationId);

    /**
     * 根据学生ID查找违纪记录
     * @param studentId 学生ID
     * @return 违纪记录列表
     */
    List<Violation> findByStudentId(String studentId);

    /**
     * 添加违纪记录
     * @param violation 违纪记录
     * @return 是否添加成功
     */
    boolean addViolation(Violation violation);

    /**
     * 更新违纪记录
     * @param violation 违纪记录
     * @return 是否更新成功
     */
    boolean updateViolation(Violation violation);

    /**
     * 删除违纪记录
     * @param violationId 违纪单号
     * @return 是否删除成功
     */
    boolean deleteViolation(String violationId);
}