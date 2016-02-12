package com.nixsolutions.service.impl;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.UserService;

@Path("user")
public class UserServiceImpl2 implements UserDetailsService {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private UserDao userDao;
	@Autowired
	RoleDao roleDao;

	// @Override
	@GET
	@Path("/getall")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	// @Override
	@GET
	@Path("/get/{id}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Transactional(readOnly = true)
	public User getUserById(@PathParam("id") String userId) {
		LOG.entry(userId);
		User user = userDao.getUserById(Long.valueOf(userId));
		LOG.debug(user);
		return LOG.exit(user);
	}

	// @Override

	 @POST
	 @Path("/create")
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Produces({MediaType.TEXT_HTML})
	 @Transactional
	public void createUser(User createUser) {
		/*
		 * Role role = roleDao.getRoleByName(roleName); User createUser = new
		 * User(usr, pswd, role);
		 */
		userDao.createUser(createUser);
		//return Response.status(200).entity(createUser).build();
	}

	// @Override
	 @PUT
	 @Path("/update")
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Produces({MediaType.TEXT_HTML})	
	 @Transactional
	public void updateUser(User updUser) {
//		User updUser = userDao.getUserById(Long.parseLong(userId));
//		Role role = roleDao.getRoleByName(roleName);
//		updUser.setRole(role);
//		updUser.setUserName(usr);
//		updUser.setUserPassword(pswd);
		userDao.updateUser(updUser);
	}

	// @Override

	@DELETE
	@Path("/delete/{id}")
	@Produces({ MediaType.TEXT_HTML })
	@Transactional
	public void deleteUser(@PathParam("id") String id) {
		
		userDao.deleteUser(userDao.getUserById(Long.valueOf(id)));
	}

	// @Override
	public User getUserByNameAndPswd(String name, String pswd) {
		return userDao.getUserByNameAndPswd(name, pswd);
	}

	// @Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		return userDao.getUserByName(name);
	}

}
