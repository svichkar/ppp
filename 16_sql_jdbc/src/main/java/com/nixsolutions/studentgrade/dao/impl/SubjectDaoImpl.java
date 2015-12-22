package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class SubjectDaoImpl implements SubjectDao {

    @Override
    public boolean create(Subject subject) {

        String sql = "INSERT INTO subject(subject_id, subject_name, term_id) VALUES ( ? , ? , ?)";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, subject.getSubjectId());
            statement.setString(2, subject.getSubjectName());
            statement.setInt(3, subject.getTermId());
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(Subject subject, Subject newSubject) {

        String sql = "UPDATE subject SET subject_id = ?, subject_name = ?, term_id = ? " +
                "WHERE subject_id = ? AND subject_name = ? AND term_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, newSubject.getSubjectId());
            statement.setString(2, newSubject.getSubjectName());
            statement.setInt(3, newSubject.getTermId());
            statement.setInt(4, subject.getSubjectId());
            statement.setString(5, subject.getSubjectName());
            statement.setInt(6, subject.getTermId());
            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Subject subject) {

        String sql = "DELETE FROM subject WHERE subject_id = ? AND subject_name = ? AND term_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, subject.getSubjectId());
            statement.setString(2, subject.getSubjectName());
            statement.setInt(3, subject.getTermId());
            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public List<Subject> findAll() {

        String sql = "SELECT * FROM subject";
        List<Subject> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getInt("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setTermId(rs.getInt("term_id"));
                list.add(subject);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Subject findById(int id) {

        String sql = String.format("SELECT subject_id, subject_name FROM subject WHERE subject_id = %d", id);
        Subject result = new Subject();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setSubjectId(rs.getInt("subject_id"));
                result.setSubjectName(rs.getString("subject_name"));
                result.setTermId(rs.getInt("term_id"));
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
