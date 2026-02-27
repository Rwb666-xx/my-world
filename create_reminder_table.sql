-- 创建提醒表
USE dormitory_management;

-- 创建提醒表
CREATE TABLE IF NOT EXISTS reminder (
    reminder_id VARCHAR(50) PRIMARY KEY COMMENT '提醒ID',
    student_id VARCHAR(20) NOT NULL COMMENT '学生学号',
    student_name VARCHAR(50) NOT NULL COMMENT '学生姓名',
    type VARCHAR(20) NOT NULL COMMENT '提醒类型：缴费提醒、违纪处理提醒',
    title VARCHAR(100) NOT NULL COMMENT '提醒标题',
    content TEXT NOT NULL COMMENT '提醒内容',
    priority VARCHAR(10) NOT NULL DEFAULT '中' COMMENT '优先级：高、中、低',
    status VARCHAR(20) NOT NULL DEFAULT '待处理' COMMENT '状态：待处理、已处理、已忽略',
    create_time TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    handle_time TIMESTAMP NULL COMMENT '处理时间',
    handler VARCHAR(50) COMMENT '处理人',
    INDEX idx_student_id (student_id),
    INDEX idx_type (type),
    INDEX idx_status (status),
    INDEX idx_create_time (create_time)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='提醒信息表';

-- 插入一些示例数据
INSERT INTO reminder (reminder_id, student_id, student_name, type, title, content, priority, status, create_time) VALUES
('REMINDER_001', '20240001', '张三', '缴费提醒', '住宿费未缴提醒', '学生张三(20240001)存在未缴纳的住宿费用，请及时处理。', '高', '待处理', NOW()),
('REMINDER_002', '20240002', '李四', '违纪处理提醒', '违纪记录处理提醒', '学生李四(20240002)存在未处理的违纪记录：晚归，请及时处理。', '中', '待处理', NOW()),
('REMINDER_003', '20240003', '王五', '缴费提醒', '住宿费未缴提醒', '学生王五(20240003)存在未缴纳的住宿费用，请及时处理。', '高', '已处理', NOW());