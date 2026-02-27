package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 提交维修申请对话框控制器
 */
public class SubmitRepairController {
    private static final Logger logger = LoggerFactory.getLogger(SubmitRepairController.class);

    @FXML
    private TextField faultLocationField;
    @FXML
    private TextArea faultDescArea;
    @FXML
    private TextField contactPhoneField;
    @FXML
    private Label messageLabel;
    @FXML
    private Button submitButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Button viewApplicationsButton;

    private RepairApplicationController repairController = new RepairApplicationController();
    private User currentUser;

    @FXML
    private void initialize() {
        logger.info("提交维修申请对话框初始化完成");
    }

    /**
     * 设置当前用户
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
    }

    /**
     * 处理提交按钮点击事件
     */
    @FXML
    private void handleSubmit(ActionEvent event) {
        // 获取输入数据
        String faultLocation = faultLocationField.getText().trim();
        String faultDesc = faultDescArea.getText().trim();
        String contactPhone = contactPhoneField.getText().trim();

        // 验证输入
        if (faultLocation.isEmpty()) {
            showMessage("请输入故障位置！");
            faultLocationField.requestFocus();
            return;
        }

        if (faultDesc.isEmpty()) {
            showMessage("请输入故障描述！");
            faultDescArea.requestFocus();
            return;
        }

        if (contactPhone.isEmpty()) {
            showMessage("请输入联系电话！");
            contactPhoneField.requestFocus();
            return;
        }

        // 验证电话号码格式（简单验证）
        if (!contactPhone.matches("^1[3-9]\\d{9}$")) {
            showMessage("请输入正确的手机号码！");
            contactPhoneField.requestFocus();
            return;
        }

        // 创建维修申请对象
        RepairApplication application = new RepairApplication();
        application.setStudentId(currentUser.getUserId());
        application.setFaultLocation(faultLocation);
        application.setFaultDesc(faultDesc);
        application.setContactPhone(contactPhone);

        // 禁用提交按钮
        submitButton.setDisable(true);
        submitButton.setText("提交中...");

        try {
            // 提交维修申请
            boolean result = repairController.submitRepairApplication(application);

            if (result) {
                showMessage("维修申请提交成功！");
                logger.info("维修申请提交成功：{} - {}", currentUser.getUserId(), faultLocation);

                // 禁用所有输入控件，防止重复提交
                faultLocationField.setDisable(true);
                faultDescArea.setDisable(true);
                contactPhoneField.setDisable(true);
                submitButton.setDisable(true);
                submitButton.setText("提交成功");

                // 显示查看申请按钮
                viewApplicationsButton.setVisible(true);
                cancelButton.setText("返回");
                cancelButton.setOnAction(e -> returnToMain());
            } else {
                showMessage("维修申请提交失败，请稍后重试！");
                submitButton.setDisable(false);
                submitButton.setText("提交");
            }

        } catch (Exception e) {
            logger.error("提交维修申请过程中发生异常", e);
            showMessage("提交失败：" + e.getMessage());
            submitButton.setDisable(false);
            submitButton.setText("提交");
        }
    }

    /**
     * 处理取消按钮点击事件
     */
    @FXML
    private void handleCancel(ActionEvent event) {
        logger.info("用户取消提交维修申请");
        closeDialog();
    }

    /**
     * 处理查看申请按钮点击事件
     */
    @FXML
    private void handleViewApplications(ActionEvent event) {
        logger.info("用户点击查看申请按钮");
        showMessage("请点击左侧菜单的'我的维修申请'来查看您的申请记录");
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
    }

    /**
     * 返回主界面
     */
    private void returnToMain() {
        // 这里可以通知父控制器返回主界面
        // 由于这是加载在内容区域中的，我们可以通过事件通知或者直接操作
        logger.info("返回学生主界面");

        // 尝试通过场景查找父控制器并刷新内容
        // 这里我们简单地禁用界面，让用户知道操作已完成
        showMessage("操作完成，请返回主界面查看申请记录");
    }

    /**
     * 关闭对话框（已废弃，不再使用）
     */
    @Deprecated
    private void closeDialog() {
        // 这个方法不再使用，因为这个界面不是独立窗口
        logger.warn("closeDialog方法已被废弃，此界面不应关闭窗口");
    }
}