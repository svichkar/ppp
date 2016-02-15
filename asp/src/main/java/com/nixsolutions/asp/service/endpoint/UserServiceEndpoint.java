package com.nixsolutions.asp.service.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.google.common.base.Strings;
import com.nixsolutions.asp.dao.RoleDao;
import com.nixsolutions.asp.dao.UserDao;
import com.nixsolutions.asp.entity.Role;
import com.nixsolutions.asp.entity.User;
import com.nixsolutions.asp.service.soap.CreateUserRequest;
import com.nixsolutions.asp.service.soap.CreateUserResponse;
import com.nixsolutions.asp.service.soap.UpdateUserRequest;
import com.nixsolutions.asp.service.soap.UpdateUserResponse;

@Endpoint
public class UserServiceEndpoint {

	private static final String NAMESPACE_URI = "http://asp.nixsolutions.com/service/soap";

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleDao roleDao;

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
	@ResponsePayload
	public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
		CreateUserResponse response = new CreateUserResponse();
		User user = request.getUser();
		Role role = user.getRole();
		if (role.getRoleId() == 0) {
			role.setRoleId(roleDao.getByRoleName(role.getRoleName()).getRoleId());
		}
		if (Strings.isNullOrEmpty(role.getRoleName())) {
			role.setRoleName(roleDao.getByRoleId(role.getRoleId()).getRoleName());
		}
		userDao.create(user);
		response.setUserId(userDao.getByUserName(user.getUserName()).getUserId());
		return response;
	}

	@PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
	@ResponsePayload
	public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
		UpdateUserResponse response = new UpdateUserResponse();
		User user = request.getUser();
		userDao.update(user);
		response.setUser(userDao.getByUserName(user.getUserName()));
		return response;
	}

}
