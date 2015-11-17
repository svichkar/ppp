package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.Part;

public class PartDAOImpl implements GenericDao<Part> {

	Connection conn;

	private class ChPart extends Part {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	public PartDAOImpl(Connection conn) throws Exception {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	@Override
	public boolean create(Part t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO part (part_name, vendor, amount) VALUES (?,?,?)");
		ps.setString(1, t.getPart_name());
		ps.setString(2, t.getVendor());
		ps.setInt(3, t.getAmount());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Part t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE part SET part_name=?, vendor=?, amount=?  WHERE part_id=?");
		ps.setString(1, t.getPart_name());
		ps.setString(2, t.getVendor());
		ps.setInt(3, t.getAmount());
		ps.setInt(4, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Part t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM part WHERE part_id=?");
		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Part findByPK(int id) throws SQLException {
		boolean resultExecution;
		ChPart part = new ChPart();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM part WHERE part_id=?");
		ps.setInt(1, id);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				part.setId(rs.getInt("part_id"));
				part.setPart_name(rs.getString("part_name"));
				part.setVendor(rs.getString("vendor"));
				part.setAmount(rs.getInt("amount"));
			}
		} else {
			return null;
		}
		return part;
	}

	@Override
	public Part findByName(String name) throws SQLException {
		boolean resultExecution;
		ChPart part = new ChPart();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM part WHERE part_name=?");
		ps.setString(1, name);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				part.setId(rs.getInt("part_id"));
				part.setPart_name(rs.getString("part_name"));
				part.setVendor(rs.getString("vendor"));
				part.setAmount(rs.getInt("amount"));
			}
		} else {
			return null;
		}
		return part;
	}

}
