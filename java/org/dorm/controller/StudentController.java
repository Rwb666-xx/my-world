package org.dorm.controller;

import org.dorm.model.entity.Student;
import org.dorm.model.service.StudentService;
import org.dorm.model.service.impl.StudentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 学生控制器
 * 处理学生相关的业务逻辑
 */
public class StudentController {
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);

    public StudentController() {
        logger.info("StudentController构造函数开始执行");
        try {
            this.studentService = new StudentServiceImpl();
            logger.info("StudentService初始化成功");
        } catch (Exception e) {
            logger.error("StudentService初始化失败", e);
            throw e;
        }
    }

    private StudentService studentService;

    /**
     * 获取所有学生
     * @return 学生列表
     */
    public List<Student> getAllStudents() {
        try {
            List<Student> students = studentService.getAllStudents();
            logger.info("获取所有学生成功：{}人", students.size());
            return students;
        } catch (Exception e) {
            logger.error("获取所有学生失败", e);
            throw new RuntimeException("获取学生列表失败", e);
        }
    }

    /**
     * 根据学号查找学生
     * @param studentId 学号
     * @return 学生信息
     */
    public Student getStudentById(String studentId) {
        try {
            Student student = studentService.getStudentById(studentId);
            if (student != null) {
                logger.info("查找学生成功：{}", studentId);
            } else {
                logger.warn("未找到学生：{}", studentId);
            }
            return student;
        } catch (Exception e) {
            logger.error("查找学生失败：{}", studentId, e);
            throw new RuntimeException("查找学生失败", e);
        }
    }

    /**
     * 添加新学生
     * @param student 学生信息
     * @return 是否成功
     */
    public boolean addStudent(Student student) {
        try {
            boolean result = studentService.addStudent(student);
            if (result) {
                logger.info("添加学生成功：{}", student.getStudentId());
            } else {
                logger.warn("添加学生失败：{}", student.getStudentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("添加学生异常：{}", student.getStudentId(), e);
            throw new RuntimeException("添加学生失败", e);
        }
    }

    /**
     * 更新学生信息
     * @param student 学生信息
     * @return 是否成功
     */
    public boolean updateStudent(Student student) {
        try {
            boolean result = studentService.updateStudent(student);
            if (result) {
                logger.info("更新学生成功：{}", student.getStudentId());
            } else {
                logger.warn("更新学生失败：{}", student.getStudentId());
            }
            return result;
        } catch (Exception e) {
            logger.error("更新学生异常：{}", student.getStudentId(), e);
            throw new RuntimeException("更新学生失败", e);
        }
    }

    /**
     * 删除学生
     * @param studentId 学号
     * @return 是否成功
     */
    public boolean deleteStudent(String studentId) {
        try {
            boolean result = studentService.deleteStudent(studentId);
            if (result) {
                logger.info("删除学生成功：{}", studentId);
            } else {
                logger.warn("删除学生失败：{}", studentId);
            }
            return result;
        } catch (Exception e) {
            logger.error("删除学生异常：{}", studentId, e);
            throw new RuntimeException("删除学生失败", e);
        }
    }

    /**
     * 根据姓名搜索学生
     * @param name 学生姓名
     * @return 学生列表
     */
    public List<Student> searchStudentsByName(String name) {
        try {
            List<Student> students = studentService.searchStudentsByName(name);
            logger.info("搜索学生成功，姓名：{}，结果：{}人", name, students.size());
            return students;
        } catch (Exception e) {
            logger.error("搜索学生异常，姓名：{}", name, e);
            throw new RuntimeException("搜索学生失败", e);
        }
    }

    /**
     * 获取学生总数
     * @return 学生数量
     */
    public int getStudentCount() {
        try {
            int count = studentService.getStudentCount();
            logger.info("获取学生总数：{}", count);
            return count;
        } catch (Exception e) {
            logger.error("获取学生总数异常", e);
            throw new RuntimeException("获取学生总数失败", e);
        }
    }
}