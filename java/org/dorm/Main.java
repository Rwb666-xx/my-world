package org.dorm;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * 宿舍管理系统主应用程序类
 */
public class Main extends Application {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    @Override
    public void start(Stage primaryStage) {
        try {
            // 测试数据库连接
            if (!DatabaseUtil.testConnection()) {
                logger.error("数据库连接失败，请检查数据库配置和MySQL服务");
                // 显示错误对话框
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("数据库连接错误");
                alert.setHeaderText("无法连接到数据库");
                alert.setContentText("请检查：\n1. MySQL服务是否启动\n2. 数据库配置是否正确\n3. 数据库是否存在");
                alert.showAndWait();
                System.exit(1);
                return;
            }

            // 加载登录界面
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Scene scene = new Scene(loader.load(), 1200, 800); // 设置更大的窗口尺寸

            primaryStage.setTitle("宿舍管理系统 - 登录");
            primaryStage.setScene(scene);
            primaryStage.setResizable(true); // 允许调整窗口大小
            primaryStage.setMinWidth(1000); // 设置最小宽度
            primaryStage.setMinHeight(700); // 设置最小高度
            primaryStage.centerOnScreen(); // 窗口居中显示
            primaryStage.show();

            logger.info("宿舍管理系统启动成功");
        } catch (IOException e) {
            logger.error("加载登录界面失败", e);
            System.exit(1);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}