package com.nixsolutions.ws.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import com.nixsolutions.dto.CarCustomer;
import com.nixsolutions.entities.Car;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.service.CarService;
import com.nixsolutions.service.CustomerService;

@Path("car")
@Controller
public class CarRestWebService {
	private final static Logger LOG = LogManager.getLogger(CarRestWebService.class);
	@Autowired
	private CarService carServiceImpl;
	@Autowired
	private CustomerService customerServiceImpl;

	@GET
	@Path("/")
	public String welcome() {
		return "<h1 align='center'>Welcome to REST service of car station</h1>";
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public CarCustomer getCarById(@PathParam("id") String carId) {
		CarCustomer result = null;
		if (NumberUtils.isDigits(carId)) {
			result = carServiceImpl.getCarCustomer(Integer.valueOf(carId));
		}
		return result;
	}

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/all")
	public List<CarCustomer> getAllCars() {
		List<CarCustomer> result = carServiceImpl.getAllCarCustomers();
		return result;
	}
	
	@POST
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/")
	public Response addCar(@RequestBody CarCustomer carCustomer) {
		long resultId = 0;
		if (carCustomer != null) {
			Customer customer = customerServiceImpl.getCustomerById(carCustomer.getCustomerId());
			if (customer != null) {
				Car car = new Car(carCustomer.getModel(), carCustomer.getVin(), carCustomer.getDescription(), customer);
				try {
					carServiceImpl.addCar(car);
					resultId = carServiceImpl.getCarByVin(car.getVin()).getCarId();
				} catch (Exception ex) {
					LOG.error(ex, ex);
					resultId = 0;
				}

			} else {
				Customer customerNew = new Customer(carCustomer.getFname(), carCustomer.getLname(), "", null);
				customerServiceImpl.addCustomer(customerNew);
				customerNew = customerServiceImpl.getCustomerByFullName(carCustomer.getFname(), carCustomer.getLname());
				Car car = new Car(carCustomer.getModel(), carCustomer.getVin(), carCustomer.getDescription(),
						customerNew);
				try {
					carServiceImpl.addCar(car);
					resultId = carServiceImpl.getCarByVin(car.getVin()).getCarId();
				} catch (Exception ex) {
					LOG.error(ex, ex);
					resultId = 0;
				}
			}
		}
		if (resultId > 0) {
			return Response.status(201).entity(resultId).build();
		} else {
			return Response.status(500).entity(resultId).build();
		}

	}

	@PUT
	@Produces({ MediaType.APPLICATION_JSON })
	@Consumes({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response updateCar(@RequestBody CarCustomer carCustomer) {
		long resultId = 0;
		if (carCustomer != null) {
			//long parsedCarId = carCustomer.getId() > 0 ? carCustomer.getId() : Long.valueOf(carId);
			Customer customerFound = customerServiceImpl.getCustomerById(carCustomer.getCustomerId());
			Car carFound = carServiceImpl.getCarById(carCustomer.getId());
			if (customerFound != null && carFound != null) {
				carFound.setDescription(carCustomer.getDescription());
				carFound.setModel(carCustomer.getModel());
				carFound.setVin(carCustomer.getVin());
				try {
					carServiceImpl.updateCar(carFound);
					resultId = carFound.getCarId();
				} catch (Exception ex) {
					LOG.error(ex, ex);
					resultId = 0;
				}

			}
		}
		if (resultId > 0) {
			return Response.status(200).entity(resultId).build();
		} else {
			return Response.status(500).entity(resultId).build();
		}
	}

	@DELETE
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/{id}")
	public Response deleteCar(@PathParam("id") String carId) {
		long resultId = 0;
		if (NumberUtils.isDigits(carId)) {
			Car car = carServiceImpl.getCarById(Integer.valueOf(carId));
			try {
				carServiceImpl.deleteCar(car);
				resultId = car.getCarId();
			} catch (Exception ex) {
				LOG.error(ex, ex);
				resultId = 0;
			}
		}
		if (resultId > 0) {
			return Response.status(200).entity(resultId).build();
		} else {
			return Response.status(500).entity(resultId).build();
		}
	}

}
