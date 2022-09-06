package com.example.vuebackend.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Review;

@SpringBootTest
public class ReviewRepositoryTest {
    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    @Transactional
    void reviewTest(){
        List<Review> reviews = reviewRepository.findAll();
        // List<Review> reviews = reviewRepository.findAllByFetchJoin();
        // List<Review> reviews = reviewRepository.findAllByEntityGraph();

        // System.out.println(reviews);

        // System.out.println("전채 내용");
        
        // System.out.println(reviews.get(0).getComments());

        // System.out.println("첫번째 리뷰의 코맨트");

        // System.out.println(reviews.get(1).getComments());

        // System.out.println("두번째 리뷰의 코맨트");

        reviews.forEach(System.out::println);
    }

}
