package com.nixsolutions.spring.service;

import com.nixsolutions.spring.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by kozlovskij on 2/11/2016.
 */

@Path("user")
@Component
public class JerseyUserService {
    @Autowired
    private UserDAO userDAO;

    @Transactional
    @GET
    @Path("/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserById(@PathParam("id") String userId){
        if (userDAO == null) {
            return "UserDAO is null\n" +
                    "hello user, with id " + userId;
        } else {
            return userDAO.findByID(Long.valueOf(userId)).getLogin();
        }
    }
}
