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

public class CarDAOImpl implements CarDAO<Car> {

	private final static Logger LOG = LogManager.getLogger(CarDAOImpl.class);
	private Connection conn;
	private PreparedStatement ps;

	public CarDAOImpl() {
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
		try {
			ps = conn
					.prepareStatement("UPDATE car SET model=?, vin=?, description=?, customer_id=? WHERE car_id=?");
			ps.setString(1, t.getModel());
			ps.setString(2, t.getVin());
			ps.setString(3, t.getDescription());
			ps.setInt(4, t.getCustomer_id());
			ps.setInt(5, t.getId());
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
				car = null;
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
