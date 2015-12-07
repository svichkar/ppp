package com.nixsolutions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class AnnotationTaskMain {
	private static final Logger LOG = LogManager.getRootLogger();

	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Employee employee = new Employee("Tom", 25, 1, 1500.7d);
		try {
			LOG.info("Standart result: " + ConverterUtils.getFields(employee));
			LOG.info("Google result: " + ConverterUtils.getFieldsGoogle(employee));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			LOG.catching(e);
		}
	}

}
