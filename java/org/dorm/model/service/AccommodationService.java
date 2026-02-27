package org.dorm.model.service;

import org.dorm.model.entity.Accommodation;
import java.util.List;

/**
 * 住宿分配服务接口
 */
public interface AccommodationService {
    /**
     * 获取所有住宿信息
     * @return 住宿信息列表
     */
    List<Accommodation> getAllAccommodations();

    /**
     * 根据学生ID获取住宿信息
     * @param studentId 学生ID
     * @return 住宿信息
     */
    Accommodation getAccommodationByStudentId(String studentId);

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
    List<String> getAvailableBeds();

    /**
     * 根据条件搜索住宿信息
     * @param searchText 搜索文本
     * @param searchType 搜索类型
     * @return 住宿信息列表
     */
    List<Accommodation> searchAccommodations(String searchText, String searchType);
}