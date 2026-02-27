package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dorm.model.entity.User;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主界面控制器
 */
public class MainController implements DataUpdateManager.DataUpdateListener {
    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @FXML
    private Label titleLabel;
    @FXML
    private Label userInfoLabel;
    @FXML
    private Button logoutButton;

    // 统计数据标签
    @FXML
    private Label studentCount;
    @FXML
    private Label dormitoryCount;
    @FXML
    private Label bedCount;
    @FXML
    private Label repairCount;
    @FXML
    private Label reminderCount;
    @FXML
    private Label reminderTrend;

    @FXML
    private Button studentManageButton;
    @FXML
    private Button dormManageButton;
    @FXML
    private Button accommodationManageButton;
    @FXML
    private Button repairManageButton;
    @FXML
    private Button paymentManageButton;
    @FXML
    private Button violationManageButton;
    @FXML
    private Button myInfoButton;
    @FXML
    private Button statisticsButton;
    @FXML
    private Button reminderManageButton;
    @FXML
    private Button changePasswordButton;
    @FXML
    private VBox contentArea;

    // 图表组件
    @FXML
    private BarChart<String, Number> collegeChart;
    @FXML
    private PieChart dormitoryChart;

    private User currentUser;

    // 定时器，用于定期检查提醒
    private Timer reminderTimer;

    /**
     * 设置当前用户信息
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        updateUserInterface();
        showWelcomeMessage();
        initializeCharts();
    }

    /**
     * 初始化图表数据
     */
    private void initializeCharts() {
        try {
            // 从数据库获取实时统计数据
            updateStatisticsFromDatabase();

            // 初始化学院分布图表
            if (collegeChart != null) {
                updateCollegeChart();
            }

            // 初始化宿舍使用率饼图
            if (dormitoryChart != null) {
                updateDormitoryChart();
            }
        } catch (Exception e) {
            logger.error("初始化图表失败", e);
        }
    }

    /**
     * 从数据库获取统计数据
     */
    private void updateStatisticsFromDatabase() {
        try {
            // 按需创建Controller实例，避免在应用启动时就连接数据库
            org.dorm.controller.StudentController studentController = new org.dorm.controller.StudentController();
            org.dorm.controller.DormitoryController dormitoryController = new org.dorm.controller.DormitoryController();
            org.dorm.controller.BedController bedController = new org.dorm.controller.BedController();
            org.dorm.controller.RepairApplicationController repairController = new org.dorm.controller.RepairApplicationController();
            org.dorm.controller.ReminderController reminderController = new org.dorm.controller.ReminderController();

            // 从数据库获取实时统计数据
            int totalStudents = studentController.getAllStudents().size();
            int totalDormitories = dormitoryController.getAllDormitories().size();
            int totalBeds = bedController.getAllBeds().size();
            int totalRepairs = repairController.getAllApplications().size();
            int totalReminders = reminderController.getUnprocessedReminders().size();

            // 更新统计数字
            if (studentCount != null) studentCount.setText(String.valueOf(totalStudents));
            if (dormitoryCount != null) dormitoryCount.setText(String.valueOf(totalDormitories));
            if (bedCount != null) bedCount.setText(String.valueOf(totalBeds));
            if (repairCount != null) repairCount.setText(String.valueOf(totalRepairs));
            if (reminderCount != null) reminderCount.setText(String.valueOf(totalReminders));
            if (reminderTrend != null) {
                if (totalReminders > 0) {
                    reminderTrend.setText("有" + totalReminders + "条待处理");
                } else {
                    reminderTrend.setText("暂无待处理事项");
                }
            }

            logger.info("更新首页统计数据：学生{}人，宿舍{}栋，床位{}个，维修申请{}个，待处理提醒{}条",
                       totalStudents, totalDormitories, totalBeds, totalRepairs, totalReminders);

        } catch (Exception e) {
            logger.error("获取统计数据失败", e);
            // 设置默认值
            if (studentCount != null) studentCount.setText("0");
            if (dormitoryCount != null) dormitoryCount.setText("0");
            if (bedCount != null) bedCount.setText("0");
            if (repairCount != null) repairCount.setText("0");
        }
    }

