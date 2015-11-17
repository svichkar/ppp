package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.Customer;

public class CustomerDAOImpl implements GenericDao<Customer> {

	private Connection conn;

	public CustomerDAOImpl(Connection conn) throws SQLException {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	private class ChCustomer extends Customer {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	@Override
	public boolean create(Customer t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO customer (f_name,l_name, phone) VALUES (?, ?, ?)");
		ps.setString(1, t.getF_name());
		ps.setString(2, t.getL_name());
		ps.setString(3, t.getPhone());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Customer t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE customer SET  f_name=?, l_name=?, phone=? WHERE customer_id=?");
		ps.setString(1, t.getF_name());
		ps.setString(2, t.getL_name());
		ps.setString(3, t.getPhone());
		ps.setInt(4, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Customer t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM customer WHERE customer_id=?");
		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;

	}

	@Override
	public Customer findByPK(int id) throws SQLException {
		boolean resultExecution;
		ChCustomer customer = new ChCustomer();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM customer WHERE customer_id=?");
		ps.setInt(1, id);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				customer.setId(rs.getInt("customer_id"));
				customer.setf_name(rs.getString("f_name"));
				customer.setl_name(rs.getString("l_name"));
				customer.setphone(rs.getString("phone"));
			}
		} else {
			return null;
		}

		return customer;
	}

	@Override
	public Customer findByName(String name) throws SQLException {
		boolean resultExecution;
		ChCustomer customer = new ChCustomer();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM customer WHERE f_name=? OR l_name=?");
		ps.setString(1, name);
		ps.setString(2, name);
		resultExecution = ps.execute();
		if (resultExecution) {
			ResultSet rs = ps.getResultSet();
			while (rs.next()) {
				customer.setId(rs.getInt("customer_id"));
				customer.setf_name(rs.getString("f_name"));
				customer.setl_name(rs.getString("l_name"));
				customer.setphone(rs.getString("phone"));
			}
		} else {
			return null;
		}

		return customer;
	}

}
