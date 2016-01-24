package com.nixsolutions.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.app.ConnectionManager;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.entities.Customer;

public class CustomerDAOImpl implements CustomerDAO<Customer> {
	private final static Logger LOG = LogManager.getLogger(CustomerDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public CustomerDAOImpl() {

	}

	@Override
	public boolean create(Customer t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("INSERT INTO customer (f_name,l_name, phone) VALUES (?, ?, ?)");
			ps.setString(1, t.getF_name());
			ps.setString(2, t.getL_name());
			ps.setString(3, t.getPhone());
			executionResult = ps.executeUpdate();
			if (executionResult > 0) {
				return true;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Customer t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("UPDATE customer SET  f_name=?, l_name=?, phone=? WHERE customer_id=?");
			ps.setString(1, t.getF_name());
			ps.setString(2, t.getL_name());
			ps.setString(3, t.getPhone());
			ps.setInt(4, t.getId());
			executionResult = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Customer t) {
		int executionResult = 0;
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("DELETE FROM customer WHERE customer_id=?");
			ps.setInt(1, t.getId());
			executionResult = ps.executeUpdate();
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		if (executionResult > 0) {
			return true;
		}
		return false;

	}

	@Override
	public Customer findByPK(int id) {
		boolean resultExecution;
		Customer customer = new Customer();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM customer WHERE customer_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					customer.setId(rs.getInt("customer_id"));
					customer.setF_name(rs.getString("f_name"));
					customer.setL_name(rs.getString("l_name"));
					customer.setPhone(rs.getString("phone"));
				}
			} else {
				customer = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}

		return customer;
	}

	public Customer findByFullName(String f_name, String l_name) {
		boolean resultExecution;
		Customer customer = new Customer();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM customer WHERE f_name=? AND l_name=?");
			ps.setString(1, f_name);
			ps.setString(2, l_name);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					customer.setId(rs.getInt("customer_id"));
					customer.setF_name(rs.getString("f_name"));
					customer.setL_name(rs.getString("l_name"));
					customer.setPhone(rs.getString("phone"));
				}
			} else {
				customer = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}

		return customer;
	}

	public void Dispose() {

		if (this.ps != null) {
			try {
				this.ps.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

		if (this.conn != null) {
			try {
				this.conn.close();
			} catch (SQLException ex) {
				LOG.error(ex, ex);
			}
		}

	}

	@Override
	public List<Customer> getAll() {
		boolean resultExecution;
		List<Customer> lCustomers = new ArrayList<Customer>();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement("SELECT * FROM customer");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					Customer customer = new Customer();
					customer.setId(rs.getInt("customer_id"));
					customer.setF_name(rs.getString("f_name"));
					customer.setL_name(rs.getString("l_name"));
					customer.setPhone(rs.getString("phone"));
					lCustomers.add(customer);
				}
			} else {
				lCustomers = null;
			}
		} catch (SQLException | ClassNotFoundException ex) {
			LOG.error(ex, ex);
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception ex) {
				LOG.error(ex, ex);
			}
		}
		return lCustomers;
	}

}
