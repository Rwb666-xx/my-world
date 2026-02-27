package org.dorm.model.service;

import org.dorm.model.entity.Dormitory;

import java.util.List;

/**
 * 宿舍服务接口
 */
public interface DormitoryService {

    /**
     * 获取所有宿舍
     * @return 宿舍列表
     */
    List<Dormitory> getAllDormitories();

    /**
     * 根据宿舍ID获取宿舍信息
     * @param dormitoryId 宿舍ID
     * @return 宿舍信息
     */
    Dormitory getDormitoryById(String dormitoryId);

    /**
     * 添加宿舍
     * @param dormitory 宿舍信息
     * @return 是否添加成功
     */
    boolean addDormitory(Dormitory dormitory);

    /**
     * 更新宿舍信息
     * @param dormitory 宿舍信息
     * @return 是否更新成功
     */
    boolean updateDormitory(Dormitory dormitory);

    /**
     * 删除宿舍
     * @param dormitoryId 宿舍ID
     * @return 是否删除成功
     */
    boolean deleteDormitory(String dormitoryId);
}