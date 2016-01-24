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
import com.nixsolutions.dao.CarCustomerDAO;
import com.nixsolutions.entities.CarCustomer;

public class CarCustomerDAOImpl implements CarCustomerDAO<CarCustomer> {

	private final static Logger LOG = LogManager.getLogger(CarCustomerDAOImpl.class);
	private List<CarCustomer> listResult;
	private Connection conn;
	private PreparedStatement ps;

	public CarCustomerDAOImpl() {
		
	}

	@Override
	public boolean create(CarCustomer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(CarCustomer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(CarCustomer t) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CarCustomer findByPK(int car_id) {
		boolean resultExecution;
		CarCustomer cc = new CarCustomer();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
			ps = conn.prepareStatement(
					"select * from car c join customer r on c.customer_id=r.customer_id WHERE car_id=?");
			ps.setInt(1, car_id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					cc.setCustomer_id(rs.getInt("customer_id"));
					cc.setDescription(rs.getString("description"));
					cc.setF_name(rs.getString("f_name"));
					cc.setL_name(rs.getString("l_name"));
					cc.setId(rs.getInt("car_id"));
					cc.setModel(rs.getString("model"));
					cc.setVin(rs.getString("vin"));
				}
			} else {
				cc = null;
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
		return cc;

	}

	@Override
	public List<CarCustomer> getAll() {
		boolean resultExecution;
		listResult = new ArrayList<>();
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
		
			ps = conn.prepareStatement("select * from car c join customer r on c.customer_id=r.customer_id");
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					CarCustomer cc = new CarCustomer();
					cc.setCustomer_id(rs.getInt("customer_id"));
					cc.setDescription(rs.getString("description"));
					cc.setF_name(rs.getString("f_name"));
					cc.setL_name(rs.getString("l_name"));
					cc.setId(rs.getInt("car_id"));
					cc.setModel(rs.getString("model"));
					cc.setVin(rs.getString("vin"));
					listResult.add(cc);
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

}
