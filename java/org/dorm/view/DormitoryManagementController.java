package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.dorm.controller.DormitoryController;
import org.dorm.model.entity.Dormitory;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

/**
 * 宿舍管理界面控制器
 */
public class DormitoryManagementController {
    private static final Logger logger = LoggerFactory.getLogger(DormitoryManagementController.class);

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Label statusLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private TableView<Dormitory> dormitoryTable;
    @FXML
    private TableColumn<Dormitory, String> dormIdColumn;
    @FXML
    private TableColumn<Dormitory, String> buildingColumn;
    @FXML
    private TableColumn<Dormitory, Integer> floorColumn;
    @FXML
    private TableColumn<Dormitory, Integer> capacityColumn;
    @FXML
    private TableColumn<Dormitory, String> managerColumn;
    @FXML
    private TableColumn<Dormitory, String> managerPhoneColumn;

    private ObservableList<Dormitory> dormitoryData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // 初始化表格列
        dormIdColumn.setCellValueFactory(new PropertyValueFactory<>("dormitoryId"));
        buildingColumn.setCellValueFactory(new PropertyValueFactory<>("building"));
        floorColumn.setCellValueFactory(new PropertyValueFactory<>("floor"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("totalBeds"));
        managerColumn.setCellValueFactory(new PropertyValueFactory<>("managerName"));
        managerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("managerPhone"));

        // 设置表格数据
        dormitoryTable.setItems(dormitoryData);

        // 加载初始数据
        loadDormitories();

