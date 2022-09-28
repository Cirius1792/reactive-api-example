package com.example.examplereactive.entity;


import lombok.*;

@Getter
@Setter
@Builder
@ToString
@EqualsAndHashCode
public class User {
    public static final String MALE = "male";
    public static final String FEMALE = "female";
    Long id;
    String name;
    String email;
    String status;
    String gender;
}
