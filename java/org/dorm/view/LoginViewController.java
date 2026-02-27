package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.LoginController;
import org.dorm.controller.UserController;
import org.dorm.model.entity.User;
import org.dorm.view.StudentMainController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 登录界面控制器
 */
public class LoginViewController {
    private static final Logger logger = LoggerFactory.getLogger(LoginViewController.class);

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button exitButton;
    @FXML
    private Label messageLabel;

    private org.dorm.controller.LoginController loginBizController = new org.dorm.controller.LoginController();

    @FXML
    private void initialize() {
        // 设置默认焦点
        usernameField.requestFocus();
    }

    /**
     * 处理登录按钮点击事件
     */
    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

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

        // 禁用登录按钮，显示登录中
        loginButton.setDisable(true);
        loginButton.setText("登录中...");
        messageLabel.setText("");

        try {
            // 调用业务逻辑进行登录验证
            User user = loginBizController.login(username, password);

            if (user != null) {
                logger.info("登录成功，跳转到主界面：{} ({})", username, user.getUserType());
                showMessage("登录成功！");

                // 跳转到主界面
                switchToMainView(user);
            } else {
                showMessage("用户名或密码错误！");
                passwordField.clear();
                passwordField.requestFocus();
            }
        } catch (Exception e) {
            logger.error("登录过程中发生异常", e);
            showMessage("登录失败，请稍后重试！");
        } finally {
            // 恢复登录按钮
            loginButton.setDisable(false);
            loginButton.setText("登录");
        }
    }

    /**
     * 显示注册页面
     */
    @FXML
    private void showRegisterPage(ActionEvent event) {
        try {
            // 加载注册界面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/register.fxml"));
            Parent root = loader.load();

            // 获取当前舞台
            Stage stage = (Stage) registerButton.getScene().getWindow();

            // 设置新场景
            Scene scene = new Scene(root, 1200, 800);
            stage.setScene(scene);
            stage.setTitle("宿舍管理系统 - 注册");
            stage.centerOnScreen();

            logger.info("跳转到注册页面");
        } catch (IOException e) {
            logger.error("加载注册界面失败", e);
            showMessage("加载注册页面失败！");
        }
    }

    /**
     * 处理退出按钮点击事件
     */
    @FXML
    private void handleExit(ActionEvent event) {
        logger.info("用户退出系统");
        System.exit(0);
    }


    /**
     * 显示消息
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * 切换到主界面
     */
    private void switchToMainView(User user) {
        try {
            Parent root;
            String fxmlPath;
            Object controller;

            // 根据用户类型加载不同的界面
            logger.info("准备加载界面，用户类型：'{}'", user.getUserType());
            if ("学生".equals(user.getUserType()) || "ѧ��".equals(user.getUserType())) {
                // 学生界面
                fxmlPath = "/fxml/student_main.fxml";
                logger.info("正在加载学生界面：{}", fxmlPath);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                root = loader.load();
                controller = loader.getController();
                logger.info("学生界面加载成功，开始设置用户：{}", user.getUserId());
                ((StudentMainController) controller).setCurrentUser(user);
                logger.info("学生界面设置完成");
            } else {
                // 管理员界面
                fxmlPath = "/fxml/main.fxml";
                logger.info("正在加载管理员界面：{}", fxmlPath);
                FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
                root = loader.load();
                controller = loader.getController();
                ((MainController) controller).setCurrentUser(user);
                logger.info("管理员界面设置完成：{}", user.getUserId());
            }

            // 获取当前舞台
            Stage stage = (Stage) loginButton.getScene().getWindow();

            // 设置新场景
            Scene scene = new Scene(root, 1400, 900); // 设置更大的主界面尺寸
            stage.setScene(scene);
            stage.setTitle("宿舍管理系统 - 主界面");
            stage.setMinWidth(1200); // 设置最小宽度
            stage.setMinHeight(800); // 设置最小高度
            stage.centerOnScreen();

            logger.info("成功切换到主界面");
        } catch (IOException e) {
            logger.error("加载主界面失败", e);
            showMessage("加载主界面失败，请重启程序！");
        }
    }
}