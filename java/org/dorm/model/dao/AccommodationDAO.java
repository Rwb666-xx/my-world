package org.dorm.model.dao;

import org.dorm.model.entity.Accommodation;
import java.util.List;

/**
 * 住宿分配数据访问接口
 */
public interface AccommodationDAO {
    /**
     * 获取所有住宿信息
     * @return 住宿信息列表
     */
    List<Accommodation> findAll();

    /**
     * 根据学生ID获取住宿信息
     * @param studentId 学生ID
     * @return 住宿信息
     */
    Accommodation findByStudentId(String studentId);

    /**
     * 分配床位
     * @param studentId 学生ID
     * @param bedId 床位ID
     * @return 是否成功
     */
    boolean assignBed(String studentId, String bedId);

    /**
     * 调换床位
     * @param studentId 学生ID
     * @param newBedId 新床位ID
     * @return 是否成功
     */
    boolean changeBed(String studentId, String newBedId);

    /**
     * 退宿
     * @param studentId 学生ID
     * @return 是否成功
     */
    boolean checkout(String studentId);

    /**
     * 获取空闲床位列表
     * @return 空闲床位ID列表
     */
    List<String> findAvailableBeds();
}