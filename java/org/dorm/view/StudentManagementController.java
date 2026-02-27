package org.dorm.view;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.dorm.controller.StudentController;
import org.dorm.model.entity.Student;
import org.dorm.util.DataUpdateManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * 学生管理界面控制器
 */
public class StudentManagementController {
    private static final Logger logger = LoggerFactory.getLogger(StudentManagementController.class);

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
    private TableView<Student> studentTable;
    @FXML
    private TableColumn<Student, String> studentIdColumn;
    @FXML
    private TableColumn<Student, String> nameColumn;
    @FXML
    private TableColumn<Student, String> collegeColumn;
    @FXML
    private TableColumn<Student, String> classColumn;
    @FXML
    private TableColumn<Student, String> genderColumn;
    @FXML
    private TableColumn<Student, String> birthdayColumn;
    @FXML
    private TableColumn<Student, String> emailColumn;
    @FXML
    private TableColumn<Student, String> phoneColumn;
    @FXML
    private TableColumn<Student, String> bedIdColumn;

    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private boolean isStudentMode = false;
    private String currentStudentId = null;

    /**
     * 设置为学生模式，只显示指定学生的信息
     */
    public void setStudentMode(String studentId) {
        this.isStudentMode = true;
        this.currentStudentId = studentId;

        // 在学生模式下隐藏管理功能（延迟到initialize之后执行）
        Platform.runLater(() -> {
            if (addButton != null) addButton.setVisible(false);
            if (editButton != null) editButton.setVisible(false);
            if (deleteButton != null) deleteButton.setVisible(false);
        });

        // 重新加载数据
        loadStudents();
    }

