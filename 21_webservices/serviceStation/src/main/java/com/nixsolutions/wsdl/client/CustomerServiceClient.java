package com.nixsolutions.wsdl.client;

import java.math.BigInteger;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import com.nixsolutions.wsdl.CreateNewCustomerRequest;
import com.nixsolutions.wsdl.CreateNewCustomerResponse;
import com.nixsolutions.wsdl.CustomerService;
import com.nixsolutions.wsdl.UpdateCustomerRequest;
import com.nixsolutions.wsdl.UpdateCustomerResponse;
import com.nixsolutions.wsdl.UserRoleService;
import com.nixsolutions.wsdl.UserService;

public class CustomerServiceClient extends WebServiceGatewaySupport {

	public CreateNewCustomerResponse createNewCustomerResponse(String login, String password, String lastName,
			String firstName, String phone) {

		UserRoleService roleService = new UserRoleService();

		UserService userService = new UserService();
		userService.setLogin(login);
		userService.setPassword(password);
		userService.setUserRole(roleService);

		CustomerService customerServ = new CustomerService();
		customerServ.setFirstName(firstName);
		customerServ.setLastName(lastName);
		customerServ.setPhone(phone);
		customerServ.setUser(userService);

		CreateNewCustomerRequest newCustomerRequest = new CreateNewCustomerRequest();
		newCustomerRequest.setCustomer(customerServ);

		CreateNewCustomerResponse customerResponse = (CreateNewCustomerResponse) getWebServiceTemplate()
				.marshalSendAndReceive(newCustomerRequest,
						new SoapActionCallback("http://localhost:8080/wsSoapSTO/soapws/createNewCustomerRequest"));

		return customerResponse;
	}

	public UpdateCustomerResponse updateCustomerResponse(String userId, String login, String password,
			String customerId, String lastName, String firstName, String phone) {

		UserRoleService roleService = new UserRoleService();

		UserService userService = new UserService();
		userService.setUserId(BigInteger.valueOf(Integer.decode(userId).longValue()));
		userService.setLogin(login);
		userService.setPassword(password);
		userService.setUserRole(roleService);

		CustomerService customerServ = new CustomerService();
		customerServ.setCustomerId(BigInteger.valueOf(Integer.decode(customerId).longValue()));
		customerServ.setFirstName(firstName);
		customerServ.setLastName(lastName);
		customerServ.setPhone(phone);
		customerServ.setUser(userService);

		UpdateCustomerRequest newCustomerRequest = new UpdateCustomerRequest();
		newCustomerRequest.setCustomer(customerServ);

		UpdateCustomerResponse customerResponse = (UpdateCustomerResponse) getWebServiceTemplate()
				.marshalSendAndReceive(newCustomerRequest,
						new SoapActionCallback("http://localhost:8080/wsSoapSTO/soapws/updateCustomerResponse"));

		return customerResponse;

	}

}
