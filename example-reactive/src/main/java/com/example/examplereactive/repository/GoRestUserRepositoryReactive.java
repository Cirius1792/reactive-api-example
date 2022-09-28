package com.example.examplereactive.repository;

import org.springframework.web.reactive.function.client.WebClient;

import com.example.examplereactive.dto.UserDto;

import reactor.core.publisher.Flux;

public class GoRestUserRepositoryReactive implements UserRepository{
    private static final String GENDER_PARAM = "gender";
    final String endpoint;
    final WebClient client;

    public GoRestUserRepositoryReactive(String endpoint, WebClient client){
        this.endpoint = endpoint;
        this.client = client;
    }
    @Override
    public Flux<UserDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Flux<UserDto> findByGender(String gender) {
        // TODO Auto-generated method stub
        return null;
    }
    
}
