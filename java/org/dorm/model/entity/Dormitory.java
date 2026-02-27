package org.dorm.model.entity;

/**
 * 宿舍实体类
 * 用于存储宿舍基本信息
 */
public class Dormitory {
    private String dormitoryId;   // 宿舍号，唯一标识
    private String building;      // 楼栋名称或编号
    private int floor;            // 楼层
    private int totalBeds;        // 可容纳人数
    private String managerName;   // 管理员姓名
    private String managerPhone;  // 管理员联系电话

    // 构造函数
    public Dormitory() {}

    public Dormitory(String dormitoryId, String building, int floor, int totalBeds,
                    String managerName, String managerPhone) {
        this.dormitoryId = dormitoryId;
        this.building = building;
        this.floor = floor;
        this.totalBeds = totalBeds;
        this.managerName = managerName;
        this.managerPhone = managerPhone;
    }

    // Getter和Setter方法
    public String getDormitoryId() {
        return dormitoryId;
    }

    public void setDormitoryId(String dormitoryId) {
        this.dormitoryId = dormitoryId;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public int getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(int totalBeds) {
        this.totalBeds = totalBeds;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerPhone() {
        return managerPhone;
    }

    public void setManagerPhone(String managerPhone) {
        this.managerPhone = managerPhone;
    }

    @Override
    public String toString() {
        return "Dormitory{" +
                "dormitoryId='" + dormitoryId + '\'' +
                ", building='" + building + '\'' +
                ", floor=" + floor +
                ", totalBeds=" + totalBeds +
                ", managerName='" + managerName + '\'' +
                ", managerPhone='" + managerPhone + '\'' +
                '}';
    }
}