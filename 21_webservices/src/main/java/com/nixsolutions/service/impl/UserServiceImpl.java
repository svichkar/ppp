package com.nixsolutions.service.impl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService{
	public static final Logger LOG = LogManager.getLogger();
	private static final String REST_SERVICE_URL = "http://localhost:8080/webservices/rest/user";
	private Client client = ClientBuilder.newClient().register(JacksonFeature.class);
	
	@Autowired
	private UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	@Override
	public List<User> getAllUsers() {
		GenericType<List<User>> userType = new GenericType<List<User>>() {
		};
		List<User> users = client.target(REST_SERVICE_URL).path("getall").request().get(userType);
		LOG.debug("attempt to obtain collection of users: " + users);
		return users;
	}

	@Override
	public User getUserById(String userId) {
		LOG.entry(userId);
		User user = client.target(REST_SERVICE_URL).path("{userId}")
				.resolveTemplate("userId", userId).request(MediaType.APPLICATION_JSON)
				.get(User.class);
		LOG.debug(user);
		return LOG.exit(user);
	}
	
	@Override
	public void createUser(String roleName, String usr, String pswd) {
		Role role = roleDao.getRoleByName(roleName);
		User user = new User(usr, pswd, role);

		User userPersisted = client.target(REST_SERVICE_URL).request()
				.post(Entity.entity(user, MediaType.APPLICATION_JSON), User.class);

		LOG.debug(">>>>>>>attempt to save user: " + userPersisted.toString());
	}

	@Override
	public void updateUser(String userId, String roleName, String usr, String pswd) {
		Role role = roleDao.getRoleByName(roleName);
		User user = client.target(REST_SERVICE_URL).path("{userId}")
				.resolveTemplate("userId", userId).request(MediaType.APPLICATION_JSON)
				.get(User.class);
		LOG.debug("attempt to obtain user using restTemplate: " + user.toString());

		user.setUserName(usr);
		user.setUserPassword(pswd);
		user.setRole(role);

		User userPersisted = client.target(REST_SERVICE_URL).path("{userId}")
				.resolveTemplate("userId", userId)
				.request()
				.put(Entity.entity(user, MediaType.APPLICATION_JSON), User.class);
		LOG.debug(">>>>>>>attempt to update user: " + userPersisted.toString());
	}

	@Override
	public void deleteUser(String userId) {
		client.target(REST_SERVICE_URL).path("{userId}")
		.resolveTemplate("userId", userId).request().delete();
	}

	@Override
	public User getUserByNameAndPswd(String name, String pswd) {
		return userDao.getUserByNameAndPswd(name, pswd);
	}

	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return userDao.getUserByName(name);
	}


}
