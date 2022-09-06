package com.example.vuebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vuebackend.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
}
