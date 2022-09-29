package com.example.examplereactive.service;


import com.example.examplereactive.entity.User;

import reactor.core.publisher.Flux;

public interface UserService {
    Flux<User> retrieveAllUsers();
    Flux<User> retrieveUsers(String gender);
}
