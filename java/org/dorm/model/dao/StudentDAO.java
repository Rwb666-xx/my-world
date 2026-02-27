package org.dorm.model.dao;

import org.dorm.model.entity.Student;
import java.util.List;

/**
 * 学生数据访问接口
 */
public interface StudentDAO {
    /**
     * 根据学号查找学生
     * @param studentId 学号
     * @return 学生对象，如果不存在返回null
     */
    Student findByStudentId(String studentId);

    /**
     * 获取所有学生列表
     * @return 学生列表
     */
    List<Student> findAll();

    /**
     * 根据班级查找学生
     * @param className 班级名
     * @return 学生列表
     */
    List<Student> findByClass(String className);

    /**
     * 根据学院查找学生
     * @param collegeName 学院名
     * @return 学生列表
     */
    List<Student> findByCollege(String collegeName);

    /**
     * 添加新学生
     * @param student 学生对象
     * @return 是否添加成功
     */
    boolean addStudent(Student student);

    /**
     * 更新学生信息
     * @param student 学生对象
     * @return 是否更新成功
     */
    boolean updateStudent(Student student);

    /**
     * 删除学生
     * @param studentId 学号
     * @return 是否删除成功
     */
    boolean deleteStudent(String studentId);

    /**
     * 更新学生床位信息
     * @param studentId 学号
     * @param bedId 床位号
     * @return 是否更新成功
     */
    boolean updateStudentBed(String studentId, String bedId);
}