    @FXML
    private void initialize() {
        logger.info("学生管理界面初始化开始");

        // 初始化表格列
        setupTableColumns();

        // 初始化搜索类型
        if (searchTypeCombo != null) {
            searchTypeCombo.setItems(FXCollections.observableArrayList("全部", "学号", "姓名", "学院"));
            searchTypeCombo.setValue("全部");
        }

        // 初始化选中项计数
        if (selectedLabel != null) {
            selectedLabel.setText("已选择 0 项");
        }

        // 初始化表格选择监听器
        if (studentTable != null) {
            studentTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
                updateSelectedCount();
            });
        }

        // 加载初始数据
        loadStudents();

        logger.info("学生管理界面初始化完成");
    }

    /**
     * 初始化表格列
     */
    private void setupTableColumns() {
        if (studentIdColumn != null) {
            studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        }
        if (nameColumn != null) {
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        }
        if (collegeColumn != null) {
            collegeColumn.setCellValueFactory(new PropertyValueFactory<>("collegeName"));
        }
        if (classColumn != null) {
            classColumn.setCellValueFactory(new PropertyValueFactory<>("className"));
        }
        if (genderColumn != null) {
            genderColumn.setCellValueFactory(cellData -> {
                String gender = cellData.getValue().getGender();
                String displayGender = "M".equals(gender) ? "男" : "F".equals(gender) ? "女" : gender;
                return new javafx.beans.property.SimpleStringProperty(displayGender);
            });
        }
        if (birthdayColumn != null) {
            birthdayColumn.setCellValueFactory(cellData -> {
                Student student = cellData.getValue();
                java.util.Date birthday = student.getBirthday();
                if (birthday != null) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    return new javafx.beans.property.SimpleStringProperty(sdf.format(birthday));
                } else {
                    return new javafx.beans.property.SimpleStringProperty("");
                }
            });
        }
        if (emailColumn != null) {
            emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        }
        if (phoneColumn != null) {
            phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        }
        if (bedIdColumn != null) {
            bedIdColumn.setCellValueFactory(new PropertyValueFactory<>("bedId"));
        }

        // 设置表格数据
        if (studentTable != null) {
            studentTable.setItems(studentData);
        }
    }

    /**
     * 加载学生数据
     */
    private void loadStudents() {
        try {
            // #region agent log
            logDebugEvent("StudentManagementController.java:150", "开始创建StudentController实例", java.util.Map.of(), "C");
            // #endregion

            // 按需创建Controller实例，避免在应用启动时就连接数据库
            StudentController studentController = new StudentController();

            List<Student> students;

            if (isStudentMode && currentStudentId != null) {
                // 学生模式：只加载当前学生的信息
                Student student = studentController.getStudentById(currentStudentId);
                students = student != null ? List.of(student) : List.of();
            } else {
                // 管理员模式：加载所有学生信息
                students = studentController.getAllStudents();
            }

            studentData.clear();
            studentData.addAll(students);

            String statusText = isStudentMode ? "个人信息" : "共 " + students.size() + " 条记录";
            if (statusLabel != null) {
                statusLabel.setText(statusText);
            }

            updateLastUpdateTime();

            // 重置选中计数
            if (selectedLabel != null) {
                selectedLabel.setText("已选择 0 项");
            }

            showMessage("数据加载完成", "success");

            logger.info("学生数据加载成功：{}条记录", students.size());

        } catch (Exception e) {
            // #region agent log
            logDebugEvent("StudentManagementController.java:182", "加载学生数据失败", java.util.Map.of("error", e.getMessage(), "errorType", e.getClass().getSimpleName()), "C");
            // #endregion

            logger.error("加载学生数据失败", e);
            showMessage("加载数据失败：" + e.getMessage(), "error");
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
        if (studentTable != null && selectedLabel != null) {
            int selectedCount = studentTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("已选择 " + selectedCount + " 项");
        }
    }


    /**
     * 显示添加学生对话框
     */
    @FXML
    private void showAddDialog(ActionEvent event) {
        // 创建对话框
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("添加学生");
        dialog.setHeaderText("请输入新学生的信息");

        // 设置按钮
        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField();
        studentIdField.setPromptText("学号（如：2023001）");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("登录密码");
        TextField nameField = new TextField();
        nameField.setPromptText("学生姓名");
        TextField collegeField = new TextField();
        collegeField.setPromptText("学院名称（如：计算机学院）");
        TextField classField = new TextField();
        classField.setPromptText("班级（如：计算机2101）");
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.setItems(FXCollections.observableArrayList("男", "女"));
        genderCombo.setValue("男");
        DatePicker birthdayPicker = new DatePicker();
        birthdayPicker.setPromptText("生日（可选）");
        TextField emailField = new TextField();
        emailField.setPromptText("电子邮箱（可选）");
        TextField phoneField = new TextField();
        phoneField.setPromptText("联系电话（可选）");
        TextField bedIdField = new TextField();
        bedIdField.setPromptText("床位号（如：A101-001，可选）");

        grid.add(new Label("学号*:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("密码*:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("姓名*:"), 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(new Label("学院*:"), 0, 3);
        grid.add(collegeField, 1, 3);
        grid.add(new Label("班级*:"), 0, 4);
        grid.add(classField, 1, 4);
        grid.add(new Label("性别*:"), 0, 5);
        grid.add(genderCombo, 1, 5);
        grid.add(new Label("生日:"), 0, 6);
        grid.add(birthdayPicker, 1, 6);
        grid.add(new Label("邮箱:"), 0, 7);
        grid.add(emailField, 1, 7);
        grid.add(new Label("电话:"), 0, 8);
        grid.add(phoneField, 1, 8);
        grid.add(new Label("床位号:"), 0, 9);
        grid.add(bedIdField, 1, 9);

        dialog.getDialogPane().setContent(grid);

        // 验证输入
        dialog.getDialogPane().lookupButton(saveButtonType).setDisable(true);
        java.util.function.Consumer<String> validateInput = (s) -> {
            String studentId = studentIdField.getText().trim();
            boolean disable = studentId.isEmpty() ||
                             passwordField.getText().trim().isEmpty() ||
                             nameField.getText().trim().isEmpty() ||
                             collegeField.getText().trim().isEmpty() ||
                             classField.getText().trim().isEmpty() ||
                             genderCombo.getValue() == null ||
                             !isValidStudentId(studentId);
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        };

        studentIdField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        passwordField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        nameField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        collegeField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        classField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        genderCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateInput.accept(""));

        // 处理结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Student student = new Student();
                student.setStudentId(studentIdField.getText().trim());
                student.setPassword(passwordField.getText().trim());
                student.setName(nameField.getText().trim());
                student.setCollegeName(collegeField.getText().trim());
                student.setClassName(classField.getText().trim());
                student.setGender(genderCombo.getValue());
                if (birthdayPicker.getValue() != null) {
                    student.setBirthday(java.sql.Date.valueOf(birthdayPicker.getValue()));
                }
                student.setEmail(emailField.getText().trim().isEmpty() ? null : emailField.getText().trim());
                student.setPhone(phoneField.getText().trim().isEmpty() ? null : phoneField.getText().trim());
                String bedId = bedIdField.getText().trim();
                if (!bedId.isEmpty() && isBedOccupied(bedId)) {
                    throw new IllegalArgumentException("该床位已被占用，请选择其他床位！");
                }
                student.setBedId(bedId.isEmpty() ? null : bedId);
                return student;
            }
            return null;
        });

        java.util.Optional<Student> result = dialog.showAndWait();
        result.ifPresent(student -> {
            try {
                StudentController studentController = new StudentController();
                boolean success = studentController.addStudent(student);
                if (success) {
                    showMessage("添加学生成功！", "success");
                    loadStudents(); // 刷新数据
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyStudentDataChanged("add");
                    logger.info("添加学生成功：{}", student.getStudentId());
                } else {
                    showMessage("添加学生失败，可能学号已存在", "error");
                }
            } catch (Exception e) {
                logger.error("添加学生失败", e);
                showMessage("添加学生失败：" + e.getMessage(), "error");
            }
        });
    }

    /**
     * 显示编辑学生对话框
     */
    @FXML
    private void showEditDialog(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showMessage("请先选择要编辑的学生！", "warning");
            return;
        }

        // 创建对话框
        Dialog<Student> dialog = new Dialog<>();
        dialog.setTitle("编辑学生");
        dialog.setHeaderText("修改学生信息");

        // 设置按钮
        ButtonType saveButtonType = new ButtonType("保存", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(saveButtonType, ButtonType.CANCEL);

        // 创建表单
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField studentIdField = new TextField(selectedStudent.getStudentId());
        studentIdField.setEditable(false); // 学号不允许修改
        studentIdField.setStyle("-fx-background-color: #f5f5f5;");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("登录密码（留空表示不修改）");
        TextField nameField = new TextField(selectedStudent.getName());
        nameField.setPromptText("学生姓名");
        TextField collegeField = new TextField(selectedStudent.getCollegeName());
        collegeField.setPromptText("学院名称（如：计算机学院）");
        TextField classField = new TextField(selectedStudent.getClassName());
        classField.setPromptText("班级（如：计算机2101）");
        ComboBox<String> genderCombo = new ComboBox<>();
        genderCombo.setItems(FXCollections.observableArrayList("男", "女"));
        genderCombo.setValue(selectedStudent.getGender());
        DatePicker birthdayPicker = new DatePicker();
        if (selectedStudent.getBirthday() != null) {
            birthdayPicker.setValue(new java.sql.Date(selectedStudent.getBirthday().getTime()).toLocalDate());
        }
        birthdayPicker.setPromptText("生日（可选）");
        TextField emailField = new TextField(selectedStudent.getEmail() != null ? selectedStudent.getEmail() : "");
        emailField.setPromptText("电子邮箱（可选）");
        TextField phoneField = new TextField(selectedStudent.getPhone() != null ? selectedStudent.getPhone() : "");
        phoneField.setPromptText("联系电话（可选）");
        TextField bedIdField = new TextField(selectedStudent.getBedId() != null ? selectedStudent.getBedId() : "");
        bedIdField.setPromptText("床位号（如：A101-001，可选）");

        grid.add(new Label("学号*:"), 0, 0);
        grid.add(studentIdField, 1, 0);
        grid.add(new Label("密码:"), 0, 1);
        grid.add(passwordField, 1, 1);
        grid.add(new Label("姓名*:"), 0, 2);
        grid.add(nameField, 1, 2);
        grid.add(new Label("学院*:"), 0, 3);
        grid.add(collegeField, 1, 3);
        grid.add(new Label("班级*:"), 0, 4);
        grid.add(classField, 1, 4);
        grid.add(new Label("性别*:"), 0, 5);
        grid.add(genderCombo, 1, 5);
        grid.add(new Label("生日:"), 0, 6);
        grid.add(birthdayPicker, 1, 6);
        grid.add(new Label("邮箱:"), 0, 7);
        grid.add(emailField, 1, 7);
        grid.add(new Label("电话:"), 0, 8);
        grid.add(phoneField, 1, 8);
        grid.add(new Label("床位号:"), 0, 9);
        grid.add(bedIdField, 1, 9);

        dialog.getDialogPane().setContent(grid);

        // 验证输入
        dialog.getDialogPane().lookupButton(saveButtonType).setDisable(true);
        java.util.function.Consumer<String> validateInput = (s) -> {
            boolean disable = nameField.getText().trim().isEmpty() ||
                             collegeField.getText().trim().isEmpty() ||
                             classField.getText().trim().isEmpty() ||
                             genderCombo.getValue() == null;
            dialog.getDialogPane().lookupButton(saveButtonType).setDisable(disable);
        };

        nameField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        collegeField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        classField.textProperty().addListener((obs, oldText, newText) -> validateInput.accept(newText));
        genderCombo.valueProperty().addListener((obs, oldVal, newVal) -> validateInput.accept(""));

        // 处理结果
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == saveButtonType) {
                Student student = new Student();
                student.setStudentId(selectedStudent.getStudentId()); // 学号保持不变
                // 如果密码为空，保持原有密码
                String newPassword = passwordField.getText().trim();
                if (!newPassword.isEmpty()) {
                    // 验证新密码长度
                    if (newPassword.length() < 6) {
                        showMessage("密码长度至少6位！", "warning");
                        return null;
                    }
                    student.setPassword(newPassword);
                } else {
                    // 保持原有密码
                    student.setPassword(selectedStudent.getPassword());
                }
                student.setName(nameField.getText().trim());
                student.setCollegeName(collegeField.getText().trim());
                student.setClassName(classField.getText().trim());
                student.setGender(genderCombo.getValue());
                if (birthdayPicker.getValue() != null) {
                    student.setBirthday(java.sql.Date.valueOf(birthdayPicker.getValue()));
                }
                student.setEmail(emailField.getText().trim().isEmpty() ? null : emailField.getText().trim());
                student.setPhone(phoneField.getText().trim().isEmpty() ? null : phoneField.getText().trim());
                String bedId = bedIdField.getText().trim();
                if (!bedId.isEmpty()) {
                    // 验证床位是否已被占用（排除当前学生的床位）
                    if (isBedOccupied(bedId) && !bedId.equals(selectedStudent.getBedId())) {
                        showMessage("该床位已被占用，请选择其他床位！", "warning");
                        return null;
                    }
                }
                student.setBedId(bedId.isEmpty() ? null : bedId);
                return student;
            }
            return null;
        });

        java.util.Optional<Student> result = dialog.showAndWait();
        result.ifPresent(student -> {
            try {
                StudentController studentController = new StudentController();
                boolean success = studentController.updateStudent(student);
                if (success) {
                    showMessage("编辑学生成功！", "success");
                    loadStudents(); // 刷新数据
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyStudentDataChanged("update");
                    logger.info("编辑学生成功：{}", student.getStudentId());
                } else {
                    showMessage("编辑学生失败", "error");
                }
            } catch (Exception e) {
                logger.error("编辑学生失败", e);
                showMessage("编辑学生失败：" + e.getMessage(), "error");
            }
        });
    }

    /**
     * 删除学生
     */
    @FXML
    private void deleteStudent(ActionEvent event) {
        Student selectedStudent = studentTable.getSelectionModel().getSelectedItem();
        if (selectedStudent == null) {
            showMessage("请先选择要删除的学生！", "warning");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认删除");
        alert.setHeaderText("确定要删除学生吗？");
        alert.setContentText("学号: " + selectedStudent.getStudentId() + "\n姓名: " + selectedStudent.getName());

        java.util.Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                StudentController studentController = new StudentController();
                boolean success = studentController.deleteStudent(selectedStudent.getStudentId());
                if (success) {
                    showMessage("删除学生成功！", "success");
                    loadStudents(); // 刷新数据
                    // 通知其他界面数据已更新
                    DataUpdateManager.getInstance().notifyStudentDataChanged("delete");
                } else {
                    showMessage("删除学生失败", "error");
                }
            } catch (Exception e) {
                showMessage("删除学生失败：" + e.getMessage(), "error");
            }
        }
    }

    /**
     * 刷新学生列表
     */
    @FXML
    private void refreshStudents(ActionEvent event) {
        loadStudents();
    }

    /**
     * 搜索学生
     */
    @FXML
    private void searchStudents(ActionEvent event) {
        String searchText = searchField.getText().toLowerCase().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            loadStudents(); // 如果搜索为空，显示所有记录
            return;
        }

        try {
            StudentController studentController = new StudentController();
            List<Student> allStudents = studentController.getAllStudents();
            List<Student> filteredStudents;

            switch (searchType) {
                case "学号":
                    filteredStudents = allStudents.stream()
                        .filter(s -> s.getStudentId().toLowerCase().contains(searchText))
                        .toList();
                    break;
                case "姓名":
                    filteredStudents = allStudents.stream()
                        .filter(s -> s.getName().toLowerCase().contains(searchText))
                        .toList();
                    break;
                default:
                    // 全部搜索
                    filteredStudents = allStudents.stream()
                        .filter(s -> s.getStudentId().toLowerCase().contains(searchText) ||
                                   s.getName().toLowerCase().contains(searchText) ||
                                   s.getCollegeName().toLowerCase().contains(searchText))
                        .toList();
                    break;
            }

            studentData.clear();
            studentData.addAll(filteredStudents);
            statusLabel.setText("搜索到 " + filteredStudents.size() + " 条记录");
            showMessage("搜索完成", "success");

        } catch (Exception e) {
            logger.error("搜索学生失败", e);
            showMessage("搜索失败：" + e.getMessage(), "error");
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
                    messageLabel.setText("就绪");
                    messageLabel.getStyleClass().removeAll("success", "error", "warning");
                });
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
    }

    /**
     * 验证学号格式（中北大学学号格式：24开头8位数字）
     */
    private boolean isValidStudentId(String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return false;
        }
        // 学号长度应该在6-12位之间
        if (studentId.length() < 6 || studentId.length() > 12) {
            return false;
        }
        // 检查是否都是数字或字母的组合（允许字母如计算机专业等）
        return studentId.matches("[a-zA-Z0-9]+");
    }

    /**
     * 检查床位是否已被占用
     */
    private boolean isBedOccupied(String bedId) {
        if (bedId == null || bedId.trim().isEmpty()) {
            return false;
        }

        try {
            // 获取所有学生，检查是否有学生占用此床位
            StudentController studentController = new StudentController();
            List<Student> allStudents = studentController.getAllStudents();
            for (Student student : allStudents) {
                if (bedId.equals(student.getBedId())) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            logger.error("检查床位占用状态失败: {}", bedId, e);
            return false; // 出错时假设未占用，避免误判
        }
    }

    /**
     * 分页相关方法（暂时简化）
     */
    @FXML
    private void goToFirstPage(ActionEvent event) {
        // TODO: 实现分页功能
        showMessage("分页功能暂未实现", "info");
    }

    @FXML
    private void goToPrevPage(ActionEvent event) {
        // TODO: 实现分页功能
        showMessage("分页功能暂未实现", "info");
    }

    @FXML
    private void goToNextPage(ActionEvent event) {
        // TODO: 实现分页功能
        showMessage("分页功能暂未实现", "info");
    }

    @FXML
    private void goToLastPage(ActionEvent event) {
        // TODO: 实现分页功能
        showMessage("分页功能暂未实现", "info");
    }

    @FXML
    private void changePageSize(ActionEvent event) {
        // TODO: 实现分页功能
        showMessage("分页功能暂未实现", "info");
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        showMessage(message, "info");
    }

    /**
     * 记录调试事件
     */
    private void logDebugEvent(String location, String message, java.util.Map<String, Object> data, String hypothesisId) {
        try {
            String logEntry = String.format("{\"id\":\"log_%d_%s\",\"timestamp\":%d,\"location\":\"%s\",\"message\":\"%s\",\"data\":%s,\"sessionId\":\"debug-session\",\"runId\":\"initial-test\",\"hypothesisId\":\"%s\"}",
                System.currentTimeMillis(),
                java.util.UUID.randomUUID().toString().substring(0, 6),
                System.currentTimeMillis(),
                location,
                message,
                data.toString().replace("=", "\":\"").replace("{", "{\"").replace("}", "\"}").replace(", ", "\",\""),
                hypothesisId
            );
            java.nio.file.Files.writeString(
                java.nio.file.Paths.get("c:\\Users\\任万博\\Desktop\\课设\\dormitory-management-system\\.cursor\\debug.log"),
                logEntry + "\n",
                java.nio.file.StandardOpenOption.CREATE,
                java.nio.file.StandardOpenOption.APPEND
            );
        } catch (Exception e) {
            logger.warn("记录调试日志失败", e);
        }
    }
}