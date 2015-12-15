package com.nixsolutions.app;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.nixsolutions.dao.DaoFactory;
import com.nixsolutions.entity.Car;

public class test {
	private final static Logger logger = LogManager.getLogger();

	public static void main(String[] args) {
		List<Car> cars=null;
		cars=DaoFactory.getCarDaoImpl().getAllCar();

	}

}
