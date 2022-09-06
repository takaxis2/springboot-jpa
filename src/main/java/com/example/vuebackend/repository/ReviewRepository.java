package com.example.vuebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vuebackend.domain.Review;

public interface ReviewRepository extends JpaRepository<Review,Long>{

    @Query("select distinct r from Review r join fetch r.comments")
    List<Review> findAllByFetchJoin();

    @EntityGraph(attributePaths = "comments")
    @Query("select r from Review r")
    List<Review> findAllByEntityGraph();

    @EntityGraph(attributePaths = "comments")
    List<Review> findAll();
    
}
