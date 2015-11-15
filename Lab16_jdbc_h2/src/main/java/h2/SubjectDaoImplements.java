package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.SubjectDao;
import com.nixsolutions.entities.Subject;

public class SubjectDaoImplements implements SubjectDao {
	
	private final static Logger LOG = LogManager.getLogger(SubjectDaoImplements.class.getName());
	private Connection conn;

	public SubjectDaoImplements(Connection connection) {
		conn = connection;
	}

	public void create(String name, int termId) {
		PreparedStatement stm = null;
		try {
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
		}
	}

	public void update(Subject subject) {
		PreparedStatement stm = null;
		String sql = "UPDATE subject SET name=?, term_id=? WHERE subject_id=?;";
		try {
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
		}
	}
	
	public void delete(Subject subject) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM subject WHERE subject_id=?;";
		try {
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
		}
	}

	public Subject getBySubjectId(int subjectId) {
		Subject subject = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM subject WHERE subject_id = ?;";
		try {
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
		}
		return subject;
	}
	
	public Subject getBySubjectName(String name, int termId){
		Subject subject = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM subject WHERE name = ? AND term_id = ?;";
		try {
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
		}
		return subject;
	}

	public List<Subject> getAll() {
		List<Subject> toReturn = new ArrayList<Subject>();
		Statement stm = null;
		String sql = "SELECT * FROM subject;";
		try {
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
		}
		return toReturn;
	}

	public List<Subject> getSubjectsByTermId(int termId) {
		List<Subject> toReturn = new ArrayList<Subject>();
		PreparedStatement stm = null;
		String sql = "SELECT * FROM subject WHERE term_id=?;";
		try {
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
		}
		return toReturn;
	}
	
}
