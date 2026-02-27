package org.dorm.model.dao;

import org.dorm.model.entity.Bed;

import java.util.List;

/**
 * 床位数据访问接口
 */
public interface BedDAO {
    /**
     * 获取所有床位
     * @return 床位列表
     */
    List<Bed> findAll();

    /**
     * 根据床位ID查找床位
     * @param bedId 床位ID
     * @return 床位
     */
    Bed findByBedId(String bedId);

    /**
     * 获取可用床位
     * @return 可用床位列表
     */
    List<Bed> findAvailableBeds();

    /**
     * 根据宿舍ID查找床位
     * @param dormId 宿舍ID
     * @return 床位列表
     */
    List<Bed> findByDormId(String dormId);

    /**
     * 添加床位
     * @param bed 床位
     * @return 是否添加成功
     */
    boolean addBed(Bed bed);

    /**
     * 更新床位
     * @param bed 床位
     * @return 是否更新成功
     */
    boolean updateBed(Bed bed);

    /**
     * 删除床位
     * @param bedId 床位ID
     * @return 是否删除成功
     */
    boolean deleteBed(String bedId);
}