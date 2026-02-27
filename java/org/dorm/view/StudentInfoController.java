package org.dorm.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 学生个人信息界面控制器
 */
public class StudentInfoController {
    private static final Logger logger = LoggerFactory.getLogger(StudentInfoController.class);

    @FXML private Label studentIdLabel;
    @FXML private Label nameLabel;
    @FXML private Label genderLabel;
    @FXML private Label birthdayLabel;
    @FXML private Label collegeLabel;
    @FXML private Label classLabel;
    @FXML private Label emailLabel;
    @FXML private Label phoneLabel;
    @FXML private Label buildingLabel;
    @FXML private Label roomLabel;
    @FXML private Label bedLabel;
    @FXML private Label statusLabel;
    @FXML private Button editButton;
    @FXML private Button refreshButton;
    @FXML private Button backButton;

    private StudentController studentController = new StudentController();
    private User currentUser;
    private Student currentStudent;

    @FXML
    private void initialize() {
        logger.info("学生个人信息界面初始化完成");
    }

    /**
     * 设置当前用户
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        loadStudentInfo();
    }

    /**
     * 加载学生信息
     */
    private void loadStudentInfo() {
        if (currentUser == null) {
            showError("用户信息未设置");
            return;
        }

        try {
            // 获取学生信息
            currentStudent = studentController.getStudentById(currentUser.getUserId());

            if (currentStudent != null) {
                // 显示学生基本信息
                studentIdLabel.setText(currentStudent.getStudentId());
                nameLabel.setText(currentStudent.getName() != null ? currentStudent.getName() : "-");
                genderLabel.setText(currentStudent.getGender() != null ? currentStudent.getGender() : "-");
                birthdayLabel.setText(currentStudent.getBirthday() != null ?
                    new java.text.SimpleDateFormat("yyyy-MM-dd").format(currentStudent.getBirthday()) : "-");
                collegeLabel.setText(currentStudent.getCollegeName() != null ? currentStudent.getCollegeName() : "-");
                classLabel.setText(currentStudent.getClassName() != null ? currentStudent.getClassName() : "-");
                emailLabel.setText(currentStudent.getEmail() != null ? currentStudent.getEmail() : "-");
                phoneLabel.setText(currentStudent.getPhone() != null ? currentStudent.getPhone() : "-");

                // 显示住宿信息
                if (currentStudent.getBedId() != null && !currentStudent.getBedId().isEmpty()) {
                    // 解析床位ID，格式通常为 "文瀛1-101-01"
                    String bedId = currentStudent.getBedId();
                    String[] parts = bedId.split("-");
                    if (parts.length >= 3) {
                        // 重新组合宿舍楼名称
                        String buildingName = parts[0]; // 文瀛1
                        // 根据前缀确定完整的楼名
                        if (buildingName.startsWith("文瀛")) {
                            buildingLabel.setText("文瀛苑");
                        } else if (buildingName.startsWith("文韬")) {
                            buildingLabel.setText("文韬苑");
                        } else if (buildingName.startsWith("文澜")) {
                            buildingLabel.setText("文澜苑");
                        } else if (buildingName.startsWith("怡丁")) {
                            buildingLabel.setText("怡丁苑");
                        } else {
                            buildingLabel.setText(buildingName);
                        }

                        roomLabel.setText(parts[1]);     // 101
                        bedLabel.setText(parts[2]);      // 01
                    } else {
                        buildingLabel.setText("-");
                        roomLabel.setText("-");
                        bedLabel.setText(bedId);
                    }
                } else {
                    buildingLabel.setText("-");
                    roomLabel.setText("-");
                    bedLabel.setText("-");
                }

                statusLabel.setText("信息加载完成");
                logger.info("学生信息加载成功：{}", currentStudent.getStudentId());
            } else {
                showError("未找到学生信息，请联系管理员");
                clearAllFields();
            }

        } catch (Exception e) {
            logger.error("加载学生信息失败", e);
            showError("加载信息失败：" + e.getMessage());
            clearAllFields();
        }
    }

    /**
     * 清空所有字段
     */
    private void clearAllFields() {
        studentIdLabel.setText("-");
        nameLabel.setText("-");
        genderLabel.setText("-");
        birthdayLabel.setText("-");
        collegeLabel.setText("-");
        classLabel.setText("-");
        emailLabel.setText("-");
        phoneLabel.setText("-");
        buildingLabel.setText("-");
        roomLabel.setText("-");
        bedLabel.setText("-");
    }

    /**
     * 编辑信息
     */
    @FXML
    private void handleEdit(ActionEvent event) {
        if (currentStudent == null) {
            showError("没有可编辑的信息");
            return;
        }

        try {
            // 加载学生管理界面进行编辑
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/student_management.fxml"));
            Parent root = loader.load();

            StudentManagementController controller = loader.getController();
            controller.setStudentMode(currentUser.getUserId());

            Stage stage = new Stage();
            stage.setTitle("编辑个人信息");
            stage.setScene(new Scene(root, 1200, 700));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();

            // 编辑完成后刷新信息
            loadStudentInfo();

        } catch (IOException e) {
            logger.error("打开编辑界面失败", e);
            showError("打开编辑界面失败：" + e.getMessage());
        }
    }

    /**
     * 刷新信息
     */
    @FXML
    private void handleRefresh(ActionEvent event) {
        loadStudentInfo();
        statusLabel.setText("信息已刷新");
    }

    /**
     * 返回上一级
     */
    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        stage.close();
    }

    /**
     * 显示错误信息
     */
    private void showError(String message) {
        statusLabel.setText("错误：" + message);

        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText("操作失败");
        alert.setContentText(message);
        alert.showAndWait();
    }
}