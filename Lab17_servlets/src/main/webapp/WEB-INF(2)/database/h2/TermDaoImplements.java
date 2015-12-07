package database.h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import database.dao.TermDao;
import database.entities.Term;

public class TermDaoImplements implements TermDao {

	private final static Logger LOG = LogManager.getLogger(TermDaoImplements.class.getName());
	private Connection conn;

	public TermDaoImplements(Connection connection) {
		conn = connection;
	}

	public void create(String alias) {
		PreparedStatement stm = null;
		try {
			String sql = "INSERT INTO term (alias) VALUES (?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, alias);
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
		}
	}

	public void update(Term term) {
		PreparedStatement stm = null;
		String sql = "UPDATE term SET alias=? WHERE term_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(2, term.getId());
			stm.setString(1, term.getAlias());
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
		}
	}

	public void delete(Term term) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM term WHERE term_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, term.getId());
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
		}
	}

	public List<Term> getAll() {
		List<Term> toReturn = new ArrayList<Term>();
		Statement stm = null;
		String sql = "SELECT * FROM term;";
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add(new Term(rs.getInt("term_id"), rs.getString("alias")));
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
		}
		return toReturn;
	}

	public Term getByTermId(int termId) {
		Term term = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM term WHERE term_id = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, termId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			term = new Term(rs.getInt("term_id"), rs.getString("alias"));
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
		}
		return term;
	}

	public Term getByTermAlias(String alias) {
		Term term = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM term WHERE alias = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, alias);
			ResultSet rs = stm.executeQuery();
			rs.next();
			term = new Term(rs.getInt("term_id"), rs.getString("alias"));
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
		}
		return term;
	}
}
