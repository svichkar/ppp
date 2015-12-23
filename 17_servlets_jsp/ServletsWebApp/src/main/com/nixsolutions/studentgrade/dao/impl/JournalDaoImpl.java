package com.nixsolutions.studentgrade.dao.impl;

import com.nixsolutions.studentgrade.dao.JournalDao;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.util.M2ConnectionManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by svichkar on 12/18/2015.
 */
public class JournalDaoImpl implements JournalDao {

    @Override
    public boolean create(Journal journal) {

        String sql;

        if (journal.getJournalId() == 0) {
            sql = "INSERT INTO journal(student_id, subject_id, grade_id) VALUES ( ?, ?, ? )";
        } else {
            sql = "INSERT INTO journal(journal_id, student_id, subject_id, grade_id) VALUES ( ?, ?, ?, ? )";
        }

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (journal.getJournalId() == 0) {
                statement.setInt(1, journal.getStudentId());
                statement.setInt(2, journal.getSubjectId());
                statement.setInt(3, journal.getGradeId());
            } else {
                statement.setInt(1, journal.getJournalId());
                statement.setInt(2, journal.getStudentId());
                statement.setInt(3, journal.getSubjectId());
                statement.setInt(4, journal.getGradeId());
            }
            statement.executeUpdate();
            return true;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int update(Journal journal, Journal newJournal) {

        String sql;
        if (newJournal.getJournalId() == 0) {
            sql = "UPDATE journal SET student_id = ?, subject_id = ?, grade_id = ? " +
                    "WHERE journal_id = ? AND student_id = ? AND subject_id = ? AND grade_id = ?";
        } else {
            sql = "UPDATE journal SET journal_id = ?, student_id = ?, subject_id = ?, grade_id = ? " +
                    "WHERE journal_id = ? AND student_id = ? AND subject_id = ? AND grade_id = ?";
        }

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            if (newJournal.getJournalId() == 0) {
                //SET closure:
                statement.setInt(1, newJournal.getStudentId());
                statement.setInt(2, newJournal.getSubjectId());
                statement.setInt(3, newJournal.getGradeId());
                //WHERE closure:
                statement.setInt(4, journal.getJournalId());
                statement.setInt(5, journal.getStudentId());
                statement.setInt(6, journal.getSubjectId());
                statement.setInt(7, journal.getGradeId());
            } else {
                //SET closure:
                statement.setInt(1, newJournal.getJournalId());
                statement.setInt(2, newJournal.getStudentId());
                statement.setInt(3, newJournal.getSubjectId());
                statement.setInt(4, newJournal.getGradeId());
                //WHERE closure:
                statement.setInt(5, journal.getJournalId());
                statement.setInt(6, journal.getStudentId());
                statement.setInt(7, journal.getSubjectId());
                statement.setInt(8, journal.getGradeId());
            }

            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int delete(Journal journal) {

        String sql = "DELETE FROM journal WHERE journal_id = ? AND student_id = ? AND subject_id = ? AND grade_id = ?";

        try (Connection connection = M2ConnectionManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, journal.getJournalId());
            statement.setInt(2, journal.getStudentId());
            statement.setInt(3, journal.getSubjectId());
            statement.setInt(4, journal.getGradeId());
            return statement.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return 0;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
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
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }
}
