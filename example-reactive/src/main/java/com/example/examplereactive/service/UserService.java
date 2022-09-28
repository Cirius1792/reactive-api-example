package com.example.examplereactive.service;


import java.util.List;

import com.example.examplereactive.entity.User;

public interface UserService {
    List<User> retrieveAllUsers();
    List<User> retrieveUsers(String gender);
}
