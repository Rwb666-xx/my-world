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
import javafx.scene.layout.HBox;
import org.dorm.controller.PaymentController;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.Payment;
import org.dorm.model.entity.Student;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * 缴费管理界面控制器
 */
public class PaymentManagementController {

    private boolean isStudentMode = false;
    private String currentStudentId;

    /**
     * 设置为学生模式，只显示指定学生的缴费记录
     */
    public void setStudentMode(String studentId) {
        this.isStudentMode = true;
        this.currentStudentId = studentId;

        // 在学生模式下隐藏管理功能
        if (addButton != null) addButton.setVisible(false);
        if (editButton != null) editButton.setVisible(false);
        if (deleteButton != null) deleteButton.setVisible(false);

        // 重新加载数据
        loadPayments();
    }
    private static final Logger logger = LoggerFactory.getLogger(PaymentManagementController.class);

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
    private TableView<Payment> paymentTable;
    @FXML
    private TableColumn<Payment, String> paymentIdColumn;
    @FXML
    private TableColumn<Payment, String> studentIdColumn;
    @FXML
    private TableColumn<Payment, String> studentNameColumn;
    @FXML
    private TableColumn<Payment, String> amountColumn;
    @FXML
    private TableColumn<Payment, String> paymentDateColumn;
    @FXML
    private TableColumn<Payment, String> semesterColumn;
    @FXML
    private TableColumn<Payment, String> statusColumn;

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

    private ObservableList<Payment> paymentData = FXCollections.observableArrayList();
    private PaymentController paymentController = new PaymentController();
    private StudentController studentController = new StudentController();

    // 分页相关变量
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalPages = 1;
    private int totalRecords = 0;

