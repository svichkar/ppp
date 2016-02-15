package com.nixsolutions.service.endpoints;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.dao.UserDao;
import com.nixsolutions.service.UserService;
import com.nixsolutions.webservices.userservice.CreateUserRequest;
import com.nixsolutions.webservices.userservice.GetUserRequest;
import com.nixsolutions.webservices.userservice.GetUserResponse;
import com.nixsolutions.webservices.userservice.UpdateUserRequest;

import com.nixsolutions.entity.User;


@Endpoint
public class UserServiceEndpoint {
	public static final Logger LOG = LogManager.getLogger();	
	private static final String NAMESPACE_URI = "http://webservices.nixsolutions.com/userservice";
	
	@Autowired
	UserService userService;
	@Autowired
	UserDao userDao;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "getUserRequest")
	@ResponsePayload
	public GetUserResponse getUserById(@RequestPayload GetUserRequest request) {
		
		LOG.entry(request.getName());
		GetUserResponse response = new GetUserResponse();
		response.setUser(userService.getUserById(request.getName()));		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	@ResponsePayload
	public GetUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		
		LOG.entry(request.getUser());
		GetUserResponse response = new GetUserResponse();
		userDao.updateUser(request.getUser());
		response.setUser(userDao.getUserById(request.getUser().getUserId()));		
		return response;
	}
	
	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
	@ResponsePayload
	public GetUserResponse createUser(@RequestPayload CreateUserRequest request) {
		
		LOG.entry(request.getUser());
		GetUserResponse response = new GetUserResponse();
		userDao.createUser(request.getUser());
		response.setUser(userDao.getUserById(request.getUser().getUserId()));		
		return response;
	}
	
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
