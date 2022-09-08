package com.example.assessment.controller;

import com.example.assessment.controller.response.UsersResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserController {
    @GetMapping(
            value = "/users",
            produces = {"application/json"}
    )
    ResponseEntity<UsersResponse> getUsers(@RequestParam(name = "gender", required = false) String gender);
}
