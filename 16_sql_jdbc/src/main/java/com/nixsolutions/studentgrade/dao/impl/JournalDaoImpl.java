package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class JournalDaoImpl implements JournalDao {

    private static final Logger LOG = LogManager.getLogger(JournalDaoImpl.class);

    @Override
    public Journal create(Journal journal) {

        String sql = "INSERT INTO journal(student_id, subject_id, grade_id) VALUES ( ?, ?, ? )";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, journal.getStudentId());
            statement.setInt(2, journal.getSubjectId());
            statement.setInt(3, journal.getGradeId());
            statement.executeUpdate();
            return journal;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public boolean update(Journal journal) {

        String sql = "UPDATE journal SET student_id = ?, subject_id = ?, grade_id = ? WHERE journal_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, journal.getStudentId());
            statement.setInt(2, journal.getSubjectId());
            statement.setInt(3, journal.getGradeId());
            statement.setInt(4, journal.getJournalId());
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public boolean delete(Journal journal) {

        String sql = "DELETE FROM journal WHERE journal_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, journal.getJournalId());
            statement.executeUpdate();
            return true;

        } catch (SQLException e) {
            LOG.error(e);
            return false;
        }
    }

    @Override
    public List<Journal> findAll() {

        String sql = "SELECT * FROM journal";
        List<Journal> list = new ArrayList<>();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Journal journal = new Journal();
                journal.setJournalId(rs.getInt("journal_id"));
                journal.setStudentId(rs.getInt("student_id"));
                journal.setSubjectId(rs.getInt("subject_id"));
                journal.setGradeId(rs.getInt("grade_id"));
                list.add(journal);
            }
            return list;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }

    @Override
    public Journal findById(int id) {

        String sql = String.format("SELECT journal_id, student_id, subject_id, grade_id FROM journal WHERE journal_id = %d", id);
        Journal result = new Journal();

        try (Connection connection = M2ConnectionManager.getConnection();
             Statement statement = connection.createStatement();) {

            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                result.setJournalId(rs.getInt("journal_id"));
                result.setStudentId(rs.getInt("student_id"));
                result.setSubjectId(rs.getInt("subject_id"));
                result.setGradeId(rs.getInt("grade_id"));
            }
            return result;
        } catch (SQLException e) {
            LOG.error(e);
            return null;
        }
    }
}
