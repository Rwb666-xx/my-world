package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.dorm.controller.PaymentController;
import org.dorm.controller.StudentController;
import org.dorm.controller.ViolationController;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Payment;
import org.dorm.model.entity.Violation;
import org.dorm.model.entity.RepairApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

/**
 * 学生个人中心控制器
 * 显示学生的缴费情况、违纪情况、维修申请、个人信息等
 */
public class StudentDashboardController {
    private static final Logger logger = LoggerFactory.getLogger(StudentDashboardController.class);

    // 缴费信息
    @FXML private Label pendingPaymentLabel;
    @FXML private Label currentMonthPaymentLabel;
    @FXML private Label totalPaymentLabel;

    // 违纪信息
    @FXML private Label violationCountLabel;
    @FXML private Label latestViolationLabel;
    @FXML private Label violationStatusLabel;

    // 维修信息
    @FXML private Label pendingRepairLabel;
    @FXML private Label inProgressRepairLabel;
    @FXML private Label completedRepairLabel;

    // 个人信息
    @FXML private Label studentIdLabel;
    @FXML private Label nameLabel;
    @FXML private Label collegeLabel;
    @FXML private Label classLabel;
    @FXML private Label genderLabel;
    @FXML private Label bedIdLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;

    private String currentStudentId;
    private StudentController studentController;
    private PaymentController paymentController;
    private ViolationController violationController;
    private RepairApplicationController repairController;

    @FXML
    private void initialize() {
        logger.info("学生个人中心初始化");

        // 初始化控制器
        this.studentController = new StudentController();
        this.paymentController = new PaymentController();
        this.violationController = new ViolationController();
        this.repairController = new RepairApplicationController();

        // 显示默认值
        showDefaultValues();

        // 如果有学生ID，加载数据
        if (currentStudentId != null) {
            loadStudentData();
        }
    }

    /**
     * 显示默认值
     */
    private void showDefaultValues() {
        // 缴费信息
        pendingPaymentLabel.setText("¥0.00");
        currentMonthPaymentLabel.setText("¥0.00");
        totalPaymentLabel.setText("¥0.00");

        // 违纪信息
        violationCountLabel.setText("0次");
        latestViolationLabel.setText("无");
        violationStatusLabel.setText("良好");

        // 维修信息
        pendingRepairLabel.setText("0个");
        inProgressRepairLabel.setText("0个");
        completedRepairLabel.setText("0个");

        // 个人信息
        studentIdLabel.setText("未登录");
        nameLabel.setText("未登录");
        collegeLabel.setText("未设置");
        classLabel.setText("未设置");
        genderLabel.setText("未设置");
        bedIdLabel.setText("未分配");
        emailLabel.setText("未填写");
        phoneLabel.setText("未填写");
    }

    /**
     * 设置当前学生ID
     */
    public void setCurrentStudentId(String studentId) {
        this.currentStudentId = studentId;
        logger.info("设置学生ID: {}", studentId);

        // 确保控制器已初始化
        if (studentController == null) {
            this.studentController = new StudentController();
            this.paymentController = new PaymentController();
            this.violationController = new ViolationController();
            this.repairController = new RepairApplicationController();
        }

        // 加载学生数据
        loadStudentData();
    }

    /**
     * 加载学生数据
     */
    private void loadStudentData() {
        try {
            logger.info("加载学生数据：{}", currentStudentId);

            // 加载个人信息
            loadStudentInfo();

            // 加载缴费信息
            loadPaymentInfo();

            // 加载违纪信息
            loadViolationInfo();

            // 加载维修信息
            loadRepairInfo();

            logger.info("学生数据加载完成：{}", currentStudentId);

        } catch (Exception e) {
            logger.error("加载学生数据失败：{}", currentStudentId, e);
        }
    }

    /**
     * 加载个人信息
     */
    private void loadStudentInfo() {
        try {
            Student student = studentController.getStudentById(currentStudentId);
            if (student != null) {
                studentIdLabel.setText(student.getStudentId());
                nameLabel.setText(student.getName());
                collegeLabel.setText(student.getCollegeName());
                classLabel.setText(student.getClassName());
                genderLabel.setText("M".equals(student.getGender()) ? "男" : "F".equals(student.getGender()) ? "女" : student.getGender());
                bedIdLabel.setText(student.getBedId() != null ? student.getBedId() : "未分配");
                emailLabel.setText(student.getEmail() != null ? student.getEmail() : "未填写");
                phoneLabel.setText(student.getPhone() != null ? student.getPhone() : "未填写");
            }
        } catch (Exception e) {
            logger.error("加载学生信息失败", e);
        }
    }

