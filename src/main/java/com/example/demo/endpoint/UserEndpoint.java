package com.example.demo.endpoint;

import com.example.demo.service.UserService;
import com.example.demo.soap.GetUserRequest;
import com.example.demo.soap.GetUserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class UserEndpoint {
    private static final String namespaceUri = "http://demo.example.com/soap";
    private UserService userService;

    @Autowired
    public UserEndpoint(UserService userService) {
        this.userService = userService;
    }

    @PayloadRoot(namespace = namespaceUri, localPart = "getUserRequest")
    @ResponsePayload()
    public GetUserResponse getUserResponse(@RequestPayload GetUserRequest request) {
        GetUserResponse response = new GetUserResponse();
        response.setUser(userService.getUser(request.getName()));
        return response;
    }
}
