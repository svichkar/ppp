package com.nixsolutions.studentgrade.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.studentgrade.entity.Subject;
import com.nixsolutions.studentgrade.dao.SubjectDAO;
import com.nixsolutions.studentgrade.util.ConnectionManager;

public class SubjectDAOImpl implements SubjectDAO {
	private static Logger LOG = LogManager.getLogger(SubjectDAOImpl.class.getName());

	@Override
	public void createSubject(Subject subject) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO subject VALUES( ?, ?, ?)")) {
				ps.setLong(1, subject.getSubjectId());
				ps.setString(2, subject.getSubjectName());
				ps.setLong(3, subject.getTermId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void updateSubject(Subject subject) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn
					.prepareStatement("UPDATE subject SET subject_name = ?, term_id=? WHERE subject_id = ?")) {
				ps.setString(1, subject.getSubjectName());
				ps.setLong(2, subject.getTermId());
				ps.setLong(3, subject.getSubjectId());
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
				ps.setLong(1, subject.getSubjectId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public Subject findSubjectById(Long subjectId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject WHERE subject_id = ?")) {
				ps.setLong(1, subjectId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Subject(rs.getLong("subject_id"), rs.getString("subject_name"), rs.getLong("term_id"));
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
					subject.setSubjectId(rs.getLong("subject_id"));
					subject.setSubjectName(rs.getString("subject_name"));
					subject.setTermId(rs.getLong("term_id"));
					result.add(subject);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Subject> findSubjectsByName(String subjectName) {
		List<Subject> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject WHERE subject_name=?")) {
				ps.setString(1, subjectName);
				rs = ps.executeQuery();
				while (rs.next()) {
					Subject subject = new Subject();
					subject.setSubjectId(rs.getLong("subject_id"));
					subject.setSubjectName(rs.getString("subject_name"));
					subject.setTermId(rs.getLong("term_id"));
					result.add(subject);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Subject> findSubjectsByTermId(Long termId) {
		List<Subject> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject WHERE term_id=?")) {
				ps.setLong(1, termId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Subject subject = new Subject();
					subject.setSubjectId(rs.getLong("subject_id"));
					subject.setSubjectName(rs.getString("subject_name"));
					subject.setTermId(rs.getLong("term_id"));
					result.add(subject);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Subject> findSubjectByNameAndTermId(String subjectName, Long termId) {
		List<Subject> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM subject WHERE subject_name=? AND term_id=?")) {
				ps.setString(1, subjectName);
				ps.setLong(2, termId);
				rs = ps.executeQuery();
				while (rs.next()) {
					Subject subject = new Subject();
					subject.setSubjectId(rs.getLong("subject_id"));
					subject.setSubjectName(rs.getString("subject_name"));
					subject.setTermId(rs.getLong("term_id"));
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
