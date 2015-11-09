package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO;
import entities.OrderInWork;
import entities.PersistenceException;

public class OrderInWorkDAO extends AbstractDAO<OrderInWork>{
	
	public OrderInWorkDAO(Connection conn) {
		super(conn);
	}

	private class ChOrderInWork extends OrderInWork {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	@Override
	public OrderInWork create() throws PersistenceException {
		OrderInWork oiw = new OrderInWork();
		return createFrom(oiw);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM order_in_work WHERE order_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM order_in_work;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE order_in_work SET %1$s WHERE order_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM order_in_work WHERE order_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO order_in_work (order_status_id, description, car_id, timestamp_start, timestamp_finish) VALUES (%1$s);";
	}

	@Override
	public List<OrderInWork> parseResults(ResultSet rs) throws PersistenceException {
		List<OrderInWork> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChOrderInWork oiw = new ChOrderInWork();
				oiw.setId(rs.getInt("order_id"));
				oiw.setOrderStatusId(rs.getInt("order_status_id"));
				oiw.setDescription(rs.getString("description"));
				oiw.setCarId(rs.getInt("car_id"));
				oiw.setTimestampStart(rs.getTimestamp("timestamp_start"));
				oiw.setTimestampFinish(rs.getTimestamp("timestamp_finish"));
				resultList.add(oiw);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}
	
	
}
