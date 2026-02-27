# 宿舍管理系统

基于Java + JavaFX + MySQL开发的宿舍管理系统，实现了宿舍资源管理、学生住宿管理、缴费违纪管理、查询统计及维修申请管理等功能。

## 功能特性

### 核心功能
- ✅ 用户登录认证（管理员/学生）
- ✅ 宿舍资源管理
- ✅ 学生住宿管理
- ✅ 缴费记录管理
- ✅ 违纪记录管理
- ✅ 查询统计功能
- ✅ **维修申请管理**（新增功能）

### 技术栈
- Java 11
- JavaFX 17.0.2
- MySQL 8.0+
- Maven 3.6+
- SLF4J日志框架

## 环境要求

- JDK 11 或更高版本
- MySQL 8.0 或更高版本
- Maven 3.6 或更高版本

## 快速开始

### 1. 数据库初始化

1. 启动MySQL服务
2. 创建数据库：
   ```sql
   CREATE DATABASE dormitory_management CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
   ```
3. 执行初始化脚本：
   ```bash
   mysql -u root -p dormitory_management < src/main/resources/database_init.sql
   ```

### 2. 配置数据库连接

编辑 `src/main/java/org/dorm/util/DatabaseUtil.java` 中的数据库连接参数：
```java
private static final String URL = "jdbc:mysql://localhost:3306/dormitory_management?useSSL=false&serverTimezone=Asia/Shanghai";
private static final String USER = "root";  // 替换为你的数据库用户名
private static final String PASSWORD = "123456";  // 替换为你的数据库密码
```

### 3. 编译运行

```bash
# 编译项目
mvn clean compile

# 运行应用程序
mvn javafx:run
```

## 测试账号

### 管理员账号
- 用户名：`admin`
- 密码：`123456`

### 学生账号
- 用户名：`2024001`（张三）
- 密码：`123456`
- 用户名：`2024002`（李四）
- 密码：`123456`

## 项目结构

```
src/main/java/org/dorm/
├── controller/          # 控制器层（业务逻辑）
├── model/               # 模型层
│   ├── dao/            # 数据访问层接口
│   ├── entity/         # 实体类
│   └── service/        # 业务逻辑服务层
├── util/               # 工具类
└── view/               # 视图层（JavaFX控制器）
src/main/resources/
├── fxml/               # JavaFX界面文件
├── css/                # 样式文件
└── database_init.sql   # 数据库初始化脚本
```

## 核心功能说明

### 维修申请管理（新增功能）

#### 学生端功能
- 在线提交维修申请（填写故障位置、描述、联系方式）
- 查看个人维修申请历史
- 实时查看申请处理进度

#### 管理员端功能
- 查看所有待处理的维修申请
- 更新申请状态（待受理 → 已受理 → 维修中 → 已完成）
- 记录处理人和完成时间
- 批量管理维修申请

### 系统架构

本系统采用经典的MVC（Model-View-Controller）设计模式：

- **Model（模型层）**：负责数据存储和业务逻辑
- **View（视图层）**：负责用户界面展示（JavaFX）
- **Controller（控制层）**：负责处理用户请求和协调各层

## 数据库设计

### E-R图

#### 实体及属性
- **用户(User)**: 用户ID(主键), 密码, 用户类型
- **学生(Student)**: 学号(主键), 密码, 姓名, 学院名, 班级号, 性别, 生日, 邮箱, 电话, 床位ID(外键)
- **宿舍(Dormitory)**: 宿舍号(主键), 楼栋, 楼层, 容纳人数, 管理员姓名, 管理员电话
- **床位(Bed)**: 床位号(主键), 宿舍号(外键), 状态, 学生学号(外键)
- **缴费记录(Payment)**: 缴费ID(主键), 学生学号(外键), 金额, 缴费日期, 学期, 状态
- **违纪记录(Violation)**: 违纪ID(主键), 学生学号(外键), 描述, 违纪日期, 处罚结果
- **维修申请(RepairApplication)**: 申请ID(主键), 学生学号(外键), 故障位置, 故障描述, 联系电话, 申请时间, 状态, 处理人, 完成时间

#### 实体关系
```
┌─────────┐     ┌─────────┐
│   用户  │─────│   学生  │
│ (User)  │1:1  │(Student)│
└─────────┘     └─────────┘
                    │
                    │1:*
                    ▼
┌─────────┐     ┌─────────┐     ┌─────────┐
│ 缴费记录 │     │ 违纪记录 │     │ 维修申请 │
│(Payment)│     │(Violation│     │(RepairApp│
└─────────┘     └─────────┘     └─────────┘
                    ▲
                    │1:*
               ┌─────────┐
               │   床位  │
               │  (Bed)  │
               └─────────┘
                    ▲
                    │1:*
               ┌─────────┐
               │   宿舍  │
               │(Dormitory)
               └─────────┘
```

