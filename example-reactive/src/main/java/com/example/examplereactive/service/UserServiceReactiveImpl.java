package com.example.examplereactive.service;

import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.examplereactive.entity.User;
import com.example.examplereactive.mapper.UserMapper;
import com.example.examplereactive.repository.UserRepository;
import reactor.core.publisher.Flux;

public class UserServiceReactiveImpl implements UserService {
  private static final Logger logger = LoggerFactory.getLogger(UserServiceReactiveImpl.class);
  final UserRepository repository;
  final UserMapper mapper;

  public UserServiceReactiveImpl(UserRepository repository, UserMapper mapper) {
    logger.info("Repository is null? {}", repository == null);
    logger.info("Mapper is null?     {}", mapper == null);
    this.repository = repository;
    this.mapper = mapper;
  }

  @Override
  public Flux<User> retrieveAllUsers() {
    return this.repository.findAll()
        .map(mapper::userDtoToUser);
  }

  @Override
  public Flux<User> retrieveUsers(String gender) {
    if (Strings.isBlank(gender))
      return this.retrieveAllUsers();
    if (!User.MALE.equals(gender) && !User.FEMALE.equals(gender))
      throw new IllegalArgumentException("Invalid value for gender parameter");
    return this.repository.findByGender(gender)
        .map(mapper::userDtoToUser);
  }
}
