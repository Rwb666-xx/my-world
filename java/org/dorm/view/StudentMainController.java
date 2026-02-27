package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 学生主界面控制器
 * 专为学生用户设计的简化界面，只显示学生相关的功能
 */
public class StudentMainController {
    private static final Logger logger = LoggerFactory.getLogger(StudentMainController.class);

    @FXML private Label titleLabel;
    @FXML private Label userInfoLabel;
    @FXML private Label welcomeLabel;
    @FXML private Button logoutButton;
    @FXML private Button myInfoButton;
    @FXML private Button submitRepairButton;
    @FXML private Button myRepairButton;
    @FXML private Button myPaymentButton;
    @FXML private Button myViolationButton;
    @FXML private Button changePasswordButton;
    @FXML private VBox contentArea;

    private User currentUser;

    @FXML
    private void initialize() {
        logger.info("学生主界面初始化完成");
    }

    /**
     * 设置当前用户
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;

        // 更新用户信息显示
        String userTypeText = "admin".equals(user.getUserType()) ? "管理员" : "student".equals(user.getUserType()) ? "学生" : user.getUserType();
        userInfoLabel.setText("当前用户：" + currentUser.getUserId() + " (" + userTypeText + ")");

        // 更新欢迎信息
        welcomeLabel.setText("欢迎您，" + currentUser.getUserId() + "！");

        logger.info("设置当前学生用户：{}", currentUser.getUserId());
    }

    /**
     * 加载内容到主界面
     */
    private void loadContent(String fxmlPath, String title) {
        try {
            logger.info("正在加载界面：{} - {}", title, fxmlPath);

            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent content = loader.load();

            // 如果控制器有setCurrentUser方法，则设置用户信息
            if (loader.getController() instanceof StudentInfoController) {
                ((StudentInfoController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof SubmitRepairController) {
                ((SubmitRepairController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof StudentRepairController) {
                ((StudentRepairController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof ChangePasswordController) {
                ((ChangePasswordController) loader.getController()).setCurrentUser(currentUser);
            } else if (loader.getController() instanceof PaymentManagementController) {
                // 为缴费管理设置学生模式，只显示当前学生的缴费记录
                ((PaymentManagementController) loader.getController()).setStudentMode(currentUser.getUserId());
            } else if (loader.getController() instanceof ViolationManagementController) {
                // 为违纪管理设置学生模式，只显示当前学生的违纪记录
                ((ViolationManagementController) loader.getController()).setStudentMode(currentUser.getUserId());
            }

            // 清空内容区域并添加新内容
            contentArea.getChildren().clear();
            contentArea.getChildren().add(content);

            logger.info("界面加载成功：{}", title);
        } catch (IOException e) {
            logger.error("加载界面失败：{}", fxmlPath, e);
        }
    }

    /**
     * 显示我的信息
     */
    @FXML
    private void showStudentMyInfo(ActionEvent event) {
        logger.info("显示我的信息");
        loadContent("/fxml/student_info.fxml", "我的信息");
    }

    /**
     * 显示提交维修申请界面
     */
    @FXML
    private void showStudentSubmitRepair(ActionEvent event) {
        logger.info("显示提交维修申请");
        loadContent("/fxml/submit_repair.fxml", "提交维修申请");
    }

    /**
     * 显示我的维修申请
     */
    @FXML
    private void showStudentMyRepair(ActionEvent event) {
        logger.info("显示我的维修申请");
        loadContent("/fxml/student_repair.fxml", "我的维修申请");
    }

    /**
     * 显示我的缴费记录
     */
    @FXML
    private void showStudentMyPayment(ActionEvent event) {
        logger.info("显示我的缴费记录");
        loadContent("/fxml/payment_management.fxml", "我的缴费记录");
    }

    /**
     * 显示我的违纪记录
     */
    @FXML
    private void showStudentMyViolation(ActionEvent event) {
        logger.info("显示我的违纪记录");
        loadContent("/fxml/violation_management.fxml", "我的违纪记录");
    }

    /**
     * 显示修改密码界面
     */
    @FXML
    private void showStudentChangePassword(ActionEvent event) {
        logger.info("显示修改密码");
        loadContent("/fxml/change_password.fxml", "修改密码");
    }

    /**
     * 处理退出登录
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        logger.info("退出登录");
        try {
            // 加载登录界面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // 获取当前舞台
            Stage stage = (Stage) logoutButton.getScene().getWindow();

            // 设置新场景
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.setTitle("宿舍管理系统 - 登录");
            stage.centerOnScreen();

            logger.info("成功退出并返回登录界面");
        } catch (IOException e) {
            logger.error("加载登录界面失败", e);
        }
    }
}