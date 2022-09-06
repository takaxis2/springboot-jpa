package com.example.vuebackend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.vuebackend.repository.CommentRepository;

@SpringBootTest
public class CommentServiceTest {
    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentRepository commentRepository;

    @Test
    void commentTest(){
        commentService.init();

        // commentRepository.findAll().forEach(System.out::println);

        commentService.updateSomething();
        commentService.insertSomething();
    }
}
