package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.entity.Grade;
import com.nixsolutions.studentgrade.entity.Journal;
import com.nixsolutions.studentgrade.dao.DAOFactory;
import com.nixsolutions.studentgrade.dao.GradeDAO;
import com.nixsolutions.studentgrade.dao.JournalDAO;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class JournalDAOImpl implements JournalDAO {

	private static Logger LOG = LogManager.getLogger(JournalDAOImpl.class.getName());

	@Override
	public void createJournal(Journal journal) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO journal VALUES( ?, ?, ?, ?)")) {
				ps.setLong(1, journal.getJournalId());
				ps.setLong(2, journal.getStudentId());
				ps.setLong(3, journal.getSubjectId());
				ps.setInt(4, journal.getGradeId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void updateJournal(Journal journal) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(
					"UPDATE journal SET student_id=?, subject_id=?, grade_id=? WHERE journal_id = ?")) {
				ps.setLong(1, journal.getStudentId());
				ps.setLong(2, journal.getSubjectId());
				ps.setInt(3, journal.getGradeId());
				ps.setLong(4, journal.getJournalId());
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
				ps.setLong(1, journal.getJournalId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public Journal findJournalById(Long journalId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM journal WHERE journal_id = ?")) {
				ps.setLong(1, journalId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Journal(rs.getLong("journal_id"), rs.getLong("student_id"), rs.getLong("subject_id"),
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
					journal.setJournalId(rs.getLong("journal_id"));
					journal.setStudentId(rs.getLong("student_id"));
					journal.setSubjectId(rs.getLong("subject_id"));
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

	@Override
	public List<Journal> findJournalsByStudentIdAndTermId(Long studentId, Long termId) {
		List<Journal> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement(
					"SELECT * FROM journal j JOIN subject s ON j.subject_id=s.subject_id WHERE j.student_id=? AND s.term_id=?")) {
				ps.setLong(1, studentId);
				ps.setLong(2, termId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Journal journal = new Journal();
					journal.setJournalId(rs.getLong("journal_id"));
					journal.setStudentId(rs.getLong("student_id"));
					journal.setSubjectId(rs.getLong("subject_id"));
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

	@Override
	public Grade findGPAByStudentIdAndTermId(Long studentId, Long termId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT AVG(grade_id) as grade_id FROM journal j JOIN subject s ON j.subject_id=s.subject_id "
					+ "WHERE j.student_id=? AND s.term_id=?")) {
				ps.setLong(1, studentId);
				ps.setLong(2, termId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				GradeDAO grade = DAOFactory.getGrade();
				return grade.findGradeById(rs.getInt("grade_id"));
			}
		}catch(

	SQLException e)

	{
		LOG.error(e);
		return null;
	}
}

}