    /**
     * 更新学院分布图表
     */
    private void updateCollegeChart() {
        if (collegeChart == null) return;

        try {
            collegeChart.getData().clear();

            XYChart.Series<String, Number> collegeSeries = new XYChart.Series<>();
            collegeSeries.setName("学生数量");

            // 按需创建Controller实例
            org.dorm.controller.StudentController studentController = new org.dorm.controller.StudentController();

            // 从数据库统计各学院学生数
            java.util.List<org.dorm.model.entity.Student> students = studentController.getAllStudents();
            java.util.Map<String, Integer> collegeStats = new java.util.HashMap<>();

            // 统计各学院学生数量
            for (org.dorm.model.entity.Student student : students) {
                String college = student.getCollegeName();
                if (college != null && !college.trim().isEmpty()) {
                    collegeStats.put(college, collegeStats.getOrDefault(college, 0) + 1);
                }
            }

            // 添加数据到图表
            for (java.util.Map.Entry<String, Integer> entry : collegeStats.entrySet()) {
                collegeSeries.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
            }

            if (!collegeSeries.getData().isEmpty()) {
                collegeChart.getData().add(collegeSeries);
            }

            logger.info("更新学院分布图表：{}个学院", collegeStats.size());

        } catch (Exception e) {
            logger.error("更新学院图表失败", e);
        }
    }

    /**
     * 更新宿舍使用率饼图
     */
    private void updateDormitoryChart() {
        if (dormitoryChart == null) return;

        try {
            dormitoryChart.getData().clear();

            // 按需创建Controller实例
            org.dorm.controller.DormitoryController dormitoryController = new org.dorm.controller.DormitoryController();
            org.dorm.controller.BedController bedController = new org.dorm.controller.BedController();

            // 从数据库获取宿舍和床位数据
            java.util.List<org.dorm.model.entity.Dormitory> dormitories = dormitoryController.getAllDormitories();
            java.util.List<org.dorm.model.entity.Bed> beds = bedController.getAllBeds();

            // 统计各宿舍楼的使用情况
            java.util.Map<String, Integer> buildingStats = new java.util.HashMap<>();
            int totalOccupied = 0;
            int totalAvailable = 0;

            // 统计每个宿舍楼的床位使用情况
            for (org.dorm.model.entity.Dormitory dorm : dormitories) {
                String building = dorm.getBuilding();
                if (building != null && !building.trim().isEmpty()) {
                    buildingStats.put(building, buildingStats.getOrDefault(building, 0) + dorm.getTotalBeds());
                }
            }

            // 统计占用和空闲床位
            for (org.dorm.model.entity.Bed bed : beds) {
                if ("占用".equals(bed.getStatus())) {
                    totalOccupied++;
                } else if ("空闲".equals(bed.getStatus())) {
                    totalAvailable++;
                }
            }

            // 添加数据到饼图
            if (totalOccupied > 0) {
                dormitoryChart.getData().add(new PieChart.Data("已占用床位 (" + totalOccupied + "个)", totalOccupied));
            }
            if (totalAvailable > 0) {
                dormitoryChart.getData().add(new PieChart.Data("空闲床位 (" + totalAvailable + "个)", totalAvailable));
            }

            // 如果没有具体数据，至少显示一个默认项
            if (dormitoryChart.getData().isEmpty()) {
                dormitoryChart.getData().add(new PieChart.Data("暂无数据", 1));
            }

            logger.info("更新宿舍使用情况图表：占用{}个，空闲{}个", totalOccupied, totalAvailable);

        } catch (Exception e) {
            logger.error("更新宿舍图表失败", e);
            // 添加错误数据
            dormitoryChart.getData().clear();
            dormitoryChart.getData().add(new PieChart.Data("数据加载失败", 1));
        }
    }

    @FXML
    private void initialize() {
        // 注册数据更新监听器
        DataUpdateManager.getInstance().addListener(this);

        // 启动提醒定时检查任务
        startReminderCheckTimer();

        logger.info("主界面控制器初始化完成，已注册数据更新监听器和提醒定时检查");
    }