    @FXML
    private void initialize() {
        // 初始化表格列
        paymentIdColumn.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));

        // 学生姓名列需要特殊处理，通过学号查找姓名
        studentNameColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            try {
                Student student = studentController.getStudentById(payment.getStudentId());
                return new javafx.beans.property.SimpleStringProperty(
                    student != null ? student.getName() : "未知");
            } catch (Exception e) {
                return new javafx.beans.property.SimpleStringProperty("未知");
            }
        });

        // 金额列需要格式化显示
        amountColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            java.math.BigDecimal amount = payment.getAmount();
            if (amount != null) {
                return new javafx.beans.property.SimpleStringProperty(String.format("%.2f", amount.doubleValue()));
            } else {
                return new javafx.beans.property.SimpleStringProperty("0.00");
            }
        });

        // 缴费日期格式化
        paymentDateColumn.setCellValueFactory(cellData -> {
            Payment payment = cellData.getValue();
            java.util.Date date = payment.getPaymentDate();
            if (date != null) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                return new javafx.beans.property.SimpleStringProperty(sdf.format(date));
            } else {
                return new javafx.beans.property.SimpleStringProperty("");
            }
        });

        semesterColumn.setCellValueFactory(new PropertyValueFactory<>("semester"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        // 设置表格数据
        paymentTable.setItems(paymentData);

        // 初始化搜索类型
        if (searchTypeCombo != null) {
            searchTypeCombo.setValue("全部");
        }

        // 初始化页面大小
        if (pageSizeCombo != null) {
            pageSizeCombo.setValue("10");
        }

        // 初始化表格选择监听器
        if (paymentTable != null) {
            paymentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                updateSelectedCount();
            });
        }

        // 初始化分页按钮状态
        updatePaginationButtons();

        // 加载初始数据
        loadPayments();

        logger.info("缴费管理界面初始化完成");
    }

    /**
     * 加载缴费数据
     */
    private void loadPayments() {
        try {
            List<Payment> payments;

            if (isStudentMode && currentStudentId != null) {
                // 学生模式：只加载当前学生的缴费记录
                payments = paymentController.getPaymentsByStudentId(currentStudentId);
            } else {
                // 管理员模式：加载所有缴费记录
                payments = paymentController.getAllPayments();
            }

            paymentData.clear();
            paymentData.addAll(payments);

            totalRecords = payments.size();
            totalPages = (int) Math.ceil((double) totalRecords / pageSize);

            // 更新状态显示
            statusLabel.setText("共 " + totalRecords + " 条记录");
            updateLastUpdateTime();
            updatePageInfo();
            showMessage("数据加载完成", "success");

            logger.info("加载缴费数据成功：{}条记录", payments.size());

        } catch (Exception e) {
            logger.error("加载缴费数据失败", e);
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
        if (paymentTable != null && selectedLabel != null) {
            int selectedCount = paymentTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("已选择 " + selectedCount + " 项");
        }
    }

    /**
     * 显示添加缴费记录对话框
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 创建对话框
        Dialog<Payment> dialog = new Dialog<>();
        dialog.setTitle("添加缴费记录");
        dialog.setHeaderText("请输入新的缴费记录信息");

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
        TextField amountField = new TextField();
        DatePicker paymentDatePicker = new DatePicker();
        TextField semesterField = new TextField();
        ComboBox<String> statusCombo = new ComboBox<>();

        // 初始化学生下拉框
        try {
            List<Student> students = studentController.getAllStudents();
            for (Student student : students) {
                studentCombo.getItems().add(student.getStudentId() + " - " + student.getName());
            }
        } catch (Exception e) {
            logger.error("加载学生列表失败", e);
        }

        // 初始化状态下拉框
        statusCombo.getItems().addAll("未缴", "已缴");
        statusCombo.setValue("未缴");

        // 设置学期默认值
        semesterField.setText("2024-2025学年第一学期");

        // 设置日期默认值为今天
        paymentDatePicker.setValue(java.time.LocalDate.now());

        grid.add(new Label("学生:"), 0, 0);
        grid.add(studentCombo, 1, 0);
        grid.add(new Label("缴费金额:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(new Label("缴费日期:"), 0, 2);
        grid.add(paymentDatePicker, 1, 2);
        grid.add(new Label("缴费学期:"), 0, 3);
        grid.add(semesterField, 1, 3);
        grid.add(new Label("缴费状态:"), 0, 4);
        grid.add(statusCombo, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // 转换结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Payment payment = new Payment();

                    // 生成缴费单号
                    String paymentId = "PAY" + System.currentTimeMillis();
                    payment.setPaymentId(paymentId);

                    // 解析学生ID
                    String selectedStudent = studentCombo.getValue();
                    if (selectedStudent != null && selectedStudent.contains(" - ")) {
                        String studentId = selectedStudent.split(" - ")[0];
                        payment.setStudentId(studentId);
                    }

                    // 设置其他字段
                    if (!amountField.getText().isEmpty()) {
                        payment.setAmount(java.math.BigDecimal.valueOf(Double.parseDouble(amountField.getText())));
                    }
                    payment.setPaymentDate(java.sql.Date.valueOf(paymentDatePicker.getValue()));
                    payment.setSemester(semesterField.getText());
                    payment.setStatus(statusCombo.getValue());

                    return payment;
                } catch (Exception e) {
                    logger.error("创建缴费记录失败", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Payment> result = dialog.showAndWait();
        result.ifPresent(payment -> {
            try {
                boolean success = paymentController.addPayment(payment);
                if (success) {
                    showMessage("缴费记录添加成功！");
                    loadPayments();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyPaymentDataChanged("add");
                    logger.info("添加缴费记录成功：{}", payment.getPaymentId());
                } else {
                    showMessage("缴费记录添加失败！");
                }
            } catch (Exception e) {
                logger.error("添加缴费记录异常", e);
                showMessage("添加失败：" + e.getMessage());
            }
        });
    }

    /**
     * 显示编辑缴费记录对话框
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            showMessage("请先选择要编辑的缴费记录！");
            return;
        }

        // 创建对话框
        Dialog<Payment> dialog = new Dialog<>();
        dialog.setTitle("编辑缴费记录");
        dialog.setHeaderText("修改缴费记录信息");

        // 设置按钮
        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        // 表单控件
        Label studentLabel = new Label(selectedPayment.getStudentId() + " - " +
            (studentController.getStudentById(selectedPayment.getStudentId()) != null ?
             studentController.getStudentById(selectedPayment.getStudentId()).getName() : "未知"));
        TextField amountField = new TextField(String.valueOf(selectedPayment.getAmount()));
        DatePicker paymentDatePicker = new DatePicker();
        TextField semesterField = new TextField(selectedPayment.getSemester());
        ComboBox<String> statusCombo = new ComboBox<>();

        // 设置日期
        if (selectedPayment.getPaymentDate() != null) {
            paymentDatePicker.setValue(selectedPayment.getPaymentDate().toInstant()
                .atZone(java.time.ZoneId.systemDefault()).toLocalDate());
        } else {
            paymentDatePicker.setValue(java.time.LocalDate.now());
        }

        // 初始化状态下拉框
        statusCombo.getItems().addAll("未缴", "已缴");
        statusCombo.setValue(selectedPayment.getStatus());

        grid.add(new Label("学生:"), 0, 0);
        grid.add(studentLabel, 1, 0);
        grid.add(new Label("缴费金额:"), 0, 1);
        grid.add(amountField, 1, 1);
        grid.add(new Label("缴费日期:"), 0, 2);
        grid.add(paymentDatePicker, 1, 2);
        grid.add(new Label("缴费学期:"), 0, 3);
        grid.add(semesterField, 1, 3);
        grid.add(new Label("缴费状态:"), 0, 4);
        grid.add(statusCombo, 1, 4);

        dialog.getDialogPane().setContent(grid);

        // 转换结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                try {
                    Payment payment = new Payment();
                    payment.setPaymentId(selectedPayment.getPaymentId());
                    payment.setStudentId(selectedPayment.getStudentId());

                    if (!amountField.getText().isEmpty()) {
                        payment.setAmount(java.math.BigDecimal.valueOf(Double.parseDouble(amountField.getText())));
                    }
                    payment.setPaymentDate(java.sql.Date.valueOf(paymentDatePicker.getValue()));
                    payment.setSemester(semesterField.getText());
                    payment.setStatus(statusCombo.getValue());

                    return payment;
                } catch (Exception e) {
                    logger.error("更新缴费记录失败", e);
                    return null;
                }
            }
            return null;
        });

        Optional<Payment> result = dialog.showAndWait();
        result.ifPresent(payment -> {
            try {
                boolean success = paymentController.updatePayment(payment);
                if (success) {
                    showMessage("缴费记录更新成功！");
                    loadPayments();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyPaymentDataChanged("update");
                    logger.info("更新缴费记录成功：{}", payment.getPaymentId());
                } else {
                    showMessage("缴费记录更新失败！");
                }
            } catch (Exception e) {
                logger.error("更新缴费记录异常", e);
                showMessage("更新失败：" + e.getMessage());
            }
        });
    }

    /**
     * 删除缴费记录
     */
    @FXML
    private void deletePayment(ActionEvent event) {
        Payment selectedPayment = paymentTable.getSelectionModel().getSelectedItem();
        if (selectedPayment == null) {
            showMessage("请先选择要删除的缴费记录！");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认删除");
        alert.setHeaderText("确定要删除这条缴费记录吗？");
        alert.setContentText("缴费单号: " + selectedPayment.getPaymentId() + "\n学生: " + selectedPayment.getStudentId());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                boolean success = paymentController.deletePayment(selectedPayment.getPaymentId());
                if (success) {
                    showMessage("缴费记录删除成功！");
                    loadPayments();
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyPaymentDataChanged("delete");
                    logger.info("删除缴费记录成功：{}", selectedPayment.getPaymentId());
                } else {
                    showMessage("缴费记录删除失败！");
                }
            } catch (Exception e) {
                logger.error("删除缴费记录异常", e);
                showMessage("删除失败：" + e.getMessage());
            }
        }
    }

    /**
     * 刷新缴费记录列表
     */
    @FXML
    private void refreshPayments() {
        loadPayments();
    }

    /**
     * 搜索缴费记录
     */
    @FXML
    private void searchPayments(ActionEvent event) {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadPayments(); // 如果搜索为空，显示所有记录
            return;
        }

        try {
            List<Payment> allPayments = paymentController.getAllPayments();
            List<Payment> filteredPayments;

            switch (searchType) {
                case "学号":
                    filteredPayments = allPayments.stream()
                        .filter(p -> p.getStudentId().contains(searchText))
                        .toList();
                    break;
                case "姓名":
                    filteredPayments = allPayments.stream()
                        .filter(p -> {
                            try {
                                Student student = studentController.getStudentById(p.getStudentId());
                                return student != null && student.getName().contains(searchText);
                            } catch (Exception e) {
                                return false;
                            }
                        })
                        .toList();
                    break;
                case "学期":
                    filteredPayments = allPayments.stream()
                        .filter(p -> p.getSemester() != null && p.getSemester().contains(searchText))
                        .toList();
                    break;
                default:
                    // 全部搜索
                    filteredPayments = allPayments.stream()
                        .filter(p -> p.getStudentId().contains(searchText) ||
                                   p.getSemester() != null && p.getSemester().contains(searchText) ||
                                   p.getStatus() != null && p.getStatus().contains(searchText))
                        .toList();
                    break;
            }

            paymentData.clear();
            paymentData.addAll(filteredPayments);
            statusLabel.setText("搜索到 " + filteredPayments.size() + " 条记录");
            showMessage("搜索完成", "success");

        } catch (Exception e) {
            logger.error("搜索缴费记录失败", e);
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