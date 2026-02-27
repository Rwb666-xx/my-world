package org.dorm.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;
import org.dorm.controller.StudentController;
import org.dorm.controller.ViolationController;
import org.dorm.model.entity.Student;
import org.dorm.model.entity.Violation;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * 违纪管理界面控制器
 */
public class ViolationManagementController {

    private boolean isStudentMode = false;
    private String currentStudentId;

    /**
     * 设置为学生模式，只显示指定学生的违纪记录
     */
    public void setStudentMode(String studentId) {
        this.isStudentMode = true;
        this.currentStudentId = studentId;

        // 在学生模式下隐藏管理功能
        if (addButton != null) addButton.setVisible(false);
        if (editButton != null) editButton.setVisible(false);
        if (deleteButton != null) deleteButton.setVisible(false);

        // 重新加载数据
        loadViolations();
    }
    private static final Logger logger = LoggerFactory.getLogger(ViolationManagementController.class);

    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button refreshButton;
    @FXML
    private Button searchButton;
    @FXML
    private TextField searchField;
    @FXML
    private ComboBox<String> searchTypeCombo;
    @FXML
    private Label statusLabel;
    @FXML
    private Label selectedLabel;
    @FXML
    private Label messageLabel;
    @FXML
    private Label lastUpdateLabel;
    @FXML
    private TableView<Violation> violationTable;
    @FXML
    private TableColumn<Violation, String> violationIdColumn;
    @FXML
    private TableColumn<Violation, String> studentIdColumn;
    @FXML
    private TableColumn<Violation, String> studentNameColumn;
    @FXML
    private TableColumn<Violation, String> descriptionColumn;
    @FXML
    private TableColumn<Violation, String> violationDateColumn;
    @FXML
    private TableColumn<Violation, String> penaltyColumn;

    // 分页相关
    @FXML
    private Button firstPageButton;
    @FXML
    private Button prevPageButton;
    @FXML
    private Button nextPageButton;
    @FXML
    private Button lastPageButton;
    @FXML
    private Label pageInfoLabel;
    @FXML
    private ComboBox<String> pageSizeCombo;

    private ObservableList<Violation> violationData = FXCollections.observableArrayList();
    private ViolationController violationController = new ViolationController();
    private StudentController studentController = new StudentController();

    // 分页相关变量
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalPages = 1;
    private int totalRecords = 0;

    @FXML
    private void initialize() {
        // 初始化表格列
        violationIdColumn.setCellValueFactory(new PropertyValueFactory<>("violationId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        // 学生姓名列需要特殊处理，通过学号查找姓名
        studentNameColumn.setCellValueFactory(cellData -> {
            Violation violation = cellData.getValue();
            try {
                Student student = studentController.getStudentById(violation.getStudentId());
                return new javafx.beans.property.SimpleStringProperty(
                    student != null ? student.getName() : "未知");
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty("未知");
            }
        });

        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));

        // 违纪日期格式化
        violationDateColumn.setCellValueFactory(cellData -> {
            Violation violation = cellData.getValue();
            java.util.Date date = violation.getViolationDate();
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return new javafx.beans.property.SimpleStringProperty(sdf.format(date));
            } else {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        penaltyColumn.setCellValueFactory(new PropertyValueFactory<>("penalty"));

        // 设置表格数据
        violationTable.setItems(violationData);

        // 初始化搜索类型
        if (searchTypeCombo != null) {
            searchTypeCombo.setValue("全部");
        }

        // 初始化页面大小
        if (pageSizeCombo != null) {
            pageSizeCombo.setValue("10");
        }

        // 初始化表格选择监听器
        if (violationTable != null) {
            violationTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                updateSelectedCount();
            });
        }

        // 初始化分页按钮状态
        updatePaginationButtons();

        // 加载初始数据
        loadViolations();

        logger.info("违纪管理界面初始化完成");
    }

    /**
     * 加载违纪数据
     */
    private void loadViolations() {
        try {
            List<Violation> violations;

            if (isStudentMode && currentStudentId != null) {
                // 学生模式：只加载当前学生的违纪记录
                violations = violationController.getViolationsByStudentId(currentStudentId);
            } else {
                // 管理员模式：加载所有违纪记录
                violations = violationController.getAllViolations();
            }

            violationData.clear();
            violationData.addAll(violations);

            totalRecords = violations.size();
            totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // 更新状态显示
            statusLabel.setText("共 " + totalRecords + " 条记录");
            updateLastUpdateTime();
            updatePageInfo();
            showMessage("数据加载完成", "success");

            logger.info("加载违纪数据成功：{}条记录", violations.size());

        } catch (Exception e) {
            logger.error("加载违纪数据失败", e);
            showMessage("加载数据失败：" + e.getMessage(), "error");
        }
    }

