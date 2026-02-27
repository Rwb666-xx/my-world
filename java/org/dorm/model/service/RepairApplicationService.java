package org.dorm.model.service;

import org.dorm.model.entity.RepairApplication;
import java.util.List;

/**
 * 维修申请业务逻辑服务接口
 */
public interface RepairApplicationService {
    /**
     * 提交维修申请
     * @param application 维修申请对象
     * @return 是否提交成功
     */
    boolean submitRepairApplication(RepairApplication application);

    /**
     * 获取学生自己的维修申请
     * @param studentId 学生学号
     * @return 维修申请列表
     */
    List<RepairApplication> getStudentApplications(String studentId);

    /**
     * 获取所有维修申请（管理员功能）
     * @return 维修申请列表
     */
    List<RepairApplication> getAllApplications();

    /**
     * 获取待处理的维修申请
     * @return 维修申请列表
     */
    List<RepairApplication> getPendingApplications();

    /**
     * 更新维修申请状态
     * @param applyId 申请单号
     * @param status 新状态
     * @param handler 处理人
     * @return 是否更新成功
     */
    boolean updateApplicationStatus(String applyId, String status, String handler);

    /**
     * 完成维修申请
     * @param applyId 申请单号
     * @return 是否完成成功
     */
    boolean completeRepairApplication(String applyId);

    /**
     * 生成申请单号
     * @param studentId 学生学号
     * @return 申请单号
     */
    String generateApplyId(String studentId);
}