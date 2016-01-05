package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class JournalDAOImpl implements JournalDAO {

	private static Logger LOG = LogManager.getLogger(JournalDAOImpl.class.getName());

	@Override
	public Journal createJournal(int journalId, int studentId, int subjectId, int gradeId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO journal VALUES( ?, ?, ?, ?)")) {
				ps.setInt(1, journalId);
				ps.setInt(2, studentId);
				ps.setInt(3, subjectId);
				ps.setInt(4, gradeId);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return new Journal(journalId, studentId, subjectId, gradeId);
	}

	@Override
	public void updateJournal(Journal journal) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(
					"UPDATE journal SET student_id=?, subject_id=?, grade_id=? WHERE journal_id = ?")) {
				ps.setInt(1, journal.getStudentId());
				ps.setInt(2, journal.getSubjectId());
				ps.setInt(3, journal.getGradeId());
				ps.setInt(4, journal.getJournalId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void deleteJournal(Journal journal) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from journal WHERE journal_id = ?")) {
				ps.setInt(1, journal.getJournalId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public Journal findJournalById(int journalId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM journal WHERE journal_id = ?")) {
				ps.setInt(1, journalId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Journal(rs.getInt("journal_id"), rs.getInt("student_id"), rs.getInt("subject_id"),
						rs.getInt("grade_id"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Journal> findAllJournals() {
		List<Journal> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM journal")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					Journal journal = new Journal();
					journal.setJournalId(rs.getInt("journal_id"));
					journal.setStudentId(rs.getInt("student_id"));
					journal.setSubjectId(rs.getInt("subject_id"));
					journal.setGradeId(rs.getInt("grade_id"));
					result.add(journal);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
