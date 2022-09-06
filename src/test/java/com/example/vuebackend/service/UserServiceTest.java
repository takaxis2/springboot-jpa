package com.example.vuebackend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.vuebackend.repository.UserRepository;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        userService.put();

        System.out.println(">>>> " + userRepository.findByEmail("newUser@gmail.com"));
    }
}
