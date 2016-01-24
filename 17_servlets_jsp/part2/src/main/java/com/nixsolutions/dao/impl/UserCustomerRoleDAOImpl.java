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
import com.nixsolutions.dao.GenericDao;
import com.nixsolutions.entities.User;
import com.nixsolutions.entities.UserCustomerRole;

public class UserCustomerRoleDAOImpl implements GenericDao<UserCustomerRole> {

	private final static Logger LOG = LogManager.getLogger(UserCustomerRoleDAOImpl.class);
	private List<UserCustomerRole> listResult;
	private Connection conn;
	private PreparedStatement ps;

	public UserCustomerRoleDAOImpl() {

	}

	public UserCustomerRole findByUser(User user) {
		boolean resultExecution;
		UserCustomerRole ucr = new UserCustomerRole();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"select * from user u join role r on u.role_id=r.role_id join customer c on u.customer_id=c.customer_id  WHERE user_id=?");
			ps.setInt(1, user.getId());
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					ucr.setUsername(rs.getString("username"));
					ucr.setPassword(rs.getString("password"));
					ucr.setF_name(rs.getString("f_name"));
					ucr.setL_name(rs.getString("l_name"));
					ucr.setRole(rs.getString("rolename"));
					ucr.setRoleId(rs.getInt("role_id"));
					ucr.setUser_id(rs.getInt("user_id"));
					ucr.setCustomerId(rs.getInt("customer_id"));
				}
			} else {
				ucr = null;
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
		return ucr;
	}

	@Override
	public List<UserCustomerRole> getAll() {
		listResult = new ArrayList<>();
		boolean resultExecution;
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"select * from user u join role r on u.role_id=r.role_id join customer c on u.customer_id=c.customer_id");
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					UserCustomerRole ucr = new UserCustomerRole();
					ucr.setUsername(rs.getString("username"));
					ucr.setPassword(rs.getString("password"));
					ucr.setF_name(rs.getString("f_name"));
					ucr.setL_name(rs.getString("l_name"));
					ucr.setRole(rs.getString("rolename"));
					ucr.setRoleId(rs.getInt("role_id"));
					ucr.setUser_id(rs.getInt("user_id"));
					ucr.setCustomerId(rs.getInt("customer_id"));
					listResult.add(ucr);
				}
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
		return listResult;
	}

	@Deprecated
	@Override
	public boolean create(UserCustomerRole t) {
		return false;
	}

	@Deprecated
	@Override
	public boolean update(UserCustomerRole t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Deprecated
	@Override
	public boolean delete(UserCustomerRole t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public UserCustomerRole findByPK(int id) {
		boolean resultExecution;
		UserCustomerRole ucr = new UserCustomerRole();
		try {
			conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"select * from user u join role r on u.role_id=r.role_id join customer c on u.customer_id=c.customer_id  WHERE user_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					ucr.setUsername(rs.getString("username"));
					ucr.setPassword(rs.getString("password"));
					ucr.setF_name(rs.getString("f_name"));
					ucr.setL_name(rs.getString("l_name"));
					ucr.setRole(rs.getString("rolename"));
					ucr.setRoleId(rs.getInt("role_id"));
					ucr.setUser_id(rs.getInt("user_id"));
					ucr.setCustomerId(rs.getInt("customer_id"));
				}
			} else {
				ucr = null;
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
		return ucr;

	}

}
