package com.nixsolutions.reflections;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Task1Test {
	private static final Logger LOG = LogManager.getLogger();

	public static void main(String[] args) {
		String objToString = null;
		String googleObjToString = null;
		SimpleCar car = new SimpleCar();
		car.setBrand("Mazda");
		car.setColor("Black");
		car.setSpeed(280);
		car.setPrice(35000.34);
		
		try {
			objToString = MyUtils.getAnnotationFields(car);
			googleObjToString = MyUtils.googleGetAnnotFileds(car);
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOG.error(e.getMessage(), e);
		}
		LOG.info("result using standard methods: " + objToString);
		LOG.info("result using GUAVA methods: " + googleObjToString);
	}

}
