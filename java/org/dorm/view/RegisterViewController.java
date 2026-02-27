package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.UserController;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.User;
import org.dorm.model.entity.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 注册界面控制器
 */
public class RegisterViewController {
    private static final Logger logger = LoggerFactory.getLogger(RegisterViewController.class);

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private RadioButton adminRadioButton;
    @FXML
    private RadioButton studentRadioButton;
    @FXML
    private ToggleGroup userTypeGroup;
    @FXML
    private Button registerButton;
    @FXML
    private Button backButton;
    @FXML
    private Label messageLabel;

    private UserController userController = new UserController();
    private StudentController studentController = new StudentController();

    @FXML
    private void initialize() {
        // 设置默认焦点
        usernameField.requestFocus();
    }

    /**
     * 处理注册按钮点击事件
     */
    @FXML
    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // 验证输入
        if (username.isEmpty()) {
            showMessage("请输入用户名！");
            usernameField.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            showMessage("请输入密码！");
            passwordField.requestFocus();
            return;
        }

        if (password.length() < 6) {
            showMessage("密码长度至少6位！");
            passwordField.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            showMessage("两次输入的密码不一致！");
            confirmPasswordField.clear();
            confirmPasswordField.requestFocus();
            return;
        }

        String userType = getSelectedUserType();

        // 禁用注册按钮，显示注册中
        registerButton.setDisable(true);
        registerButton.setText("创建中...");
        messageLabel.setText("");

        try {
            // 创建新用户
            User newUser = new User();
            newUser.setUserId(username);
            newUser.setPassword(password);
            newUser.setUserType(userType);

            // 调用业务逻辑进行注册
            boolean success = userController.addUser(newUser);

            if (success) {
                // 如果是学生用户，同时创建学生记录
                if ("学生".equals(userType)) {
                    try {
                        Student newStudent = new Student();
                        newStudent.setStudentId(username);
                        newStudent.setPassword(password);
                        newStudent.setName(""); // 姓名暂时为空，需要后续完善信息
                        newStudent.setCollegeName(""); // 学院暂时为空
                        newStudent.setClassName(""); // 班级暂时为空
                        newStudent.setGender(""); // 性别暂时为空
                        newStudent.setEmail(username + "@nuc.edu.cn"); // 默认邮箱
                        newStudent.setPhone(""); // 电话暂时为空
                        // bedId暂时为空，需要后续分配

                        boolean studentSuccess = studentController.addStudent(newStudent);
                        if (!studentSuccess) {
                            logger.warn("学生记录创建失败，但用户账户已创建：{}", username);
                            // 用户账户已创建，这里不影响注册成功
                        }
                    } catch (Exception e) {
                        logger.error("创建学生记录时发生异常，但用户账户已创建：{}", username, e);
                        // 用户账户已创建，这里不影响注册成功
                    }
                }

                logger.info("注册成功：{} ({})", username, userType);
                showMessage("账号创建成功！请返回登录页面使用新账号登录。" + ("学生".equals(userType) ? "\n请在登录后完善您的个人信息。" : ""));

                // 清空表单
                usernameField.clear();
                passwordField.clear();
                confirmPasswordField.clear();
            } else {
                showMessage("注册失败，用户名可能已存在！");
                usernameField.requestFocus();
            }
        } catch (Exception e) {
            logger.error("注册过程中发生异常", e);
            showMessage("注册失败，请稍后重试！");
        } finally {
            // 恢复注册按钮
            registerButton.setDisable(false);
            registerButton.setText("创建账号");
        }
    }

    /**
     * 返回登录页面
     */
    @FXML
    private void backToLogin(ActionEvent event) {
        try {
            // 加载登录界面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            // 获取当前舞台
            Stage stage = (Stage) backButton.getScene().getWindow();

            // 设置新场景
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.setTitle("宿舍管理系统 - 登录");
            stage.centerOnScreen();

            logger.info("返回登录页面");
        } catch (IOException e) {
            logger.error("加载登录界面失败", e);
            showMessage("返回登录页面失败！");
        }
    }

    /**
     * 获取选中的用户类型
     */
    private String getSelectedUserType() {
        if (adminRadioButton != null && adminRadioButton.isSelected()) {
            return "管理员";
        } else if (studentRadioButton != null && studentRadioButton.isSelected()) {
            return "学生";
        }
        return "管理员"; // 默认选择管理员
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
        // 添加样式类
        messageLabel.getStyleClass().removeAll("success", "error", "warning");
        if (message.contains("成功")) {
            messageLabel.getStyleClass().add("success");
        } else {
            messageLabel.getStyleClass().add("error");
        }
    }
}