    /**
     * 更新分页按钮状态
     */
    private void updatePaginationButtons() {
        // 暂时禁用所有分页按钮，因为分页功能还未完全实现
        if (firstPageButton != null) firstPageButton.setDisable(true);
        if (prevPageButton != null) prevPageButton.setDisable(true);
        if (nextPageButton != null) nextPageButton.setDisable(true);
        if (lastPageButton != null) lastPageButton.setDisable(true);
    }

    /**
     * 更新页面信息
     */
    private void updatePageInfo() {
        if (pageInfoLabel != null) {
            pageInfoLabel.setText("第 " + currentPage + " 页 / 共 " + Math.max(1, totalPages) + " 页");
        }
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
     * 更新选中项计数
     */
    private void updateSelectedCount() {
        if (violationTable != null && selectedLabel != null) {
            int selectedCount = violationTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("已选择 " + selectedCount + " 项");
        }
    }

    /**
     * 显示添加违纪记录对话框
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 创建对话框
        Dialog<Violation> dialog = new Dialog<>();
        dialog.setTitle("添加违纪记录");
        dialog.setHeaderText("请输入新的违纪记录信息");

        // 设置按钮
        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // 表单控件
        ComboBox<String> studentCombo = new ComboBox<>();
        TextArea descriptionArea = new TextArea();
        DatePicker violationDatePicker = new DatePicker();
        TextField penaltyField = new TextField();

        // 初始化学生下拉框
        try {
            List<Student> students = studentController.getAllStudents();
            for (Student student : students) {
                studentCombo.getItems().add(student.getStudentId() + " - " + student.getName());
            }
        } catch (Exception e) {
            logger.error("加载学生列表失败", e);
        }

        descriptionArea.setPrefRowCount(3);
        descriptionArea.setWrapText(true);

        // 设置日期默认值为今天
        violationDatePicker.setValue(java.time.LocalDate.now());

        grid.add(new Label("学生:"), 0, 0);
        grid.add(studentCombo, 1, 0);
        grid.add(new Label("违纪描述:"), 0, 1);
        grid.add(descriptionArea, 1, 1);
        grid.add(new Label("违纪日期:"), 0, 2);
        grid.add(violationDatePicker, 1, 2);
        grid.add(new Label("处罚措施:"), 0, 3);
        grid.add(penaltyField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // 转换结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Violation violation = new Violation();

                    // 生成违纪单号
                    String violationId = "VIO" + System.currentTimeMillis();
                    violation.setViolationId(violationId);

                    // 解析学生ID
                    String selectedStudent = studentCombo.getValue();
                    if (selectedStudent != null && selectedStudent.contains(" - ")) {
                        String studentId = selectedStudent.split(" - ")[0];
                        violation.setStudentId(studentId);
                    }

                    // 设置其他字段
                    violation.setDescription(descriptionArea.getText());
                    violation.setViolationDate(java.sql.Date.valueOf(violationDatePicker.getValue()));
                    violation.setPenalty(penaltyField.getText());

                    return violation;
                } catch (Exception e) {
                    logger.error("创建违纪记录失败", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Violation> result = dialog.showAndWait();
        result.ifPresent(violation -> {
            try {
                boolean success = violationController.addViolation(violation);
                if (success) {
                    showMessage("违纪记录添加成功！");
                    loadViolations();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyViolationDataChanged("add");
                    logger.info("添加违纪记录成功：{}", violation.getViolationId());
                } else {
                    showMessage("违纪记录添加失败！");
                }
            } catch (Exception e) {
                logger.error("添加违纪记录异常", e);
                showMessage("添加失败：" + e.getMessage());
            }
        });
    }

    /**
     * 显示编辑违纪记录对话框
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Violation selectedViolation = violationTable.getSelectionModel().getSelectedItem();
        if (selectedViolation == null) {
            showMessage("请先选择要编辑的违纪记录！");
            return;
        }

        // 创建对话框
        Dialog<Violation> dialog = new Dialog<>();
        dialog.setTitle("编辑违纪记录");
        dialog.setHeaderText("修改违纪记录信息");

        // 设置按钮
        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // 表单控件
        Label studentLabel = new Label(selectedViolation.getStudentId() + " - " +
            (studentController.getStudentById(selectedViolation.getStudentId()) != null ?
             studentController.getStudentById(selectedViolation.getStudentId()).getName() : "未知"));
        TextArea descriptionArea = new TextArea(selectedViolation.getDescription());
        DatePicker violationDatePicker = new DatePicker();
        TextField penaltyField = new TextField(selectedViolation.getPenalty());

        descriptionArea.setPrefRowCount(3);
        descriptionArea.setWrapText(true);

        // 设置日期
        if (selectedViolation.getViolationDate() != null) {
            violationDatePicker.setValue(selectedViolation.getViolationDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        } else {
            violationDatePicker.setValue(java.time.LocalDate.now());
        }

        grid.add(new Label("学生:"), 0, 0);
        grid.add(studentLabel, 1, 0);
        grid.add(new Label("违纪描述:"), 0, 1);
        grid.add(descriptionArea, 1, 1);
        grid.add(new Label("违纪日期:"), 0, 2);
        grid.add(violationDatePicker, 1, 2);
        grid.add(new Label("处罚措施:"), 0, 3);
        grid.add(penaltyField, 1, 3);

        dialog.getDialogPane().setContent(grid);

        // 转换结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Violation violation = new Violation();
                    violation.setViolationId(selectedViolation.getViolationId());
                    violation.setStudentId(selectedViolation.getStudentId());

                    violation.setDescription(descriptionArea.getText());
                    violation.setViolationDate(java.sql.Date.valueOf(violationDatePicker.getValue()));
                    violation.setPenalty(penaltyField.getText());

                    return violation;
                } catch (Exception e) {
                    logger.error("更新违纪记录失败", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Violation> result = dialog.showAndWait();
        result.ifPresent(violation -> {
            try {
                boolean success = violationController.updateViolation(violation);
                if (success) {
                    showMessage("违纪记录更新成功！");
                    loadViolations();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyViolationDataChanged("update");
                    logger.info("更新违纪记录成功：{}", violation.getViolationId());
                } else {
                    showMessage("违纪记录更新失败！");
                }
            } catch (Exception e) {
                logger.error("更新违纪记录异常", e);
                showMessage("更新失败：" + e.getMessage());
            }
        });
    }

    /**
     * 删除违纪记录
     */
    @FXML
    private void deleteViolation(ActionEvent event) {
        Violation selectedViolation = violationTable.getSelectionModel().getSelectedItem();
        if (selectedViolation == null) {
            showMessage("请先选择要删除的违纪记录！");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认删除");
        alert.setHeaderText("确定要删除这条违纪记录吗？");
        alert.setContentText("违纪单号: " + selectedViolation.getViolationId() + "\n学生: " + selectedViolation.getStudentId());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean success = violationController.deleteViolation(selectedViolation.getViolationId());
                if (success) {
                    showMessage("违纪记录删除成功！");
                    loadViolations();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyViolationDataChanged("delete");
                    logger.info("删除违纪记录成功：{}", selectedViolation.getViolationId());
                } else {
                    showMessage("违纪记录删除失败！");
                }
            } catch (Exception e) {
                logger.error("删除违纪记录异常", e);
                showMessage("删除失败：" + e.getMessage());
            }
        }
    }

