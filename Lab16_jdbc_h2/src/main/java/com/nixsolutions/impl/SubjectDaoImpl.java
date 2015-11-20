package com.nixsolutions.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nixsolutions.util.ConnectionManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.entity.Subject;

public class SubjectDaoImpl implements SubjectDao {
	
	private final static Logger LOG = LogManager.getLogger(SubjectDaoImpl.class.getName());

	public SubjectDaoImpl() {
	}

	public void create(String name, int termId) {
		PreparedStatement stm = null;
        Connection conn = null;
		try {
            conn = ConnectionManager.getConnection();
			String sql = "INSERT INTO subject (name, term_id) VALUES (?, ?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setInt(2, termId);
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
			ConnectionManager.releaseConnection(conn);
		}
	}

	public void update(Subject subject) {
		PreparedStatement stm = null;
        Connection conn = null;
		String sql = "UPDATE subject SET name=?, term_id=? WHERE subject_id=?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(3, subject.getId());
			stm.setString(1, subject.getName());
			stm.setInt(2, subject.getTermId());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.releaseConnection(conn);
		}
	}
	
	public void delete(Subject subject) {
		PreparedStatement stm = null;
        Connection conn = null;
		String sql = "DELETE FROM subject WHERE subject_id=?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, subject.getId());
			stm.executeUpdate();
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.releaseConnection(conn);
		}
	}

	public Subject getBySubjectId(int subjectId) {
		Subject subject = null;
		PreparedStatement stm = null;
        Connection conn = null;
		String sql = "SELECT * FROM subject WHERE subject_id = ?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, subjectId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			subject = new Subject(rs.getInt("subject_id"), rs.getString("name"), rs.getInt("term_id"));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.releaseConnection(conn);
		}
		return subject;
	}
	
	public Subject getBySubjectName(String name, int termId){
		Subject subject = null;
        Connection conn = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM subject WHERE name = ? AND term_id = ?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setString(1, name);
			stm.setInt(2, termId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			subject = new Subject(rs.getInt("subject_id"), rs.getString("name"), rs.getInt("term_id"));
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.releaseConnection(conn);
		}
		return subject;
	}

	public List<Subject> getAll() {
		List<Subject> toReturn = new ArrayList<Subject>();
		Statement stm = null;
        Connection conn = null;
		String sql = "SELECT * FROM subject;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add(new Subject(rs.getInt("subject_id"), rs.getString("name"), rs.getInt("term_id")));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.releaseConnection(conn);
		}
		return toReturn;
	}

	public List<Subject> getSubjectsByTermId(int termId) {
		List<Subject> toReturn = new ArrayList<Subject>();
		PreparedStatement stm = null;
        Connection conn = null;
		String sql = "SELECT * FROM subject WHERE term_id=?;";
		try {
            conn = ConnectionManager.getConnection();
			stm = conn.prepareStatement(sql);
			stm.setInt(1, termId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				toReturn.add(new Subject(rs.getInt("subject_id"), rs.getString("name"), rs.getInt("term_id")));
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		} finally {
			if (stm != null) {
				try {
					stm.close();
				} catch (SQLException e) {
					LOG.error(e.getMessage(), e);
				}
			}
            ConnectionManager.releaseConnection(conn);
		}
		return toReturn;
	}
}