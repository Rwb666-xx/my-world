/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : dormitory_management

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 13/01/2026 21:16:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bed
-- ----------------------------
DROP TABLE IF EXISTS `bed`;
CREATE TABLE `bed`  (
  `bed_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `dorm_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '床位状态：占用/空闲',
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bed_id`) USING BTREE,
  INDEX `dorm_id`(`dorm_id`) USING BTREE,
  CONSTRAINT `bed_ibfk_1` FOREIGN KEY (`dorm_id`) REFERENCES `dormitory` (`dorm_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bed
-- ----------------------------
INSERT INTO `bed` VALUES ('怡丁1-106-01', '怡丁1-106', '空闲', NULL);
INSERT INTO `bed` VALUES ('怡丁1-106-02', '怡丁1-106', '空闲', NULL);
INSERT INTO `bed` VALUES ('怡丁1-213-01', '怡丁1-213', '空闲', NULL);
INSERT INTO `bed` VALUES ('怡丁1-213-02', '怡丁1-213', '空闲', NULL);
INSERT INTO `bed` VALUES ('怡丁2-305-01', '怡丁2-305', '空闲', NULL);
INSERT INTO `bed` VALUES ('怡丁2-305-02', '怡丁2-305', '空闲', NULL);
INSERT INTO `bed` VALUES ('怡丁2-412-01', '怡丁2-412', '空闲', NULL);
INSERT INTO `bed` VALUES ('怡丁2-412-02', '怡丁2-412', '空闲', NULL);
INSERT INTO `bed` VALUES ('文澜1-202-01', '文澜1-202', '占用', '2413041811');
INSERT INTO `bed` VALUES ('文澜1-202-02', '文澜1-202', '占用', '2413041812');
INSERT INTO `bed` VALUES ('文澜1-202-03', '文澜1-202', '占用', '2413041813');
INSERT INTO `bed` VALUES ('文澜1-202-04', '文澜1-202', '占用', '2413041814');
INSERT INTO `bed` VALUES ('文澜1-315-01', '文澜1-315', '占用', '2413041815');
INSERT INTO `bed` VALUES ('文澜1-315-02', '文澜1-315', '占用', '2413041816');
INSERT INTO `bed` VALUES ('文澜1-315-03', '文澜1-315', '占用', '2413041817');
INSERT INTO `bed` VALUES ('文澜1-315-04', '文澜1-315', '占用', '2413041818');
INSERT INTO `bed` VALUES ('文澜2-108-01', '文澜2-108', '占用', '2413051812');
INSERT INTO `bed` VALUES ('文澜2-108-02', '文澜2-108', '占用', '2413051813');
INSERT INTO `bed` VALUES ('文澜2-108-03', '文澜2-108', '占用', '2413051814');
INSERT INTO `bed` VALUES ('文澜2-108-04', '文澜2-108', '占用', '2413051815');
INSERT INTO `bed` VALUES ('文澜2-220-01', '文澜2-220', '占用', '2413151801');
INSERT INTO `bed` VALUES ('文澜2-220-02', '文澜2-220', '占用', '2413151802');
INSERT INTO `bed` VALUES ('文澜2-220-03', '文澜2-220', '占用', '2413151803');
INSERT INTO `bed` VALUES ('文澜2-220-04', '文澜2-220', '占用', '2413151804');
INSERT INTO `bed` VALUES ('文瀛1-101-01', '文瀛1-101', '占用', '2413051801');
INSERT INTO `bed` VALUES ('文瀛1-101-02', '文瀛1-101', '占用', '2413051802');
INSERT INTO `bed` VALUES ('文瀛1-101-03', '文瀛1-101', '占用', '2413051803');
INSERT INTO `bed` VALUES ('文瀛1-101-04', '文瀛1-101', '占用', '2413051804');
INSERT INTO `bed` VALUES ('文瀛1-205-01', '文瀛1-205', '占用', '2413051805');
INSERT INTO `bed` VALUES ('文瀛1-205-02', '文瀛1-205', '占用', '2413051806');
INSERT INTO `bed` VALUES ('文瀛1-205-03', '文瀛1-205', '占用', '2413051807');
INSERT INTO `bed` VALUES ('文瀛1-205-04', '文瀛1-205', '占用', '2413051808');
INSERT INTO `bed` VALUES ('文瀛1-312-01', '文瀛1-312', '占用', '2413051809');
INSERT INTO `bed` VALUES ('文瀛1-312-02', '文瀛1-312', '占用', '2413051810');
INSERT INTO `bed` VALUES ('文瀛1-312-03', '文瀛1-312', '占用', '2413051811');
INSERT INTO `bed` VALUES ('文瀛1-312-04', '文瀛1-312', '占用', '2413181802');
INSERT INTO `bed` VALUES ('文瀛1-408-01', '文瀛1-408', '占用', '2413161801');
INSERT INTO `bed` VALUES ('文瀛1-408-02', '文瀛1-408', '占用', '2413161802');
INSERT INTO `bed` VALUES ('文瀛1-408-03', '文瀛1-408', '占用', '2413161803');
INSERT INTO `bed` VALUES ('文瀛1-408-04', '文瀛1-408', '占用', '2413161804');
INSERT INTO `bed` VALUES ('文瀛2-103-01', '文瀛2-103', '占用', '2413061801');
INSERT INTO `bed` VALUES ('文瀛2-103-02', '文瀛2-103', '占用', '2413061802');
INSERT INTO `bed` VALUES ('文瀛2-103-03', '文瀛2-103', '占用', '2413061803');
INSERT INTO `bed` VALUES ('文瀛2-103-04', '文瀛2-103', '占用', '2413061804');
INSERT INTO `bed` VALUES ('文瀛2-215-01', '文瀛2-215', '占用', '2413061805');
INSERT INTO `bed` VALUES ('文瀛2-215-02', '文瀛2-215', '占用', '2413061806');
INSERT INTO `bed` VALUES ('文瀛2-215-03', '文瀛2-215', '占用', '2413061807');
INSERT INTO `bed` VALUES ('文瀛2-215-04', '文瀛2-215', '占用', '2413181803');
INSERT INTO `bed` VALUES ('文瀛2-322-01', '文瀛2-322', '占用', '2413061809');
INSERT INTO `bed` VALUES ('文瀛2-322-02', '文瀛2-322', '占用', '2413061810');
INSERT INTO `bed` VALUES ('文瀛2-322-03', '文瀛2-322', '占用', '2413061811');
INSERT INTO `bed` VALUES ('文瀛2-322-04', '文瀛2-322', '占用', '2413061812');
INSERT INTO `bed` VALUES ('文瀛2-410-01', '文瀛2-410', '占用', '2413061813');
INSERT INTO `bed` VALUES ('文瀛2-410-02', '文瀛2-410', '占用', '2413061814');
INSERT INTO `bed` VALUES ('文瀛2-410-03', '文瀛2-410', '占用', '2413061815');
INSERT INTO `bed` VALUES ('文瀛2-410-04', '文瀛2-410', '占用', '2413171801');
INSERT INTO `bed` VALUES ('文瀛3-328-01', '文瀛3-328', '占用', '2413041804');
INSERT INTO `bed` VALUES ('文瀛3-328-02', '文瀛3-328', '占用', '2413041805');
INSERT INTO `bed` VALUES ('文瀛3-328-03', '文瀛3-328', '占用', '2413041806');
INSERT INTO `bed` VALUES ('文瀛3-328-04', '文瀛3-328', '占用', '2413041807');
INSERT INTO `bed` VALUES ('文瀛3-431-01', '文瀛3-431', '占用', '2413041837');
INSERT INTO `bed` VALUES ('文瀛3-431-02', '文瀛3-431', '占用', '2413041801');
INSERT INTO `bed` VALUES ('文瀛3-431-03', '文瀛3-431', '占用', '2413041802');
INSERT INTO `bed` VALUES ('文瀛3-431-04', '文瀛3-431', '占用', '2413041803');
INSERT INTO `bed` VALUES ('文瀛3-506-01', '文瀛3-506', '占用', '2413041808');
INSERT INTO `bed` VALUES ('文瀛3-506-02', '文瀛3-506', '占用', '2413041809');
INSERT INTO `bed` VALUES ('文瀛3-506-03', '文瀛3-506', '占用', '2413041810');
INSERT INTO `bed` VALUES ('文瀛3-506-04', '文瀛3-506', '占用', '2413181801');
INSERT INTO `bed` VALUES ('文瀛4-218-01', '文瀛4-218', '占用', '2413171802');
INSERT INTO `bed` VALUES ('文瀛4-218-02', '文瀛4-218', '占用', '2413171803');
INSERT INTO `bed` VALUES ('文瀛4-218-03', '文瀛4-218', '占用', '2413171804');
INSERT INTO `bed` VALUES ('文瀛4-218-04', '文瀛4-218', '占用', '2413071804');
INSERT INTO `bed` VALUES ('文瀛4-309-01', '文瀛4-309', '占用', '2413081801');
INSERT INTO `bed` VALUES ('文瀛4-309-02', '文瀛4-309', '占用', '2413081802');
INSERT INTO `bed` VALUES ('文瀛4-309-03', '文瀛4-309', '占用', '2413081803');
INSERT INTO `bed` VALUES ('文瀛4-309-04', '文瀛4-309', '占用', '2413181804');
INSERT INTO `bed` VALUES ('文瀛5-112-01', '文瀛5-112', '占用', '2413091801');
INSERT INTO `bed` VALUES ('文瀛5-112-02', '文瀛5-112', '占用', '2413091802');
INSERT INTO `bed` VALUES ('文瀛5-112-03', '文瀛5-112', '占用', '2413091803');
INSERT INTO `bed` VALUES ('文瀛5-112-04', '文瀛5-112', '占用', '2413091804');
INSERT INTO `bed` VALUES ('文瀛5-207-01', '文瀛5-207', '占用', '2413101801');
INSERT INTO `bed` VALUES ('文瀛5-207-02', '文瀛5-207', '占用', '2413101802');
INSERT INTO `bed` VALUES ('文瀛5-207-03', '文瀛5-207', '占用', '2413101803');
INSERT INTO `bed` VALUES ('文瀛5-207-04', '文瀛5-207', '占用', '2413101804');
INSERT INTO `bed` VALUES ('文韬1-105-01', '文韬1-105', '占用', '2413111801');
INSERT INTO `bed` VALUES ('文韬1-105-02', '文韬1-105', '占用', '2413111802');
INSERT INTO `bed` VALUES ('文韬1-105-03', '文韬1-105', '占用', '2413111803');
INSERT INTO `bed` VALUES ('文韬1-105-04', '文韬1-105', '占用', '2413111804');
INSERT INTO `bed` VALUES ('文韬1-211-01', '文韬1-211', '占用', '2413121801');
INSERT INTO `bed` VALUES ('文韬1-211-02', '文韬1-211', '占用', '2413121802');
INSERT INTO `bed` VALUES ('文韬1-211-03', '文韬1-211', '占用', '2413121803');
INSERT INTO `bed` VALUES ('文韬1-211-04', '文韬1-211', '占用', '2413121804');
INSERT INTO `bed` VALUES ('文韬2-303-01', '文韬2-303', '占用', '2413131801');
INSERT INTO `bed` VALUES ('文韬2-303-02', '文韬2-303', '占用', '2413131802');
INSERT INTO `bed` VALUES ('文韬2-303-03', '文韬2-303', '占用', '2413131803');
INSERT INTO `bed` VALUES ('文韬2-303-04', '文韬2-303', '占用', '2413131804');
INSERT INTO `bed` VALUES ('文韬2-416-01', '文韬2-416', '占用', '2413141801');
INSERT INTO `bed` VALUES ('文韬2-416-02', '文韬2-416', '占用', '2413141802');
INSERT INTO `bed` VALUES ('文韬2-416-03', '文韬2-416', '占用', '2413141803');
INSERT INTO `bed` VALUES ('文韬2-416-04', '文韬2-416', '占用', '2413141804');

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory`  (
  `dorm_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `building` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `floor` int(0) NOT NULL,
  `capacity` int(0) NOT NULL,
  `manager_name` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `manager_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`dorm_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('怡丁1-106', '怡丁苑', 1, 4, '吴强', '13934561240');
INSERT INTO `dormitory` VALUES ('怡丁1-213', '怡丁苑', 2, 4, '吴强', '13934561240');
INSERT INTO `dormitory` VALUES ('怡丁2-305', '怡丁苑', 3, 4, '郑华', '13934561241');
INSERT INTO `dormitory` VALUES ('怡丁2-412', '怡丁苑', 4, 4, '郑华', '13934561241');
INSERT INTO `dormitory` VALUES ('文澜1-202', '文澜苑', 2, 4, '孙丽', '13934561238');
INSERT INTO `dormitory` VALUES ('文澜1-315', '文澜苑', 3, 4, '孙丽', '13934561238');
INSERT INTO `dormitory` VALUES ('文澜2-108', '文澜苑', 1, 4, '黄静', '13934561239');
INSERT INTO `dormitory` VALUES ('文澜2-220', '文澜苑', 2, 4, '黄静', '13934561239');
INSERT INTO `dormitory` VALUES ('文瀛1-101', '文瀛苑', 1, 4, '李建军', '13934561230');
INSERT INTO `dormitory` VALUES ('文瀛1-205', '文瀛苑', 2, 4, '李建军', '13934561230');
INSERT INTO `dormitory` VALUES ('文瀛1-312', '文瀛苑', 3, 4, '李建军', '13934561230');
INSERT INTO `dormitory` VALUES ('文瀛1-408', '文瀛苑', 4, 4, '李建军', '13934561230');
INSERT INTO `dormitory` VALUES ('文瀛2-103', '文瀛苑', 1, 4, '王红梅', '13934561231');
INSERT INTO `dormitory` VALUES ('文瀛2-215', '文瀛苑', 2, 4, '王红梅', '13934561231');
INSERT INTO `dormitory` VALUES ('文瀛2-322', '文瀛苑', 3, 4, '王红梅', '13934561231');
INSERT INTO `dormitory` VALUES ('文瀛2-410', '文瀛苑', 4, 4, '王红梅', '13934561231');
INSERT INTO `dormitory` VALUES ('文瀛3-328', '文瀛苑', 3, 4, '张志强', '13934561232');
INSERT INTO `dormitory` VALUES ('文瀛3-431', '文瀛苑', 4, 4, '张志强', '13934561232');
INSERT INTO `dormitory` VALUES ('文瀛3-506', '文瀛苑', 5, 4, '张志强', '13934561232');
INSERT INTO `dormitory` VALUES ('文瀛4-218', '文瀛苑', 2, 4, '刘芳', '13934561233');
INSERT INTO `dormitory` VALUES ('文瀛4-309', '文瀛苑', 3, 4, '刘芳', '13934561233');
INSERT INTO `dormitory` VALUES ('文瀛5-112', '文瀛苑', 1, 4, '赵刚', '13934561234');
INSERT INTO `dormitory` VALUES ('文瀛5-207', '文瀛苑', 2, 4, '赵刚', '13934561234');
INSERT INTO `dormitory` VALUES ('文韬1-105', '文韬苑', 1, 4, '陈明', '13934561235');
INSERT INTO `dormitory` VALUES ('文韬1-211', '文韬苑', 2, 4, '陈明', '13934561235');
INSERT INTO `dormitory` VALUES ('文韬2-303', '文韬苑', 3, 4, '杨丽', '13934561236');
INSERT INTO `dormitory` VALUES ('文韬2-416', '文韬苑', 4, 4, '杨丽', '13934561236');
INSERT INTO `dormitory` VALUES ('文韬3-209', '文韬苑', 2, 4, '周明', '13934561237');

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `payment_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `amount` decimal(10, 2) NOT NULL,
  `payment_date` date NOT NULL,
  `semester` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `status` enum('未缴','已缴') CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT '未缴',
  PRIMARY KEY (`payment_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES ('PAY001', '2413041837', 1200.00, '2024-09-01', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY002', '2413041801', 1200.00, '2024-09-02', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY003', '2413041802', 1200.00, '2024-09-03', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY004', '2413041803', 1200.00, '2024-09-04', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY005', '2413041804', 1200.00, '2024-09-05', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY006', '2413041805', 1200.00, '2024-09-06', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY007', '2413041806', 1200.00, '2024-09-07', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY008', '2413041807', 1200.00, '2024-09-08', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY009', '2413041808', 1200.00, '2024-09-09', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY010', '2413041809', 1200.00, '2024-09-10', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY011', '2413041810', 1200.00, '2024-09-11', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY012', '2413041811', 1200.00, '2024-09-12', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY013', '2413041812', 1200.00, '2024-09-13', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY014', '2413041813', 1200.00, '2024-09-14', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY015', '2413041814', 1200.00, '2024-09-15', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY016', '2413041815', 1200.00, '2024-09-16', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY017', '2413041816', 1200.00, '2024-09-17', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY018', '2413041817', 1200.00, '2024-09-18', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY019', '2413041818', 1200.00, '2024-09-19', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY020', '2413051801', 1200.00, '2024-09-01', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY021', '2413051802', 1200.00, '2024-09-02', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY022', '2413051803', 1200.00, '2024-09-03', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY023', '2413051804', 1200.00, '2024-09-04', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY024', '2413051805', 1200.00, '2024-09-05', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY025', '2413051806', 1200.00, '2024-09-06', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY026', '2413051807', 1200.00, '2024-09-07', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY027', '2413051808', 1200.00, '2024-09-08', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY028', '2413051809', 1200.00, '2024-09-09', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY029', '2413051810', 1200.00, '2024-09-10', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY030', '2413051811', 1200.00, '2024-09-11', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY031', '2413051812', 1200.00, '2024-09-12', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY032', '2413051813', 1200.00, '2024-09-13', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY033', '2413051814', 1200.00, '2024-09-14', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY034', '2413051815', 1200.00, '2024-09-15', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY035', '2413061801', 1200.00, '2024-09-01', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY036', '2413061802', 1200.00, '2024-09-02', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY037', '2413061803', 1200.00, '2024-09-03', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY038', '2413061804', 1200.00, '2024-09-04', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY039', '2413061805', 1200.00, '2024-09-05', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY040', '2413061806', 1200.00, '2024-09-06', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY041', '2413061807', 1200.00, '2024-09-07', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY042', '2413061808', 1200.00, '2024-09-08', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY043', '2413061809', 1200.00, '2024-09-09', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY044', '2413061810', 1200.00, '2024-09-10', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY045', '2413061811', 1200.00, '2024-09-11', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY046', '2413061812', 1200.00, '2024-09-12', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY047', '2413061813', 1200.00, '2024-09-13', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY048', '2413061814', 1200.00, '2024-09-14', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY049', '2413061815', 1200.00, '2024-09-15', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY050', '2413071801', 1200.00, '2024-09-01', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY051', '2413071802', 1200.00, '2024-09-02', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY052', '2413071803', 1200.00, '2024-09-03', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY053', '2413071804', 1200.00, '2024-09-04', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY054', '2413081801', 1200.00, '2024-09-05', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY055', '2413081802', 1200.00, '2024-09-06', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY056', '2413081803', 1200.00, '2024-09-07', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY057', '2413081804', 1200.00, '2024-09-08', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY058', '2413091801', 1200.00, '2024-09-09', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY059', '2413091802', 1200.00, '2024-09-10', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY060', '2413091803', 1200.00, '2024-09-11', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY061', '2413091804', 1200.00, '2024-09-12', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY062', '2413101801', 1200.00, '2024-09-13', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY063', '2413101802', 1200.00, '2024-09-14', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY064', '2413101803', 1200.00, '2024-09-15', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY065', '2413101804', 1200.00, '2024-09-16', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY066', '2413111801', 1200.00, '2024-09-17', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY067', '2413111802', 1200.00, '2024-09-18', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY068', '2413111803', 1200.00, '2024-09-19', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY069', '2413111804', 1200.00, '2024-09-20', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY070', '2413121801', 1200.00, '2024-09-21', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY071', '2413121802', 1200.00, '2024-09-22', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY072', '2413121803', 1200.00, '2024-09-23', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY073', '2413121804', 1200.00, '2024-09-24', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY074', '2413131801', 1200.00, '2024-09-25', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY075', '2413131802', 1200.00, '2024-09-26', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY076', '2413131803', 1200.00, '2024-09-27', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY077', '2413131804', 1200.00, '2024-09-28', '2024-2025第一学期', '未缴');
INSERT INTO `payment` VALUES ('PAY078', '2413141801', 1200.00, '2024-09-29', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY079', '2413141802', 1200.00, '2024-09-30', '2024-2025第一学期', '已缴');
INSERT INTO `payment` VALUES ('PAY080', '2413141803', 1200.00, '2025-02-01', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY081', '2413141804', 1200.00, '2025-02-02', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY082', '2413151801', 1200.00, '2025-02-03', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY083', '2413151802', 1200.00, '2025-02-04', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY084', '2413151803', 1200.00, '2025-02-05', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY085', '2413151804', 1200.00, '2025-02-06', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY086', '2413161801', 1200.00, '2025-02-07', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY087', '2413161802', 1200.00, '2025-02-08', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY088', '2413161803', 1200.00, '2025-02-09', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY089', '2413161804', 1200.00, '2025-02-10', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY090', '2413171801', 1200.00, '2025-02-11', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY091', '2413171802', 1200.00, '2025-02-12', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY092', '2413171803', 1200.00, '2025-02-13', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY093', '2413171804', 1200.00, '2025-02-14', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY094', '2413181801', 1200.00, '2025-02-15', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY095', '2413181802', 1200.00, '2025-02-16', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY096', '2413181803', 1200.00, '2025-02-17', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY097', '2413181804', 1200.00, '2025-02-18', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY098', '2413071801', 1200.00, '2025-02-19', '2024-2025第二学期', '未缴');
INSERT INTO `payment` VALUES ('PAY099', '2413081802', 1200.00, '2025-02-20', '2024-2025第二学期', '已缴');
INSERT INTO `payment` VALUES ('PAY100', '2413091803', 1200.00, '2025-02-21', '2024-2025第二学期', '已缴');

-- ----------------------------
-- Table structure for reminder
-- ----------------------------
DROP TABLE IF EXISTS `reminder`;
CREATE TABLE `reminder`  (
  `reminder_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `priority` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '中',
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT '待处理',
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP,
  `handle_time` timestamp(0) NULL DEFAULT NULL,
  `handler` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`reminder_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of reminder
-- ----------------------------
INSERT INTO `reminder` VALUES ('REMINDER_09a06370', '2413041809', '马子轩', '缴费提醒', '住宿费未缴提醒', '学生 马子轩 (2413041809) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:09', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_1373c4ac', '2413041813', '杨若曦', '缴费提醒', '住宿费未缴提醒', '学生 杨若曦 (2413041813) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:09', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_14c0a85f', '2413051814', '杨雅婷', '缴费提醒', '住宿费未缴提醒', '学生 杨雅婷 (2413051814) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_18be9142', '2413061803', '王宇轩', '缴费提醒', '住宿费未缴提醒', '学生 王宇轩 (2413061803) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_1963ef55', '2413041816', '徐佳怡', '缴费提醒', '住宿费未缴提醒', '学生 徐佳怡 (2413041816) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:09', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_1e971a66', '2413061811', '刘泽宇', '缴费提醒', '住宿费未缴提醒', '学生 刘泽宇 (2413061811) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_2249db29', '2413131804', '阎泽宇', '缴费提醒', '住宿费未缴提醒', '学生 阎泽宇 (2413131804) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_31745fbc', '2413051802', '王梓涵', '缴费提醒', '住宿费未缴提醒', '学生 王梓涵 (2413051802) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:09', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_34f9fe0c', '2413061814', '黄浩然', '缴费提醒', '住宿费未缴提醒', '学生 黄浩然 (2413061814) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_43775121', '2413171804', '易泽宇', '缴费提醒', '住宿费未缴提醒', '学生 易泽宇 (2413171804) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_459985cd', '2413051808', '吴浩然', '缴费提醒', '住宿费未缴提醒', '学生 吴浩然 (2413051808) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_4957aad0', '2413151804', '任思琪', '缴费提醒', '住宿费未缴提醒', '学生 任思琪 (2413151804) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_51741729', '2413061808', '郑浩然', '缴费提醒', '住宿费未缴提醒', '学生 郑浩然 (2413061808) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_55802b54', '2413151801', '钟梦瑶', '缴费提醒', '住宿费未缴提醒', '学生 钟梦瑶 (2413151801) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:34:32', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_57737e25', '2413101803', '许浩然', '缴费提醒', '住宿费未缴提醒', '学生 许浩然 (2413101803) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_5ffd6fcb', '2413111801', '彭浩然', '缴费提醒', '住宿费未缴提醒', '学生 彭浩然 (2413111801) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_6bd92809', '2413091804', '曹泽宇', '缴费提醒', '住宿费未缴提醒', '学生 曹泽宇 (2413091804) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_7815d011', '2413041807', '吴俊驰', '缴费提醒', '住宿费未缴提醒', '学生 吴俊驰 (2413041807) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:09', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_7e15da40', '2413071804', '罗泽宇', '缴费提醒', '住宿费未缴提醒', '学生 罗泽宇 (2413071804) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_7ff554bc', '2413181803', '庞浩然', '缴费提醒', '住宿费未缴提醒', '学生 庞浩然 (2413181803) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_84d9e713', '2413081803', '韩浩然', '缴费提醒', '住宿费未缴提醒', '学生 韩浩然 (2413081803) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_8c88dace', '2413061805', '孙浩然', '缴费提醒', '住宿费未缴提醒', '学生 孙浩然 (2413061805) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_961673ab', '2413161803', '史浩然', '缴费提醒', '住宿费未缴提醒', '学生 史浩然 (2413161803) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_a1965071', '2413121803', '贾浩然', '缴费提醒', '住宿费未缴提醒', '学生 贾浩然 (2413121803) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_a1b7a485', '2413091801', '冯浩然', '缴费提醒', '住宿费未缴提醒', '学生 冯浩然 (2413091801) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_b2937747', '2413111804', '卢泽宇', '缴费提醒', '住宿费未缴提醒', '学生 卢泽宇 (2413111804) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_b537419f', '2413051811', '朱泽宇', '缴费提醒', '住宿费未缴提醒', '学生 朱泽宇 (2413051811) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_b92358f0', '2413051805', '赵俊豪', '缴费提醒', '住宿费未缴提醒', '学生 赵俊豪 (2413051805) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_b9a0430a', '2413071801', '徐浩然', '缴费提醒', '住宿费未缴提醒', '学生 徐浩然 (2413071801) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_befb2788', '2413171801', '汤浩然', '缴费提醒', '住宿费未缴提醒', '学生 汤浩然 (2413171801) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_c8fd01b8', '2413041818', '高思琪', '缴费提醒', '住宿费未缴提醒', '学生 高思琪 (2413041818) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:09', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_cc137b5b', '2413131801', '魏浩然', '缴费提醒', '住宿费未缴提醒', '学生 魏浩然 (2413131801) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_edb24e91', '2413141803', '戴浩然', '缴费提醒', '住宿费未缴提醒', '学生 戴浩然 (2413141803) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:10', NULL, NULL);
INSERT INTO `reminder` VALUES ('REMINDER_f1f62fd7', '2413041805', '孙博文', '缴费提醒', '住宿费未缴提醒', '学生 孙博文 (2413041805) 存在未缴纳的住宿费用，请及时处理。', '高', '待处理', '2026-01-13 18:26:09', NULL, NULL);

-- ----------------------------
-- Table structure for repair_application
-- ----------------------------
DROP TABLE IF EXISTS `repair_application`;
CREATE TABLE `repair_application`  (
  `apply_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fault_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `fault_desc` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `contact_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `apply_time` datetime(0) NULL,
  `status` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'pending',
  `handler` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `finish_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`apply_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `repair_application_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of repair_application
-- ----------------------------
INSERT INTO `repair_application` VALUES ('REP001', '2413041837', '文瀛苑3号楼431宿舍', '宿舍阳台水龙头漏水，拧紧无效，一直滴水', '15392589987', '2024-09-05 09:23:15', 'completed', '张师傅', '2024-09-05 15:40:22');
INSERT INTO `repair_application` VALUES ('REP002', '2413041801', '文瀛苑3号楼431宿舍', '宿舍书桌抽屉滑轨损坏，无法正常推拉', '15392581234', '2024-09-06 10:15:30', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP003', '2413041802', '文瀛苑3号楼431宿舍', '上铺床板有裂缝，翻身有异响，存在安全隐患', '15392582345', '2024-09-07 14:08:22', 'repairing', '李师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP004', '2413041803', '文瀛苑3号楼431宿舍', '宿舍顶灯闪烁，接触不良，偶尔熄灯', '15392583456', '2024-09-08 08:55:10', 'accepted', '王师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP005', '2413041804', '文瀛苑3号楼328宿舍', '空调制冷效果差，调16度室温还是28度', '15392584567', '2024-09-09 16:20:05', 'completed', '赵师傅', '2024-09-10 11:30:18');
INSERT INTO `repair_application` VALUES ('REP006', '2413041805', '文瀛苑3号楼328宿舍', '宿舍门锁卡顿，开门需要用力拧钥匙', '15392585678', '2024-09-10 09:10:33', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP007', '2413051801', '文瀛苑1号楼101宿舍', '马桶冲水无力，容易堵塞，需要多次冲水', '15392581111', '2024-09-11 13:45:27', 'accepted', '刘师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP008', '2413051802', '文瀛苑1号楼101宿舍', '墙面插座接触不良，手机充电断断续续', '15392582222', '2024-09-12 11:22:40', 'repairing', '陈师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP009', '2413051803', '文瀛苑1号楼101宿舍', '阳台晾衣架生锈断裂，无法挂衣服', '15392583333', '2024-09-13 15:09:15', 'completed', '杨师傅', '2024-09-14 09:50:30');
INSERT INTO `repair_application` VALUES ('REP010', '2413061801', '文瀛苑2号楼103宿舍', '宿舍暖气片不热，冬季供暖不足', '13934561111', '2024-09-14 08:30:55', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP011', '2413061802', '文瀛苑2号楼103宿舍', '窗户关不严，漏风严重，密封条老化', '13934562222', '2024-09-15 14:20:12', 'accepted', '黄师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP012', '2413061803', '文瀛苑2号楼103宿舍', '洗漱台下水管道堵塞，排水缓慢积水', '13934563333', '2024-09-16 10:05:47', 'repairing', '周师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP013', '2413071801', '文瀛苑4号楼218宿舍', '宿舍网络端口损坏，插网线无信号', '13834568888', '2024-09-17 16:18:33', 'completed', '吴师傅', '2024-09-18 10:25:10');
INSERT INTO `repair_application` VALUES ('REP014', '2413071802', '文瀛苑4号楼218宿舍', '床腿松动，摇晃严重，需要加固螺丝', '13834569999', '2024-09-18 09:40:20', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP015', '2413081801', '文瀛苑4号楼309宿舍', '卫生间排风扇异响，转动时噪音过大', '15392586677', '2024-09-19 13:15:08', 'accepted', '郑师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP016', '2413081802', '文瀛苑4号楼309宿舍', '宿舍衣柜门锁损坏，无法上锁', '15392587788', '2024-09-20 11:00:50', 'repairing', '马师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP017', '2413091801', '文瀛苑5号楼112宿舍', '水龙头出水变小，滤网堵塞严重', '13834561122', '2024-09-21 08:25:35', 'completed', '朱师傅', '2024-09-22 14:10:25');
INSERT INTO `repair_application` VALUES ('REP018', '2413091802', '文瀛苑5号楼112宿舍', '墙面墙皮脱落，大面积掉灰', '13834562233', '2024-09-22 15:30:17', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP019', '2413101801', '文瀛苑5号楼207宿舍', '空调遥控器失灵，无法调节温度和开关', '13934561122', '2024-09-23 10:50:42', 'accepted', '林师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP020', '2413101802', '文瀛苑5号楼207宿舍', '宿舍门合页松动，关门有异响', '13934562233', '2024-09-24 14:25:13', 'repairing', '徐师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP021', '2413111801', '文韬苑1号楼105宿舍', '地面瓷砖空鼓，踩上去松动有声', '15392581133', '2024-09-25 09:15:28', 'completed', '胡师傅', '2024-09-26 10:30:15');
INSERT INTO `repair_application` VALUES ('REP022', '2413111802', '文韬苑1号楼105宿舍', '阳台地漏堵塞，积水无法排出', '15392582244', '2024-09-26 13:40:05', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP023', '2413121801', '文韬苑1号楼211宿舍', '书桌柜门合页损坏，无法关闭', '13834565577', '2024-09-27 11:05:33', 'accepted', '高师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP024', '2413121802', '文韬苑1号楼211宿舍', '宿舍顶灯烧坏，完全不亮', '13834566688', '2024-09-28 08:30:20', 'repairing', '罗师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP025', '2413131801', '文韬苑2号楼303宿舍', '空调外机噪音过大，影响休息', '13934565577', '2024-09-29 16:00:47', 'completed', '宋师傅', '2024-09-30 11:15:30');
INSERT INTO `repair_application` VALUES ('REP026', '2413131802', '文韬苑2号楼303宿舍', '插座烧坏，有焦糊味，无法使用', '13934566688', '2024-09-30 09:20:12', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP027', '2413141801', '文韬苑2号楼416宿舍', '床护栏松动，存在坠床风险', '15392585577', '2024-10-01 14:10:55', 'accepted', '谢师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP028', '2413141802', '文韬苑2号楼416宿舍', '洗漱台台面开裂，有渗水情况', '15392586688', '2024-10-02 10:35:23', 'repairing', '韩师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP029', '2413151801', '文澜苑2号楼220宿舍', '女生宿舍衣柜隔板断裂，承重不足', '13834569911', '2024-10-03 08:45:10', 'completed', '唐师傅', '2024-10-04 15:20:05');
INSERT INTO `repair_application` VALUES ('REP030', '2413151802', '文澜苑2号楼220宿舍', '卫生间水龙头开关失灵，无法停水', '13834560022', '2024-10-04 13:20:37', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP031', '2413161801', '文瀛苑1号楼408宿舍', '宿舍网络WiFi信号弱，无法正常上网', '15392589911', '2024-10-05 11:00:22', 'accepted', '冯师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP032', '2413161802', '文瀛苑1号楼408宿舍', '床板凹陷，中间塌陷，睡觉不舒服', '15392580022', '2024-10-06 15:15:08', 'repairing', '董师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP033', '2413171801', '文瀛苑2号楼410宿舍', '暖气片漏水，墙面被泡起皮', '13834561144', '2024-10-07 09:30:45', 'completed', '程师傅', '2024-10-08 10:50:20');
INSERT INTO `repair_application` VALUES ('REP034', '2413171802', '文瀛苑4号楼218宿舍', '窗户把手损坏，无法正常开窗关窗', '13834562255', '2024-10-08 14:00:13', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP035', '2413041811', '文澜苑1号楼202宿舍', '女生宿舍梳妆镜松动，墙面固定不稳', '13834564567', '2024-10-09 10:10:30', 'accepted', '曹师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP036', '2413041812', '文澜苑1号楼202宿舍', '阳台水龙头断裂，漏水严重', '13834565678', '2024-10-10 08:20:55', 'repairing', '袁师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP037', '2413051812', '文澜苑2号楼108宿舍', '宿舍门锁反锁失灵，出门无法反锁', '13834564444', '2024-10-11 13:30:27', 'completed', '邓师傅', '2024-10-12 14:15:10');
INSERT INTO `repair_application` VALUES ('REP038', '2413051813', '文澜苑2号楼108宿舍', '墙面插座没电，整个插座失效', '13834565555', '2024-10-12 11:05:08', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP039', '2413061810', '文瀛苑2号楼322宿舍', '书桌抽屉锁芯损坏，钥匙插不进去', '15392580000', '2024-10-13 15:20:33', 'accepted', '许师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP040', '2413061811', '文瀛苑2号楼322宿舍', '空调制热效果差，冬季不暖和', '15392581122', '2024-10-14 09:40:15', 'repairing', '沈师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP041', '2413071803', '文瀛苑4号楼218宿舍', '卫生间瓷砖脱落，墙面缺块', '13934569999', '2024-10-15 14:05:47', 'completed', '彭师傅', '2024-10-16 11:20:25');
INSERT INTO `repair_application` VALUES ('REP042', '2413081803', '文瀛苑4号楼309宿舍', '地漏反味严重，卫生间有臭味', '15392588899', '2024-10-16 10:10:20', 'completed', '管理员', '2026-01-13 11:21:42');
INSERT INTO `repair_application` VALUES ('REP043', '2413091803', '文瀛苑5号楼112宿舍', '床底储物抽屉滑轮损坏，无法拉出', '13834563344', '2024-10-17 08:35:55', 'accepted', '吕师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP044', '2413101803', '文瀛苑5号楼207宿舍', '宿舍顶灯镇流器损坏，灯光闪烁', '13934563344', '2024-10-18 13:20:12', 'repairing', '苏师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP045', '2413111803', '文韬苑1号楼105宿舍', '阳台晾衣架升降失灵，无法调节高度', '15392583355', '2024-10-19 16:05:30', 'completed', '卢师傅', '2024-10-20 09:45:10');
INSERT INTO `repair_application` VALUES ('REP046', '2413121803', '文韬苑1号楼211宿舍', '宿舍门密封条脱落，漏风漏灰', '13834567799', '2024-10-20 11:15:08', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('REP047', '2413131803', '文韬苑2号楼303宿舍', '空调过滤网堵塞，出风小有异味', '13934567799', '2024-10-21 14:30:27', '已完成', '蒋师傅', '2026-01-12 18:36:36');
INSERT INTO `repair_application` VALUES ('REP048', '2413141803', '文韬苑2号楼416宿舍', '洗漱台水龙头出水歪，溅水严重', '15392587799', '2024-10-22 08:50:45', 'repairing', '蔡师傅', NULL);
INSERT INTO `repair_application` VALUES ('REP049', '2413151803', '文澜苑2号楼220宿舍', '女生宿舍衣柜拉手脱落，无法开门', '13834560022', '2024-10-23 10:20:13', 'completed', '贾师傅', '2024-10-24 15:00:20');
INSERT INTO `repair_application` VALUES ('REP050', '2413161803', '文瀛苑1号楼408宿舍', '宿舍网络端口松动，网线容易掉线', '15392581133', '2024-10-24 13:10:33', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('WX2026011218377251', '2413041837', '11', '11', '15392589987', '2026-01-12 17:53:30', 'accepted', '管理员', NULL);
INSERT INTO `repair_application` VALUES ('WX20260113183756F0', '2413041837', '111', '111', '15392589987', '2026-01-13 21:15:17', 'pending', NULL, NULL);
INSERT INTO `repair_application` VALUES ('WX202601131837CB9C', '2413041837', '太远', '太原', '15392589987', '2026-01-13 16:23:42', 'pending', NULL, NULL);

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `college_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `class_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `gender` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `birthday` date NULL DEFAULT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `bed_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`student_id`) USING BTREE,
  INDEX `bed_id`(`bed_id`) USING BTREE,
  CONSTRAINT `student_ibfk_1` FOREIGN KEY (`bed_id`) REFERENCES `bed` (`bed_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('2413041801', '123456', '张宇轩', '软件学院', '24130418', '男', '2006-01-15', 'zhangyx@nuc.edu.cn', '15392581234', '怡丁1-106-01');
INSERT INTO `student` VALUES ('2413041802', '123456', '李泽阳', '软件学院', '24130418', '男', '2006-03-22', 'lizeyang@qq.com', '15392582345', '怡丁1-213-01');
INSERT INTO `student` VALUES ('2413041803', '123456', '王浩宇', '软件学院', '24130418', '男', '2006-05-08', 'wanghy@163.com', '15392583456', '文瀛3-431-04');
INSERT INTO `student` VALUES ('2413041804', '123456', '赵嘉豪', '软件学院', '24130418', '男', '2006-07-19', 'zhaojh@nuc.edu.cn', '15392584567', '文瀛3-328-01');
INSERT INTO `student` VALUES ('2413041805', '123456', '孙博文', '软件学院', '24130418', '男', '2006-09-05', 'sunbw@qq.com', '15392585678', '文瀛3-328-02');
INSERT INTO `student` VALUES ('2413041806', '123456', '周泽楷', '软件学院', '24130418', '男', '2006-11-12', 'zhouzk@163.com', '15392586789', '文瀛3-328-03');
INSERT INTO `student` VALUES ('2413041807', '123456', '吴俊驰', '软件学院', '24130418', '男', '2005-12-28', 'wujc@nuc.edu.cn', '15392587890', '文瀛3-328-04');
INSERT INTO `student` VALUES ('2413041808', '123456', '郑浩然', '软件学院', '24130418', '男', '2005-10-16', 'zhenghr@qq.com', '13834561234', '文瀛3-506-01');
INSERT INTO `student` VALUES ('2413041809', '123456', '马子轩', '软件学院', '24130418', '男', '2005-08-09', 'mazx@163.com', '13834562345', '文瀛3-506-02');
INSERT INTO `student` VALUES ('2413041810', '123456', '朱梓晨', '软件学院', '24130418', '男', '2005-06-23', 'zhuzc@nuc.edu.cn', '13834563456', '文瀛3-506-03');
INSERT INTO `student` VALUES ('2413041811', '123456', '刘子涵', '软件学院', '24130418', '女', '2006-04-10', 'liuzh@qq.com', '13834564567', '文澜1-202-01');
INSERT INTO `student` VALUES ('2413041812', '123456', '陈思雨', '软件学院', '24130418', '女', '2006-02-25', 'chensy@163.com', '13834565678', '文澜1-202-02');
INSERT INTO `student` VALUES ('2413041813', '123456', '杨若曦', '软件学院', '24130418', '女', '2006-07-05', 'yangrx@nuc.edu.cn', '13834566789', '文澜1-202-03');
INSERT INTO `student` VALUES ('2413041814', '123456', '黄雅婷', '软件学院', '24130418', '女', '2006-09-18', 'huangyt@qq.com', '13834567890', '文澜1-202-04');
INSERT INTO `student` VALUES ('2413041815', '123456', '林梦瑶', '软件学院', '24130418', '女', '2005-11-02', 'linmy@163.com', '13934561234', '文澜1-315-01');
INSERT INTO `student` VALUES ('2413041816', '123456', '徐佳怡', '软件学院', '24130418', '女', '2005-05-20', 'xujy@nuc.edu.cn', '13934562345', '文澜1-315-02');
INSERT INTO `student` VALUES ('2413041817', '123456', '胡雨桐', '软件学院', '24130418', '女', '2005-03-12', 'huyt@qq.com', '13934563456', '文澜1-315-03');
INSERT INTO `student` VALUES ('2413041818', '123456', '高思琪', '软件学院', '24130418', '女', '2005-01-08', 'gaosq@163.com', '13934564567', '文澜1-315-04');
INSERT INTO `student` VALUES ('2413041837', '123456', '任万博', '软件学院', '24130418', '男', '2006-02-08', '1507213289@qq.com', '15392589987', '文瀛3-431-01');
INSERT INTO `student` VALUES ('241305148', '123456', '人群4', '软件学院', '24130514', '男', '2026-01-01', NULL, NULL, NULL);
INSERT INTO `student` VALUES ('2413051801', '123456', '刘博文', '计算机学院', '24130518', '男', '2006-03-15', 'liubw@nuc.edu.cn', '15392581111', '文瀛1-101-01');
INSERT INTO `student` VALUES ('2413051802', '123456', '王梓涵', '计算机学院', '24130518', '男', '2006-05-22', 'wangzh@qq.com', '15392582222', '文瀛1-101-02');
INSERT INTO `student` VALUES ('2413051803', '123456', '张泽宇', '计算机学院', '24130518', '男', '2006-07-08', 'zhangzy@163.com', '15392583333', '文瀛1-101-03');
INSERT INTO `student` VALUES ('2413051804', '123456', '李浩然', '计算机学院', '24130518', '男', '2006-09-19', 'lihr@nuc.edu.cn', '15392584444', '文瀛1-101-04');
INSERT INTO `student` VALUES ('2413051805', '123456', '赵俊豪', '计算机学院', '24130518', '男', '2005-11-05', 'zhaojh@qq.com', '15392585555', '文瀛1-205-01');
INSERT INTO `student` VALUES ('2413051806', '123456', '孙泽楷', '计算机学院', '24130518', '男', '2005-12-28', 'sunzk@163.com', '15392586666', '文瀛1-205-02');
INSERT INTO `student` VALUES ('2413051807', '123456', '周宇轩', '计算机学院', '24130518', '男', '2005-08-16', 'zhouyx@nuc.edu.cn', '15392587777', '文瀛1-205-03');
INSERT INTO `student` VALUES ('2413051808', '123456', '吴浩然', '计算机学院', '24130518', '男', '2005-06-09', 'wuhr@qq.com', '15392588888', '文瀛1-205-04');
INSERT INTO `student` VALUES ('2413051809', '123456', '郑子轩', '计算机学院', '24130518', '男', '2006-04-23', 'zhengzx@163.com', '13834561111', '文瀛1-312-01');
INSERT INTO `student` VALUES ('2413051810', '123456', '马浩然', '计算机学院', '24130518', '男', '2006-02-10', 'mahr@nuc.edu.cn', '13834562222', '文瀛1-312-02');
INSERT INTO `student` VALUES ('2413051811', '123456', '朱泽宇', '计算机学院', '24130518', '男', '2006-07-05', 'zhuzeyu@qq.com', '13834563333', '文瀛1-312-03');
INSERT INTO `student` VALUES ('2413051812', '123456', '刘思琪', '计算机学院', '24130518', '女', '2006-09-18', 'liusq@163.com', '13834564444', '文澜2-108-01');
INSERT INTO `student` VALUES ('2413051813', '123456', '陈雨桐', '计算机学院', '24130518', '女', '2005-11-02', 'chenyt@nuc.edu.cn', '13834565555', '文澜2-108-02');
INSERT INTO `student` VALUES ('2413051814', '123456', '杨雅婷', '计算机学院', '24130518', '女', '2005-05-20', 'yangyt@qq.com', '13834566666', '文澜2-108-03');
INSERT INTO `student` VALUES ('2413051815', '123456', '黄梦瑶', '计算机学院', '24130518', '女', '2005-03-12', 'huangmy@163.com', '13834567777', '文澜2-108-04');
INSERT INTO `student` VALUES ('2413061801', '123456', '张浩然', '机电工程学院', '24130618', '男', '2006-01-15', 'zhanghr@nuc.edu.cn', '13934561111', '文瀛2-103-01');
INSERT INTO `student` VALUES ('2413061802', '123456', '李泽楷', '机电工程学院', '24130618', '男', '2006-03-22', 'lizk@qq.com', '13934562222', '文瀛2-103-02');
INSERT INTO `student` VALUES ('2413061803', '123456', '王宇轩', '机电工程学院', '24130618', '男', '2006-05-08', 'wangyx@163.com', '13934563333', '文瀛2-103-03');
INSERT INTO `student` VALUES ('2413061804', '123456', '赵梓涵', '机电工程学院', '24130618', '男', '2006-07-19', 'zhaozh@nuc.edu.cn', '13934564444', '文瀛2-103-04');
INSERT INTO `student` VALUES ('2413061805', '123456', '孙浩然', '机电工程学院', '24130618', '男', '2006-09-05', 'sunhr@qq.com', '13934565555', '文瀛2-215-01');
INSERT INTO `student` VALUES ('2413061806', '123456', '周子轩', '机电工程学院', '24130618', '男', '2006-11-12', 'zhouzx@163.com', '13934566666', '文瀛2-215-02');
INSERT INTO `student` VALUES ('2413061807', '123456', '吴泽宇', '机电工程学院', '24130618', '男', '2005-12-28', 'wuzy@nuc.edu.cn', '13934567777', '文瀛2-215-03');
INSERT INTO `student` VALUES ('2413061808', '123456', '郑浩然', '机电工程学院', '24130618', '男', '2005-10-16', 'zhenghr@qq.com', '13934568888', '文瀛2-215-04');
INSERT INTO `student` VALUES ('2413061809', '123456', '马泽楷', '机电工程学院', '24130618', '男', '2005-08-09', 'mazk@163.com', '15392589999', '文瀛2-322-01');
INSERT INTO `student` VALUES ('2413061810', '123456', '朱浩然', '机电工程学院', '24130618', '男', '2005-06-23', 'zhuhr@nuc.edu.cn', '15392580000', '文瀛2-322-02');
INSERT INTO `student` VALUES ('2413061811', '123456', '刘泽宇', '机电工程学院', '24130618', '男', '2006-04-10', 'liuzeyu@qq.com', '15392581122', '文瀛2-322-03');
INSERT INTO `student` VALUES ('2413061812', '123456', '陈浩然', '机电工程学院', '24130618', '男', '2006-02-25', 'chenhr@163.com', '15392582233', '文瀛2-322-04');
INSERT INTO `student` VALUES ('2413061813', '123456', '杨泽楷', '机电工程学院', '24130618', '男', '2006-07-05', 'yangzk@nuc.edu.cn', '15392583344', '文瀛2-410-01');
INSERT INTO `student` VALUES ('2413061814', '123456', '黄浩然', '机电工程学院', '24130618', '男', '2006-09-18', 'huanghr@qq.com', '15392584455', '文瀛2-410-02');
INSERT INTO `student` VALUES ('2413061815', '123456', '林泽宇', '机电工程学院', '24130618', '男', '2005-11-02', 'linzy@163.com', '15392585566', '文瀛2-410-03');
INSERT INTO `student` VALUES ('2413071801', '123456', '徐浩然', '材料科学与工程学院', '24130718', '男', '2006-05-20', 'xuhar@nuc.edu.cn', '13834568888', '文瀛4-218-01');
INSERT INTO `student` VALUES ('2413071802', '123456', '胡泽宇', '材料科学与工程学院', '24130718', '男', '2006-03-12', 'huzy@qq.com', '13834569999', '文瀛4-218-02');
INSERT INTO `student` VALUES ('2413071803', '123456', '高浩然', '材料科学与工程学院', '24130718', '男', '2006-01-08', 'gaohr@163.com', '13934569999', '文瀛4-218-03');
INSERT INTO `student` VALUES ('2413071804', '123456', '罗泽宇', '材料科学与工程学院', '24130718', '男', '2005-11-22', 'luozy@nuc.edu.cn', '13934560000', '文瀛4-218-04');
INSERT INTO `student` VALUES ('2413081801', '123456', '宋浩然', '电子信息工程学院', '24130818', '男', '2006-07-15', 'songhr@qq.com', '15392586677', '文瀛4-309-01');
INSERT INTO `student` VALUES ('2413081802', '123456', '谢泽宇', '电子信息工程学院', '24130818', '男', '2006-09-22', 'xiezy@163.com', '15392587788', '文瀛4-309-02');
INSERT INTO `student` VALUES ('2413081803', '123456', '韩浩然', '电子信息工程学院', '24130818', '男', '2006-05-08', 'hanhr@nuc.edu.cn', '15392588899', '文瀛4-309-03');
INSERT INTO `student` VALUES ('2413081804', '123456', '唐泽宇', '电子信息工程学院', '24130818', '男', '2006-03-19', 'tangzy@qq.com', '15392589900', '文瀛4-309-04');
INSERT INTO `student` VALUES ('2413091801', '123456', '冯浩然', '自动化与测控工程学院', '24130918', '男', '2005-12-05', 'fenghr@163.com', '13834561122', '文瀛5-112-01');
INSERT INTO `student` VALUES ('2413091802', '123456', '董泽宇', '自动化与测控工程学院', '24130918', '男', '2005-10-16', 'dongzy@nuc.edu.cn', '13834562233', '文瀛5-112-02');
INSERT INTO `student` VALUES ('2413091803', '123456', '程浩然', '自动化与测控工程学院', '24130918', '男', '2005-08-09', 'chenghr@qq.com', '13834563344', '文瀛5-112-03');
INSERT INTO `student` VALUES ('2413091804', '123456', '曹泽宇', '自动化与测控工程学院', '24130918', '男', '2005-06-23', 'caozy@163.com', '13834564455', '文瀛5-112-04');
INSERT INTO `student` VALUES ('2413101801', '123456', '袁浩然', '机械工程学院', '24131018', '男', '2006-04-10', 'yuanhr@nuc.edu.cn', '13934561122', '文瀛5-207-01');
INSERT INTO `student` VALUES ('2413101802', '123456', '邓泽宇', '机械工程学院', '24131018', '男', '2006-02-25', 'dengzy@qq.com', '13934562233', '文瀛5-207-02');
INSERT INTO `student` VALUES ('2413101803', '123456', '许浩然', '机械工程学院', '24131018', '男', '2006-07-05', 'xuhr@163.com', '13934563344', '文瀛5-207-03');
INSERT INTO `student` VALUES ('2413101804', '123456', '沈泽宇', '机械工程学院', '24131018', '男', '2006-09-18', 'shenzy@nuc.edu.cn', '13934564455', '文瀛5-207-04');
INSERT INTO `student` VALUES ('2413111801', '123456', '彭浩然', '化工学院', '24131118', '男', '2005-11-02', 'penghr@qq.com', '15392581133', '文韬1-105-01');
INSERT INTO `student` VALUES ('2413111802', '123456', '吕泽宇', '化工学院', '24131118', '男', '2005-05-20', 'lüzy@163.com', '15392582244', '文韬1-105-02');
INSERT INTO `student` VALUES ('2413111803', '123456', '苏浩然', '化工学院', '24131118', '男', '2005-03-12', 'suhr@nuc.edu.cn', '15392583355', '文韬1-105-03');
INSERT INTO `student` VALUES ('2413111804', '123456', '卢泽宇', '化工学院', '24131118', '男', '2005-01-08', 'luzy@qq.com', '15392584466', '文韬1-105-04');
INSERT INTO `student` VALUES ('2413121801', '123456', '蒋浩然', '化工学院', '24131218', '男', '2006-03-15', 'jianghr@163.com', '13834565577', '文韬1-211-01');
INSERT INTO `student` VALUES ('2413121802', '123456', '蔡泽宇', '化工学院', '24131218', '男', '2006-05-22', 'caizy@nuc.edu.cn', '13834566688', '文韬1-211-02');
INSERT INTO `student` VALUES ('2413121803', '123456', '贾浩然', '化工学院', '24131218', '男', '2006-07-08', 'jiahr@qq.com', '13834567799', '文韬1-211-03');
INSERT INTO `student` VALUES ('2413121804', '123456', '丁泽宇', '化工学院', '24131218', '男', '2006-09-19', 'dingzy@163.com', '13834568800', '文韬1-211-04');
INSERT INTO `student` VALUES ('2413131801', '123456', '魏浩然', '机械工程学院', '24131318', '男', '2005-11-05', 'weihr@nuc.edu.cn', '13934565577', '文韬2-303-01');
INSERT INTO `student` VALUES ('2413131802', '123456', '薛泽宇', '机械工程学院', '24131318', '男', '2005-12-28', 'xuezy@qq.com', '13934566688', '文韬2-303-02');
INSERT INTO `student` VALUES ('2413131803', '123456', '叶浩然', '机械工程学院', '24131318', '男', '2005-08-16', 'yehr@163.com', '13934567799', '文韬2-303-03');
INSERT INTO `student` VALUES ('2413131804', '123456', '阎泽宇', '机械工程学院', '24131318', '男', '2005-06-09', 'yanzy@nuc.edu.cn', '13934568800', '文韬2-303-04');
INSERT INTO `student` VALUES ('2413141801', '123456', '余浩然', '自动化学院', '24131418', '男', '2006-04-23', 'yuhr@qq.com', '15392585577', '文韬2-416-01');
INSERT INTO `student` VALUES ('2413141802', '123456', '潘泽宇', '自动化学院', '24131418', '男', '2006-02-10', 'panzy@163.com', '15392586688', '文韬2-416-02');
INSERT INTO `student` VALUES ('2413141803', '123456', '戴浩然', '自动化学院', '24131418', '男', '2006-07-05', 'daihr@nuc.edu.cn', '15392587799', '文韬2-416-03');
INSERT INTO `student` VALUES ('2413141804', '123456', '夏泽宇', '自动化学院', '24131418', '男', '2006-09-18', 'xiazy@qq.com', '15392588800', '文韬2-416-04');
INSERT INTO `student` VALUES ('2413151801', '123456', '钟梦瑶', '电子学院', '24131518', '女', '2006-05-20', 'zhongmy@nuc.edu.cn', '13834569911', '文澜2-220-01');
INSERT INTO `student` VALUES ('2413151802', '123456', '汪雨桐', '电子学院', '24131518', '女', '2006-03-12', 'wangyt@qq.com', '13834560022', '文澜2-220-02');
INSERT INTO `student` VALUES ('2413151803', '123456', '田雅婷', '电子学院', '24131518', '女', '2006-01-08', 'tianyt@163.com', '13934569911', '文澜2-220-03');
INSERT INTO `student` VALUES ('2413151804', '123456', '任思琪', '电子学院', '24131518', '女', '2005-11-22', 'rensq@nuc.edu.cn', '13934560022', '文澜2-220-04');
INSERT INTO `student` VALUES ('2413161801', '123456', '石浩然', '材料学院', '24131618', '男', '2006-07-15', 'shahr@qq.com', '15392589911', '文瀛1-408-01');
INSERT INTO `student` VALUES ('2413161802', '123456', '龙泽宇', '材料学院', '24131618', '男', '2006-09-22', 'longzy@163.com', '15392580022', '文瀛1-408-02');
INSERT INTO `student` VALUES ('2413161803', '123456', '史浩然', '材料学院', '24131618', '男', '2006-05-08', 'shihn@nuc.edu.cn', '15392581133', '文瀛1-408-03');
INSERT INTO `student` VALUES ('2413161804', '123456', '雷泽宇', '材料学院', '24131618', '男', '2006-03-19', 'leizy@qq.com', '15392582244', '文瀛1-408-04');
INSERT INTO `student` VALUES ('2413171801', '123456', '汤浩然', '化工学院', '24131718', '男', '2005-12-05', 'tanghr@163.com', '13834561144', '文瀛2-410-04');
INSERT INTO `student` VALUES ('2413171802', '123456', '尹泽宇', '化工学院', '24131718', '男', '2005-10-16', 'yinzy@nuc.edu.cn', '13834562255', '文瀛4-218-01');
INSERT INTO `student` VALUES ('2413171803', '123456', '黎浩然', '化工学院', '24131718', '男', '2005-08-09', 'lihr@qq.com', '13834563366', '文瀛4-218-02');
INSERT INTO `student` VALUES ('2413171804', '123456', '易泽宇', '化工学院', '24131718', '男', '2005-06-23', 'yizeyu@163.com', '13834564477', '文瀛4-218-03');
INSERT INTO `student` VALUES ('2413181801', '123456', '乔浩然', '软件学院', '24130418', '男', '2006-04-10', 'qiaohr@nuc.edu.cn', '13934561144', '文瀛3-506-04');
INSERT INTO `student` VALUES ('2413181802', '123456', '伍泽宇', '软件学院', '24130418', '男', '2006-02-25', 'wuzy@qq.com', '13934562255', '文瀛1-312-04');
INSERT INTO `student` VALUES ('2413181803', '123456', '庞浩然', '软件学院', '24130418', '男', '2006-07-05', 'panghr@163.com', '13934563366', '文瀛2-215-04');
INSERT INTO `student` VALUES ('2413181804', '123456', '邢泽宇', '软件学院', '24130418', '男', '2006-09-18', 'xingzy@nuc.edu.cn', '13934564477', '文瀛4-309-04');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `user_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('123456', '123456', '管理员');
INSERT INTO `user` VALUES ('2413041837', '123456', '学生');

-- ----------------------------
-- Table structure for violation
-- ----------------------------
DROP TABLE IF EXISTS `violation`;
CREATE TABLE `violation`  (
  `violation_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `student_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `violation_date` date NOT NULL,
  `penalty` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  PRIMARY KEY (`violation_id`) USING BTREE,
  INDEX `student_id`(`student_id`) USING BTREE,
  CONSTRAINT `violation_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of violation
-- ----------------------------
INSERT INTO `violation` VALUES ('VIO001', '2413041806', '23:30晚归宿舍，未在宿管处登记报备', '2024-09-10', '口头警告');
INSERT INTO `violation` VALUES ('VIO002', '2413041808', '宿舍内违规使用电煮锅，属于大功率电器', '2024-09-12', '警告处分');
INSERT INTO `violation` VALUES ('VIO003', '2413041814', '宿舍卫生检查被子未叠、地面有垃圾，评分不合格', '2024-09-15', '口头警告');
INSERT INTO `violation` VALUES ('VIO004', '2413051802', '夜间23点后在宿舍大声打游戏，影响他人休息', '2024-09-18', '警告处分');
INSERT INTO `violation` VALUES ('VIO005', '2413051805', '宿舍内发现香烟及打火机，存在吸烟行为', '2024-09-20', '记过处分');
INSERT INTO `violation` VALUES ('VIO006', '2413051811', '未经宿管许可，留宿校外人员一晚', '2024-09-22', '严重警告');
INSERT INTO `violation` VALUES ('VIO007', '2413061803', '私拉电线给电动车充电，存在消防安全隐患', '2024-09-25', '警告处分');
INSERT INTO `violation` VALUES ('VIO008', '2413061809', '晚归超过门禁时间1小时，态度恶劣', '2024-09-28', '严重警告');
INSERT INTO `violation` VALUES ('VIO009', '2413061814', '宿舍卫生连续三次检查不合格，拒不整改', '2024-10-01', '警告处分');
INSERT INTO `violation` VALUES ('VIO010', '2413071804', '违规使用吹风机，功率超标触发跳闸', '2024-10-03', '口头警告');
INSERT INTO `violation` VALUES ('VIO011', '2413081803', '在宿舍墙面乱涂乱画，损坏公共财物', '2024-10-05', '警告处分');
INSERT INTO `violation` VALUES ('VIO012', '2413091801', '夜间翻墙回宿舍，违反宿舍管理条例', '2024-10-08', '记过处分');
INSERT INTO `violation` VALUES ('VIO013', '2413091804', '宿舍内堆放杂物过多，堵塞消防通道', '2024-10-10', '严重警告');
INSERT INTO `violation` VALUES ('VIO014', '2413101803', '晚归未登记，谎称请假外出', '2024-10-12', '口头警告');
INSERT INTO `violation` VALUES ('VIO015', '2413111801', '宿舍内饮酒，酒瓶乱扔', '2024-10-15', '警告处分');
INSERT INTO `violation` VALUES ('VIO016', '2413111804', '私换宿舍床位，未向宿管报备', '2024-10-18', '口头警告');
INSERT INTO `violation` VALUES ('VIO017', '2413121803', '使用明火蜡烛，存在火灾隐患', '2024-10-20', '记过处分');
INSERT INTO `violation` VALUES ('VIO018', '2413131801', '宿舍卫生检查发现宠物（仓鼠），违规养宠', '2024-10-22', '严重警告');
INSERT INTO `violation` VALUES ('VIO019', '2413131804', '晚归与宿管发生口角争执', '2024-10-25', '警告处分');
INSERT INTO `violation` VALUES ('VIO020', '2413141803', '违规使用电热毯，冬季取暖存在安全隐患', '2024-10-28', '口头警告');
INSERT INTO `violation` VALUES ('VIO021', '2413151802', '宿舍内音响音量过大，多次投诉无效', '2024-11-01', '警告处分');
INSERT INTO `violation` VALUES ('VIO022', '2413151804', '卫生检查床底有杂物，未及时清理', '2024-11-03', '口头警告');
INSERT INTO `violation` VALUES ('VIO023', '2413161803', '私拆宿舍家具，损坏书桌抽屉', '2024-11-05', '严重警告');
INSERT INTO `violation` VALUES ('VIO024', '2413161804', '晚归后在楼道大声喧哗，影响整层休息', '2024-11-08', '警告处分');
INSERT INTO `violation` VALUES ('VIO025', '2413171801', '违规使用电磁炉做饭，油烟过大', '2024-11-10', '记过处分');
INSERT INTO `violation` VALUES ('VIO026', '2413171804', '宿舍门锁损坏，未及时报修且私自撬锁', '2024-11-12', '口头警告');
INSERT INTO `violation` VALUES ('VIO027', '2413041817', '卫生检查个人物品摆放混乱，拒不整理', '2024-11-15', '警告处分');
INSERT INTO `violation` VALUES ('VIO028', '2413041810', '晚归超过2小时，无正当理由', '2024-11-18', '严重警告');
INSERT INTO `violation` VALUES ('VIO029', '2413051808', '宿舍内发现管制刀具（水果刀过长）', '2024-11-20', '记过处分');
INSERT INTO `violation` VALUES ('VIO030', '2413061805', '私拉网线，导致宿舍网络故障', '2024-11-22', '口头警告');
INSERT INTO `violation` VALUES ('VIO031', '2413071802', '卫生检查阳台有积水，滋生蚊虫', '2024-11-25', '警告处分');
INSERT INTO `violation` VALUES ('VIO032', '2413081801', '晚归后翻墙进入宿舍楼，被监控抓拍', '2024-11-28', '严重警告');
INSERT INTO `violation` VALUES ('VIO033', '2413091802', '宿舍内使用充电宝过夜充电，存在爆炸隐患', '2024-12-01', '口头警告');
INSERT INTO `violation` VALUES ('VIO034', '2413101801', '留宿外人两天，未报备', '2024-12-03', '警告处分');
INSERT INTO `violation` VALUES ('VIO035', '2413111802', '宿舍卫生检查垃圾桶未倒，异味严重', '2024-12-05', '口头警告');
INSERT INTO `violation` VALUES ('VIO036', '2413121801', '违规使用卷发棒，功率超标', '2024-12-08', '警告处分');
INSERT INTO `violation` VALUES ('VIO037', '2413131802', '晚归后在宿舍唱歌，影响他人学习', '2024-12-10', '严重警告');
INSERT INTO `violation` VALUES ('VIO038', '2413141801', '私换宿舍门锁，不让宿管查房', '2024-12-12', '记过处分');
INSERT INTO `violation` VALUES ('VIO039', '2413151803', '卫生检查发现过期食品，未及时清理', '2024-12-15', '口头警告');
INSERT INTO `violation` VALUES ('VIO040', '2413161801', '违规使用电暖器，冬季取暖', '2024-12-18', '警告处分');
INSERT INTO `violation` VALUES ('VIO041', '2413171802', '晚归未登记，冒用他人学生证', '2024-12-20', '严重警告');
INSERT INTO `violation` VALUES ('VIO042', '2413041807', '宿舍内打球，损坏天花板灯具', '2024-12-22', '警告处分');
INSERT INTO `violation` VALUES ('VIO043', '2413041804', '卫生检查被子叠放不规范，被通报批评', '2024-12-25', '口头警告');
INSERT INTO `violation` VALUES ('VIO044', '2413051803', '违规使用游戏机，夜间玩游戏影响休息', '2024-12-28', '警告处分');
INSERT INTO `violation` VALUES ('VIO045', '2413061807', '私拉电线给手机充电，线路裸露', '2025-01-01', '严重警告');
INSERT INTO `violation` VALUES ('VIO046', '2413071803', '晚归后在楼道追逐打闹，噪音扰民', '2025-01-03', '口头警告');
INSERT INTO `violation` VALUES ('VIO047', '2413081804', '宿舍内发现烟头，拒不承认吸烟行为', '2025-01-05', '警告处分');
INSERT INTO `violation` VALUES ('VIO048', '2413091803', '卫生检查连续四次不合格，通报学院', '2025-01-08', '严重警告');
INSERT INTO `violation` VALUES ('VIO049', '2413101804', '违规使用电热杯，煮面时烫伤自己', '2025-01-10', '记过处分');
INSERT INTO `violation` VALUES ('VIO050', '2413111803', '晚归超过门禁时间，翻墙时摔伤', '2025-01-12', '警告处分');
INSERT INTO `violation` VALUES ('VIO1768212750354', '2413041837', '太笨了 太懒了', '2026-01-12', '奖励敲代码');
INSERT INTO `violation` VALUES ('VIO1768292670458', '2413041837', '违纪了', '2026-01-13', '没');

SET FOREIGN_KEY_CHECKS = 1;
