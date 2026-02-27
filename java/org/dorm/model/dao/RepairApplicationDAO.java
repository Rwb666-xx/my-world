package org.dorm.model.dao;

import org.dorm.model.entity.RepairApplication;
import java.util.List;

/**
 * 维修申请数据访问接口
 */
public interface RepairApplicationDAO {
    /**
     * 根据申请单号查找维修申请
     * @param applyId 申请单号
     * @return 维修申请对象，如果不存在返回null
     */
    RepairApplication findByApplyId(String applyId);

    /**
     * 根据学生学号查找维修申请
     * @param studentId 学生学号
     * @return 维修申请列表
     */
    List<RepairApplication> findByStudentId(String studentId);

    /**
     * 获取所有维修申请
     * @return 维修申请列表
     */
    List<RepairApplication> findAll();

    /**
     * 根据状态查找维修申请
     * @param status 处理状态
     * @return 维修申请列表
     */
    List<RepairApplication> findByStatus(String status);

    /**
     * 添加维修申请
     * @param application 维修申请对象
     * @return 是否添加成功
     */
    boolean addRepairApplication(RepairApplication application);

    /**
     * 更新维修申请状态
     * @param applyId 申请单号
     * @param status 新状态
     * @param handler 处理人
     * @return 是否更新成功
     */
    boolean updateStatus(String applyId, String status, String handler);

    /**
     * 完成维修申请
     * @param applyId 申请单号
     * @param finishTime 完成时间
     * @return 是否更新成功
     */
    boolean completeRepair(String applyId, java.util.Date finishTime);
}