package h2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.GenericDao;
import entities.Car;

public class CarDAOImpl implements GenericDao<Car> {

	Connection conn;

	private class ChCar extends Car {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	public CarDAOImpl(Connection conn) throws Exception {
		this.conn = conn;
		//define schema
		PreparedStatement ps = conn.prepareStatement("SET SCHEMA SQLLAB");
		ps.execute();
	}

	@Override
	public boolean create(Car t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("INSERT INTO car (model, vin, description, customer_id) VALUES (?,?,?,?)");
		ps.setString(1, t.getModel());
		ps.setString(2, t.getVin());
		ps.setString(3, t.getDescription());
		ps.setInt(4, t.getCustomer_id());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}

		return false;
	}

	@Override
	public boolean update(Car t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("UPDATE car SET model=?, vin=?, description=?, customer_id=? WHERE car_id=?");
		ps.setString(1, t.getModel());
		ps.setString(2, t.getVin());
		ps.setString(3, t.getDescription());
		ps.setInt(4, t.getCustomer_id());
		ps.setInt(5, t.getId());
		executionResult = ps.executeUpdate();
		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(Car t) throws SQLException {
		int executionResult;
		PreparedStatement ps = conn
				.prepareStatement("DELETE FROM car WHERE car_id=?");
		ps.setInt(1, t.getId());
		executionResult = ps.executeUpdate();

		if (executionResult > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Car findByPK(int id) throws SQLException {
		boolean resultExecution;
		ChCar car = new ChCar();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM car WHERE car_id=?");
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
			return null;
		}

		return car;
	}

	@Override
	public Car findByName(String name) throws SQLException {

		boolean resultExecution;
		ChCar car = new ChCar();
		PreparedStatement ps = conn
				.prepareStatement("SELECT * FROM car WHERE model=?");
		ps.setString(1, name);
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
			return null;
		}

		return car;
	}

}
