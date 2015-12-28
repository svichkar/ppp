package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.SubjectDAO;
import com.nixsolutions.entity.Subject;
import com.nixsolutions.util.ConnectionManager;

public class SubjectDAOImpl implements SubjectDAO {
	private static Logger LOG = LogManager.getLogger(SubjectDAOImpl.class.getName());

	@Override
	public Subject createSubject(int subjectId, String subjectName, int termId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO subject VALUES( ?, ?, ?)")) {
				ps.setInt(1, subjectId);
				ps.setString(2, subjectName);
				ps.setInt(3, termId);
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
		return new Subject(subjectId, subjectName, termId);
	}

	@Override
	public void updateSubject(Subject subject) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn
					.prepareStatement("UPDATE subject SET subject_name = ?, term_id=? WHERE subject_id = ?")) {
				ps.setString(1, subject.getSubjectName());
				ps.setInt(2, subject.getTermId());
				ps.setInt(3, subject.getSubjectId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void deleteSubject(Subject subject) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from subject WHERE subject_id = ?")) {
				ps.setInt(1, subject.getSubjectId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public Subject findSubjectById(int subjectId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject WHERE subject_id = ?")) {
				ps.setInt(1, subjectId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Subject(rs.getInt("subject_id"), rs.getString("subject_name"), rs.getInt("term_id"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Subject> findAllSubjects() {
		List<Subject> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					Subject subject = new Subject();
					subject.setSubjectId(rs.getInt("subject_id"));
					subject.setSubjectName(rs.getString("subject_name"));
					subject.setTermId(rs.getInt("term_id"));
					result.add(subject);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
