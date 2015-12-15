package com.nixsolutions.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.service.CarService;

import junitx.framework.ListAssert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test.xml")
public class TestCarServiceJersey {

	public static Logger LOG = LogManager.getLogger(TestCarServiceJersey.class.getName());
	@Autowired
	private CarDAO carDao;
	@Autowired
	private CarService carService;
	
	@Before
	public void setUp() {
		Car car = new Car("test", "test", "test", null);
		carDao.createFrom(car);
	}
	
	@Test
	public void getAll() {
		List<Car> carListDao = carDao.getAll();
		List<Car> carListService = carService.getAllCars();
		Assert.assertEquals(carListDao.get(0), carListService.get(0));
		ListAssert.assertEquals(carListDao, carListService);
	}
}
