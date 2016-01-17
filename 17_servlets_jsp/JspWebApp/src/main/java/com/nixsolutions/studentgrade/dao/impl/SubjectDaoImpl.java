package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.SubjectDao;
import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.util.H2ConnectionManager;
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
    public boolean create(Subject subject) {

        String sql = "INSERT INTO subject(subject_name, term_id) VALUES ( ? , ?)";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, subject.getSubjectName());
            statement.setLong(2, subject.getTermId());
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean update(Subject subject) {

        String sql = "UPDATE subject SET subject_name = ?, term_id = ? " +
                "WHERE subject_id = ?";

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, subject.getSubjectName());
            statement.setLong(2, subject.getTermId());
            statement.setLong(3, subject.getSubjectId());
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

        try (Connection connection = H2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, subject.getSubjectId());
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

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getLong("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setTermId(rs.getLong("term_id"));
                list.add(subject);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Subject findById(Long id) {

        String sql = String.format("SELECT subject_id, subject_name, term_id FROM subject WHERE subject_id = %d", id);
        Subject result = new Subject();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setSubjectId(rs.getLong("subject_id"));
                result.setSubjectName(rs.getString("subject_name"));
                result.setTermId(rs.getLong("term_id"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Subject findByName(String subjectName) {

        String sql = String.format("SELECT subject_id, subject_name, term_id " +
                "FROM subject WHERE subject_name = '%s'", subjectName);
        Subject result = new Subject();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setSubjectId(rs.getLong("subject_id"));
                result.setSubjectName(rs.getString("subject_name"));
                result.setTermId(rs.getLong("term_id"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Subject findByNameAndTermId(String subjectName, Long termId) {

        String sql = String.format("SELECT subject_id, subject_name, term_id " +
                "FROM subject WHERE subject_name = '%s' AND term_id = %d", subjectName, termId);
        Subject result = new Subject();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setSubjectId(rs.getLong("subject_id"));
                result.setSubjectName(rs.getString("subject_name"));
                result.setTermId(rs.getLong("term_id"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public List<Subject> findByTermId(Long termId) {

        String sql = String.format("SELECT * FROM subject WHERE term_id = %d", termId);
        List<Subject> list = new ArrayList<>();

        try (Connection connection = H2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Subject subject = new Subject();
                subject.setSubjectId(rs.getLong("subject_id"));
                subject.setSubjectName(rs.getString("subject_name"));
                subject.setTermId(rs.getLong("term_id"));
                list.add(subject);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
