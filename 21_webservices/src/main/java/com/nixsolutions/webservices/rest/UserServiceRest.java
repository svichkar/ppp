package com.nixsolutions.webservices.rest;

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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.UserService;

@Path("user")
@Service("userServiceRest")
public class UserServiceRest {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private UserDao userDao;
	@Autowired
	RoleDao roleDao;

	@GET
	@Path("/getall")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Transactional(readOnly = true)
	public List<User> getAllUsers() {
		return userDao.getAllUsers();
	}

	@GET
	@Path("/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	@Transactional(readOnly = true)
	public User getUserById(@PathParam("id") Long userId) {
		LOG.entry(userId);
		User user = userDao.getUserById(userId);;
		return LOG.exit(user);
	}

	 @POST
	 @Path("/")
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Produces({MediaType.APPLICATION_JSON})
	 @Transactional
	public Response createUser(@RequestBody User createUser) {
		userDao.createUser(createUser);
		return Response.ok(createUser).build();
	}

	 @PUT
	 @Path("/{id}")
	 @Consumes({MediaType.APPLICATION_JSON})
	 @Produces({MediaType.APPLICATION_JSON})	
	 @Transactional
	public Response updateUser(@RequestBody User updUser) {
		 LOG.entry(updUser);
		userDao.updateUser(updUser);
		return Response.ok(updUser).build();
	}

	@DELETE
	@Path("/{id}")
	@Produces({ MediaType.TEXT_HTML })
	@Transactional
	public void deleteUser(@PathParam("id") Long userId) {	
		User user = userDao.getUserById(userId);
		userDao.deleteUser(user);
	}
}