#### 关系说明
- 用户与学生：一对一关系（一个用户账号对应一个学生或管理员）
- 学生与床位：多对一关系（多个学生可先后占用同一床位）
- 宿舍与床位：一对多关系（一个宿舍包含多个床位）
- 学生与缴费记录：一对多关系（一个学生可有多条缴费记录）
- 学生与违纪记录：一对多关系（一个学生可有多条违纪记录）
- 学生与维修申请：一对多关系（一个学生可提交多条维修申请）

### 核心数据表及数据字典

#### 1. 用户表 (user)
| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
|--------|----------|------|------|------|
| user_id | VARCHAR | 20 | PRIMARY KEY | 用户名，唯一标识系统用户，主键 |
| password | VARCHAR | 50 | NOT NULL | 用户登录密码 |
| user_type | ENUM | - | NOT NULL | 用户类型，取值为'管理员'或'学生' |

#### 2. 学生信息表 (student)
| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
|--------|----------|------|------|------|
| student_id | VARCHAR | 20 | PRIMARY KEY | 学号，唯一标识学生，主键 |
| password | VARCHAR | 50 | NOT NULL | 学生登录密码 |
| name | VARCHAR | 50 | NOT NULL | 学生姓名 |
| college_name | VARCHAR | 50 | NOT NULL | 学生所属学院名 |
| class_name | VARCHAR | 50 | NOT NULL | 学生所属班级号 |
| gender | CHAR | 1 | NOT NULL | 学生性别，取值为'男'或'女' |
| birthday | DATE | - | - | 学生生日 |
| email | VARCHAR | 100 | - | 学生电子邮箱 |
| phone | VARCHAR | 20 | - | 学生联系电话 |
| bed_id | VARCHAR | 20 | FOREIGN KEY | 关联的床位号，外键关联bed表 |

#### 3. 宿舍信息表 (dormitory)
| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
|--------|----------|------|------|------|
| dorm_id | VARCHAR | 20 | PRIMARY KEY | 宿舍号，唯一标识宿舍，主键 |
| building | VARCHAR | 50 | NOT NULL | 宿舍楼栋名称或编号 |
| floor | INT | - | NOT NULL | 宿舍所在楼层 |
| capacity | INT | - | NOT NULL | 宿舍可容纳人数 |
| manager_name | VARCHAR | 50 | - | 宿舍管理员姓名 |
| manager_phone | VARCHAR | 20 | - | 宿舍管理员联系电话 |

#### 4. 床位信息表 (bed)
| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
|--------|----------|------|------|------|
| bed_id | VARCHAR | 20 | PRIMARY KEY | 床位号，唯一标识床位，主键 |
| dorm_id | VARCHAR | 20 | NOT NULL, FOREIGN KEY | 关联的宿舍号，外键关联dormitory表 |
| status | ENUM | - | NOT NULL, DEFAULT '空闲' | 床位状态，取值为'空闲'或'占用' |
| student_id | VARCHAR | 20 | FOREIGN KEY | 关联的学生学号，外键关联student表 |

#### 5. 缴费记录表 (payment)
| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
|--------|----------|------|------|------|
| payment_id | VARCHAR | 50 | PRIMARY KEY | 缴费记录ID，唯一标识一条缴费记录，主键 |
| student_id | VARCHAR | 20 | NOT NULL, FOREIGN KEY | 关联的学号，外键关联student表 |
| amount | DECIMAL | 10,2 | NOT NULL | 缴费金额，保留2位小数 |
| payment_date | DATE | - | NOT NULL | 缴费日期 |
| semester | VARCHAR | 20 | NOT NULL | 缴费对应的学期 |
| status | ENUM | - | NOT NULL, DEFAULT '未缴' | 缴费状态，取值为'未缴'或'已缴' |

#### 6. 违纪记录表 (violation)
| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
|--------|----------|------|------|------|
| violation_id | VARCHAR | 50 | PRIMARY KEY | 违纪记录ID，唯一标识一条违纪记录，主键 |
| student_id | VARCHAR | 20 | NOT NULL, FOREIGN KEY | 关联的学号，外键关联student表 |
| description | TEXT | - | NOT NULL | 违纪行为描述 |
| violation_date | DATE | - | NOT NULL | 违纪发生日期 |
| penalty | VARCHAR | 100 | - | 对应的处罚措施 |