    /**
     * 加载缴费信息
     */
    private void loadPaymentInfo() {
        try {
            // 获取所有缴费记录
            List<Payment> payments = paymentController.getPaymentsByStudentId(currentStudentId);

            BigDecimal pendingAmount = BigDecimal.ZERO;
            BigDecimal currentMonthAmount = BigDecimal.ZERO;
            BigDecimal totalAmount = BigDecimal.ZERO;

            LocalDate now = LocalDate.now();
            for (Payment payment : payments) {
                BigDecimal amount = payment.getAmount();
                if (amount != null) {
                    totalAmount = totalAmount.add(amount);

                    // 计算本月缴费
                    if (payment.getPaymentDate() != null) {
                        LocalDate paymentDate = payment.getPaymentDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                        if (paymentDate.getYear() == now.getYear() && paymentDate.getMonth() == now.getMonth()) {
                            currentMonthAmount = currentMonthAmount.add(amount);
                        }
                    }

                    // 计算待缴费（假设未缴状态为待缴费）
                    if ("未缴".equals(payment.getStatus())) {
                        pendingAmount = pendingAmount.add(amount);
                    }
                }
            }

            pendingPaymentLabel.setText("¥" + pendingAmount.toString());
            currentMonthPaymentLabel.setText("¥" + currentMonthAmount.toString());
            totalPaymentLabel.setText("¥" + totalAmount.toString());

        } catch (Exception e) {
            logger.error("加载缴费信息失败", e);
            pendingPaymentLabel.setText("¥0.00");
            currentMonthPaymentLabel.setText("¥0.00");
            totalPaymentLabel.setText("¥0.00");
        }
    }

    /**
     * 加载违纪信息
     */
    private void loadViolationInfo() {
        try {
            List<Violation> violations = violationController.getViolationsByStudentId(currentStudentId);

            violationCountLabel.setText(violations.size() + "次");

            if (!violations.isEmpty()) {
                // 找到最近的违纪记录
                Violation latest = violations.stream()
                    .filter(v -> v.getViolationDate() != null)
                    .max((v1, v2) -> v1.getViolationDate().compareTo(v2.getViolationDate()))
                    .orElse(violations.get(0));

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                latestViolationLabel.setText(sdf.format(latest.getViolationDate()));

                // 根据违纪次数设置状态
                if (violations.size() >= 3) {
                    violationStatusLabel.setText("严重");
                    violationStatusLabel.getStyleClass().add("danger");
                } else if (violations.size() >= 1) {
                    violationStatusLabel.setText("一般");
                    violationStatusLabel.getStyleClass().add("warning");
                } else {
                    violationStatusLabel.setText("良好");
                    violationStatusLabel.getStyleClass().add("good");
                }
            } else {
                latestViolationLabel.setText("无");
                violationStatusLabel.setText("良好");
                violationStatusLabel.getStyleClass().add("good");
            }

        } catch (Exception e) {
            logger.error("加载违纪信息失败", e);
            violationCountLabel.setText("0次");
            latestViolationLabel.setText("无");
            violationStatusLabel.setText("良好");
        }
    }

    /**
     * 加载维修信息
     */
    private void loadRepairInfo() {
        try {
            List<RepairApplication> repairs = repairController.getStudentApplications(currentStudentId);

            int pendingCount = 0;
            int inProgressCount = 0;
            int completedCount = 0;

            for (RepairApplication repair : repairs) {
                switch (repair.getStatus()) {
                    case "待处理":
                        pendingCount++;
                        break;
                    case "进行中":
                        inProgressCount++;
                        break;
                    case "已完成":
                        completedCount++;
                        break;
                }
            }

            pendingRepairLabel.setText(pendingCount + "个");
            inProgressRepairLabel.setText(inProgressCount + "个");
            completedRepairLabel.setText(completedCount + "个");

        } catch (Exception e) {
            logger.error("加载维修信息失败", e);
            pendingRepairLabel.setText("0个");
            inProgressRepairLabel.setText("0个");
            completedRepairLabel.setText("0个");
        }
    }

    // 事件处理方法
    @FXML
    private void showPaymentDetails(ActionEvent event) {
        // 这里可以导航到缴费详情页面
        logger.info("查看缴费详情");
    }

    @FXML
    private void showViolationDetails(ActionEvent event) {
        // 这里可以导航到违纪详情页面
        logger.info("查看违纪详情");
    }

    @FXML
    private void showRepairDetails(ActionEvent event) {
        // 这里可以导航到维修详情页面
        logger.info("查看维修详情");
    }

    @FXML
    private void showEditProfile(ActionEvent event) {
        // 这里可以导航到修改个人信息页面
        logger.info("修改个人信息");
    }
}