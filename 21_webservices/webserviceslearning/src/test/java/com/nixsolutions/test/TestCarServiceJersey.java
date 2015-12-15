package com.nixsolutions.test;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.dao.CustomerDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.service.CarService;

import junit.framework.Assert;
import junitx.framework.ListAssert;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-test.xml")
public class TestCarServiceJersey {

	public static Logger LOG = LogManager.getLogger(TestCarServiceJersey.class.getName());
	@Autowired
	private CarDAO carDao;
	@Autowired
	private CustomerDAO customerDao;
	@Autowired
	private CarService carService;
	
	@Before
	public void setUp() {
		Customer customer = new Customer("test", "test", "test", null);
		customerDao.createFrom(customer);
		Car car = new Car("test", "test", "test", customer);
		carDao.createFrom(car);
	}
	
	@Test
	public void testGetAll() {
		List<Car> carListDao = carDao.getAll();
		List<Car> carListService = carService.getAllCars();
		ListAssert.assertEquals(carListDao, carListService);
	}
	
	@Test
	public void testGetById() {
		Car carFromDao = carDao.getByPK(1);
		Car carFromService = carService.getCarById(1);
		Assert.assertEquals(carFromDao, carFromService);
	}
	
	@Test
	public void testGetByCustomerId() {
		Customer customer = customerDao.getAll().get(0);
		List<Car> carListDao = carDao.getCarsByCustomerId(customer.getCustomerId());
		List<Car> carListService = carService.getCarsByCustomerId(customer.getCustomerId());
		ListAssert.assertEquals(carListDao, carListService);
	}
	
	@Test
	public void testDelete() {
		Car car = carDao.getAll().get(0);
		carService.deleteCar(car);
		car = carDao.getByPK(car.getCarId());
		Assert.assertNull(car);
	}
}
