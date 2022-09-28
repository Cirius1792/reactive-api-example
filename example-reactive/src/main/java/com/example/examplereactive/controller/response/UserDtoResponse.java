package com.example.examplereactive.controller.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class UserDtoResponse {
    Long id;
    String name;
    String email;
    String status;
    String gender;
}
