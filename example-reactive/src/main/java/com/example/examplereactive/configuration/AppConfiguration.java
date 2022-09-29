package com.example.examplereactive.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.examplereactive.controller.UserHandler;
import com.example.examplereactive.mapper.UserMapper;
import com.example.examplereactive.repository.GoRestUserRepositoryReactive;
import com.example.examplereactive.repository.UserRepository;
import com.example.examplereactive.service.UserService;
import com.example.examplereactive.service.UserServiceReactiveImpl;

@Configuration
public class AppConfiguration {
    
	@Bean
	UserRepository userRepository(@Value("${user.repository.url}") String endpoint, WebClient.Builder builder){
		return new GoRestUserRepositoryReactive(endpoint, builder);
	}

	@Bean 
	UserService userService(UserRepository userRepository){
		return new UserServiceReactiveImpl(userRepository, UserMapper.INSTANCE);
	}
	@Bean
	UserHandler userHandler(UserService userService){
		return new UserHandler(userService);
	}
}
