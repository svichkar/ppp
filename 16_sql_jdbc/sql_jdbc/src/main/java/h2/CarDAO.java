package h2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.AbstractDAO;
import entities.Car;
import entities.PersistenceException;

public class CarDAO extends AbstractDAO<Car>{
	
	public CarDAO(Connection conn) {
		super(conn);
	}

	private class ChCar extends Car {
		@Override
		public void setId(int value) {
			super.setId(value);
		}
	}

	@Override
	public Car create() throws PersistenceException {
		Car car = new Car();
		return createFrom(car);
	}

	@Override
	public String getSelectByID() {
		return "SELECT * FROM car WHERE car_id = ?;";
	}

	@Override
	public String getSelectAll() {
		return "SELECT * FROM car;";
	}

	@Override
	public String getUpdate() {
		return "UPDATE car SET %1$s WHERE car_id = %2$s;";
	}

	@Override
	public String getDelete() {
		return "DELETE FROM car WHERE car_id = ?;";
	}

	@Override
	public String getCreate() {
		return "INSERT INTO car (model, vin, description, customer_id) VALUES (%1$s);";
	}

	@Override
	public List<Car> parseResults(ResultSet rs) throws PersistenceException {
		List<Car> resultList = new ArrayList<>();
		try {
			while (rs.next()) {
				ChCar car = new ChCar();
				car.setId(rs.getInt("car_id"));
				car.setModel(rs.getString("model"));
				car.setVin(rs.getString("vin"));
				car.setDescription(rs.getString("description"));
				car.setCustomerId(rs.getInt("customer_id"));
				resultList.add(car);
			}
		} catch (Exception ex) {
			throw new PersistenceException(ex);
		}
		return resultList;
	}

}
