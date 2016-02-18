package com.nixsolutions.ws.soap.endpoint;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;
import com.nixsolutions.ws.soap.operation.CreateUserRequest;
import com.nixsolutions.ws.soap.operation.CreateUserResponse;
import com.nixsolutions.ws.soap.operation.DeleteUserRequest;
import com.nixsolutions.ws.soap.operation.DeleteUserResponse;
import com.nixsolutions.ws.soap.operation.GetUserByIdRequest;
import com.nixsolutions.ws.soap.operation.GetUserByIdResponse;
import com.nixsolutions.ws.soap.operation.UpdateUserRequest;
import com.nixsolutions.ws.soap.operation.UpdateUserResponse;
import com.nixsolutions.entities.Role;
import com.nixsolutions.entities.User;

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
			User user = userServiceImpl.getUserById(userId);
			if (user != null) {
				response.setUser(user);
			}
		}
		return response;
	}

	@PayloadRoot(namespace = "http://soap.ws.nixsolutions.com/", localPart = "updateUserRequest")
	@ResponsePayload
	public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		if (request.getUser().getRoleId() > 0) {
			User user = userServiceImpl.getUserById(request.getUser().getUserId());
			Role role = roleServiceImpl.findRoleByid(request.getUser().getRoleId());
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
			User user = userServiceImpl.getUserById(request.getId());
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
			Role role = roleServiceImpl.findRoleByid(request.getUser().getRoleId());
			User user = new com.nixsolutions.entities.User();
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
