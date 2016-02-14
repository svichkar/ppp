package com.nixsolutions.ws.soap;

import org.apache.commons.lang.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.service.UserService;

@Endpoint
public class UserEndpoint {

	@Autowired
	private UserService userServiceImpl;

	@PayloadRoot(namespace = "http://soap.ws.nixsolutions.com/", localPart = "getUserByIdRequest")
	@ResponsePayload
	public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request) {
		GetUserByIdResponse response = new GetUserByIdResponse();
		if (NumberUtils.isDigits(request.getId())) {
			int userId = Integer.valueOf(request.getId());
			com.nixsolutions.entities.User user = userServiceImpl.getUserById(userId);
			User userForWs = new User();
			userForWs.setPassword(user.getPassword());
			userForWs.setUsername(user.getUsername());
			userForWs.setRoleId(user.getRole().getRoleId());
			userForWs.setUserId(String.valueOf(user.getUserId()));
			response.setUser(userForWs);
		}
		return response;
	}
}
