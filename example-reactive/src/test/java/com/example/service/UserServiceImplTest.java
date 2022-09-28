package com.example.service;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.example.examplereactive.dto.UserDto;
import com.example.examplereactive.entity.User;
import com.example.examplereactive.mapper.UserMapper;
import com.example.examplereactive.repository.UserRepository;
import com.example.examplereactive.service.UserService;

class UserServiceImplTest {

    // @Test
    // void retrieveAllUsers() {
    //     UserRepository repository = Mockito.mock(UserRepository.class);
    //     List<UserDto> sampleData = this.buildSampleUserDtoList();
    //     List<User> target = this.buildSampleUserList();
    //     Mockito.when(repository.findAll())
    //             .thenReturn(sampleData);
    //     UserService service = new UserServiceImpl(repository, UserMapper.INSTANCE);
    //     List<User> actual = service.retrieveAllUsers();
    //     Assertions.assertNotNull(actual);
    //     Assertions.assertEquals(2, actual.size());
    //     Assertions.assertEquals(target.get(0), actual.get(0));
    //     Assertions.assertEquals(target.get(1), actual.get(1));
    // }

    // @Test
    // void retrieveUsersByGender() {
    //     UserRepository repository = Mockito.mock(UserRepository.class);
    //     List<UserDto> sampleData = this.buildSampleUserDtoList();
    //     String gender = "female";
    //     Mockito.when(repository.findAll())
    //             .thenReturn(sampleData);
    //     UserService service = new UserServiceImpl(repository, UserMapper.INSTANCE);
    //     List<User> actual = service.retrieveUsers(gender);
    //     Assertions.assertEquals(actual.size(), actual.stream()
    //             .map(User::getGender)
    //             .filter(gender::equals)
    //             .count());
    //     Assertions.assertThrows(IllegalArgumentException.class, () -> service.retrieveUsers("xxx")) ;
    // }

    protected List<UserDto> buildSampleUserDtoList() {
        return Arrays.asList(UserDto.builder()
                        .id(1L)
                        .email("bob@test.com")
                        .status("male")
                        .name("Bob")
                        .build(),
                UserDto.builder()
                        .id(2L)
                        .email("sara@test.com")
                        .status("female")
                        .name("Sara")
                        .build()
        );
    }
    protected List<User> buildSampleUserList() {
        return Arrays.asList(User.builder()
                        .id(1L)
                        .email("bob@test.com")
                        .status("male")
                        .name("Bob")
                        .build(),
                User.builder()
                        .id(2L)
                        .email("sara@test.com")
                        .status("female")
                        .name("Sara")
                        .build()
        );
    }
}