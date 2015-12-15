package com.nixsolutions.service.jersey;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.nixsolutions.bean.CarBean;
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.service.CarService;

@Singleton
@Component
@Path("/car")
public class CarServiceJersey implements CarService {

	@Autowired
	private CarDAO carDao;

	@GET
	@Path("/getAll")
	@Produces({ MediaType.APPLICATION_XML })
	@Override
	public List<Car> getAllCars() {
		return carDao.getAll();
	}

	@GET
	@Path("/getAllAsBeans")
	@Produces({ MediaType.APPLICATION_XML })
	@Override
	public List<CarBean> getAllCarsAsBeans() {
		return processAsBeans(getAllCars());
	}

	@GET
	@Path("/getByCustomerId")
	@Produces({ MediaType.APPLICATION_XML })
	@Override
	public List<Car> getCarsByCustomerId(@QueryParam("customerId") int customerId) {
		return carDao.getCarsByCustomerId(customerId);
	}

	@GET
	@Path("/getById")
	@Produces({ MediaType.APPLICATION_XML })
	@Override
	public Car getCarById(@QueryParam("carId") int carId) {
		return carDao.getByPK(carId);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/add")
	@Override
	public void addCar(Car car) {
		carDao.createFrom(car);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/update")
	@Override
	public void updateCar(Car car) {
		carDao.update(car);
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML })
	@Path("/delete")
	@Override
	public void deleteCar(Car car) {
		carDao.delete(car);
	}

	private List<CarBean> processAsBeans(List<Car> carList) {
		List<CarBean> resultList = new ArrayList<>();
		for (Car item : carList) {
			CarBean cb = new CarBean();
			cb.setCarId(item.getCarId());
			cb.setCarModel(item.getModel());
			cb.setCarVin(item.getVin());
			cb.setCarDescription(item.getDescription());
			Customer c = item.getCustomer();
			cb.setCustomerName(c.getFirstName() + " " + c.getLastName());
			resultList.add(cb);
		}
		return resultList;
	}
}
