package com.nixsolutions.hibernate.service;

import com.nixsolutions.hibernate.dao.RoleDAO;
import com.nixsolutions.hibernate.dao.UserDAO;
import com.nixsolutions.hibernate.dao.impl.RoleDaoImpl;
import com.nixsolutions.hibernate.dao.impl.UserDaoImpl;
import com.nixsolutions.hibernate.entity.User;


import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by kozlovskij on 2/11/2016.
 */

@Path("user")
public class JerseyUserService {
    public UserDAO userDAO = new UserDaoImpl();
    public RoleDAO roleDAO = new RoleDaoImpl();

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("id") Long userId) {
        return userDAO.findByID(userId);
    }

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAllUsers(){
        return userDAO.findAll();
    }

    @POST
    @Path("/create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser (User user){
        user.setRole(roleDAO.findByID(user.getRoleId()));
        userDAO.create(user);
        return Response.ok(user.getUserId()).build();
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser (User user){
        userDAO.update(user);
        return Response.ok().build();
    }

    @DELETE
    @Path("/delete")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser (User user){
        userDAO.delete(user);
        return Response.ok().build();
    }
}
