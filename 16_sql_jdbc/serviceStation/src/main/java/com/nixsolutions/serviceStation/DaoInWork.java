/**
 * 
 */
package com.nixsolutions.serviceStation;

import java.sql.SQLException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.serviceStation.dbCommon.DbConnector;
import com.nixsolutions.serviceStation.dbObjects.Car;
import com.nixsolutions.serviceStation.h2Objects.CarDaoImpl;

/**
 * @author mixeyes
 *
 */
public class DaoInWork {
	private final static Logger logger = LogManager.getLogger();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DbConnector connector = null;
		try {
			connector = new DbConnector();
		} catch (ClassNotFoundException | SQLException e) {
			logger.catching(e);
		}
		CarDaoImpl carDaoImpl = new CarDaoImpl(connector.getConnection());
		List<Car> cars = carDaoImpl.getAllCar();
		carDaoImpl.createNewCar("aasdasd", "21341234sdafas", null, "IVANOV","IVAN");
		Car car = carDaoImpl.getCarByVINNumber("21341234sdafas");
		cars = carDaoImpl.getAllCar();
	}

}
