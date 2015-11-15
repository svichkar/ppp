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

import com.nixsolutions.dao.StatusDao;
import com.nixsolutions.entities.Status;

public class StatusDaoImplements implements StatusDao {
	private final static Logger LOG = LogManager.getLogger(StatusDaoImplements.class.getName());
	private Connection conn;

	public StatusDaoImplements(Connection connection) {
		conn = connection;
	}
	public void create(String statusName) {
		PreparedStatement stm = null;
		try {
			String sql = "INSERT INTO status (status_name) VALUES (?);";
			stm = conn.prepareStatement(sql);
			stm.setString(1, statusName);
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

	public void update(Status status) {
		PreparedStatement stm = null;
		String sql = "UPDATE status SET status_name=? WHERE status_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(2, status.getId());
			stm.setString(1, status.getStatusName());
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

	public void delete(Status status) {
		PreparedStatement stm = null;
		String sql = "DELETE FROM status WHERE status_id=?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, status.getId());
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

	public Status getByStatusId(int statusId) {
		Status status = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM status WHERE status_id = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setInt(1, statusId);
			ResultSet rs = stm.executeQuery();
			rs.next();
			status = new Status(rs.getInt("status_id"), rs.getString("status_name"));
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
		return status;
	}

	public Status getByStatusName(String statusName) {
		Status status = null;
		PreparedStatement stm = null;
		String sql = "SELECT * FROM status WHERE status_name = ?;";
		try {
			stm = conn.prepareStatement(sql);
			stm.setString(1, statusName);
			ResultSet rs = stm.executeQuery();
			rs.next();
			status = new Status(rs.getInt("status_id"), rs.getString("status_name"));
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
		return status;
	}

	public List<Status> getAll() {
		List<Status> toReturn = new ArrayList<Status>();
		Statement stm = null;
		String sql = "SELECT * FROM status;";
		try {
			stm = conn.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while (rs.next()) {
				toReturn.add(new Status(rs.getInt("status_id"), rs.getString("status_name")));
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
