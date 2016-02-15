package com.nixsolutions.service.endpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.service.UserService;
import com.nixsolutions.webservices.userservice.GetUserRequest;
import com.nixsolutions.webservices.userservice.GetUserResponse;
import com.nixsolutions.webservices.userservice.Role;
import com.nixsolutions.webservices.userservice.User;

@Endpoint
public class UserServiceEndpoint {

	private static final String NAMESPACE_URI = "http://com/nixsolutions/webservices/userservice";
	
	@Autowired
	UserService userService;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getCountry(@RequestPayload GetUserRequest request) {
		GetUserResponse response = new GetUserResponse();
		//response.setUser(userService.getUserById(request.getName()));
		//will be deleted
		User usr = new User();
		usr.setId(1);
		usr.setName("wsTest");
		Role role = new Role();
		role.setId(1);
		role.setName("wsRole");
		usr.setRole(role);
		response.setUser(usr);
		//will be deletd
		return response;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
