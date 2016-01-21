package com.nixsolutions.soap.endpoint;

import java.math.BigInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.dao.CustomerDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.dao.UserRoleDao;
import com.nixsolutions.entity.Customer;
import com.nixsolutions.entity.User;
import com.nixsolutions.soap.wsdl.CreateNewCustomerRequest;
import com.nixsolutions.soap.wsdl.CreateNewCustomerResponse;
import com.nixsolutions.soap.wsdl.UpdateCustomerRequest;
import com.nixsolutions.soap.wsdl.UpdateCustomerResponse;

@Endpoint
public class CustomerEndpoint {
	private static final String NAMESPACE_URI = "http://localhost:8080/wsSoapSTO/soapws";

	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private UserRoleDao userRoleDao;
	@Autowired
	private UserDao userDao;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createNewCustomerRequest")
	@ResponsePayload
	public CreateNewCustomerResponse createNewCustomer(@RequestPayload CreateNewCustomerRequest request) {
		CreateNewCustomerResponse response = new CreateNewCustomerResponse();
		Customer customer = new Customer();
		customer.setLastName(request.getCustomer().getLastName());
		customer.setFirstName(request.getCustomer().getFirstName());
		customer.setPhone(request.getCustomer().getPhone());

		userDao.createNewUser(request.getCustomer().getUser().getLogin(), request.getCustomer().getUser().getPassword(),
				userRoleDao.getUserRole(2));
		User user = userDao.getUserByLogin(request.getCustomer().getUser().getLogin());
		customer.setUser(user);
		customerDao.createNewCustomer(customer);
		response.setResult("Customer was created");
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateCustomerRequest")
	@ResponsePayload
	public UpdateCustomerResponse updateCustomer(@RequestPayload UpdateCustomerRequest request) {
		UpdateCustomerResponse response = new UpdateCustomerResponse();
		User user = userDao.getUserByID(request.getCustomer().getUser().getUserId().intValue());
		user.setUserLogin(request.getCustomer().getUser().getLogin());
		user.setUserPassword(request.getCustomer().getUser().getPassword());
		userDao.updateUser(user);
		Customer customer = customerDao.getCustomerByID(request.getCustomer().getCustomerId().intValue());
		customer.setLastName(request.getCustomer().getLastName());
		customer.setFirstName(request.getCustomer().getFirstName());
		customer.setPhone(request.getCustomer().getPhone());
		customer.setUser(user);
		customerDao.updateCustomer(customer);
		response.setResult("Customer was updated");
		return response;
	}
}
