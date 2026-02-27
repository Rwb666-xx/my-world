package org.dorm.model.dao.impl;

import org.dorm.model.dao.StudentDAO;
import org.dorm.model.entity.Student;
import org.dorm.util.DatabaseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 学生数据访问实现类
 */
public class StudentDAOImpl implements StudentDAO {
    private static final Logger logger = LoggerFactory.getLogger(StudentDAOImpl.class);

    @Override
    public Student findByStudentId(String studentId) {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);
            rs = stmt.executeQuery();

            if (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                return student;
            }
        } catch (SQLException e) {
            logger.error("查找学生失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return null;
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student ORDER BY student_id";
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            logger.error("获取所有学生失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return students;
    }

    @Override
    public List<Student> findByClass(String className) {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student WHERE class_name = ? ORDER BY student_id";
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, className);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            logger.error("按班级查找学生失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return students;
    }

    @Override
    public List<Student> findByCollege(String collegeName) {
        String sql = "SELECT student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id FROM student WHERE college_name = ? ORDER BY student_id";
        List<Student> students = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, collegeName);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getString("student_id"));
                student.setPassword(rs.getString("password"));
                student.setName(rs.getString("name"));
                student.setCollegeName(rs.getString("college_name"));
                student.setClassName(rs.getString("class_name"));
                student.setGender(rs.getString("gender"));
                student.setBirthday(rs.getDate("birthday"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                student.setBedId(rs.getString("bed_id"));
                students.add(student);
            }
        } catch (SQLException e) {
            logger.error("按学院查找学生失败", e);
        } finally {
            closeResources(conn, stmt, rs);
        }
        return students;
    }

    @Override
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO student (student_id, password, name, college_name, class_name, gender, birthday, email, phone, bed_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getStudentId());
            stmt.setString(2, student.getPassword());
            stmt.setString(3, student.getName());
            stmt.setString(4, student.getCollegeName());
            stmt.setString(5, student.getClassName());
            stmt.setString(6, student.getGender());
            stmt.setDate(7, student.getBirthday() != null ? new java.sql.Date(student.getBirthday().getTime()) : null);
            stmt.setString(8, student.getEmail());
            stmt.setString(9, student.getPhone());
            stmt.setString(10, student.getBedId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("添加学生失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateStudent(Student student) {
        String sql = "UPDATE student SET password = ?, name = ?, college_name = ?, class_name = ?, gender = ?, birthday = ?, email = ?, phone = ?, bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, student.getPassword());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getCollegeName());
            stmt.setString(4, student.getClassName());
            stmt.setString(5, student.getGender());
            stmt.setDate(6, student.getBirthday() != null ? new java.sql.Date(student.getBirthday().getTime()) : null);
            stmt.setString(7, student.getEmail());
            stmt.setString(8, student.getPhone());
            stmt.setString(9, student.getBedId());
            stmt.setString(10, student.getStudentId());

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新学生失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean deleteStudent(String studentId) {
        String sql = "DELETE FROM student WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, studentId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("删除学生失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    @Override
    public boolean updateStudentBed(String studentId, String bedId) {
        String sql = "UPDATE student SET bed_id = ? WHERE student_id = ?";
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = DatabaseUtil.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, bedId);
            stmt.setString(2, studentId);

            int rows = stmt.executeUpdate();
            return rows > 0;
        } catch (SQLException e) {
            logger.error("更新学生床位失败", e);
            return false;
        } finally {
            closeResources(conn, stmt, null);
        }
    }

    /**
     * 关闭数据库资源
     */
    private void closeResources(Connection conn, PreparedStatement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                logger.error("关闭ResultSet失败", e);
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                logger.error("关闭PreparedStatement失败", e);
            }
        }
        DatabaseUtil.closeConnection(conn);
    }
}