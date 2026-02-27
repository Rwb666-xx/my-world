package org.dorm.model.service.impl;

import org.dorm.model.dao.StudentDAO;
import org.dorm.model.dao.impl.StudentDAOImpl;
import org.dorm.model.entity.Student;
import org.dorm.model.service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 学生服务实现类
 */
public class StudentServiceImpl implements StudentService {
    private static final Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public List<Student> getAllStudents() {
        return studentDAO.findAll();
    }

    @Override
    public Student getStudentById(String studentId) {
        return studentDAO.findByStudentId(studentId);
    }

    @Override
    public boolean addStudent(Student student) {
        try {
            // 检查学号是否已存在
            if (studentDAO.findByStudentId(student.getStudentId()) != null) {
                logger.warn("学号已存在：{}", student.getStudentId());
                return false;
            }

            return studentDAO.addStudent(student);
        } catch (Exception e) {
            logger.error("添加学生失败", e);
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        return studentDAO.updateStudent(student);
    }

    @Override
    public boolean deleteStudent(String studentId) {
        return studentDAO.deleteStudent(studentId);
    }

    @Override
    public List<Student> searchStudentsByName(String name) {
        return studentDAO.findByClass(name); // 暂时用班级查找代替
    }

    @Override
    public int getStudentCount() {
        return studentDAO.findAll().size(); // 暂时用列表大小代替
    }
}