package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.Worker;

public class WorkerDAOImpl implements GenericDao<Worker> {

	Connection conn;

	private class ChWorker extends Worker {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	public WorkerDAOImpl(Connection conn) throws Exception {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	@Override
	public boolean create(Worker t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO worker (f_name,l_name, spec_id, status_id) VALUES (?, ?, ?, ?)");
		ps.setString(1, t.getF_name());
		ps.setString(2, t.getL_name());
		ps.setInt(3, t.getSpec_id());
		ps.setInt(4, t.getStatus_id());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Worker t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE worker SET f_name=?,l_name=?, spec_id=?, status_id=? WHERE worker_id=?");
		ps.setString(1, t.getF_name());
		ps.setString(2, t.getL_name());
		ps.setInt(3, t.getSpec_id());
		ps.setInt(4, t.getStatus_id());
		ps.setInt(5, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Worker t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM worker WHERE worker_id=?");
		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Worker findByPK(int id) throws SQLException {
		boolean resultExecution;
		ChWorker worker = new ChWorker();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM worker WHERE worker_id=?");
		ps.setInt(1, id);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				worker.setId(rs.getInt("worker_id"));
				worker.setF_name(rs.getString("f_name"));
				worker.setL_name(rs.getString("l_name"));
				worker.setSpec_id(rs.getInt("spec_id"));
				worker.setStatus_id(rs.getInt("status_id"));
			}
		} else {
			return null;
		}
		return worker;
	}

	@Override
	public Worker findByName(String name) throws SQLException {
		boolean resultExecution;
		ChWorker worker = new ChWorker();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM worker WHERE f_name=? OR l_name=?");
		ps.setString(1, name);
		ps.setString(2, name);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				worker.setId(rs.getInt("worker_id"));
				worker.setF_name(rs.getString("f_name"));
				worker.setL_name(rs.getString("l_name"));
				worker.setSpec_id(rs.getInt("spec_id"));
				worker.setStatus_id(rs.getInt("status_id"));
			}
		} else {
			return null;
		}
		return worker;
	}

}
