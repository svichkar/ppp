package com.nixsolutions.service.impl;

import java.util.List;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.nixsolutions.dao.RoleDao;
import com.nixsolutions.dao.UserDao;
import com.nixsolutions.entity.Role;
import com.nixsolutions.entity.User;
import com.nixsolutions.service.RoleService;
import com.nixsolutions.service.UserService;

@Path("user")
@Transactional
public class UserServiceImpl2 {
	public static final Logger LOG = LogManager.getLogger();
	@Autowired
	private UserDao userDao;
	@Autowired
	RoleDao roleDao;
	
	
	
	@GET
    @Path("/{id}")
    @Produces("text/plain")
	public String getUserBy(@PathParam("id") String userId) {
		return "it is working " + userId;
	}
	
	
//	@GET
//    @Path("/{id}")
//    @Produces("text/plain")
//	@Transactional
//	public User getUserById(@PathParam("id") String userId) {
//		LOG.entry(userId);
//		User user = userDao.getUserById(Long.valueOf(userId));
//		LOG.debug(user);
//		return LOG.exit(user);
//	}
	
}
