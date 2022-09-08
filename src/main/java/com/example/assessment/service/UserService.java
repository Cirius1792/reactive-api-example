package com.example.assessment.service;

import com.example.assessment.entity.User;

import java.util.List;

public interface UserService {
    List<User> retrieveAllUsers();
    List<User> retrieveUsers(String gender);
}
