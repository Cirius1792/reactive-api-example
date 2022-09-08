package com.example.assessment.repository;

import com.example.assessment.dto.UserDto;

import java.util.List;

public interface UserRepository {
    List<UserDto> findAll();
    List<UserDto> findByGender(String gender);
}
