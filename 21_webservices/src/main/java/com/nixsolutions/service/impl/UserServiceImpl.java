package com.nixsolutions.service.impl;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	@Autowired
	private UserDao userDao;
	@Autowired
	RoleDao roleDao;
	Client client = ClientBuilder.newClient();
	WebTarget userWebTarget = client.target("http://localhost:8080/webservices/rest/user");
	
	
	@Override
	public List<User> getAllUsers() {
		
		//WebTarget getAllwebTarget = userWebTarget.path("getall");
		List<User> responseEntity = ClientBuilder.newClient()
	            .target("http://localhost:8080/webservices").path("rest/user/getall")
	                        .request().get(List.class);
		return responseEntity;
	}

	@Override
	public User getUserById(String userId) {
		LOG.entry(userId);
		User user = userDao.getUserById(Long.valueOf(userId));
		LOG.debug(user);
		return LOG.exit(user);
	}
	
	@Override
	public void createUser(String roleName, String usr, String pswd) {
		Role role = roleDao.getRoleByName(roleName);
		User createUser = new User(usr, pswd, role);
		userDao.createUser(createUser);
	}

	@Override
	public void updateUser(String userId, String roleName, String usr, String pswd) {
		User updUser = userDao
				.getUserById(Long.parseLong(userId));
		Role role = roleDao.getRoleByName(roleName);
		updUser.setRole(role);
		updUser.setUserName(usr);
		updUser.setUserPassword(pswd);
		userDao.updateUser(updUser);
	}

	@Override
	public void deleteUser(User user) {
		userDao.deleteUser(user);
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
