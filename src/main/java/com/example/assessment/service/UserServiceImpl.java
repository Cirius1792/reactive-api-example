package com.example.assessment.service;

import com.example.assessment.entity.User;
import com.example.assessment.mapper.UserMapper;
import com.example.assessment.repository.UserRepository;
import org.apache.logging.log4j.util.Strings;

import java.util.List;
import java.util.stream.Collectors;

public class UserServiceImpl implements UserService {

    final UserRepository repository;
    final UserMapper mapper;

    public UserServiceImpl(UserRepository repository, UserMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }


    @Override
    public List<User> retrieveAllUsers() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::userDtoToUser)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> retrieveUsers(String gender) {
        if(Strings.isBlank(gender))
            return this.retrieveAllUsers();
        if(!User.MALE.equals(gender) && !User.FEMALE.equals(gender))
            throw new IllegalArgumentException("Invalid value for gender parameter");
        return repository.findByGender(gender)
                .stream().map(this.mapper::userDtoToUser)
                .collect(Collectors.toList());
    }
}
