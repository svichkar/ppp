package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.Status;

public class StatusDAOImpl implements GenericDao<Status> {

	Connection conn;

	private class ChStatus extends Status {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	public StatusDAOImpl(Connection conn) throws Exception {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	@Override
	public boolean create(Status t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO status (status_name) VALUES (?)");
		ps.setString(1, t.getStatus_name());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Status t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE status SET status_name=?  WHERE status_id=?");
		ps.setString(1, t.getStatus_name());
		ps.setInt(2, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Status t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM status WHERE status_id=?");
		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Status findByPK(int id) throws SQLException {
		int resultExecution;
		ChStatus status = new ChStatus();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM status WHERE status_id=?");
		ps.setInt(1, id);
		resultExecution = ps.executeUpdate();
		if (resultExecution > 0) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				status.setId(rs.getInt("status_id"));
				status.setStatus_name(rs.getString("status_name"));
			}
		} else {
			return null;
		}
		return status;
	}

	@Override
	public Status findByName(String name) throws SQLException {
		boolean resultExecution;
		ChStatus status = new ChStatus();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM status WHERE status_name=?");
		ps.setString(1, name);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				status.setId(rs.getInt("status_id"));
				status.setStatus_name(rs.getString("status_name"));
			}
		} else {
			return null;
		}
		return status;
	}

}
