package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.GenericDAO;
import entities.OrderPart;
import entities.PersistenceException;

public class OrderPartDAOImpl implements GenericDAO<OrderPart> {

	private Connection conn;

	public OrderPartDAOImpl(Connection conn) {
		this.conn = conn;
	}
	
	private class ChOrderPart extends OrderPart {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
		
		@Override
		public void setPartId(int value) {
			super.setPartId(value);
		}
	}

	public String getSelectByID() {
		return "SELECT * FROM sqllab.order_part WHERE order_id = ? AND part_id = ?;";
	}

	public String getSelectAll() {
		return "SELECT * FROM sqllab.order_part;";
	}

	public String getUpdate() {
		return "UPDATE sqllab.order_part SET %1$s WHERE order_id = %2$s AND part_id = %3$s;";
	}

	public String getDelete() {
		return "DELETE FROM sqllab.order_part WHERE order_id = ? AND part_id = ?;";
	}

	public String getCreate() {
		return "INSERT INTO sqllab.order_part VALUES (%1$s);";
	}
	
	public List<OrderPart> parseResults(ResultSet rs) throws PersistenceException {
		List<OrderPart> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChOrderPart op = new ChOrderPart();
				op.setId(rs.getInt("order_id"));
				op.setPartId(rs.getInt("part_id"));
				op.setUsedAmount(rs.getInt("used_amount"));
				resultList.add(op);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}
	
	@Override
	public OrderPart create() throws PersistenceException {
		//throw new UnsupportedOperationException();
		return null;
	}

	@Override
	public OrderPart createFrom(OrderPart entity) throws PersistenceException {
		OrderPart entInstance = null;
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
			stmt.setInt(2, entity.getPartId());
			ResultSet rs = stmt.executeQuery();
			List<OrderPart> resultList = parseResults(rs);
			if (resultList == null || resultList.size() != 1) {
				throw new PersistenceException("No records found by id. Order_ID: " + 
						entity.getId() + ". Par_ID: " + entity.getPartId());
			}
			entInstance = resultList.get(0);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		return entInstance;
	}

	@Override
	public void update(OrderPart entity) throws PersistenceException {
		String sql = getUpdate();
		/*try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, entity.toString());
			stmt.setInt(2, entity.getId());
			stmt.setInt(3, entity.getPartId());
			int updCount = stmt.executeUpdate();*/
		try {
			Statement stmt = conn.createStatement();
			int updCount = stmt.executeUpdate(String.format(sql, 
					entity.toString(), entity.getId(), entity.getPartId()));
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
	public void delete(OrderPart entity) throws PersistenceException {
		String sql = getDelete();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, entity.getId());
			stmt.setInt(2, entity.getPartId());
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
	
	public OrderPart getByPK(int orderId, int partId) throws PersistenceException {
		List<OrderPart> resultList = null;
		String sql = getSelectByID();
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, orderId);
			stmt.setInt(2, partId);
			ResultSet rs = stmt.executeQuery();
			resultList = parseResults(rs);
		} catch (SQLException ex) {
			throw new PersistenceException(ex);
		}
		if (resultList == null || resultList.size() == 0) {
			return null;
		} else if (resultList.size() > 1) {
			throw new PersistenceException("More than one result by presumably unique id. Order_id: " + 
					orderId + ". Part_id: " + partId);
		}
		return resultList.get(0);
	}

	@Override
	public OrderPart getByPK(int id) throws PersistenceException {
		//throw new UnsupportedOperationException();
		return null;
	}

	@Override
	public List<OrderPart> getAll() throws PersistenceException {
		List<OrderPart> resultList = null;
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
