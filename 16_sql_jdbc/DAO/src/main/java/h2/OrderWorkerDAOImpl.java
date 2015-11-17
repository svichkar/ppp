package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.GenericDao;
import entities.OrderWorker;

public class OrderWorkerDAOImpl implements GenericDao<OrderWorker> {

	Connection conn;

	public OrderWorkerDAOImpl(Connection conn) throws Exception {
		this.conn = conn;
		// define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	@Override
	public boolean create(OrderWorker t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO order_worker (worker_id, order_id, completed) VALUES (?, ?, ?)");
		ps.setInt(1, t.getWorker_id());
		ps.setInt(2, t.getId());
		ps.setBoolean(3, t.getCompleted());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(OrderWorker t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE order_worker SET completed=? WHERE worker_id=? AND order_id=?");
		ps.setBoolean(1, t.getCompleted());
		ps.setInt(2, t.getWorker_id());
		ps.setInt(3, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(OrderWorker t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM order_worker WHERE worker_id=? AND order_id=?");
		ps.setInt(1, t.getWorker_id());
		ps.setInt(2, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;

	}

	@Override
	public OrderWorker findByPK(int id) throws SQLException {
		return null;
	}

	@Override
	public OrderWorker findByName(String name) throws SQLException {
		return null;
	}

}
