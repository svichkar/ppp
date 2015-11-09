package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.GenericDAO;
import entities.PersistenceException;
import entities.WorkerStatus;

public class WorkerStatusDAO implements GenericDAO<WorkerStatus> {
	
	private Connection conn;

	public WorkerStatusDAO(Connection conn) throws PersistenceException {
		this.conn = conn;
	}

	@Override
	public WorkerStatus create() throws PersistenceException {
		throw new UnsupportedOperationException();
	}

	public String getSelectByID() {
		return "SELECT * FROM worker_status WHERE worker_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM worker_status;";
	}

	public String getUpdate() {
		return "UPDATE worker_status SET %1$s WHERE worker_id = %2$s;";
	}

	public String getDelete() {
		return "DELETE FROM worker_status WHERE worker_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO worker_status VALUES (%1$s);";
	}

	public List<WorkerStatus> parseResults(ResultSet rs) throws PersistenceException {
		List<WorkerStatus> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				WorkerStatus ws = new WorkerStatus();
				ws.setId(rs.getInt("worker_id"));
				ws.setStatusId(rs.getInt("status_id"));
				resultList.add(ws);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

	@Override
	public WorkerStatus createFrom(WorkerStatus entity) throws PersistenceException {
		WorkerStatus entInstance = null;
		String sql = getCreate();
/*		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.getValuesCommaSeparated());
			int insertCount = stmt.executeUpdate();*/
		try {
			Statement stmt = conn.createStatement();
			int insertCount = stmt.executeUpdate(String.format(sql, entity.getValuesCommaSeparated()));
			if (insertCount != 1) {
				throw new PersistenceException("On creation either multiple or no records were affected. Count: " + insertCount);
			}
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			ResultSet rs = stmt.executeQuery();
			List<WorkerStatus> resultList = parseResults(rs);
			if (resultList == null || resultList.size() != 1) {
				throw new PersistenceException("No records found by id. ID: " + entity.getId());
			}
			entInstance = resultList.get(0);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return entInstance;
	}

	@Override
	public void update(WorkerStatus entity) throws PersistenceException {
		String sql = getUpdate();
		/*try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.toString());
			stmt.setInt(2, entity.getId());
			stmt.setInt(3, entity.getPartId());
			int updCount = stmt.executeUpdate();*/
		try {
			Statement stmt = conn.createStatement();
			int updCount = stmt.executeUpdate(String.format(sql, entity.toString(), entity.getId()));
			if (updCount != 1) {
				throw new PersistenceException("On update either multiple or no records were affected. Count: " + updCount);
			}
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
	}

	@Override
	public void delete(WorkerStatus entity) throws PersistenceException {
		String sql = getDelete();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			int updCount = stmt.executeUpdate();
			if (updCount != 1) {
				throw new PersistenceException("On deletion either multiple or no records were affected. Count: " + updCount);
			}
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
	}

	public WorkerStatus getByPK(int orderId, int statusId) throws PersistenceException {
		List<WorkerStatus> resultList = null;
		String sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			stmt.setInt(2, statusId);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new PersistenceException("More than one result by presumably unique id. Order_id: " + 
					orderId + ". Worker_id: " + statusId);
		}
		return resultList.get(0);
	}
	
	@Override
	public WorkerStatus getByPK(int id) throws PersistenceException {
		throw new UnsupportedOperationException();
	}

	@Override
	public List<WorkerStatus> getAll() throws PersistenceException {
		List<WorkerStatus> resultList = null;
		String sql = getSelectAll();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

}
