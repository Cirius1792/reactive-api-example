package com.example.examplereactive.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.examplereactive.dto.UserDto;

import reactor.core.publisher.Flux;

public class GoRestUserRepositoryReactive implements UserRepository {
    private static final Logger logger = LoggerFactory.getLogger(GoRestUserRepositoryReactive.class);

    private static final String GENDER_PARAM = "gender";
    final String endpoint;
    final WebClient client;

    public GoRestUserRepositoryReactive(String endpoint, WebClient.Builder builder) {
        logger.info("User endpoint: {}", endpoint);
        this.endpoint = endpoint;
        this.client = builder.baseUrl(this.endpoint).build();
    }

    @Override
    public Flux<UserDto> findAll() {
        // return Flux.fromIterable(mockedUsers);
        return this.client.get()
                .retrieve()
                .bodyToFlux(UserDto.class);
    }

    @Override
    public Flux<UserDto> findByGender(String gender) {
        return this.client.get()
                .uri(uriBuilder -> uriBuilder.queryParam(GENDER_PARAM, gender).build())
                .retrieve()
                .bodyToFlux(UserDto.class);
    }

}
