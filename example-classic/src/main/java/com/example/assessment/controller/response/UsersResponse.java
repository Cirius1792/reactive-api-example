package com.example.assessment.controller.response;

import lombok.*;

import java.util.List;


@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
public class UsersResponse {
    List<UserDtoResponse> data;
}
