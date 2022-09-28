package com.example.examplereactive.repository;

import com.example.examplereactive.dto.UserDto;

import reactor.core.publisher.Flux;

public interface UserRepository {
    Flux<UserDto> findAll();
    Flux<UserDto> findByGender(String gender);
}
