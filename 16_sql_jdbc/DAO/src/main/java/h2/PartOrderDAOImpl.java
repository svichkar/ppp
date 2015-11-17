package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.GenericDao;
import entities.Part;
import entities.PartOrder;

public class PartOrderDAOImpl implements GenericDao<PartOrder> {

	Connection conn;

	public PartOrderDAOImpl(Connection conn) throws Exception {
		this.conn = conn;
		// define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	@Override
	public boolean create(PartOrder t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO part_order (order_id, part_id, amount) VALUES (?,?,?);");
		ps.setInt(1, t.getId());
		ps.setInt(2, t.getPartid());
		ps.setInt(3, t.getAmount());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(PartOrder t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE part_order SET amount=?  WHERE order_id=? AND part_id=?");
		ps.setInt(1, t.getAmount());
		ps.setInt(2, t.getId());
		ps.setInt(3, t.getPartid());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(PartOrder t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM part_order WHERE order_id=? AND part_id=?");
		ps.setInt(1, t.getId());
		ps.setInt(2, t.getPartid());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public PartOrder findByPK(int id) throws SQLException {
		return null;
	}

	@Override
	public PartOrder findByName(String name) throws SQLException {
		return null;
	}

}
