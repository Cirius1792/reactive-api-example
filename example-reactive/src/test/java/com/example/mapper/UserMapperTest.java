package com.example.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.examplereactive.dto.UserDto;
import com.example.examplereactive.entity.User;
import com.example.examplereactive.mapper.UserMapper;

class UserMapperTest {

    @Test
    void userToUserDto() {
        User entity = User.builder()
                .id(1L)
                .email("bob@test.com")
                .status("male")
                .name("Bob")
                .build();
        UserDto model = UserDto.builder()
                .id(1L)
                .email("bob@test.com")
                .status("male")
                .name("Bob")
                .build();
        UserMapper mapper = UserMapper.INSTANCE;
        UserDto actual = mapper.userToUserDto(entity);
        Assertions.assertEquals(model, actual);
    }
    @Test
    void userDtoToUser() {
        User entity = User.builder()
                .id(1L)
                .email("bob@test.com")
                .status("male")
                .name("Bob")
                .build();
        UserDto model = UserDto.builder()
                .id(1L)
                .email("bob@test.com")
                .status("male")
                .name("Bob")
                .build();
        UserMapper mapper = UserMapper.INSTANCE;
        User actual = mapper.userDtoToUser(model);
        Assertions.assertEquals(entity, actual);
    }
}