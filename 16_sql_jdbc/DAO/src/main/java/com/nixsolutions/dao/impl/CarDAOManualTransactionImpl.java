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
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.entities.Car;

public class CarDAOManualTransactionImpl implements CarDAO<Car> {
	private Connection conn;
	private PreparedStatement ps;

	private final static Logger LOG = LogManager
			.getLogger(CarDAOManualTransactionImpl.class);

	public CarDAOManualTransactionImpl() {
		try {
			this.conn = ConnectionManager.getConnection();
			// define schema
			ps = conn.prepareStatement("SET SCHEMA SQLLAB");
			ps.execute();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		} catch (ClassNotFoundException ex) {
			LOG.error(ex, ex);
		}
	}

	@Override
	public boolean create(Car t) {
		int executionResult = 0;
		try {
			ps = conn
					.prepareStatement("INSERT INTO car (model, vin, description, customer_id) VALUES (?,?,?,?)");
			ps.setString(1, t.getModel());
			ps.setString(2, t.getVin());
			ps.setString(3, t.getDescription());
			ps.setInt(4, t.getCustomer_id());
			executionResult = ps.executeUpdate();
			if (executionResult > 0) {
				return true;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Car t) {
		int executionResult = 0;
		List<Boolean> lresult = new ArrayList<Boolean>();
		PreparedStatement ps1;
		PreparedStatement ps2;
		PreparedStatement ps3;
		try {
			conn.setAutoCommit(false);
			ps1 = conn
					.prepareStatement("UPDATE car SET model=? WHERE car_id=?");
			ps1.setString(1, t.getModel());
			ps1.setInt(2, t.getId());
			executionResult = ps1.executeUpdate();
			if (executionResult > 0) {
				lresult.add(true);
			} else {
				lresult.add(false);
			}

			ps2 = conn.prepareStatement("UPDATE car SET vin=? WHERE car_id=?");
			ps2.setString(1, t.getVin());
			ps2.setInt(2, t.getId());
			executionResult = ps2.executeUpdate();
			if (executionResult > 0) {
				lresult.add(true);
			} else {
				lresult.add(false);
			}

			ps3 = conn
					.prepareStatement("UPDATE car SET description=? WHERE car_id=?");
			ps3.setString(1, t.getDescription());
			ps3.setInt(2, t.getId());
			executionResult = ps3.executeUpdate();
			if (executionResult > 0) {
				lresult.add(true);
			} else {
				lresult.add(false);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}

		if (!lresult.contains(false)) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Car t) {
		int executionResult = 0;
		try {
			ps = conn.prepareStatement("DELETE FROM car WHERE car_id=?");
			ps.setInt(1, t.getId());
			executionResult = ps.executeUpdate();
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Car findByPK(int id) {
		boolean resultExecution;
		Car car = new Car();
		try {
			ps = conn.prepareStatement("SELECT * FROM car WHERE car_id=?");
			ps.setInt(1, id);
			resultExecution = ps.execute();
			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					car.setId(rs.getInt("car_id"));
					car.setCustomer_id(rs.getInt("customer_id"));
					car.setVin(rs.getString("vin"));
					car.setDescription(rs.getString("description"));
					car.setModel(rs.getString("model"));
				}
			} else {
				return car = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		return car;
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
	public List<Car> getAll() {
		boolean resultExecution;
		List<Car> lCars = new ArrayList<Car>();
		try {
			ps = conn.prepareStatement("SELECT * FROM car");
			resultExecution = ps.execute();

			if (resultExecution) {
				ResultSet rs = ps.getResultSet();
				while (rs.next()) {
					Car car = new Car();
					car.setId(rs.getInt("car_id"));
					car.setCustomer_id(rs.getInt("customer_id"));
					car.setVin(rs.getString("vin"));
					car.setDescription(rs.getString("description"));
					car.setModel(rs.getString("model"));
					lCars.add(car);
				}
			} else {
				lCars = null;
			}
		} catch (SQLException ex) {
			LOG.error(ex, ex);
		}
		
		return lCars;
	}

}