#### 7. 维修申请表 (repair_application)
| 字段名 | 数据类型 | 长度 | 约束 | 说明 |
|--------|----------|------|------|------|
| apply_id | VARCHAR | 50 | PRIMARY KEY | 申请单号，唯一标识一条维修申请，主键 |
| student_id | VARCHAR | 20 | NOT NULL, FOREIGN KEY | 关联的学号，外键关联student表 |
| fault_location | VARCHAR | 100 | NOT NULL | 故障位置，如'A栋101宿舍灯具' |
| fault_desc | TEXT | - | NOT NULL | 故障描述，如'灯具不亮，无法正常使用' |
| contact_phone | VARCHAR | 20 | NOT NULL | 申请人联系电话 |
| apply_time | DATETIME | - | NOT NULL | 申请提交时间 |
| status | VARCHAR | 20 | NOT NULL, DEFAULT 'pending' | 处理状态 |
| handler | VARCHAR | 50 | - | 处理人，即宿舍管理员姓名 |
| finish_time | DATETIME | - | - | 维修完成时间，未完成时为空 |

## 开发说明

### 添加新的界面

1. 在 `src/main/resources/fxml/` 创建新的FXML文件
2. 在 `src/main/java/org/dorm/view/` 创建对应的Controller类
3. 在 `MainController.java` 中添加导航逻辑

### 添加新的业务功能

1. 在 `model/entity/` 创建新的实体类
2. 在 `model/dao/` 创建DAO接口和实现类
3. 在 `model/service/` 创建Service层
4. 在 `controller/` 创建Controller层
5. 创建相应的界面

## 常见问题

### Q: 编译时出现JavaFX相关错误？
A: 确保JavaFX依赖配置正确，且Java版本为11或更高。

### Q: 数据库连接失败？
A: 检查DatabaseUtil.java中的数据库配置，以及MySQL服务是否启动。

### Q: 界面无法显示？
A: 确保所有FXML文件的controller属性配置正确。

## 许可证

本项目仅用于课程设计和学习目的。

## 作者

任万博 - 2413041837

---

# 个人修改/扩展内容

本章节记录了本人在课程设计过程中对宿舍管理系统的各项修改和功能扩展内容。

## 一、核心功能扩展

### 1. 维修申请管理模块（新增核心功能）



#### 学生端功能
- **在线提交维修申请**：学生可通过图形界面填写故障位置、故障描述、联系方式等信息
- **查看申请历史**：学生可以查看自己提交的所有维修申请记录
- **实时进度跟踪**：实时查看维修申请的处理状态

#### 管理员端功能
- **待处理申请列表**：集中展示所有待处理的维修申请
- **状态更新管理**：支持将申请状态从"待受理"更新为"已受理"、"维修中"、"已完成"
- **处理信息记录**：记录处理人和完成时间

#### 技术实现
- 新增 `RepairApplication` 实体类
- 新增 `RepairApplicationDao` 数据访问接口
- 新增 `RepairApplicationService` 业务逻辑层
- 新增 `RepairApplicationController` 控制层
- 新增 `StudentRepairController.java` 学生端视图控制器
- 新增 `AdminRepairController.java` 管理员端视图控制器
- 新增 `submit_repair.fxml` 学生提交维修申请界面
- 新增 `student_repair.fxml` 学生维修申请列表界面
- 新增 `admin_repair.fxml` 管理员维修管理界面

### 2. 通知提醒功能（新增功能）

#### 功能说明
- 管理员可以向学生发送通知提醒
- 学生可以查看自己的通知列表
- 支持多种提醒类型

#### 新增文件
- 新增 `Reminder` 实体类
- 新增 `ReminderDao`、`ReminderService`
- 新增 `ReminderController`
- 新增 `reminder_management_new.fxml` 界面

### 3. 公告管理功能（新增功能）

#### 功能说明
- 管理员可以发布宿舍管理公告
- 学生可以查看最新公告
- 公告包含标题、内容、发布时间等信息

#### 新增文件
- 新增 `Announcement` 实体类
- 新增 `AnnouncementDao`、`AnnouncementService`
- 新增 `AnnouncementController`

### 4. 住宿管理功能（新增功能）

#### 功能说明
- 完整的住宿登记管理
- 学生入住、退宿操作
- 住宿历史记录查询

#### 新增文件
- 新增 `Accommodation` 实体类
- 新增 `AccommodationDao`、`AccommodationService`
- 新增 `AccommodationController`
- 新增 `accommodation_management.fxml` 界面

## 二、数据库扩展

### 新增数据表

#### 1. 维修申请表 (repair_application)
```sql
CREATE TABLE repair_application (
    apply_id VARCHAR(50) PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    fault_location VARCHAR(100) NOT NULL,
    fault_desc TEXT NOT NULL,
    contact_phone VARCHAR(20) NOT NULL,
    apply_time DATETIME NOT NULL,
    status VARCHAR(20) DEFAULT 'pending',
    handler VARCHAR(50),
    finish_time DATETIME,
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);
```

