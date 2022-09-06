package com.example.vuebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vuebackend.domain.BookReviewInfo;

public interface BookReviewInfoRepository extends JpaRepository<BookReviewInfo, Long>{
    
}
