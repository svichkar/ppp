package com.nixsolutions.servicestation.endpoint;

import com.nixsolutions.servicestation.dao.UserDAO;
import com.nixsolutions.servicestation.entity.User;
import com.nixsolutions.servicestation.soap.CreateUserRequest;
import com.nixsolutions.servicestation.soap.CreateUserResponse;
import com.nixsolutions.servicestation.soap.UpdateUserRequest;
import com.nixsolutions.servicestation.soap.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


/**
 * Created by rybkinrolla on 16.02.2016.
 */
@Endpoint
public class UserEndpoint {

    private static final String URI = "http://www.servicestation.nixsolutions.com/soap";

    @Autowired
    private UserDAO userDAO;

    @PayloadRoot(namespace = URI, localPart = "updateUserRequest")
    @ResponsePayload
    public UpdateUserResponse updateUser(@RequestPayload UpdateUserRequest request) {
        UpdateUserResponse response = new UpdateUserResponse();
        User user = request.getUser();
        userDAO.update(request.getUser());
        response.setUser(user);
        return response;
    }

    @PayloadRoot(namespace = URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {
        CreateUserResponse response = new CreateUserResponse();
        User user = request.getUser();
        userDAO.create(user);
        response.setUser(user);
        return response;
    }
}