package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class SubjectDaoImpl implements SubjectDao {

    private static final Logger LOG = LogManager.getLogger(SubjectDaoImpl.class);

    @Override
    public Subject create(Subject subject) {

        String sql = "INSERT INTO subject(subject_id, subject_name, term_id) VALUES ( ? , ? , ?)";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, subject.getSubjectId());
            statement.setString(2, subject.getSubjectName());
            statement.setInt(3, subject.getTermId());
            statement.executeUpdate();
            return subject;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public boolean update(Subject subject) {

        String sql = "UPDATE subject SET subject_name = ?, term_id = ? " +
                "WHERE subject_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, subject.getSubjectName());
            statement.setInt(2, subject.getTermId());
            statement.setInt(3, subject.getSubjectId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Subject subject) {

        String sql = "DELETE FROM subject WHERE subject_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, subject.getSubjectId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
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
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Subject findById(int id) {

        String sql = String.format("SELECT subject_id, subject_name, term_id FROM subject WHERE subject_id = %d", id);
        Subject result = new Subject();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setSubjectId(rs.getInt("subject_id"));
                result.setSubjectName(rs.getString("subject_name"));
                result.setTermId(rs.getInt("term_id"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
