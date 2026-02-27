package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.RepairApplication;
import org.dorm.model.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 学生维修申请界面控制器
 */
public class StudentRepairController {
    private static final Logger logger = LoggerFactory.getLogger(StudentRepairController.class);

    @FXML
    private Button submitButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<RepairApplication> repairTable;
    @FXML
    private TableColumn<RepairApplication, String> applyIdColumn;
    @FXML
    private TableColumn<RepairApplication, String> faultLocationColumn;
    @FXML
    private TableColumn<RepairApplication, String> faultDescColumn;
    @FXML
    private TableColumn<RepairApplication, String> applyTimeColumn;
    @FXML
    private TableColumn<RepairApplication, String> statusColumn;
    @FXML
    private TableColumn<RepairApplication, String> handlerColumn;
    @FXML
    private TableColumn<RepairApplication, String> finishTimeColumn;

    private RepairApplicationController repairController = new RepairApplicationController();
    private User currentUser;
    private ObservableList<RepairApplication> repairData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 初始化表格列
        applyIdColumn.setCellValueFactory(new PropertyValueFactory<>("applyId"));
        faultLocationColumn.setCellValueFactory(new PropertyValueFactory<>("faultLocation"));
        faultDescColumn.setCellValueFactory(new PropertyValueFactory<>("faultDesc"));
        applyTimeColumn.setCellValueFactory(cellData -> {
            RepairApplication app = cellData.getValue();
            String timeStr = app.getApplyTime() != null ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(app.getApplyTime()) : "";
            return new javafx.beans.property.SimpleStringProperty(timeStr);
        });
        statusColumn.setCellValueFactory(cellData -> {
            String status = cellData.getValue().getStatus();
            // 将英文状态转换为中文显示
            String displayStatus;
            switch (status) {
                case "pending":
                    displayStatus = "待受理";
                    break;
                case "accepted":
                    displayStatus = "已受理";
                    break;
                case "repairing":
                    displayStatus = "维修中";
                    break;
                case "completed":
                    displayStatus = "已完成";
                    break;
                default:
                    displayStatus = status;
                    break;
            }
            return new javafx.beans.property.SimpleStringProperty(displayStatus);
        });
        handlerColumn.setCellValueFactory(new PropertyValueFactory<>("handler"));
        finishTimeColumn.setCellValueFactory(cellData -> {
            RepairApplication app = cellData.getValue();
            String timeStr = app.getFinishTime() != null ?
                new SimpleDateFormat("yyyy-MM-dd HH:mm").format(app.getFinishTime()) : "";
            return new javafx.beans.property.SimpleStringProperty(timeStr);
        });

        // 设置表格数据
        repairTable.setItems(repairData);

        logger.info("学生维修申请界面初始化完成");
    }

    /**
     * 设置当前用户
     */
    public void setCurrentUser(User user) {
        this.currentUser = user;
        loadRepairApplications();
    }

    /**
     * 加载维修申请数据
     */
    private void loadRepairApplications() {
        if (currentUser == null) {
            showMessage("用户信息未设置");
            return;
        }

        try {
            List<RepairApplication> applications = repairController.getStudentApplications(currentUser.getUserId());
            repairData.clear();
            repairData.addAll(applications);

            statusLabel.setText("共" + applications.size() + "条记录");
            showMessage("数据加载完成");

            logger.info("加载学生维修申请数据成功：{}条记录", applications.size());

        } catch (Exception e) {
            logger.error("加载维修申请数据失败", e);
            showMessage("加载数据失败：" + e.getMessage());
        }
    }

    /**
     * 显示提交维修申请对话框
     */
    @FXML
    private void showSubmitDialog(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/submit_repair.fxml"));
            Parent root = loader.load();

            // 获取提交维修申请控制器并传递当前用户信息
            SubmitRepairController controller = loader.getController();
            controller.setCurrentUser(currentUser);

            Stage stage = new Stage();
            stage.setTitle("提交维修申请");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);

            // 设置对话框关闭后的回调
            stage.setOnHidden(e -> loadRepairApplications());

            stage.showAndWait();

        } catch (IOException e) {
            logger.error("加载提交维修申请界面失败", e);
            showMessage("打开提交界面失败");
        }
    }

    /**
     * 刷新维修申请列表
     */
    @FXML
    private void refreshApplications(ActionEvent event) {
        loadRepairApplications();
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        messageLabel.setText(message);
        // 3秒后清除消息
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> messageLabel.setText(""));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}