package org.dorm.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据更新管理器 - 单例模式
 * 用于在各个Controller之间传递数据更新通知
 */
public class DataUpdateManager {

    // 单例实例
    private static DataUpdateManager instance;

    // 数据更新监听器接口
    public interface DataUpdateListener {
        void onDataUpdated(String dataType, String operation);
    }

    // 监听器列表
    private List<DataUpdateListener> listeners = new ArrayList<>();

    // 私有构造函数
    private DataUpdateManager() {}

    // 获取单例实例
    public static synchronized DataUpdateManager getInstance() {
        if (instance == null) {
            instance = new DataUpdateManager();
        }
        return instance;
    }

    // 注册监听器
    public void addListener(DataUpdateListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    // 移除监听器
    public void removeListener(DataUpdateListener listener) {
        listeners.remove(listener);
    }

    // 通知所有监听器数据已更新
    public void notifyDataUpdated(String dataType, String operation) {
        for (DataUpdateListener listener : listeners) {
            try {
                listener.onDataUpdated(dataType, operation);
            } catch (Exception e) {
                // 防止一个监听器的异常影响其他监听器
                System.err.println("数据更新监听器异常: " + e.getMessage());
            }
        }
    }

    // 便捷方法 - 学生数据更新
    public void notifyStudentDataChanged(String operation) {
        notifyDataUpdated("student", operation);
    }

    // 便捷方法 - 宿舍数据更新
    public void notifyDormitoryDataChanged(String operation) {
        notifyDataUpdated("dormitory", operation);
    }

    // 便捷方法 - 缴费数据更新
    public void notifyPaymentDataChanged(String operation) {
        notifyDataUpdated("payment", operation);
    }

    // 便捷方法 - 违纪数据更新
    public void notifyViolationDataChanged(String operation) {
        notifyDataUpdated("violation", operation);
    }

    // 便捷方法 - 维修申请数据更新
    public void notifyRepairDataChanged(String operation) {
        notifyDataUpdated("repair", operation);
    }
}