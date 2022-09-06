package com.example.vuebackend.repository;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Comment;

@SpringBootTest
public class CommentRepositoryTest {
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    void commentTest(){
        // Comment comment = commentRepository.findById(3L).get();
        Comment comment = new Comment();
        comment.setComment("not good");
        // comment.setCommentedAt(LocalDateTime.now());

        commentRepository.saveAndFlush(comment);

        entityManager.clear(); //캐시삭제

        // System.out.println(commentRepository.findById(3L).get());
        commentRepository.findAll().forEach(System.out::println);
        // System.out.println(comment);
    }
}
