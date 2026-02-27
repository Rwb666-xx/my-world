package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.dorm.controller.RepairApplicationController;
import org.dorm.model.entity.RepairApplication;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 管理员维修管理界面控制器
 */
public class AdminRepairController {
    private static final Logger logger = LoggerFactory.getLogger(AdminRepairController.class);

    @FXML
    private Button acceptButton;
    @FXML
    private Button completeButton;
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
    private TableColumn<RepairApplication, String> studentIdColumn;
    @FXML
    private TableColumn<RepairApplication, String> studentNameColumn;
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
    private ObservableList<RepairApplication> repairData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 初始化表格列
        applyIdColumn.setCellValueFactory(new PropertyValueFactory<>("applyId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty("未知"));
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

        // 添加状态转换方法
        repairTable.setRowFactory(tv -> new TableRow<RepairApplication>() {
            @Override
            protected void updateItem(RepairApplication item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    // 根据状态设置行样式
                    switch (item.getStatus()) {
                        case "pending":
                            setStyle("-fx-background-color: #fff3cd;"); // 浅黄色
                            break;
                        case "accepted":
                            setStyle("-fx-background-color: #d1ecf1;"); // 浅蓝色
                            break;
                        case "repairing":
                            setStyle("-fx-background-color: #d4edda;"); // 浅绿色
                            break;
                        case "completed":
                            setStyle("-fx-background-color: #f8f9fa;"); // 灰色
                            break;
                        default:
                            setStyle("");
                            break;
                    }
                }
            }
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

        // 加载初始数据
        loadRepairApplications();

        logger.info("管理员维修管理界面初始化完成");
    }

    /**
     * 加载维修申请数据
     */
    private void loadRepairApplications() {
        try {
            List<RepairApplication> applications = repairController.getAllApplications();
            repairData.clear();
            repairData.addAll(applications);

            statusLabel.setText("共" + applications.size() + "条记录");
            showMessage("数据加载完成");

            logger.info("加载维修申请数据成功：{}条记录", applications.size());

        } catch (Exception e) {
            logger.error("加载维修申请数据失败", e);
            showMessage("加载数据失败：" + e.getMessage());
        }
    }

    /**
     * 受理维修申请
     */
    @FXML
    private void acceptApplication(ActionEvent event) {
        RepairApplication selectedApplication = repairTable.getSelectionModel().getSelectedItem();
        if (selectedApplication == null) {
            showMessage("请先选择要受理的申请！");
            return;
        }

        if (!"pending".equals(selectedApplication.getStatus()) &&
            !"待受理".equals(selectedApplication.getStatus())) {
            showMessage("只能受理状态为'待受理'的申请！");
            return;
        }

        try {
            boolean result = repairController.updateApplicationStatus(
                selectedApplication.getApplyId(), "accepted", "管理员");

            if (result) {
                showMessage("申请受理成功！");
                loadRepairApplications(); // 刷新数据
                // 通知其他界面数据已更新
                DataUpdateManager.getInstance().notifyRepairDataChanged("accept");
                logger.info("维修申请受理成功：{}", selectedApplication.getApplyId());
            } else {
                showMessage("申请受理失败！");
            }

        } catch (Exception e) {
            logger.error("受理维修申请失败", e);
            showMessage("受理失败：" + e.getMessage());
        }
    }

    /**
     * 完成维修申请
     */
    @FXML
    private void completeApplication(ActionEvent event) {
        RepairApplication selectedApplication = repairTable.getSelectionModel().getSelectedItem();
        if (selectedApplication == null) {
            showMessage("请先选择要完成的申请！");
            return;
        }

        if (!"accepted".equals(selectedApplication.getStatus()) &&
            !"repairing".equals(selectedApplication.getStatus()) &&
            !"已受理".equals(selectedApplication.getStatus()) &&
            !"维修中".equals(selectedApplication.getStatus())) {
            showMessage("只能完成状态为'已受理'或'维修中'的申请！");
            return;
        }

        try {
            boolean result = repairController.completeRepairApplication(selectedApplication.getApplyId());

            if (result) {
                showMessage("维修完成成功！");
                loadRepairApplications(); // 刷新数据
                // 通知其他界面数据已更新
                DataUpdateManager.getInstance().notifyRepairDataChanged("complete");
                logger.info("维修申请完成成功：{}", selectedApplication.getApplyId());
            } else {
                showMessage("维修完成失败！");
            }

        } catch (Exception e) {
            logger.error("完成维修申请失败", e);
            showMessage("完成失败：" + e.getMessage());
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