#### 2. 提醒表 (reminder)
```sql
CREATE TABLE reminder (
    reminder_id VARCHAR(50) PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    create_time DATETIME NOT NULL,
    is_read BOOLEAN DEFAULT FALSE,
    FOREIGN KEY (student_id) REFERENCES student(student_id)
);
```

#### 3. 公告表 (announcement)
```sql
CREATE TABLE announcement (
    announcement_id VARCHAR(50) PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    content TEXT NOT NULL,
    create_time DATETIME NOT NULL,
    publisher VARCHAR(50) NOT NULL
);
```

#### 4. 住宿记录表 (accommodation)
```sql
CREATE TABLE accommodation (
    accommodation_id VARCHAR(50) PRIMARY KEY,
    student_id VARCHAR(20) NOT NULL,
    dorm_id VARCHAR(20) NOT NULL,
    check_in_date DATE NOT NULL,
    check_out_date DATE,
    status VARCHAR(20) DEFAULT '入住中',
    FOREIGN KEY (student_id) REFERENCES student(student_id),
    FOREIGN KEY (dorm_id) REFERENCES dormitory(dorm_id)
);
```

## 三、界面优化与改进

### 1. 登录界面优化
- 美化登录界面布局
- 添加注册功能入口
- 添加密码修改功能

### 2. 主界面优化
- 采用选项卡式导航设计
- 优化菜单结构
- 添加快捷操作按钮

### 3. 学生端界面
- 重新设计学生仪表盘
- 优化信息展示布局
- 增加快捷功能入口

### 4. 统计图表
- 使用 JFreeChart 实现数据可视化
- 添加宿舍入住率统计图表
- 添加学生分布统计图表
- 添加缴费情况统计图表

## 四、代码架构优化

### 1. 分层架构完善
```
src/main/java/org/dorm/
├── controller/          # 控制层 - 处理用户请求
├── model/
│   ├── dao/            # 数据访问层 - 数据库操作
│   ├── entity/         # 实体层 - 数据模型
│   └── service/        # 服务层 - 业务逻辑
├── util/               # 工具类
└── view/               # 视图层 - JavaFX界面控制器
```

### 2. 设计模式应用
- **单例模式**：数据库连接管理 (DatabaseUtil)
- **观察者模式**：数据更新监听 (DataUpdateManager)
- **工厂模式**：业务对象创建

### 3. 代码规范
- 统一的命名规范
- 完整的注释文档
- 统一的异常处理

## 五、安全性增强

### 1. 密码加密
- 使用 MD5 加密存储用户密码
- 密码修改时进行加密处理

### 2. 权限控制
- 管理员和学生功能权限分离
- 界面根据用户角色动态显示

### 3. 输入验证
- 用户输入数据校验
- SQL 注入防护

## 六、可扩展性设计

### 1. 模块化设计
- 各功能模块独立实现
- 便于后续功能扩展

### 2. 配置化管理
- 数据库连接参数集中管理
- 便于环境切换

### 3. 接口设计
- 统一的 DAO 接口规范
- 统一的服务层接口规范

## 七、测试与调试

### 1. 单元测试
- JUnit 测试框架集成
- 核心业务方法测试

### 2. 集成测试
- 数据库连接测试
- 功能模块集成测试

### 3. 调试工具
- SLF4J 日志框架集成
- 分级日志记录

## 八、个人开发心得

### 技术收获
1. **JavaFX 桌面开发**：掌握了 JavaFX 的界面开发技术，包括 FXML、CSS 样式、事件处理等
2. **数据库设计**：深入学习了 MySQL 数据库设计和优化
3. **MVC 架构**：实践了标准的 MVC 设计模式
4. **Maven 构建**：熟练使用 Maven 进行项目管理和构建

### 能力提升
1. **问题分析能力**：学会分析需求，将功能转化为技术实现
2. **代码组织能力**：掌握了大型项目的代码组织和管理
3. **文档编写能力**：学会了编写技术文档和使用说明

### 改进方向
如果继续完善本系统，可以考虑以下方向：
1. **Web 版开发**：基于现有后端，开发 Web 前端版本
2. **移动端支持**：开发移动端 APP
3. **数据统计增强**：增加更多数据分析和报表功能
4. **消息推送**：实现实时消息推送功能
5. **文件上传**：增加图片上传功能（如维修现场照片）

---

**修改日期**: 2026年1月
**修改人**: 任万博 (2413041837)
**项目版本**: 1.0.0
