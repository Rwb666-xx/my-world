package org.dorm.model.service;

import org.dorm.model.entity.Student;

import java.util.List;

/**
 * 学生服务接口
 */
public interface StudentService {

    /**
     * 获取所有学生
     * @return 学生列表
     */
    List<Student> getAllStudents();

    /**
     * 根据学号查找学生
     * @param studentId 学号
     * @return 学生信息
     */
    Student getStudentById(String studentId);

    /**
     * 添加新学生
     * @param student 学生信息
     * @return 是否成功
     */
    boolean addStudent(Student student);

    /**
     * 更新学生信息
     * @param student 学生信息
     * @return 是否成功
     */
    boolean updateStudent(Student student);

    /**
     * 删除学生
     * @param studentId 学号
     * @return 是否成功
     */
    boolean deleteStudent(String studentId);

    /**
     * 根据姓名搜索学生
     * @param name 学生姓名
     * @return 学生列表
     */
    List<Student> searchStudentsByName(String name);

    /**
     * 获取学生总数
     * @return 学生数量
     */
    int getStudentCount();
}