package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.StudentDao;
import com.nixsolutions.studentgrade.entity.Student;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class StudentDaoImpl implements StudentDao {

    @Override
    public boolean create(Student student) {

        String sql;

        if (student.getStudentId() == 0) {
            sql = "INSERT INTO student(first_name, last_name, group_id, admission_date, status_id, term_id) " +
                    "VALUES ( ?, ? , ?, ? , ?, ? )";
        } else {
            sql = "INSERT INTO student(student_id, first_name, last_name, group_id, admission_date, status_id, term_id) " +
                    "VALUES ( ? , ?, ? , ?, ? , ?, ? )";
        }

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (student.getStudentId() == 0) {
                statement.setString(1, student.getFirstName());
                statement.setString(2, student.getLastName());
                statement.setInt(3, student.getGroupId());
                statement.setDate(4, student.getAdmissionDate());
                statement.setInt(5, student.getStatusId());
                statement.setInt(6, student.getTermId());
            } else {
                statement.setInt(1, student.getStudentId());
                statement.setString(2, student.getFirstName());
                statement.setString(3, student.getLastName());
                statement.setInt(4, student.getGroupId());
                statement.setDate(5, student.getAdmissionDate());
                statement.setInt(6, student.getStatusId());
                statement.setInt(7, student.getTermId());
            }

            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(Student student, Student newStudent) {
        String sql;
        if (newStudent.getStudentId() == 0) {

            sql = "UPDATE student SET first_name = ?, last_name = ?, group_id = ?, admission_date = ?, " +
                    "status_id = ?, term_id = ? " +
                    "WHERE student_id = ? AND first_name = ? AND last_name = ? AND " +
                    "group_id = ? AND admission_date = ? AND  status_id = ? AND term_id = ?";
        } else {
            sql = "UPDATE student SET student_id = ?, first_name = ?, last_name = ?, group_id = ?, " +
                    "admission_date = ?, status_id = ?, term_id = ? " +
                    "WHERE student_id = ? AND first_name = ? AND last_name = ? AND " +
                    "group_id = ? AND admission_date = ? AND  status_id = ? AND term_id = ?";
        }

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (newStudent.getStudentId() == 0) {
                //SET closure:
                statement.setString(1, newStudent.getFirstName());
                statement.setString(2, newStudent.getLastName());
                statement.setInt(3, newStudent.getGroupId());
                statement.setDate(4, newStudent.getAdmissionDate());
                statement.setInt(5, newStudent.getStatusId());
                statement.setInt(6, newStudent.getTermId());
                //WHERE closure:
                statement.setInt(7, student.getStudentId());
                statement.setString(8, student.getFirstName());
                statement.setString(9, student.getLastName());
                statement.setInt(10, student.getGroupId());
                statement.setDate(11, student.getAdmissionDate());
                statement.setInt(12, student.getStatusId());
                statement.setInt(13, student.getTermId());

            } else {
                //SET closure:
                statement.setInt(1, newStudent.getStudentId());
                statement.setString(2, newStudent.getFirstName());
                statement.setString(3, newStudent.getLastName());
                statement.setInt(4, newStudent.getGroupId());
                statement.setDate(5, newStudent.getAdmissionDate());
                statement.setInt(6, newStudent.getStatusId());
                statement.setInt(7, newStudent.getTermId());
                //WHERE closure:
                statement.setInt(8, student.getStudentId());
                statement.setString(9, student.getFirstName());
                statement.setString(10, student.getLastName());
                statement.setInt(11, student.getGroupId());
                statement.setDate(12, student.getAdmissionDate());
                statement.setInt(13, student.getStatusId());
                statement.setInt(14, student.getTermId());
            }

            return statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Student student) {

        String sql = "DELETE FROM student WHERE student_id = ? AND first_name = ? AND last_name = ? " +
                "AND group_id = ? AND admission_date = ? AND  status_id = ? AND term_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, student.getStudentId());
            statement.setString(2, student.getFirstName());
            statement.setString(3, student.getLastName());
            statement.setInt(4, student.getGroupId());
            statement.setDate(5, student.getAdmissionDate());
            statement.setInt(6, student.getStatusId());
            statement.setInt(7, student.getTermId());
            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
