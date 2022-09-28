package com.example.assessment.repository;

import com.example.assessment.dto.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GorestUserRepository implements UserRepository {

    private static final String GENDER_PARAM = "gender";
    final String endpoint;
    final RestTemplate restTemplate;

    public GorestUserRepository(String endpoint, RestTemplate restTemplate) {
        this.endpoint = endpoint;
        this.restTemplate = restTemplate;
    }

    @Override
    public List<UserDto> findAll() {
        ResponseEntity<UserDto[]> users = this.restTemplate.getForEntity(this.endpoint, UserDto[].class);
        return users.getBody() != null ? Arrays.asList(users.getBody()) : new ArrayList<>();
    }

    @Override
    public List<UserDto> findByGender(String gender) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(this.endpoint)
                .queryParam(GENDER_PARAM, gender);
        ResponseEntity<UserDto[]> users = this.restTemplate.getForEntity(builder.build().toString(), UserDto[].class);
        return users.getBody() != null ? Arrays.asList(users.getBody()) : new ArrayList<>();
    }

}
