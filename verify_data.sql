-- 数据验证脚本
-- 用于检查宿舍管理系统数据库中的数据

USE dormitory_management;

-- 1. 查看所有表的数据量
SELECT '数据统计' as title;
SELECT 'user' as table_name, COUNT(*) as count FROM user UNION ALL
SELECT 'student' as table_name, COUNT(*) as count FROM student UNION ALL
SELECT 'dormitory' as table_name, COUNT(*) as count FROM dormitory UNION ALL
SELECT 'bed' as table_name, COUNT(*) as count FROM bed UNION ALL
SELECT 'payment' as table_name, COUNT(*) as count FROM payment UNION ALL
SELECT 'violation' as table_name, COUNT(*) as count FROM violation UNION ALL
SELECT 'repair_application' as table_name, COUNT(*) as count FROM repair_application;

-- 2. 查看用户数据
SELECT '用户数据' as title;
SELECT user_id, user_type FROM user ORDER BY user_id;

-- 3. 查看学生数据
SELECT '学生数据' as title;
SELECT student_id, name, bed_id FROM student ORDER BY student_id;

-- 4. 查看宿舍数据
SELECT '宿舍数据' as title;
SELECT dorm_id, building, floor, capacity, manager_name FROM dormitory ORDER BY dorm_id;

-- 5. 查看缴费数据
SELECT '缴费数据' as title;
SELECT payment_id, student_id, amount, status FROM payment ORDER BY payment_id;

-- 6. 查看违纪数据
SELECT '违纪数据' as title;
SELECT violation_id, student_id, description FROM violation ORDER BY violation_id;

-- 7. 查看维修申请数据
SELECT '维修申请数据' as title;
SELECT apply_id, student_id, fault_location, status FROM repair_application ORDER BY apply_id;