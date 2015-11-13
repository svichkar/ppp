package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.GenericDAO;
import entities.OrderWorker;
import entities.PersistenceException;

public class OrderWorkerDAOImpl implements GenericDAO<OrderWorker> {
	
	private Connection conn;

	public OrderWorkerDAOImpl(Connection conn) throws PersistenceException {
		this.conn = conn;
		try {
			this.conn.setAutoCommit(false);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
	}

	private class ChOrderWorker extends OrderWorker {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
		
		@Override
		public void setWorkerId(int value) {
			super.setWorkerId(value);
		}
	}

	@Override
	public OrderWorker create() throws PersistenceException {
		//throw new UnsupportedOperationException();
		return null;
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.order_worker WHERE order_id = ? AND worker_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.order_worker;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.order_worker SET %1$s WHERE order_id = %2$s AND worker_id = %3$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.order_worker WHERE order_id = ? AND worker_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.order_worker VALUES (%1$s);";
	}

	public List<OrderWorker> parseResults(ResultSet rs) throws PersistenceException {
		List<OrderWorker> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChOrderWorker ow = new ChOrderWorker();
				ow.setId(rs.getInt("order_id"));
				ow.setWorkerId(rs.getInt("worker_id"));
				ow.setIsCompleted(rs.getString("is_completed"));
				resultList.add(ow);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

	@Override
	public OrderWorker createFrom(OrderWorker entity) throws PersistenceException {
		OrderWorker entInstance = null;
		String sql = getCreate();
/*		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.getValuesCommaSeparated());
			int insertCount = stmt.executeUpdate();*/
		try {
			Statement stmt = conn.createStatement();
			int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()));
			if (insertCount != 1) {
				conn.rollback();
				throw new PersistenceException("On creation either multiple or no records were affected. Count: " + insertCount);
			}
			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
			throw new PersistenceException(ex);
		}
		sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			stmt.setInt(2, entity.getWorkerId());
			ResultSet rs = stmt.executeQuery();
			List<OrderWorker> resultList = parseResults(rs);
			if (resultList == null || resultList.size() != 1) {
				throw new PersistenceException("No records found by id. Order_ID: " + 
						entity.getId() + ". Worker_ID: " + entity.getWorkerId());
			}
			entInstance = resultList.get(0);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return entInstance;
	}

	@Override
	public void update(OrderWorker entity) throws PersistenceException {
		String sql = getUpdate();
/*		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.toString());
			stmt.setInt(2, entity.getId());
			stmt.setInt(3, entity.getWorkerId());
			int updCount = stmt.executeUpdate();*/
		try {
			Statement stmt = conn.createStatement();
			int updCount = stmt.executeUpdate(String.format(sql, 
					entity.toString(), entity.getId(), entity.getWorkerId()));
			if (updCount != 1) {
				conn.rollback();
				throw new PersistenceException("On update either multiple or no records were affected. Count: " + updCount);
			}
			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
			throw new PersistenceException(ex);
		}
	}

	@Override
	public void delete(OrderWorker entity) throws PersistenceException {
		String sql = getDelete();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			stmt.setInt(2, entity.getWorkerId());
			int updCount = stmt.executeUpdate();
			if (updCount != 1) {
				conn.rollback();
				throw new PersistenceException("On deletion either multiple or no records were affected. Count: " + updCount);
			}
			conn.commit();
		} catch (SQLException ex) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				throw new PersistenceException(e);
			}
			throw new PersistenceException(ex);
		}
	}

	public OrderWorker getByPK(int orderId, int workerId) throws PersistenceException {
		List<OrderWorker> resultList = null;
		String sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			stmt.setInt(2, workerId);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new PersistenceException("More than one result by presumably unique id. Order_id: " + 
					orderId + ". Worker_id: " + workerId);
		}
		return resultList.get(0);
	}

	@Override
	public List<OrderWorker> getAll() throws PersistenceException {
		List<OrderWorker> resultList = null;
		String sql = getSelectAll();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

	@Override
	public OrderWorker getByPK(int id) throws PersistenceException {
		//throw new UnsupportedOperationException();
		return null;
	}
}
