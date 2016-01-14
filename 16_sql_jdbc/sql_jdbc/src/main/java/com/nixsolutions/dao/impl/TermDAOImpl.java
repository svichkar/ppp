package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.TermDAO;
import com.nixsolutions.entity.Term;
import com.nixsolutions.util.ConnectionManager;

public class TermDAOImpl implements TermDAO {
	private static Logger LOG = LogManager.getLogger(TermDAOImpl.class.getName());

	@Override
	public void createTerm(Term term) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("INSERT INTO term VALUES( ?, ?)")) {
				ps.setLong(1, term.getTermId());
				ps.setString(2, term.getTermName());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public void updateTerm(Term term) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("UPDATE term SET term_name = ? WHERE term_id = ?")) {
				ps.setString(1, term.getTermName());
				ps.setLong(2, term.getTermId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	@Override
	public void deleteTerm(Term term) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("DELETE from term WHERE term_id = ?")) {
				ps.setLong(1, term.getTermId());
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			LOG.error(e);
		}
	}

	@Override
	public Term findTermById(Long termId) {
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM term WHERE term_id = ?")) {
				ps.setLong(1, termId);
				ResultSet rs = ps.executeQuery();
				rs.next();
				return new Term(rs.getLong("term_id"), rs.getString("term_name"));
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

	@Override
	public List<Term> findAllTerms() {
		List<Term> result = new ArrayList<>();
		ResultSet rs;
		try (Connection conn = ConnectionManager.getConnection()) {
			try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM term")) {
				rs = ps.executeQuery();
				while (rs.next()) {
					Term term = new Term();
					term.setTermId(rs.getLong("term_id"));
					term.setTermName(rs.getString("term_name"));
					result.add(term);
				}
				return result;
			}
		} catch (SQLException e) {
			LOG.error(e);
			return null;
		}
	}

}
