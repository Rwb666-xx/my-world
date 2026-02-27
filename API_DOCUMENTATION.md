# 宿舍管理系统API文档

## 概述

本文档描述了宿舍管理系统的核心API接口，包括控制器层、业务逻辑层和数据访问层的接口定义。

## 架构说明

系统采用分层架构设计：
- **控制器层 (Controller)**: 处理用户请求，调用业务逻辑
- **业务逻辑层 (Service)**: 实现核心业务规则
- **数据访问层 (DAO)**: 处理数据库操作

## 核心接口文档

### 1. 用户服务接口 (UserService)

#### 方法列表

##### `User login(String userId, String password)`
- **功能**: 用户登录验证
- **参数**:
  - `userId`: 用户名
  - `password`: 密码
- **返回值**: User对象（登录成功）或null（登录失败）
- **异常**: 无

##### `boolean register(User user)`
- **功能**: 用户注册
- **参数**:
  - `user`: 用户对象，包含userId、password、userType
- **返回值**: true（注册成功）或false（注册失败）
- **异常**: 无

##### `boolean changePassword(String userId, String oldPassword, String newPassword)`
- **功能**: 修改密码
- **参数**:
  - `userId`: 用户名
  - `oldPassword`: 旧密码
  - `newPassword`: 新密码
- **返回值**: true（修改成功）或false（修改失败）
- **异常**: 无

##### `boolean isUserExists(String userId)`
- **功能**: 检查用户名是否存在
- **参数**:
  - `userId`: 用户名
- **返回值**: true（存在）或false（不存在）
- **异常**: 无

##### `User getUserById(String userId)`
- **功能**: 根据用户名获取用户信息
- **参数**:
  - `userId`: 用户名
- **返回值**: User对象或null（用户不存在）
- **异常**: 无

### 2. 学生服务接口 (StudentService)

#### 方法列表

##### `List<Student> getAllStudents()`
- **功能**: 获取所有学生信息
- **参数**: 无
- **返回值**: 学生列表
- **异常**: RuntimeException

##### `Student getStudentById(String studentId)`
- **功能**: 根据学号获取学生信息
- **参数**:
  - `studentId`: 学号
- **返回值**: Student对象或null
- **异常**: RuntimeException

##### `boolean addStudent(Student student)`
- **功能**: 添加新学生
- **参数**:
  - `student`: 学生对象
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `boolean updateStudent(Student student)`
- **功能**: 更新学生信息
- **参数**:
  - `student`: 学生对象
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `boolean deleteStudent(String studentId)`
- **功能**: 删除学生
- **参数**:
  - `studentId`: 学号
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `List<Student> searchStudentsByName(String name)`
- **功能**: 按姓名搜索学生
- **参数**:
  - `name`: 学生姓名
- **返回值**: 学生列表
- **异常**: RuntimeException

##### `int getStudentCount()`
- **功能**: 获取学生总数
- **参数**: 无
- **返回值**: 学生数量
- **异常**: RuntimeException

### 3. 宿舍服务接口 (DormitoryService)

#### 方法列表

##### `List<Dormitory> getAllDormitories()`
- **功能**: 获取所有宿舍信息
- **参数**: 无
- **返回值**: 宿舍列表
- **异常**: RuntimeException

##### `Dormitory getDormitoryById(String dormId)`
- **功能**: 根据宿舍号获取宿舍信息
- **参数**:
  - `dormId`: 宿舍号
- **返回值**: Dormitory对象或null
- **异常**: RuntimeException

##### `boolean addDormitory(Dormitory dormitory)`
- **功能**: 添加新宿舍
- **参数**:
  - `dormitory`: 宿舍对象
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `boolean updateDormitory(Dormitory dormitory)`
- **功能**: 更新宿舍信息
- **参数**:
  - `dormitory`: 宿舍对象
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `boolean deleteDormitory(String dormId)`
- **功能**: 删除宿舍
- **参数**:
  - `dormId`: 宿舍号
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

### 4. 维修申请服务接口 (RepairApplicationService)

#### 方法列表

##### `List<RepairApplication> getAllApplications()`
- **功能**: 获取所有维修申请
- **参数**: 无
- **返回值**: 维修申请列表
- **异常**: RuntimeException

##### `RepairApplication getApplicationById(String applyId)`
- **功能**: 根据申请ID获取维修申请
- **参数**:
  - `applyId`: 申请ID
- **返回值**: RepairApplication对象或null
- **异常**: RuntimeException

##### `boolean addApplication(RepairApplication application)`
- **功能**: 添加新维修申请
- **参数**:
  - `application`: 维修申请对象
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `boolean updateApplication(RepairApplication application)`
- **功能**: 更新维修申请状态
- **参数**:
  - `application`: 维修申请对象
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `boolean deleteApplication(String applyId)`
- **功能**: 删除维修申请
- **参数**:
  - `applyId`: 申请ID
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

##### `List<RepairApplication> getApplicationsByStudent(String studentId)`
- **功能**: 获取学生的维修申请
- **参数**:
  - `studentId`: 学号
- **返回值**: 维修申请列表
- **异常**: RuntimeException

##### `boolean updateApplicationStatus(String applyId, String status)`
- **功能**: 更新申请状态
- **参数**:
  - `applyId`: 申请ID
  - `status`: 新状态
- **返回值**: true（成功）或false（失败）
- **异常**: RuntimeException

## 数据传输对象 (DTO)

### User (用户实体)
```java
public class User {
    private String userId;      // 用户ID
    private String password;    // 密码
    private String userType;    // 用户类型：管理员/学生
}
```

### Student (学生实体)
```java
public class Student {
    private String studentId;     // 学号
    private String password;      // 密码
    private String name;          // 姓名
    private String collegeName;   // 学院
    private String className;     // 班级
    private String gender;        // 性别
    private Date birthday;        // 生日
    private String email;         // 邮箱
    private String phone;         // 电话
    private String bedId;         // 床位ID
}
```

### Dormitory (宿舍实体)
```java
public class Dormitory {
    private String dormId;        // 宿舍号
    private String building;      // 楼栋
    private int floor;            // 楼层
    private int capacity;         // 容量
    private String managerName;   // 管理员姓名
    private String managerPhone;  // 管理员电话
}
```

### RepairApplication (维修申请实体)
```java
public class RepairApplication {
    private String applyId;       // 申请ID
    private String studentId;     // 学生ID
    private String faultLocation; // 故障位置
    private String faultDesc;     // 故障描述
    private String contactPhone;  // 联系电话
    private Date applyTime;       // 申请时间
    private String status;        // 状态
    private String handler;       // 处理人
    private Date finishTime;      // 完成时间
}
```

## 错误处理

系统采用统一的异常处理机制：
- **业务异常**: 抛出RuntimeException，包含错误描述
- **数据库异常**: 在DAO层捕获并记录日志，向上传递RuntimeException
- **参数验证**: 在Service层进行输入参数验证

## 版本信息

- **版本**: 1.0.0
- **更新日期**: 2026-01-11
- **作者**: 任万博 (2413041837)