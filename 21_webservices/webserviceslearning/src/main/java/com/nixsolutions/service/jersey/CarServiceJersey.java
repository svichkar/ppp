package com.nixsolutions.service.jersey;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.jws.WebService;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.nixsolutions.bean.CarBean;
import com.nixsolutions.dao.CarDAO;
import com.nixsolutions.hibernate.entity.Car;
import com.nixsolutions.hibernate.entity.Customer;
import com.nixsolutions.service.CarService;

//@Singleton
@Component
@Path("/services/car")
public class CarServiceJersey {

	@Autowired
	private CarDAO carDao;
	
	@GET
	@Path("/getAll")
	@Produces({"application/xml"})
	public Response getAllCars() {
		
		return Response.status(200).entity(carDao.getAll()).build(); 
	}
}
