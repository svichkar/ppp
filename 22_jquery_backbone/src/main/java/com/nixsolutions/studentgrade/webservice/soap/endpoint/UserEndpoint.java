package com.nixsolutions.studentgrade.webservice.soap.endpoint;

import com.nixsolutions.studentgrade.service.UserService;
import com.nixsolutions.studentgrade.webservice.soap.CreateUserRequest;
import com.nixsolutions.studentgrade.webservice.soap.CreateUserResponse;
import com.nixsolutions.studentgrade.webservice.soap.UpdateUserRequest;
import com.nixsolutions.studentgrade.webservice.soap.UpdateUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

/**
 * Created by konstantin on 2/13/2016.
 */

@Endpoint
public class UserEndpoint {

    private static final String NAMESPACE_URI = "http://www.user.com";

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createUserRequest")
    @ResponsePayload
    public CreateUserResponse createUser(@RequestPayload CreateUserRequest request) {

        userService.create(request.getUser());
        CreateUserResponse response = new CreateUserResponse();
        response.setUserId(userService.findByLogin(request.getUser().getLogin()).getUserId());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "updateUserRequest")
    @ResponsePayload
    public void updateUser(@RequestPayload UpdateUserRequest request) {

        userService.update(request.getUser());
        UpdateUserResponse response = new UpdateUserResponse();
        response.setUser(userService.findById(request.getUser().getUserId()));
        //return response;
    }
}

