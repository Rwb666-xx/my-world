package org.dorm.view;

/**
 * 统计数据类
 */
public class StatisticsData {
    private String category;
    private String name;
    private int count;
    private double percentage;
    private String status;

    public StatisticsData(String category, String name, int count, double percentage, String status) {
        this.category = category;
        this.name = name;
        this.count = count;
        this.percentage = percentage;
        this.status = status;
    }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getCount() { return count; }
    public void setCount(int count) { this.count = count; }

    public double getPercentage() { return percentage; }
    public void setPercentage(double percentage) { this.percentage = percentage; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}