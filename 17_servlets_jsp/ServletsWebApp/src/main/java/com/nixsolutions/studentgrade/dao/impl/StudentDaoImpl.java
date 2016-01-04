package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StudentDaoImpl implements StudentDao {

    private static final Logger LOG = LogManager.getLogger(StudentDaoImpl.class);

    @Override
    public Student create(Student student) {

        String sql = "INSERT INTO student(first_name, last_name, group_id, admission_date, status_id, term_id) " +
                "VALUES ( ?, ? , ?, ? , ?, ? )";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getGroupId());
            statement.setDate(4, student.getAdmissionDate());
            statement.setInt(5, student.getStatusId());
            statement.setInt(6, student.getTermId());
            statement.executeUpdate();
            return student;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public boolean update(Student student) {
        String sql = "UPDATE student SET first_name = ?, last_name = ?, group_id = ?, admission_date = ?, " +
                "status_id = ?, term_id = ? WHERE student_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.setInt(3, student.getGroupId());
            statement.setDate(4, student.getAdmissionDate());
            statement.setInt(5, student.getStatusId());
            statement.setInt(6, student.getTermId());
            statement.setInt(7, student.getStudentId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Student student) {

        String sql = "DELETE FROM student WHERE student_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, student.getStudentId());
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public List<Student> findAll() {

        String sql = "SELECT * FROM student";
        List<Student> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Student student = new Student();
                student.setStudentId(rs.getInt("student_id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setGroupId(rs.getInt("group_id"));
                student.setAdmissionDate(rs.getDate("admission_date"));
                student.setStatusId(rs.getInt("status_id"));
                student.setTermId(rs.getInt("term_id"));
                list.add(student);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Student findById(int id) {

        String sql = String.format("SELECT student_id, first_name, last_name, group_id, admission_date, status_id, term_id " +
                "FROM student WHERE student_id = %d", id);
        Student result = new Student();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setStudentId(rs.getInt("student_id"));
                result.setFirstName(rs.getString("first_name"));
                result.setLastName(rs.getString("last_name"));
                result.setGroupId(rs.getInt("group_id"));
                result.setAdmissionDate(rs.getDate("admission_date"));
                result.setStatusId(rs.getInt("status_id"));
                result.setTermId(rs.getInt("term_id"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
