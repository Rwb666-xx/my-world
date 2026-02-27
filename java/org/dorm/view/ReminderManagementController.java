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
import org.dorm.controller.ReminderController;
import org.dorm.model.entity.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

/**
 * 提醒管理界面控制器
 */
public class ReminderManagementController {
    private static final Logger logger = LoggerFactory.getLogger(ReminderManagementController.class);

    // 控制器
    private ReminderController reminderController = new ReminderController();

    // 分页相关
    private int currentPage = 1;
    private int pageSize = 10;
    private int totalRecords = 0;
    private int totalPages = 1;

    // 工具栏
    @FXML private Button refreshButton;
    @FXML private Button checkAllButton;
    @FXML private Button markProcessedButton;
    @FXML private Button deleteProcessedButton;
    @FXML private TextField searchField;
    @FXML private ComboBox<String> searchTypeCombo;
    @FXML private Button searchButton;
    @FXML private Label statusLabel;
    @FXML private Label selectedLabel;

    // 表格
    @FXML private TableView<Reminder> reminderTable;
    @FXML private TableColumn<Reminder, String> reminderIdColumn;
    @FXML private TableColumn<Reminder, String> studentIdColumn;
    @FXML private TableColumn<Reminder, String> studentNameColumn;
    @FXML private TableColumn<Reminder, String> typeColumn;
    @FXML private TableColumn<Reminder, String> titleColumn;
    @FXML private TableColumn<Reminder, String> priorityColumn;
    @FXML private TableColumn<Reminder, String> statusColumn;
    @FXML private TableColumn<Reminder, String> createTimeColumn;

    // 分页控件
    @FXML private Button firstPageButton;
    @FXML private Button prevPageButton;
    @FXML private Label pageInfoLabel;
    @FXML private Button nextPageButton;
    @FXML private Button lastPageButton;
    @FXML private ComboBox<String> pageSizeCombo;

    // 详情区域
    @FXML private TextArea reminderDetailArea;

    private ObservableList<Reminder> reminderData = FXCollections.observableArrayList();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @FXML
    private void initialize() {
        logger.info("初始化提醒管理界面...");

        // 初始化表格列
        reminderIdColumn.setCellValueFactory(new PropertyValueFactory<>("reminderId"));
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        priorityColumn.setCellValueFactory(new PropertyValueFactory<>("priority"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        createTimeColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue().getCreateTime() != null) {
                return new javafx.beans.property.SimpleStringProperty(
                    dateFormat.format(cellData.getValue().getCreateTime()));
            }
            return new javafx.beans.property.SimpleStringProperty("");
        });

        // 设置表格数据
        reminderTable.setItems(reminderData);

        // 初始化下拉框
        searchTypeCombo.setValue("全部");
        pageSizeCombo.setValue("10");

        // 添加表格选择监听器
        reminderTable.getSelectionModel().selectedItemProperty().addListener(
            (obs, oldSelection, newSelection) -> {
                if (newSelection != null) {
                    showReminderDetail(newSelection);
                    updateSelectedLabel();
                }
            });

        // 加载初始数据
        refreshReminders();

