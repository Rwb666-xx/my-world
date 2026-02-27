package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.dorm.controller.AccommodationController;
import org.dorm.model.entity.Accommodation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 住宿分配管理界面控制器
 */
public class AccommodationManagementController {
    private static final Logger logger = LoggerFactory.getLogger(AccommodationManagementController.class);

    @FXML
    private Button assignBedButton;
    @FXML
    private Button changeBedButton;
    @FXML
    private Button checkoutButton;
    @FXML
    private Button refreshButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchTypeCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private Label availableBedsLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private TableView<Accommodation> accommodationTable;
    @FXML
    private TableColumn<Accommodation, String> studentIdColumn;
    @FXML
    private TableColumn<Accommodation, String> studentNameColumn;
    @FXML
    private TableColumn<Accommodation, String> collegeColumn;
    @FXML
    private TableColumn<Accommodation, String> classColumn;
    @FXML
    private TableColumn<Accommodation, String> dormitoryColumn;
    @FXML
    private TableColumn<Accommodation, String> bedIdColumn;
    @FXML
    private TableColumn<Accommodation, String> checkinDateColumn;
    @FXML
    private TableColumn<Accommodation, String> statusColumn;

    private ObservableList<Accommodation> accommodationData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        logger.info("住宿分配管理界面初始化开始");

        // 初始化表格列
        setupTableColumns();

        // 初始化搜索类型
        if (searchTypeCombo != null) {
            searchTypeCombo.setItems(FXCollections.observableArrayList("全部", "学号", "姓名", "宿舍"));
            searchTypeCombo.setValue("全部");
        }

        // 加载初始数据
        loadAccommodations();

