package com.example.examplereactive.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.examplereactive.dto.UserDto;
import com.example.examplereactive.entity.User;

@Mapper
public interface UserMapper{

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto userToUserDto(User user);
    User userDtoToUser(UserDto user);
}
