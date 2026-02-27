package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dorm.controller.StudentController;
import org.dorm.controller.DormitoryController;
import org.dorm.controller.RepairApplicationController;
import org.dorm.controller.PaymentController;
import org.dorm.controller.ViolationController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Dormitory;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.entity.Payment;
import org.dorm.model.entity.Violation;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 统计查询界面控制器
 */
public class StatisticsController implements DataUpdateManager.DataUpdateListener {
    private static final Logger logger = LoggerFactory.getLogger(StatisticsController.class);

    // 控制器
    private StudentController studentController = new StudentController();
    private DormitoryController dormitoryController = new DormitoryController();
    private RepairApplicationController repairController = new RepairApplicationController();
    private org.dorm.controller.PaymentController paymentController = new org.dorm.controller.PaymentController();
    private org.dorm.controller.ViolationController violationController = new org.dorm.controller.ViolationController();

    // 统计概览卡片
    @FXML private Label totalStudentsLabel;
    @FXML private Label totalDormitoriesLabel;
    @FXML private Label totalRepairsLabel;
    @FXML private Label paymentRateLabel;
    @FXML private Label studentsChangeLabel;
    @FXML private Label dormitoriesChangeLabel;
    @FXML private Label repairsChangeLabel;
    @FXML private Label paymentChangeLabel;

    // 工具栏
    @FXML private Button refreshButton;
    @FXML private Button exportButton;
    @FXML private ComboBox<String> timeRangeCombo;
    @FXML private ComboBox<String> dataTypeCombo;

    // 图表组件
    @FXML private PieChart collegeChart;
    @FXML private BarChart<String, Number> dormitoryChart;
    @FXML private LineChart<String, Number> repairTrendChart;
    @FXML private PieChart paymentChart;

    // 详细数据表格
    @FXML private TableView<org.dorm.view.StatisticsData> detailedTable;
    @FXML private TableColumn<org.dorm.view.StatisticsData, String> categoryColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, String> nameColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, Integer> countColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, Double> percentageColumn;
    @FXML private TableColumn<org.dorm.view.StatisticsData, String> statusColumn;

    // 返回按钮
    @FXML private Button backToMainButton;

    // 状态栏
    @FXML private Label lastUpdateLabel;
    @FXML private Label dataSourceLabel;

    private ObservableList<org.dorm.view.StatisticsData> tableData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        logger.info("初始化统计界面...");

