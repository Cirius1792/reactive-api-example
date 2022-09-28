package com.example.examplereactive.dto;


import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UserDto {
    Long id;
    String name;
    String email;
    String status;
    String gender;
}
