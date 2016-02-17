package com.nixsolutions.ws.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.nixsolutions.dto.CustomerDescription;
import com.nixsolutions.entities.Customer;
import com.nixsolutions.service.CustomerService;

@Path("customer")
@Controller
public class CustomerRestWebService {

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
	public CustomerDescription getCustomerDescById(@PathParam("id") String customerId) {
		CustomerDescription result = null;
		if (NumberUtils.isDigits(customerId)) {
			result = customerServiceImpl.getCustomerDescription(Integer.valueOf(customerId));
		}
		return result;
	}	
	
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	@Path("/all")
	public List<CustomerDescription> getAllCustomers() {
		List<CustomerDescription> result = customerServiceImpl.getAllCustomerDescriptions();
		return result;
	}

}
