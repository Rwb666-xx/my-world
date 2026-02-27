// 声明当前类所在的包路径，util包用于存放项目的工具类
package org.dorm.util;

// 导入java.sql包下的Connection接口，该接口用于表示Java程序与数据库的物理连接
import java.sql.Connection;
// 导入java.sql包下的DriverManager类，该类是JDBC的驱动管理类，用于加载驱动并获取数据库连接
import java.sql.DriverManager;
// 导入java.sql包下的SQLException异常类，JDBC相关数据库操作的异常统一抛出该异常
import java.sql.SQLException;
// 导入SLF4J日志框架的Logger接口，用于日志打印
import org.slf4j.Logger;
// 导入SLF4J日志框架的LoggerFactory工厂类，用于获取Logger实例对象
import org.slf4j.LoggerFactory;

/**
 * 数据库连接工具类
 * 负责数据库连接的获取、关闭、连接测试等统一管理，提供静态工具方法供全局调用
 */
public class DatabaseUtil {
    // 声明并初始化SLF4J的日志记录器，绑定当前DatabaseUtil类，用于打印当前类的日志信息
    private static final Logger logger = LoggerFactory.getLogger(DatabaseUtil.class);

    private static final String URL = "jdbc:mysql://localhost:3306/dormitory_management?useSSL=false&serverTimezone=Asia/Shanghai&allowPublicKeyRetrieval=true";
    // 数据库登录用户名，root为MySQL默认超级管理员账号
    private static final String USER = "root";
    // 数据库登录密码
    private static final String PASSWORD = "123456";

    // 数据库驱动类全限定类名，MySQL8.x版本的驱动类是com.mysql.cj.jdbc.Driver，5.x版本是com.mysql.jdbc.Driver
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    // 静态初始化块，特点：属于类本身，在当前类第一次被加载到内存时执行，且只执行一次
    // 作用：项目启动时提前加载数据库驱动，避免每次获取连接都加载，提升性能
    static {
        try {
            // Class.forName() 反射加载指定的驱动类到JVM内存中
            // 加载后驱动类会自动注册到DriverManager中，供后续获取连接使用
            Class.forName(DRIVER);
            // 日志打印：驱动加载成功的INFO级别日志，便于排查启动问题
            logger.info("数据库驱动加载成功");
        } catch (ClassNotFoundException e) {
            // 捕获驱动类加载失败的异常（如驱动jar包缺失、类路径写错）
            // 打印ERROR级别日志，传入异常对象，可完整打印堆栈信息
            logger.error("数据库驱动加载失败", e);
            // 抛出运行时异常，终止程序启动，因为驱动加载失败后续无法进行任何数据库操作
            throw new RuntimeException("数据库驱动加载失败", e);
        }
    }

    /**
     * 获取数据库连接的核心工具方法
     * @return Connection 数据库连接对象，通过该对象可以执行SQL语句、操作数据库
     * @throws SQLException 抛出数据库连接异常，交给调用者处理，不在这里捕获吞掉异常
     */
    public static Connection getConnection() throws SQLException {
        try {
            // 调用DriverManager的静态方法，传入URL、用户名、密码，获取数据库连接对象
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 打印DEBUG级别日志，标记连接成功，DEBUG级别用于详细调试信息
            logger.debug("数据库连接成功");
            // 返回获取到的数据库连接对象
            return conn;
        } catch (SQLException e) {
            // 捕获数据库连接失败的异常（如地址错误、账号密码错误、数据库未启动等）
            logger.error("数据库连接失败", e);
            // 把异常原封不动抛出，让调用者感知连接失败并处理
            throw e;
        }
    }

    /**
     * 关闭数据库连接的工具方法，释放数据库连接资源
     * 数据库连接是稀缺资源，使用完毕必须关闭，否则会造成连接泄漏，最终数据库连接池耗尽
     * @param conn 要关闭的数据库连接对象，可以为null
     */
    public static void closeConnection(Connection conn) {
        // 非空判断：避免传入null时调用close()方法抛出空指针异常
        if (conn != null) {
            try {
                // 调用连接对象的close()方法，释放物理连接，归还到数据库连接池
                conn.close();
                // 打印DEBUG级别日志，标记连接已关闭
                logger.debug("数据库连接已关闭");
            } catch (SQLException e) {
                // 捕获关闭连接时的异常（如连接已失效、网络中断等）
                logger.error("关闭数据库连接失败", e);
            }
        }
    }

    /**
     * 测试数据库连接是否可用的工具方法
     * 用于项目自检、健康检查，快速判断数据库配置是否正确、数据库服务是否正常
     * @return boolean 连接测试结果：true=连接成功，false=连接失败
     */
    public static boolean testConnection() {
        // 声明连接对象，初始值为null，用于接收获取到的连接
        Connection conn = null;
        try {
            // 调用本类的getConnection()方法，获取数据库连接
            conn = getConnection();
            // 判断连接是否有效：连接对象不为空 并且 连接未被关闭
            boolean isConnected = conn != null && !conn.isClosed();
            // 根据连接状态打印对应日志
            if (isConnected) {
                logger.info("数据库连接测试成功");
            } else {
                logger.error("数据库连接测试失败：连接为空或已关闭");
            }
            // 返回连接测试结果
            return isConnected;
        } catch (SQLException e) {
            // 捕获连接测试过程中的所有数据库异常
            logger.error("数据库连接测试失败", e);
            // 连接失败返回false
            return false;
        } finally {
            // finally代码块：无论try/catch是否执行、是否抛出异常，最终都会执行
            // 核心作用：确保连接资源一定会被释放，防止内存泄漏
            closeConnection(conn);
        }
    }
}