        // 初始化表格列
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        percentageColumn.setCellValueFactory(new PropertyValueFactory<>("percentage"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 设置表格数据
        detailedTable.setItems(tableData);

        // 初始化下拉框
        timeRangeCombo.setValue("全部时间");
        dataTypeCombo.setValue("综合统计");

        // 加载初始统计数据
        refreshStatistics();

        // 注册数据更新监听器
        DataUpdateManager.getInstance().addListener(this);

        logger.info("统计界面初始化完成，已注册数据更新监听器");
    }

    /**
     * 刷新统计数据
     */
    @FXML
    private void refreshStatistics() {
        try {
            logger.info("开始刷新统计数据...");

            // 更新概览卡片
            updateOverviewCards();

            // 更新图表
            updateCharts();

            // 更新详细表格
            updateDetailedTable();

            // 更新最后更新时间
            updateLastUpdateTime();

            showMessage("数据刷新完成");

            logger.info("统计数据刷新完成");

        } catch (Exception e) {
            logger.error("刷新统计数据失败", e);
            showError("刷新数据失败：" + e.getMessage());
        }
    }

    /**
     * 返回主界面
     */
    @FXML
    private void backToMain(ActionEvent event) {
        try {
            // 加载主界面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
            Parent root = loader.load();

            // 获取当前舞台
            Stage stage = (Stage) backToMainButton.getScene().getWindow();

            // 设置新场景
            Scene scene = new Scene(root, 1400, 900);
            stage.setScene(scene);
            stage.setTitle("宿舍管理系统 - 主界面");
            stage.centerOnScreen();

            logger.info("返回主界面成功");

        } catch (Exception e) {
            logger.error("返回主界面失败", e);
            showError("返回主界面失败：" + e.getMessage());
        }
    }

    /**
     * 导出数据
     */
    @FXML
    private void exportData(ActionEvent event) {
        showMessage("导出功能开发中...");
    }

    /**
     * 更新概览卡片
     */
    private void updateOverviewCards() {
        try {
            // 获取学生总数
            List<Student> students = studentController.getAllStudents();
            int totalStudents = students.size();
            totalStudentsLabel.setText(String.valueOf(totalStudents));
            studentsChangeLabel.setText("较上月 +0");

            // 获取宿舍总数
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            int totalDormitories = dormitories.size();
            totalDormitoriesLabel.setText(String.valueOf(totalDormitories));
            dormitoriesChangeLabel.setText("较上月 +0");

            // 获取维修申请总数
            List<RepairApplication> repairs = repairController.getAllApplications();
            int totalRepairs = repairs.size();
            totalRepairsLabel.setText(String.valueOf(totalRepairs));
            repairsChangeLabel.setText("较上月 +0");

            // 计算缴费率 (从数据库获取真实数据)
            try {
                List<org.dorm.model.entity.Payment> payments = paymentController.getAllPayments();
                int totalPayments = payments.size();
                int paidPayments = 0;
                for (org.dorm.model.entity.Payment payment : payments) {
                    if ("已缴".equals(payment.getStatus())) {
                        paidPayments++;
                    }
                }
                double paymentRate = totalPayments > 0 ? (double) paidPayments / totalPayments * 100 : 0;
                paymentRateLabel.setText(String.format("%.1f%%", paymentRate));
                paymentChangeLabel.setText("目标 95%");
            } catch (Exception e) {
                logger.error("计算缴费率失败", e);
                paymentRateLabel.setText("0%");
                paymentChangeLabel.setText("目标 95%");
            }

        } catch (Exception e) {
            logger.error("更新概览卡片失败", e);
        }
    }

    /**
     * 更新图表
     */
    private void updateCharts() {
        updateCollegeChart();
        updateDormitoryChart();
        updateRepairTrendChart();
        updatePaymentChart();
    }

    /**
     * 更新学院分布图表
     */
    private void updateCollegeChart() {
        try {
            List<Student> students = studentController.getAllStudents();
            Map<String, Integer> collegeStats = new HashMap<>();

            // 统计各学院学生数量
            for (Student student : students) {
                String college = student.getCollegeName();
                collegeStats.put(college, collegeStats.getOrDefault(college, 0) + 1);
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            for (Map.Entry<String, Integer> entry : collegeStats.entrySet()) {
                pieChartData.add(new PieChart.Data(entry.getKey(), entry.getValue()));
            }

            collegeChart.setData(pieChartData);
            collegeChart.setTitle("学生学院分布");

        } catch (Exception e) {
            logger.error("更新学院分布图表失败", e);
        }
    }

    /**
     * 更新宿舍使用情况图表
     */
    private void updateDormitoryChart() {
        try {
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            List<Student> students = studentController.getAllStudents();

            // 统计各宿舍楼的学生数量
            Map<String, Integer> dormStats = new HashMap<>();
            for (Student student : students) {
                if (student.getBedId() != null && !student.getBedId().isEmpty()) {
                    // 从床位ID中提取宿舍ID (假设格式为 "A101-01")
                    String dormId = student.getBedId().split("-")[0];
                    dormStats.put(dormId, dormStats.getOrDefault(dormId, 0) + 1);
                }
            }

            XYChart.Series<String, Number> occupiedSeries = new XYChart.Series<>();
            occupiedSeries.setName("已入住");
            XYChart.Series<String, Number> capacitySeries = new XYChart.Series<>();
            capacitySeries.setName("总床位");

            for (Dormitory dorm : dormitories) {
                String dormId = dorm.getDormitoryId();
                int occupied = dormStats.getOrDefault(dormId, 0);
                int capacity = dorm.getTotalBeds();

                occupiedSeries.getData().add(new XYChart.Data<>(dormId, occupied));
                capacitySeries.getData().add(new XYChart.Data<>(dormId, capacity));
            }

            dormitoryChart.getData().clear();
            dormitoryChart.getData().addAll(occupiedSeries, capacitySeries);
            dormitoryChart.setTitle("宿舍使用情况 (入住/容量)");

        } catch (Exception e) {
            logger.error("更新宿舍使用情况图表失败", e);
        }
    }

    /**
     * 更新维修申请趋势图表
     */
    private void updateRepairTrendChart() {
        try {
            List<RepairApplication> repairs = repairController.getAllApplications();
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("维修申请数量");

            // 按状态统计维修申请
            Map<String, Integer> statusStats = new HashMap<>();
            for (RepairApplication repair : repairs) {
                String status = repair.getStatus();
                statusStats.put(status, statusStats.getOrDefault(status, 0) + 1);
            }

            // 添加不同状态的数据
            series.getData().add(new XYChart.Data<>("待受理", statusStats.getOrDefault("pending", 0)));
            series.getData().add(new XYChart.Data<>("已受理", statusStats.getOrDefault("accepted", 0)));
            series.getData().add(new XYChart.Data<>("维修中", statusStats.getOrDefault("repairing", 0)));
            series.getData().add(new XYChart.Data<>("已完成", statusStats.getOrDefault("completed", 0)));

            repairTrendChart.getData().clear();
            repairTrendChart.getData().add(series);
            repairTrendChart.setTitle("维修申请状态统计 (" + repairs.size() + "笔申请)");

        } catch (Exception e) {
            logger.error("更新维修申请趋势图表失败", e);
        }
    }

    /**
     * 更新缴费情况图表
     */
    private void updatePaymentChart() {
        try {
            List<org.dorm.model.entity.Payment> payments = paymentController.getAllPayments();
            int paidCount = 0;
            int unpaidCount = 0;

            for (org.dorm.model.entity.Payment payment : payments) {
                if ("已缴".equals(payment.getStatus())) {
                    paidCount++;
                } else {
                    unpaidCount++;
                }
            }

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
            if (paidCount > 0) {
                pieChartData.add(new PieChart.Data("已缴费 (" + paidCount + ")", paidCount));
            }
            if (unpaidCount > 0) {
                pieChartData.add(new PieChart.Data("未缴费 (" + unpaidCount + ")", unpaidCount));
            }

            paymentChart.setData(pieChartData);
            paymentChart.setTitle("缴费情况统计 (" + payments.size() + "笔记录)");

        } catch (Exception e) {
            logger.error("更新缴费情况图表失败", e);
        }
    }

    /**
     * 更新详细数据表格
     */
    private void updateDetailedTable() {
        try {
            tableData.clear();

            // 添加学生统计数据
            List<Student> students = studentController.getAllStudents();
            Map<String, Integer> collegeCount = new HashMap<>();
            for (Student student : students) {
                collegeCount.put(student.getCollegeName(), collegeCount.getOrDefault(student.getCollegeName(), 0) + 1);
            }

            int totalStudents = students.size();
            for (Map.Entry<String, Integer> entry : collegeCount.entrySet()) {
                double percentage = totalStudents > 0 ? (double) entry.getValue() / totalStudents * 100 : 0;
                tableData.add(new org.dorm.view.StatisticsData("学生", entry.getKey(), entry.getValue(), percentage, "正常"));
            }

            // 添加宿舍统计数据
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            for (Dormitory dorm : dormitories) {
                tableData.add(new org.dorm.view.StatisticsData("宿舍", dorm.getDormitoryId(), dorm.getTotalBeds(), 100.0, "启用"));
            }

            // 添加缴费统计数据
            try {
                List<org.dorm.model.entity.Payment> payments = paymentController.getAllPayments();
                int paidCount = 0;
                int unpaidCount = 0;
                for (org.dorm.model.entity.Payment payment : payments) {
                    if ("已缴".equals(payment.getStatus())) {
                        paidCount++;
                    } else {
                        unpaidCount++;
                    }
                }
                if (paidCount > 0) {
                    tableData.add(new org.dorm.view.StatisticsData("缴费", "已缴费", paidCount, 100.0 * paidCount / payments.size(), "正常"));
                }
                if (unpaidCount > 0) {
                    tableData.add(new org.dorm.view.StatisticsData("缴费", "未缴费", unpaidCount, 100.0 * unpaidCount / payments.size(), "待处理"));
                }
            } catch (Exception e) {
                logger.error("获取缴费统计数据失败", e);
            }

            // 添加违纪统计数据
            try {
                List<org.dorm.model.entity.Violation> violations = violationController.getAllViolations();
                Map<String, Integer> penaltyStats = new HashMap<>();
                for (org.dorm.model.entity.Violation violation : violations) {
                    String penalty = violation.getPenalty();
                    penaltyStats.put(penalty, penaltyStats.getOrDefault(penalty, 0) + 1);
                }

                for (Map.Entry<String, Integer> entry : penaltyStats.entrySet()) {
                    tableData.add(new org.dorm.view.StatisticsData("违纪", entry.getKey(), entry.getValue(), 100.0 * entry.getValue() / violations.size(), "已处理"));
                }
            } catch (Exception e) {
                logger.error("获取违纪统计数据失败", e);
            }

        } catch (Exception e) {
            logger.error("更新详细数据表格失败", e);
        }
    }

    /**
     * 更新最后更新时间
     */
    private void updateLastUpdateTime() {
        if (lastUpdateLabel != null) {
            String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            lastUpdateLabel.setText("最后更新: " + time);
        }
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        // 使用系统提示框显示消息
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 实现DataUpdateListener接口 - 处理数据更新通知
     */
    @Override
    public void onDataUpdated(String dataType, String operation) {
        logger.info("统计界面收到数据更新通知: {} - {}", dataType, operation);

        // 在JavaFX线程中刷新数据
        javafx.application.Platform.runLater(() -> {
            try {
                refreshStatistics();
                logger.info("统计数据已自动刷新");
            } catch (Exception e) {
                logger.error("自动刷新统计数据失败", e);
            }
        });
    }

    /**
     * 显示错误消息
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}