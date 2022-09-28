package com.example.assessment.configuration;

import com.example.assessment.mapper.UserMapper;
import com.example.assessment.repository.GorestUserRepository;
import com.example.assessment.repository.UserRepository;
import com.example.assessment.service.UserService;
import com.example.assessment.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AssessmentApplicationConfiguration {


    @Bean
    public UserRepository userRepository(@Value("${user.repository.url}") String endpoint){
        return new GorestUserRepository(endpoint, new RestTemplate());
    }
    @Bean
    public UserService userService(UserRepository repository, UserMapper mapper){
        return new UserServiceImpl(repository, mapper);
    }
}