        logger.info("提醒管理界面初始化完成");
    }

    /**
     * 刷新提醒列表
     */
    @FXML
    private void refreshReminders() {
        try {
            logger.info("刷新提醒列表...");

            // 获取所有提醒
            List<Reminder> reminders = reminderController.getAllReminders();
            logger.info("从数据库获取到 {} 条提醒记录", reminders.size());

            // 更新数据
            reminderData.clear();
            reminderData.addAll(reminders);

            // 更新状态信息
            totalRecords = reminders.size();
            updateStatusLabels();
            updatePagination();

            // 调试信息
            if (reminders.isEmpty()) {
                showMessage("提醒列表为空，请检查数据库连接或运行数据初始化脚本");
                logger.warn("提醒列表为空，检查数据库中的提醒数据");
            } else {
                showMessage("提醒列表刷新完成，共" + reminders.size() + "条记录");
                logger.info("提醒列表刷新完成，共{}条记录", reminders.size());
            }

        } catch (Exception e) {
            logger.error("刷新提醒列表失败", e);
            showError("刷新失败：" + e.getMessage());
        }
    }

    /**
     * 检查所有提醒（重新生成）
     */
    @FXML
    private void checkAllReminders() {
        try {
            logger.info("开始检查所有提醒...");

            // 首先检查数据库连接
            try {
                org.dorm.util.DatabaseUtil.getConnection();
                logger.info("数据库连接正常");
            } catch (Exception dbEx) {
                logger.error("数据库连接失败", dbEx);
                showError("数据库连接失败：" + dbEx.getMessage());
                return;
            }

            // 执行完整的提醒检查
            int totalReminders = reminderController.refreshAllReminders();

            // 刷新显示
            refreshReminders();

            showMessage("提醒检查完成，共生成" + totalReminders + "条新提醒");

            logger.info("提醒检查完成，共生成{}条新提醒", totalReminders);

        } catch (Exception e) {
            logger.error("检查提醒失败", e);
            showError("检查失败：" + e.getMessage());
        }
    }

    /**
     * 标记为已处理
     */
    @FXML
    private void markAsProcessed() {
        Reminder selectedReminder = reminderTable.getSelectionModel().getSelectedItem();
        if (selectedReminder == null) {
            showError("请先选择要处理的提醒");
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认处理");
        alert.setHeaderText("确定要标记这条提醒为已处理吗？");
        alert.setContentText("提醒ID: " + selectedReminder.getReminderId() + "\n学生: " + selectedReminder.getStudentName());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // 更新提醒状态
                selectedReminder.setStatus("已处理");
                selectedReminder.setHandleTime(new java.util.Date());
                selectedReminder.setHandler("管理员"); // 应该从当前登录用户获取

                boolean success = reminderController.updateReminder(selectedReminder);
                if (success) {
                    refreshReminders();
                    showMessage("提醒已标记为已处理");
                    logger.info("提醒{}已标记为已处理", selectedReminder.getReminderId());
                } else {
                    showError("标记处理失败");
                }

            } catch (Exception e) {
                logger.error("标记提醒处理失败", e);
                showError("操作失败：" + e.getMessage());
            }
        }
    }

    /**
     * 删除已处理的提醒
     */
    @FXML
    private void deleteProcessed() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("确认删除");
        alert.setHeaderText("确定要删除所有已处理的提醒吗？");
        alert.setContentText("此操作不可恢复");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                int deletedCount = reminderController.deleteProcessedReminders();

                if (deletedCount > 0) {
                    refreshReminders();
                    showMessage("成功删除了" + deletedCount + "条已处理的提醒");
                    logger.info("删除了{}条已处理的提醒", deletedCount);
                } else {
                    showMessage("没有已处理的提醒需要删除");
                }

            } catch (Exception e) {
                logger.error("删除已处理提醒失败", e);
                showError("删除失败：" + e.getMessage());
            }
        }
    }

    /**
     * 搜索提醒
     */
    @FXML
    private void searchReminders() {
        String searchText = searchField.getText().trim();
        String searchType = searchTypeCombo.getValue();

        if (searchText.isEmpty()) {
            refreshReminders();
            return;
        }

        try {
            logger.info("搜索提醒：类型={}, 关键词={}", searchType, searchText);

            List<Reminder> allReminders = reminderController.getAllReminders();
            reminderData.clear();

            for (Reminder reminder : allReminders) {
                boolean matches = false;

                switch (searchType) {
                    case "全部":
                        matches = reminder.getStudentId().contains(searchText) ||
                                 reminder.getStudentName().contains(searchText) ||
                                 reminder.getType().contains(searchText) ||
                                 reminder.getTitle().contains(searchText) ||
                                 reminder.getContent().contains(searchText);
                        break;
                    case "学号":
                        matches = reminder.getStudentId().contains(searchText);
                        break;
                    case "姓名":
                        matches = reminder.getStudentName().contains(searchText);
                        break;
                    case "类型":
                        matches = reminder.getType().contains(searchText);
                        break;
                }

                if (matches) {
                    reminderData.add(reminder);
                }
            }

            totalRecords = reminderData.size();
            updateStatusLabels();
            updatePagination();

            showMessage("搜索完成，找到" + reminderData.size() + "条记录");

        } catch (Exception e) {
            logger.error("搜索提醒失败", e);
            showError("搜索失败：" + e.getMessage());
        }
    }

    /**
     * 显示提醒详情
     */
    private void showReminderDetail(Reminder reminder) {
        if (reminderDetailArea != null && reminder != null) {
            StringBuilder detail = new StringBuilder();
            detail.append("提醒ID: ").append(reminder.getReminderId()).append("\n");
            detail.append("学生: ").append(reminder.getStudentName()).append(" (").append(reminder.getStudentId()).append(")\n");
            detail.append("类型: ").append(reminder.getType()).append("\n");
            detail.append("标题: ").append(reminder.getTitle()).append("\n");
            detail.append("优先级: ").append(reminder.getPriority()).append("\n");
            detail.append("状态: ").append(reminder.getStatus()).append("\n");
            detail.append("创建时间: ").append(dateFormat.format(reminder.getCreateTime())).append("\n");

            if (reminder.getHandleTime() != null) {
                detail.append("处理时间: ").append(dateFormat.format(reminder.getHandleTime())).append("\n");
                detail.append("处理人: ").append(reminder.getHandler()).append("\n");
            }

            detail.append("\n详细内容:\n").append(reminder.getContent());

            reminderDetailArea.setText(detail.toString());
        }
    }

    /**
     * 分页相关方法
     */
    @FXML private void goToFirstPage() { currentPage = 1; updatePagination(); }
    @FXML private void goToPrevPage() { if (currentPage > 1) currentPage--; updatePagination(); }
    @FXML private void goToNextPage() { if (currentPage < totalPages) currentPage++; updatePagination(); }
    @FXML private void goToLastPage() { currentPage = totalPages; updatePagination(); }

    @FXML
    private void changePageSize() {
        try {
            pageSize = Integer.parseInt(pageSizeCombo.getValue());
            currentPage = 1;
            updatePagination();
        } catch (NumberFormatException e) {
            logger.error("无效的页面大小", e);
        }
    }

    /**
     * 更新分页显示
     */
    private void updatePagination() {
        totalPages = (int) Math.ceil((double) totalRecords / pageSize);
        if (currentPage > totalPages) currentPage = totalPages;
        if (currentPage < 1) currentPage = 1;

        pageInfoLabel.setText("第 " + currentPage + " 页 / 共 " + totalPages + " 页");

        // 更新按钮状态
        firstPageButton.setDisable(currentPage <= 1);
        prevPageButton.setDisable(currentPage <= 1);
        nextPageButton.setDisable(currentPage >= totalPages);
        lastPageButton.setDisable(currentPage >= totalPages);
    }

    /**
     * 更新状态标签
     */
    private void updateStatusLabels() {
        if (statusLabel != null) {
            statusLabel.setText("共 " + totalRecords + " 条记录");
        }
        updateSelectedLabel();
    }

    /**
     * 更新选择状态标签
     */
    private void updateSelectedLabel() {
        if (selectedLabel != null) {
            int selectedCount = reminderTable.getSelectionModel().getSelectedItems().size();
            selectedLabel.setText("已选择 " + selectedCount + " 项");
        }
    }

    /**
     * 显示消息
     */
    private void showMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("提示");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * 显示错误消息
     */
    private void showError(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("错误");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}