    /**
     * 启动提醒定时检查任务
     * 每5分钟检查一次未缴费和未处理违纪记录
     */
    private void startReminderCheckTimer() {
        reminderTimer = new Timer("ReminderCheckTimer", true);

        // 立即执行一次检查
        reminderTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                checkAndGenerateReminders();
            }
        }, 0);

        // 每5分钟执行一次检查 (5 * 60 * 1000 毫秒)
        reminderTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                checkAndGenerateReminders();
            }
        }, 5 * 60 * 1000, 5 * 60 * 1000);

        logger.info("提醒定时检查任务已启动，每5分钟检查一次");
    }

    /**
     * 检查并生成提醒
     */
    private void checkAndGenerateReminders() {
        try {
            org.dorm.controller.ReminderController reminderController = new org.dorm.controller.ReminderController();

            // 检查未缴费学生
            int unpaidCount = reminderController.checkUnpaidStudents();

            // 检查未处理违纪记录
            int violationCount = reminderController.checkUnprocessedViolations();

            if (unpaidCount > 0 || violationCount > 0) {
                logger.info("定时检查完成，生成了{}条缴费提醒和{}条违纪提醒",
                           unpaidCount, violationCount);

                // 在JavaFX线程中更新UI
                javafx.application.Platform.runLater(() -> {
                    updateStatisticsFromDatabase();
                });
            }

        } catch (Exception e) {
            logger.error("定时检查提醒失败", e);
        }
    }

    /**
     * 停止定时器（在应用关闭时调用）
     */
    public void stopReminderTimer() {
        if (reminderTimer != null) {
            reminderTimer.cancel();
            reminderTimer.purge();
            logger.info("提醒定时检查任务已停止");
        }
    }

    /**
     * 实现DataUpdateListener接口 - 处理数据更新通知
     */
    @Override
    public void onDataUpdated(String dataType, String operation) {
        logger.info("收到数据更新通知: {} - {}", dataType, operation);

        // 根据数据类型刷新相关统计数据
        switch (dataType) {
            case "student":
            case "dormitory":
            case "payment":
            case "violation":
            case "repair":
                // 任何数据变更都可能影响统计数据，刷新所有统计
                updateStatisticsFromDatabase();
                updateCollegeChart();
                updateDormitoryChart();
                break;
            default:
                logger.warn("未知的数据类型: {}", dataType);
        }
    }


    /**
     * 更新用户界面显示
     */
    private void updateUserInterface() {
        if (currentUser != null) {
            String userType = currentUser.getUserType();
            String userTypeText = "admin".equals(userType) ? "管理员" : "student".equals(userType) ? "学生" : userType;
            userInfoLabel.setText("当前用户：" + currentUser.getUserId() + " (" + userTypeText + ")");

            // 根据用户类型显示不同的菜单
            if ("学生".equals(userType)) {
                // 学生可以看到维修管理、缴费管理、违纪管理、我的信息和修改密码
                studentManageButton.setVisible(false);
                dormManageButton.setVisible(false);
                accommodationManageButton.setVisible(false);
                statisticsButton.setVisible(false);
                repairManageButton.setVisible(true);
                paymentManageButton.setVisible(true);
                violationManageButton.setVisible(true);
                myInfoButton.setVisible(true);
                changePasswordButton.setVisible(true);
            } else {
                // 管理员可以看到所有功能
                studentManageButton.setVisible(true);
                dormManageButton.setVisible(true);
                accommodationManageButton.setVisible(true);
                statisticsButton.setVisible(true);
                repairManageButton.setVisible(true);
                paymentManageButton.setVisible(true);
                violationManageButton.setVisible(true);
                myInfoButton.setVisible(false);
                changePasswordButton.setVisible(true);
            }

            // 刷新首页统计数据
            updateStatisticsFromDatabase();
            updateCollegeChart();
            updateDormitoryChart();
        }
    }

    /**
     * 显示欢迎信息
     */
    private void showWelcomeMessage() {
        // 主页现在使用新的统计卡片布局，不需要显示欢迎文本
        logger.info("用户 {} 登录成功，显示主页", currentUser != null ? currentUser.getUserId() : "unknown");
    }

    /**
     * 处理退出登录
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        try {
            logger.info("用户退出登录：{}", currentUser != null ? currentUser.getUserId() : "未知用户");

            // 加载登录界面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // 获取当前舞台
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            // 设置新场景
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setTitle("宿舍管理系统");
            stage.centerOnScreen();

        } catch (IOException e) {
            logger.error("加载登录界面失败", e);
        }
    }

    /**
     * 显示学生管理界面
     */
    @FXML
    private void showStudentManagement(ActionEvent event) {
        loadContent("/fxml/student_management.fxml", "学生管理");
    }

    /**
     * 显示宿舍管理界面
     */
    @FXML
    private void showDormitoryManagement(ActionEvent event) {
        loadContent("/fxml/dormitory_management.fxml", "宿舍管理");
    }

    /**
     * 显示住宿分配界面
     */
    @FXML
    private void showAccommodationManagement(ActionEvent event) {
        loadContent("/fxml/accommodation_management.fxml", "住宿分配");
    }

    /**
     * 显示缴费管理界面
     */
    @FXML
    private void showPaymentManagement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/payment_management.fxml"));
            Parent root = loader.load();

            PaymentManagementController controller = loader.getController();

            // 如果是学生用户，设置为学生模式
            if ("学生".equals(currentUser.getUserType())) {
                controller.setStudentMode(currentUser.getUserId());
            }

            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

            logger.info("显示缴费管理界面");
        } catch (IOException e) {
            logger.error("加载缴费管理界面失败", e);
            showError("加载缴费管理界面失败：" + e.getMessage());
        }
    }


    /**
     * 显示违纪管理界面
     */
    @FXML
    private void showViolationManagement(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/violation_management.fxml"));
            Parent root = loader.load();

            ViolationManagementController controller = loader.getController();

            // 如果是学生用户，设置为学生模式
            if ("学生".equals(currentUser.getUserType())) {
                controller.setStudentMode(currentUser.getUserId());
            }

            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

            logger.info("显示违纪管理界面");
        } catch (IOException e) {
            logger.error("加载违纪管理界面失败", e);
            showError("加载违纪管理界面失败：" + e.getMessage());
        }
    }

    /**
     * 显示维修管理界面
     */
    @FXML
    private void showRepairManagement(ActionEvent event) {
        if ("student".equals(currentUser.getUserType())) {
            loadContent("/fxml/student_repair.fxml", "我的维修申请");
        } else {
            loadContent("/fxml/admin_repair.fxml", "维修管理");
        }
    }

    /**
     * 显示统计查询界面
     */
    @FXML
    private void showStatistics(ActionEvent event) {
        loadContent("/fxml/statistics.fxml", "统计查询");
    }

    /**
     * 显示提醒管理界面
     */
    @FXML
    private void showReminderManagement(ActionEvent event) {
        loadContent("/fxml/reminder_management_new.fxml", "提醒管理");
    }

    /**
     * 显示我的信息界面
     */
    @FXML
    private void showMyInfo(ActionEvent event) {
        try {
            // 加载学生个人信息界面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student_info.fxml"));
            Parent root = loader.load();

            StudentInfoController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            contentArea.getChildren().clear();
            contentArea.getChildren().add(root);

            logger.info("显示我的信息界面");

        } catch (IOException e) {
            logger.error("加载我的信息界面失败", e);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误");
            alert.setHeaderText("加载我的信息界面失败");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }

    /**
     * 显示修改密码界面
     */
    @FXML
    private void showChangePassword(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/change_password.fxml"));
            Parent root = loader.load();

            // 获取修改密码控制器并传递当前用户信息
            ChangePasswordController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("修改密码");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.showAndWait();

        } catch (IOException e) {
            logger.error("加载修改密码界面失败", e);
        }
    }



    /**
     * 加载内容区域
     */
    private void loadContent(String fxmlPath, String title) {
        try {
            // #region agent log
            logDebugEvent("MainController.java:514", "开始加载界面", java.util.Map.of("fxmlPath", fxmlPath, "title", title), "A");
            // #endregion

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();

            // #region agent log
            logDebugEvent("MainController.java:521", "FXML加载成功", java.util.Map.of("fxmlPath", fxmlPath, "controller", loader.getController().getClass().getSimpleName()), "A");
            // #endregion

            // JavaFX会自动调用@FXML注解的initialize方法，无需手动调用

            contentArea.getChildren().clear();
            contentArea.getChildren().add(content);

            // #region agent log
            logDebugEvent("MainController.java:528", "界面加载完成", java.util.Map.of("title", title), "A");
            // #endregion

            logger.info("成功加载界面：{}", title);

        } catch (IOException e) {
            // #region agent log
            logDebugEvent("MainController.java:535", "加载界面失败", java.util.Map.of("fxmlPath", fxmlPath, "error", e.getMessage()), "A");
            // #endregion

            logger.error("加载界面失败：{}", fxmlPath, e);
            showError("加载界面失败：" + e.getMessage());
        }
    }

    /**
     * 记录调试事件
     */
    private void logDebugEvent(String location, String message, java.util.Map<String, Object> data, String hypothesisId) {
        try {
            String logEntry = String.format("{\"id\":\"log_%d_%s\",\"timestamp\":%d,\"location\":\"%s\",\"message\":\"%s\",\"data\":%s,\"sessionId\":\"debug-session\",\"runId\":\"initial-test\",\"hypothesisId\":\"%s\"}",
                System.currentTimeMillis(),
                java.util.UUID.randomUUID().toString().substring(0, 6),
                System.currentTimeMillis(),
                location,
                message,
                data.toString().replace("=", "\":\"").replace("{", "{\"").replace("}", "\"}").replace(", ", "\",\""),
                hypothesisId
            );
            java.nio.file.Files.writeString(
                java.nio.file.Paths.get("c:\\Users\\任万博\\Desktop\\课设\\dormitory-management-system\\.cursor\\debug.log"),
                logEntry + "\n",
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            logger.warn("记录调试日志失败", e);
        }
    }

    /**
     * 显示错误信息
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText("操作失败");
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 快速操作：添加学生
     */
    @FXML
    private void showAddStudent(ActionEvent event) {
        showStudentManagement(event);
    }

    /**
     * 快速操作：添加宿舍
     */
    @FXML
    private void showAddDormitory(ActionEvent event) {
        showDormitoryManagement(event);
    }

    /**
     * 快速操作：维修申请
     */
    @FXML
    private void showRepairApplication(ActionEvent event) {
        showRepairManagement(event);
    }

    /**
     * 快速操作：查看报表
     */
}