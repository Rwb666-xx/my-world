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