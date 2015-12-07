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

import database.dao.JournalDao;
import database.entities.Journal;

public class JournalDaoImplements implements JournalDao {
	private final static Logger LOG = LogManager.getLogger(JournalDaoImplements.class.getName());
	private Connection conn;

	public JournalDaoImplements(Connection connection) {
		conn = connection;
	}

	public void create(int studentId, int subjectId, int rate) {
		PreparedStatement stm = null;
		try {
			String sql = "INSERT INTO journal (student_id, subject_id, rate) VALUES (?, ?, ?);";
			stm = conn.prepareStatement(sql);
			stm.setInt(1, studentId);
			stm.setInt(2, subjectId);
			stm.setInt(3, rate);
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

	public void update(Journal journal) {
		PreparedStatement stm = null;
		String sql = "UPDATE journal SET student_id=?, subject_id=?, rate=?  WHERE journal_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(4, journal.getId());
			stm.setInt(1, journal.getStudentId());
			stm.setInt(2, journal.getSubjectId());
			stm.setInt(3, journal.getRate());
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

	public void delete(Journal journal) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM journal WHERE journal_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, journal.getId());
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

	public Journal getByJournalById(int journalId) {
		Journal journal = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM journal WHERE journal_id = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, journalId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			journal = new Journal(rs.getInt("journal_id"), rs.getInt("student_id"), rs.getInt("subject_id"), rs.getInt("rate"));
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
		return journal;
	}

	public List<Journal> getAll() {
		List<Journal> toReturn = new ArrayList<Journal>();
		Statement stm = null;
		String sql = "SELECT * FROM journal;";
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add(new Journal(rs.getInt("journal_id"), rs.getInt("student_id"), rs.getInt("subject_id"), rs.getInt("rate")));
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

	public List<Journal> getJournalByStudentId(int studentId) {
		List<Journal> toReturn = new ArrayList<Journal>();
		PreparedStatement stm = null;
		String sql = "SELECT * FROM journal WHERE student_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, studentId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				toReturn.add(new Journal(rs.getInt("journal_id"), rs.getInt("student_id"), rs.getInt("subject_id"), rs.getInt("rate")));
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

	public List<Journal> getJournalBySubjectId(int subjectId) {
		List<Journal> toReturn = new ArrayList<Journal>();
		PreparedStatement stm = null;
		String sql = "SELECT * FROM journal WHERE subject_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, subjectId);
			ResultSet rs = stm.executeQuery();
			while (rs.next()) {
				toReturn.add(new Journal(rs.getInt("journal_id"), rs.getInt("student_id"), rs.getInt("subject_id"), rs.getInt("rate")));
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

}