    /**
     * 刷新违纪记录列表
     */
    @FXML
    private void refreshViolations() {
        loadViolations();
    }

    /**
     * 搜索违纪记录
     */
    @FXML
    private void searchViolations(ActionEvent event) {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadViolations(); // 如果搜索为空，显示所有记录
            return;
        }

        try {
            List<Violation> allViolations = violationController.getAllViolations();
            List<Violation> filteredViolations;

            switch (searchType) {
                case "学号":
                    filteredViolations = allViolations.stream()
                        .filter(v -> v.getStudentId().contains(searchText))
                        .toList();
                    break;
                case "姓名":
                    filteredViolations = allViolations.stream()
                        .filter(v -> {
                            try {
                                Student student = studentController.getStudentById(v.getStudentId());
                                return student != null && student.getName().contains(searchText);
                            } catch (Exception e) {
                                return false;
                            }
                        })
                        .toList();
                    break;
                default:
                    // 全部搜索
                    filteredViolations = allViolations.stream()
                        .filter(v -> v.getStudentId().contains(searchText) ||
                                   v.getDescription() != null && v.getDescription().contains(searchText) ||
                                   v.getPenalty() != null && v.getPenalty().contains(searchText))
                        .toList();
                    break;
            }

            violationData.clear();
            violationData.addAll(filteredViolations);
            statusLabel.setText("搜索到 " + filteredViolations.size() + " 条记录");
            showMessage("搜索完成", "success");

        } catch (Exception e) {
            logger.error("搜索违纪记录失败", e);
            showMessage("搜索失败：" + e.getMessage(), "error");
        }
    }

    // 分页相关方法（暂时未实现）
    @FXML
    private void goToFirstPage() {}
    @FXML
    private void goToPrevPage() {}
    @FXML
    private void goToNextPage() {}
    @FXML
    private void goToLastPage() {}
    @FXML
    private void changePageSize(ActionEvent event) {}

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
                    messageLabel.setText("就绪");
                    messageLabel.getStyleClass().removeAll("success", "error", "warning");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        showMessage(message, "info");
    }
}