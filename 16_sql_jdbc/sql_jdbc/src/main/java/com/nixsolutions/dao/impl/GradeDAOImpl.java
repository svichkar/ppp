package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.GradeDAO;
import com.nixsolutions.entity.Grade;
import com.nixsolutions.util.ConnectionManager;

public class GradeDAOImpl implements GradeDAO {
	private static Logger LOG = LogManager.getLogger(GradeDAOImpl.class.getName());
	private Connection conn = null;
	private PreparedStatement ps = null;

	@Override
	public void createGrade(Grade grade) {
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("INSERT INTO grade VALUES( ?, ?)");
			ps.setInt(1, grade.getGradeId());
			ps.setString(2, grade.getGradeName());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			LOG.error(e);
			try {
				conn.rollback();
			} catch (SQLException ex) {
				LOG.error(ex);
			}
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					LOG.error(e);
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					LOG.error(e);
				}
		}
	}

	@Override
	public void updateGrade(Grade grade) {
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("UPDATE grade SET grade_name = ? WHERE grade_id = ?");
			ps.setString(1, grade.getGradeName());
			ps.setInt(2, grade.getGradeId());
			ps.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			LOG.error(e);
			try {
				conn.rollback();
			} catch (SQLException ex) {
				LOG.error(ex);
			}
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					LOG.error(e);
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					LOG.error(e);
				}
		}
	}

	@Override
	public void deleteGrade(Grade grade) {
		try {
			conn = ConnectionManager.getConnection();
			conn.setAutoCommit(false);
			ps = conn.prepareStatement("DELETE from grade WHERE grade_id = ?");
				ps.setInt(1, grade.getGradeId());
				ps.executeUpdate();
				conn.commit();
		} catch (SQLException e) {
			LOG.error(e);
			try {
				conn.rollback();
			} catch (SQLException ex) {
				LOG.error(ex);
			}
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					LOG.error(e);
				}
			if (conn != null)
				try {
					conn.close();
				} catch (SQLException e) {
					LOG.error(e);
				}
		}
	}

	@Override
	public Grade findGradeById(int gradeId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM grade WHERE grade_id = ?")) {
				ps.setInt(1, gradeId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Grade(rs.getInt("grade_id"), rs.getString("grade_name"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		} 
	}

	@Override
	public List<Grade> findAllGrades() {
		List<Grade> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM grade")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					Grade grade = new Grade();
					grade.setGradeId(rs.getInt("grade_id"));
					grade.setGradeName(rs.getString("grade_name"));
					result.add(grade);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
