package com.example.assessment.controller;

import com.example.assessment.controller.response.UserDtoResponse;
import com.example.assessment.controller.response.UsersResponse;
import com.example.assessment.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserControllerImpl implements UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserControllerImpl.class);

    final UserService userService;

    public UserControllerImpl(@Autowired UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<UsersResponse> getUsers(String gender) {
        try {
            List<UserDtoResponse> users = userService.retrieveUsers(gender)
                    .stream().map(el -> UserDtoResponse.builder()
                            .id(el.getId())
                            .name(el.getName())
                            .email(el.getEmail())
                            .gender(el.getGender())
                            .status(el.getStatus())
                            .build())
                    .collect(Collectors.toList());
            return ResponseEntity.ok(new UsersResponse(users));
        } catch (IllegalArgumentException ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .build();
        } catch (Exception exception){
            logger.error("Error:", exception);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .build();
        }
    }
}
