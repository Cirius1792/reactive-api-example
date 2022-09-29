package com.example.examplereactive.controller;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.example.examplereactive.entity.User;
import com.example.examplereactive.service.UserService;

import reactor.core.publisher.Mono;

public class UserHandler {
    
    final UserService userService;
    
    public UserHandler(UserService userService){
        this.userService = userService;
    }

    public Mono<ServerResponse> getUsers(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
          .body(userService.retrieveAllUsers(), User.class);
      }
}
