package com.nixsolutions.ws.soap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.ws.rest.CarRestWebService;

@Endpoint
public class UserEndpoint {
	private final static Logger LOG = LogManager.getLogger(UserEndpoint.class);
	@Autowired
	private UserService userServiceImpl;

	@Autowired
	private RoleService roleServiceImpl;

	@PayloadRoot(namespace = "http://soap.ws.nixsolutions.com/", localPart = "getUserByIdRequest")
	@ResponsePayload
	public GetUserByIdResponse getUserById(@RequestPayload GetUserByIdRequest request) {
		GetUserByIdResponse response = new GetUserByIdResponse();
		if (request.getId() > 0) {
			long userId = request.getId();
			com.nixsolutions.entities.User user = userServiceImpl.getUserById(userId);
			if (user != null) {
				User userForWs = new User();
				userForWs.setPassword(user.getPassword());
				userForWs.setUsername(user.getUsername());
				userForWs.setRoleId(user.getRole().getRoleId());
				userForWs.setUserId(user.getUserId());
				response.setUser(userForWs);
			}
		}
		return response;
	}

	@PayloadRoot(namespace = "http://soap.ws.nixsolutions.com/", localPart = "updateUserRequest")
	@ResponsePayload
	public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		if (request.getUser().getRoleId() > 0) {
			com.nixsolutions.entities.User user = userServiceImpl.getUserById(request.getUser().getUserId());
			com.nixsolutions.entities.Role role = roleServiceImpl.findRoleByid(request.getUser().getRoleId());
			if (user != null && role != null) {
				user.setPassword(request.getUser().getPassword());
				user.setUsername(request.getUser().getUsername());
				user.setUserId(request.getUser().getUserId());
				user.setRole(role);
				try {
					userServiceImpl.updateUser(user);
					response.setId(user.getUserId());
				} catch (Exception ex) {
					LOG.error(ex, ex);
					response.setId((long) 0);
				}
			}
		}
		return response;
	}

	@PayloadRoot(namespace = "http://soap.ws.nixsolutions.com/", localPart = "deleteUserRequest")
	@ResponsePayload
	public DeleteUserResponse deleteUser(@RequestPayload DeleteUserRequest request) {
		DeleteUserResponse response = new DeleteUserResponse();
		if (request.getId() > 0) {
			com.nixsolutions.entities.User user = userServiceImpl.getUserById(request.getId());
			if (user != null) {
				try {
					userServiceImpl.deleteUser(user);
					response.setId(request.getId());
				} catch (Exception ex) {
					LOG.error(ex, ex);
					response.setId((long) 0);
				}
			}
		}
		return response;
	}

	@PayloadRoot(namespace = "http://soap.ws.nixsolutions.com/", localPart = "createUserRequest")
	@ResponsePayload
	public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		if (request.getUser().getRoleId() > 0 && request.getUser().getUsername().length() > 0) {
			com.nixsolutions.entities.Role role = roleServiceImpl.findRoleByid(request.getUser().getRoleId());
			com.nixsolutions.entities.User user = new com.nixsolutions.entities.User();
			user.setPassword(request.getUser().getPassword());
			user.setUsername(request.getUser().getUsername());
			user.setRole(role);
			try {
				userServiceImpl.addUser(user);
				com.nixsolutions.entities.User userJustCreated = userServiceImpl
						.getByNameAndPassword(user.getUsername(), user.getPassword());
				response.setId(userJustCreated.getUserId());
			} catch (Exception ex) {
				LOG.error(ex, ex);
				response.setId((long) 0);
			}
		}
		return response;
	}

}
