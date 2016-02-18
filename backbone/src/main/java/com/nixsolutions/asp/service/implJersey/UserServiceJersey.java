package com.nixsolutions.asp.service.implJersey;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nixsolutions.asp.dao.UserDao;
import com.nixsolutions.asp.entity.User;
import com.nixsolutions.asp.service.RoleService;
import com.nixsolutions.asp.service.UserService;

@Path("user")
@Service
public class UserServiceJersey implements UserService {

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private UserDao userDao;

	@POST
	@Path("/")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public int create(User user) {
		if (user.getRole().getId() == 0) {
			user.setRole(roleService.getByRoleName(user.getRole().getRoleName()));
		}
		userDao.create(user);
		return userDao.getByUserName(user.getUserName()).getId();
	}

	@PUT
	@Path("/{id}")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public void update(User user) {
		if (user.getRole().getId() == 0) {
			user.setRole(roleService.getByRoleName(user.getRole().getRoleName()));
		}
		userDao.update(user);
	}
	
	@DELETE
	@Path("/{userId}")
	public void delete(@PathParam("userId") int userId) {		
		userDao.delete(userDao.getByUserId(userId));
	}

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public User getByUserId(@PathParam("userId")int userId) {
		return userDao.getByUserId(userId);
	}
	
	@GET
	@Path("/getByName")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public User getByUserName(@QueryParam("userName")String userName) {
		return userDao.getByUserName(userName);
	}
	
	@GET
	@Path("/getAll")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<User> getAll() {
		return userDao.getAll();
	}
	
	@POST
	@Path("/checkUser")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Override
	public boolean checkUser(@QueryParam("userName")String userName, @QueryParam("password")String password) {
		User user = getByUserName(userName);
		if (user != null && user.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
}
