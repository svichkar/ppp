package com.nixsolutions.studentgrade.webservice.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.studentgrade.dao.UserDAO;
import com.nixsolutions.studentgrade.webservice.soap.CreateUserRequest;
import com.nixsolutions.studentgrade.webservice.soap.CreateUserResponse;
import com.nixsolutions.studentgrade.webservice.soap.UpdateUserRequest;
import com.nixsolutions.studentgrade.webservice.soap.UpdateUserResponse;

@Endpoint
public class UserEndpoint {
	 private static final String NAMESPACE_URI = "http://localhost:8080/21_webservices-0.0.1-SNAPSHOT/ws/soap/user";

	    @Autowired
	    UserDAO userDao;

	    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
	    @ResponsePayload
	    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
	    	userDao.createUser(request.getUser());
	        CreateUserResponse response = new CreateUserResponse();
	        response.setUserId(userDao.findUserByLogin(request.getUser().getLogin()).getUserId());
	        return response;
	    }

	    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	    @ResponsePayload
	    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {

	        userDao.updateUser(request.getUser());
	        UpdateUserResponse response = new UpdateUserResponse();
	        response.setUser(userDao.findUserById(request.getUser().getUserId()));
	        return response;
	    }
}
