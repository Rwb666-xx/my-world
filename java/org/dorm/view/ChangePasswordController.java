package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.LoginController;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 修改密码对话框控制器
 */
public class ChangePasswordController {
    private static final Logger logger = LoggerFactory.getLogger(ChangePasswordController.class);

    @FXML
    private Label userInfoLabel;
    @FXML
    private PasswordField oldPasswordField;
    @FXML
    private PasswordField newPasswordField;
    @FXML
    private PasswordField confirmPasswordField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button confirmButton;
    @FXML
    private Button cancelButton;

    private LoginController loginController = new LoginController();
    private User currentUser;

    @FXML
    private void initialize() {
        logger.info("修改密码对话框初始化完成");
    }

    /**
     * 设置当前用户
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        if (user != null) {
            userInfoLabel.setText("当前用户：" + user.getUserId());
        }
    }

    /**
     * 处理确认按钮点击事件
     */
    @FXML
    private void handleConfirm(ActionEvent event) {
        if (currentUser == null) {
            showMessage("用户信息未设置");
            return;
        }

        // 获取输入数据
        String oldPassword = oldPasswordField.getText().trim();
        String newPassword = newPasswordField.getText().trim();
        String confirmPassword = confirmPasswordField.getText().trim();

        // 验证输入
        if (oldPassword.isEmpty()) {
            showMessage("请输入当前密码！");
            oldPasswordField.requestFocus();
            return;
        }

        if (newPassword.isEmpty()) {
            showMessage("请输入新密码！");
            newPasswordField.requestFocus();
            return;
        }

        if (newPassword.length() < 6 || newPassword.length() > 20) {
            showMessage("新密码长度必须在6-20位之间！");
            newPasswordField.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            showMessage("请确认新密码！");
            confirmPasswordField.requestFocus();
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            showMessage("两次输入的新密码不一致！");
            confirmPasswordField.clear();
            confirmPasswordField.requestFocus();
            return;
        }

        if (oldPassword.equals(newPassword)) {
            showMessage("新密码不能与当前密码相同！");
            newPasswordField.clear();
            confirmPasswordField.clear();
            newPasswordField.requestFocus();
            return;
        }

        // 禁用确认按钮
        confirmButton.setDisable(true);
        confirmButton.setText("修改中...");

        try {
            // 修改密码
            boolean result = loginController.changePassword(currentUser.getUserId(), oldPassword, newPassword);

            if (result) {
                showMessage("密码修改成功！");
                logger.info("密码修改成功：{}", currentUser.getUserId());

                // 延迟关闭对话框
                new Thread(() -> {
                    try {
                        Thread.sleep(1500);
                        javafx.application.Platform.runLater(this::closeDialog);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }).start();
            } else {
                showMessage("密码修改失败，请检查当前密码是否正确！");
                confirmButton.setDisable(false);
                confirmButton.setText("确认");
            }

        } catch (Exception e) {
            logger.error("修改密码过程中发生异常", e);
            showMessage("修改失败：" + e.getMessage());
            confirmButton.setDisable(false);
            confirmButton.setText("确认");
        }
    }

    /**
     * 处理取消按钮点击事件
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        logger.info("用户取消修改密码");
        closeDialog();
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * 关闭对话框
     */
    private void closeDialog() {
        Stage stage = (Stage) confirmButton.getScene().getWindow();
        stage.close();
    }
}