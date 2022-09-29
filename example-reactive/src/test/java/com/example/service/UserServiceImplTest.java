package com.example.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.LoggerFactory;
import org.mockito.Mockito;
import org.reactivestreams.Publisher;

import com.example.examplereactive.dto.UserDto;
import com.example.examplereactive.entity.User;
import com.example.examplereactive.mapper.UserMapper;
import com.example.examplereactive.repository.UserRepository;
import com.example.examplereactive.service.UserService;
import com.example.examplereactive.service.UserServiceReactiveImpl;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

class UserServiceImplTest {
    private static final org.junit.platform.commons.logging.Logger logger = LoggerFactory
            .getLogger(UserServiceImplTest.class);

    @Test
    void retrieveAllUsers() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        Flux<UserDto> sampleData = Flux.fromIterable(this.buildSampleUserDtoList());
        List<User> target = this.buildSampleUserList();
        Mockito.when(repository.findAll())
                .thenReturn(sampleData);
        UserService service = new UserServiceReactiveImpl(repository, UserMapper.INSTANCE);
        List<User> actual = service.retrieveAllUsers().collectList().block();
        Assertions.assertNotNull(actual);
        Assertions.assertEquals(2, actual.size());
        Assertions.assertEquals(target.get(0), actual.get(0));
        Assertions.assertEquals(target.get(1), actual.get(1));
    }

    @Test
    void retrieveUsersByGender() {
        UserRepository repository = Mockito.mock(UserRepository.class);
        String gender = "female";
        Flux<UserDto> sampleData = Flux.fromIterable(this.buildSampleUserDtoList()
                .stream()
                .filter(el -> gender.equals(el.getGender()))
                .collect(Collectors.toList()));
        Mockito.when(repository.findByGender(Mockito.anyString()))
                .thenReturn(sampleData);
        UserService service = new UserServiceReactiveImpl(repository, UserMapper.INSTANCE);
        Flux<User> setup = service.retrieveUsers(gender);
        StepVerifier.create(setup)
                .consumeNextWith(user -> Assertions.assertEquals(gender, user.getGender()))
                .verifyComplete();
        Assertions.assertThrows(IllegalArgumentException.class, () -> service.retrieveUsers("xxx"));
    }

    protected List<UserDto> buildSampleUserDtoList() {
        return Arrays.asList(UserDto.builder()
                .id(1L)
                .email("bob@test.com")
                .gender("male")
                .name("Bob")
                .build(),
                UserDto.builder()
                        .id(2L)
                        .email("sara@test.com")
                        .gender("female")
                        .name("Sara")
                        .build());
    }

    protected List<User> buildSampleUserList() {
        return Arrays.asList(User.builder()
                .id(1L)
                .email("bob@test.com")
                .gender("male")
                .name("Bob")
                .build(),
                User.builder()
                        .id(2L)
                        .email("sara@test.com")
                        .gender("female")
                        .name("Sara")
                        .build());
    }
}