package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO;
import entities.OrderStatus;
import entities.PersistenceException;

public class OrderStatusDAO extends AbstractDAO<OrderStatus>{
	
	private class ChOrderStatus extends OrderStatus {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}
	
	public OrderStatusDAO(Connection conn) {
		super(conn);
	}

	@Override
	public OrderStatus create() throws PersistenceException {
		OrderStatus os = new OrderStatus();
		return createFrom(os);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM order_status WHERE order_status_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM order_status;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE order_status SET %1$s WHERE order_status_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM order_status WHERE order_status_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO order_status (order_status_name) VALUES (%1$s);";
	}

	@Override
	public List<OrderStatus> parseResults(ResultSet rs) throws PersistenceException {
		List<OrderStatus> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChOrderStatus orderStatus = new ChOrderStatus();
				orderStatus.setId(rs.getInt("order_status_id"));
				orderStatus.setOrderStatusName(rs.getString("order_status_name"));
				resultList.add(orderStatus);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

}