        logger.info("宿舍管理界面初始化完成");
    }

    /**
     * 加载宿舍数据
     */
    private void loadDormitories() {
        try {
            // 按需创建Controller实例，避免在应用启动时就连接数据库
            DormitoryController dormitoryController = new DormitoryController();
            // 从数据库获取宿舍数据
            List<Dormitory> dormitories = dormitoryController.getAllDormitories();
            dormitoryData.clear();
            dormitoryData.addAll(dormitories);

            statusLabel.setText("共 " + dormitories.size() + " 条记录");
            updateLastUpdateTime();
            showMessage("数据加载完成", "success");

            logger.info("加载宿舍数据成功：{}条记录", dormitories.size());

        } catch (Exception e) {
            logger.error("加载宿舍数据失败", e);
            showMessage("加载数据失败：" + e.getMessage(), "error");
        }
    }


    /**
     * 显示添加宿舍对话框
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 创建对话框
        Dialog<Dormitory> dialog = new Dialog<>();
        dialog.setTitle("添加宿舍");
        dialog.setHeaderText("请输入新宿舍的信息");

        // 设置按钮
        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField dormIdField = new TextField();
        dormIdField.setPromptText("宿舍号（如：A101）");
        TextField buildingField = new TextField();
        buildingField.setPromptText("楼栋（如：A栋）");
        Spinner<Integer> floorSpinner = new Spinner<>(1, 20, 1);
        floorSpinner.setEditable(true);
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 10, 4);
        capacitySpinner.setEditable(true);
        TextField managerField = new TextField();
        managerField.setPromptText("管理员姓名");
        TextField managerPhoneField = new TextField();
        managerPhoneField.setPromptText("管理员电话");

        grid.add(new Label("宿舍号:"), 0, 0);
        grid.add(dormIdField, 1, 0);
        grid.add(new Label("楼栋:"), 0, 1);
        grid.add(buildingField, 1, 1);
        grid.add(new Label("楼层:"), 0, 2);
        grid.add(floorSpinner, 1, 2);
        grid.add(new Label("容量:"), 0, 3);
        grid.add(capacitySpinner, 1, 3);
        grid.add(new Label("管理员:"), 0, 4);
        grid.add(managerField, 1, 4);
        grid.add(new Label("联系电话:"), 0, 5);
        grid.add(managerPhoneField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // 验证输入
        dialog.getDialogPane().lookupButton(saveButtonType).setDisable(true);
        dormIdField.textProperty().addListener((obs, oldText, newText) -> {
            boolean disable = dormIdField.getText().trim().isEmpty() ||
                             buildingField.getText().trim().isEmpty();
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        });
        buildingField.textProperty().addListener((obs, oldText, newText) -> {
            boolean disable = dormIdField.getText().trim().isEmpty() ||
                             buildingField.getText().trim().isEmpty();
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        });

        // 处理结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Dormitory dormitory = new Dormitory();
                dormitory.setDormitoryId(dormIdField.getText().trim());
                dormitory.setBuilding(buildingField.getText().trim());
                dormitory.setFloor(floorSpinner.getValue());
                dormitory.setTotalBeds(capacitySpinner.getValue());
                dormitory.setManagerName(managerField.getText().trim().isEmpty() ? null : managerField.getText().trim());
                dormitory.setManagerPhone(managerPhoneField.getText().trim().isEmpty() ? null : managerPhoneField.getText().trim());
                return dormitory;
            }
            return null;
        });

        Optional<Dormitory> result = dialog.showAndWait();
        result.ifPresent(dormitory -> {
            try {
                // 按需创建Controller实例
                DormitoryController dormitoryController = new DormitoryController();
                // 保存到数据库
                boolean success = dormitoryController.addDormitory(dormitory);
                if (success) {
                    showMessage("添加宿舍成功！", "success");
                    // 重新加载数据以显示最新状态
                    loadDormitories();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyDormitoryDataChanged("add");
                    logger.info("添加宿舍成功：{}", dormitory.getDormitoryId());
                } else {
                    showMessage("添加宿舍失败，宿舍号可能已存在", "error");
                }
            } catch (Exception e) {
                logger.error("添加宿舍失败", e);
                showMessage("添加宿舍失败：" + e.getMessage(), "error");
            }
        });
    }

    /**
     * 显示编辑宿舍对话框
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Dormitory selectedDormitory = dormitoryTable.getSelectionModel().getSelectedItem();
        if (selectedDormitory == null) {
            showMessage("请先选择要编辑的宿舍！", "warning");
            return;
        }

        // 创建对话框
        Dialog<Dormitory> dialog = new Dialog<>();
        dialog.setTitle("编辑宿舍");
        dialog.setHeaderText("修改宿舍信息");

        // 设置按钮
        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField dormIdField = new TextField(selectedDormitory.getDormitoryId());
        dormIdField.setEditable(false); // 宿舍号不允许修改
        TextField buildingField = new TextField(selectedDormitory.getBuilding());
        Spinner<Integer> floorSpinner = new Spinner<>(1, 20, selectedDormitory.getFloor());
        floorSpinner.setEditable(true);
        Spinner<Integer> capacitySpinner = new Spinner<>(1, 10, selectedDormitory.getTotalBeds());
        capacitySpinner.setEditable(true);
        TextField managerField = new TextField(selectedDormitory.getManagerName() != null ? selectedDormitory.getManagerName() : "");
        TextField managerPhoneField = new TextField(selectedDormitory.getManagerPhone() != null ? selectedDormitory.getManagerPhone() : "");

        grid.add(new Label("宿舍号:"), 0, 0);
        grid.add(dormIdField, 1, 0);
        grid.add(new Label("楼栋:"), 0, 1);
        grid.add(buildingField, 1, 1);
        grid.add(new Label("楼层:"), 0, 2);
        grid.add(floorSpinner, 1, 2);
        grid.add(new Label("容量:"), 0, 3);
        grid.add(capacitySpinner, 1, 3);
        grid.add(new Label("管理员:"), 0, 4);
        grid.add(managerField, 1, 4);
        grid.add(new Label("联系电话:"), 0, 5);
        grid.add(managerPhoneField, 1, 5);

        dialog.getDialogPane().setContent(grid);

        // 处理结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                selectedDormitory.setBuilding(buildingField.getText().trim());
                selectedDormitory.setFloor(floorSpinner.getValue());
                selectedDormitory.setTotalBeds(capacitySpinner.getValue());
                selectedDormitory.setManagerName(managerField.getText().trim().isEmpty() ? null : managerField.getText().trim());
                selectedDormitory.setManagerPhone(managerPhoneField.getText().trim().isEmpty() ? null : managerPhoneField.getText().trim());
                return selectedDormitory;
            }
            return null;
        });

        Optional<Dormitory> result = dialog.showAndWait();
        if (result.isPresent()) {
            try {
                // 按需创建Controller实例
                DormitoryController dormitoryController = new DormitoryController();
                // 保存到数据库
                boolean success = dormitoryController.updateDormitory(selectedDormitory);
                if (success) {
                    showMessage("编辑宿舍成功！", "success");
                    // 重新加载数据以显示最新状态
                    loadDormitories();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyDormitoryDataChanged("update");
                    logger.info("编辑宿舍成功：{}", selectedDormitory.getDormitoryId());
                } else {
                    showMessage("编辑宿舍失败", "error");
                }
            } catch (Exception e) {
                logger.error("编辑宿舍失败", e);
                showMessage("编辑宿舍失败：" + e.getMessage(), "error");
            }
        }
    }

    /**
     * 删除宿舍
     */
    @FXML
    private void deleteDormitory(ActionEvent event) {
        Dormitory selectedDormitory = dormitoryTable.getSelectionModel().getSelectedItem();
        if (selectedDormitory == null) {
            showMessage("请先选择要删除的宿舍！", "warning");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认删除");
        alert.setHeaderText("确定要删除宿舍吗？");
        alert.setContentText("宿舍号: " + selectedDormitory.getDormitoryId() + "\n楼栋: " + selectedDormitory.getBuilding() +
                           "\n注意：删除宿舍将同时删除该宿舍内的所有床位和学生信息！");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // 按需创建Controller实例
                DormitoryController dormitoryController = new DormitoryController();
                // 从数据库删除
                boolean success = dormitoryController.deleteDormitory(selectedDormitory.getDormitoryId());
                if (success) {
                    showMessage("删除宿舍成功！", "success");
                    // 重新加载数据以显示最新状态
                    loadDormitories();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyDormitoryDataChanged("delete");
                    logger.info("删除宿舍成功：{}", selectedDormitory.getDormitoryId());
                } else {
                    showMessage("删除宿舍失败", "error");
                }
            } catch (Exception e) {
                showMessage("删除宿舍失败：" + e.getMessage(), "error");
                logger.error("删除宿舍失败", e);
            }
        }
    }

    /**
     * 刷新宿舍列表
     */
    @FXML
    private void refreshDormitories(ActionEvent event) {
        loadDormitories();
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        showMessage(message, "info");
    }

    /**
     * 更新最后更新时间
     */
    private void updateLastUpdateTime() {
        if (lastUpdateLabel != null) {
            lastUpdateLabel.setText("最后更新: " + java.time.LocalDateTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));
        }
    }

    /**
     * 显示消息（带类型）
     */
    private void showMessage(String message, String type) {
        messageLabel.setText(message);
        messageLabel.getStyleClass().removeAll("success", "error", "warning");
        messageLabel.getStyleClass().add(type);

        // 3秒后清除消息
        new Thread(() -> {
            try {
                Thread.sleep(3000);
                javafx.application.Platform.runLater(() -> {
                    messageLabel.setText("");
                    messageLabel.getStyleClass().removeAll("success", "error", "warning");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }
}