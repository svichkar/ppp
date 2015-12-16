package com.nixsolutions.test;

import static org.mockito.Mockito.verify;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.service.jersey.CarServiceJersey;

@RunWith(MockitoJUnitRunner.class)
public class TestCarServiceJersey {

	public static Logger LOG = LogManager.getLogger(TestCarServiceJersey.class.getName());
	@Mock
	private CarDAO carDao;
	@InjectMocks
	private CarServiceJersey carService;

	@Test
	public void testGetAll() {
		carService.getAllCars();
		verify(carDao).getAll();
	}

	@Test
	public void testGetById() {
		carService.getCarById(0);
		verify(carDao).getByPK(0);
	}

	@Test
	public void testGetByCustomerId() {
		carService.getCarsByCustomerId(0);
		verify(carDao).getCarsByCustomerId(0);
	}

	@Test
	public void testDelete() {
		Car car = new Car();
		carService.deleteCar(car);
		verify(carDao).delete(car);
	}

	@Test
	public void testUpdate() {
		Car car = new Car();
		carService.updateCar(car);
		verify(carDao).update(car);
	}
}