        logger.info("住宿分配管理界面初始化完成");
    }

    /**
     * 初始化表格列
     */
    private void setupTableColumns() {
        if (studentIdColumn != null) {
            studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        }
        if (studentNameColumn != null) {
            studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        }
        if (collegeColumn != null) {
            collegeColumn.setCellValueFactory(new PropertyValueFactory<>("collegeName"));
        }
        if (classColumn != null) {
            classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        }
        if (dormitoryColumn != null) {
            dormitoryColumn.setCellValueFactory(new PropertyValueFactory<>("dormitoryId"));
        }
        if (bedIdColumn != null) {
            bedIdColumn.setCellValueFactory(new PropertyValueFactory<>("bedId"));
        }
        if (checkinDateColumn != null) {
            checkinDateColumn.setCellValueFactory(cellData -> {
                Accommodation accommodation = cellData.getValue();
                java.util.Date checkinDate = accommodation.getCheckinDate();
                if (checkinDate != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return new javafx.beans.property.SimpleStringProperty(sdf.format(checkinDate));
                } else {
                    return new javafx.beans.property.SimpleStringProperty("");
                }
            });
        }
        if (statusColumn != null) {
            statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        }

        // 设置表格数据
        if (accommodationTable != null) {
            accommodationTable.setItems(accommodationData);
        }
    }

    /**
     * 加载住宿数据
     */
    private void loadAccommodations() {
        try {
            logger.info("开始加载住宿数据...");

            // 创建控制器实例
            AccommodationController accommodationController = new AccommodationController();

            // 获取住宿数据
            List<Accommodation> accommodations = accommodationController.getAllAccommodations();

            accommodationData.clear();
            accommodationData.addAll(accommodations);

            String statusText = "共 " + accommodations.size() + " 条记录";
            if (statusLabel != null) {
                statusLabel.setText(statusText);
            }

            showMessage("数据加载完成", "success");

            logger.info("住宿数据加载成功：{}条记录", accommodations.size());

        } catch (Exception e) {
            logger.error("加载住宿数据失败", e);
            if (statusLabel != null) {
                statusLabel.setText("加载失败");
            }
            showMessage("加载数据失败：" + e.getMessage(), "error");
        }
    }

    @FXML
    private void showAssignBedDialog(ActionEvent event) {
        // 创建分配床位对话框
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("分配床位");
        dialog.setHeaderText("为学生分配床位");

        // 设置按钮
        ButtonType assignButtonType = new ButtonType("分配", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(assignButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("输入学生学号");
        ComboBox<String> bedCombo = new ComboBox<>();
        Label infoLabel = new Label("请先输入学生学号");

        // 学生学号输入监听器
        studentIdField.textProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null && !newVal.trim().isEmpty()) {
                try {
                    AccommodationController controller = new AccommodationController();
                    List<String> availableBeds = controller.getAvailableBeds();
                    bedCombo.getItems().clear();
                    bedCombo.getItems().addAll(availableBeds);
                    infoLabel.setText("找到 " + availableBeds.size() + " 个空闲床位");
                } catch (Exception e) {
                    infoLabel.setText("加载床位失败：" + e.getMessage());
                    logger.error("加载床位失败", e);
                }
            }
        });

        grid.add(new Label("学生学号:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("选择床位:"), 0, 1);
        grid.add(bedCombo, 1, 1);
        grid.add(infoLabel, 0, 2, 2, 1);

        dialog.getDialogPane().setContent(grid);

        // 验证输入
        dialog.getDialogPane().lookupButton(assignButtonType).setDisable(true);
        bedCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            dialog.getDialogPane().lookupButton(assignButtonType).setDisable(
                newVal == null || studentIdField.getText().trim().isEmpty());
        });

        // 处理结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == assignButtonType) {
                String studentId = studentIdField.getText().trim();
                String bedId = bedCombo.getValue();
                if (studentId != null && bedId != null) {
                    try {
                        AccommodationController controller = new AccommodationController();
                        boolean success = controller.assignBed(studentId, bedId);
                        if (success) {
                            showMessage("分配床位成功！", "success");
                            loadAccommodations();
                            logger.info("分配床位成功：学生{} -> 床位{}", studentId, bedId);
                        } else {
                            showMessage("分配床位失败", "error");
                        }
                    } catch (Exception e) {
                        logger.error("分配床位异常", e);
                        showMessage("分配床位失败：" + e.getMessage(), "error");
                    }
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    @FXML
    private void showChangeBedDialog(ActionEvent event) {
        Accommodation selectedAccommodation = accommodationTable.getSelectionModel().getSelectedItem();
        if (selectedAccommodation == null) {
            showMessage("请先选择要调换床位的学生！", "warning");
            return;
        }

        if (!"已入住".equals(selectedAccommodation.getStatus())) {
            showMessage("只有已入住的学生才能调换床位！", "warning");
            return;
        }

        // 创建调换床位对话框
        Dialog<String> dialog = new Dialog<>();
        dialog.setTitle("调换床位");
        dialog.setHeaderText("为学生 " + selectedAccommodation.getStudentName() + " 调换床位");

        // 设置按钮
        ButtonType changeButtonType = new ButtonType("调换", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(changeButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        ComboBox<String> bedCombo = new ComboBox<>();
        Label currentBedLabel = new Label("当前床位: " + selectedAccommodation.getBedId());

        // 加载空闲床位
        try {
            AccommodationController controller = new AccommodationController();
            List<String> availableBeds = controller.getAvailableBeds();
            bedCombo.getItems().addAll(availableBeds);
        } catch (Exception e) {
            logger.error("加载床位列表失败", e);
            showMessage("加载床位列表失败：" + e.getMessage(), "error");
            return;
        }

        grid.add(currentBedLabel, 0, 0, 2, 1);
        grid.add(new Label("选择新床位:"), 0, 1);
        grid.add(bedCombo, 1, 1);

        dialog.getDialogPane().setContent(grid);

        // 验证输入
        dialog.getDialogPane().lookupButton(changeButtonType).setDisable(true);
        bedCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
            dialog.getDialogPane().lookupButton(changeButtonType).setDisable(newVal == null);
        });

        // 处理结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == changeButtonType) {
                String newBed = bedCombo.getValue();
                if (newBed != null) {
                    try {
                        AccommodationController controller = new AccommodationController();
                        boolean success = controller.changeBed(selectedAccommodation.getStudentId(), newBed);
                        if (success) {
                            showMessage("调换床位成功！", "success");
                            loadAccommodations();
                            logger.info("调换床位成功：学生{} -> 新床位{}",
                                selectedAccommodation.getStudentId(), newBed);
                        } else {
                            showMessage("调换床位失败", "error");
                        }
                    } catch (Exception e) {
                        logger.error("调换床位异常", e);
                        showMessage("调换床位失败：" + e.getMessage(), "error");
                    }
                }
            }
            return null;
        });

        dialog.showAndWait();
    }

    @FXML
    private void showCheckoutDialog(ActionEvent event) {
        Accommodation selectedAccommodation = accommodationTable.getSelectionModel().getSelectedItem();
        if (selectedAccommodation == null) {
            showMessage("请先选择要退宿的学生！", "warning");
            return;
        }

        if (!"已入住".equals(selectedAccommodation.getStatus())) {
            showMessage("只有已入住的学生才能退宿！", "warning");
            return;
        }

        // 创建确认对话框
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认退宿");
        alert.setHeaderText("确认要让学生 " + selectedAccommodation.getStudentName() + " 退宿吗？");
        alert.setContentText("退宿后，该学生的床位将被释放，可以重新分配给其他学生。");

        alert.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                try {
                    AccommodationController controller = new AccommodationController();
                    boolean success = controller.checkout(selectedAccommodation.getStudentId());
                    if (success) {
                        showMessage("退宿成功！", "success");
                        loadAccommodations();
                        logger.info("退宿成功：学生{}", selectedAccommodation.getStudentId());
                    } else {
                        showMessage("退宿失败", "error");
                    }
                } catch (Exception e) {
                    logger.error("退宿异常", e);
                    showMessage("退宿失败：" + e.getMessage(), "error");
                }
            }
        });
    }

    @FXML
    private void refreshAccommodations(ActionEvent event) {
        loadAccommodations();
    }

    @FXML
    private void searchAccommodations(ActionEvent event) {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadAccommodations(); // 如果搜索文本为空，加载所有数据
            return;
        }

        try {
            AccommodationController controller = new AccommodationController();
            List<Accommodation> results = controller.searchAccommodations(searchText, searchType);

            accommodationData.clear();
            accommodationData.addAll(results);

            String statusText = "找到 " + results.size() + " 条记录";
            if (statusLabel != null) {
                statusLabel.setText(statusText);
            }

            showMessage("搜索完成", "success");
            logger.info("搜索住宿信息：类型={}, 关键词={}, 结果={}", searchType, searchText, results.size());

        } catch (Exception e) {
            logger.error("搜索住宿信息失败", e);
            showMessage("搜索失败：" + e.getMessage(), "error");
        }
    }

    /**
     * 显示消息
     */
    private void showMessage(String message, String type) {
        if (messageLabel != null) {
            messageLabel.setText(message);
            // 这里可以根据type设置不同的样式
        }
        logger.info("显示消息: {} ({})", message, type);
    }
}