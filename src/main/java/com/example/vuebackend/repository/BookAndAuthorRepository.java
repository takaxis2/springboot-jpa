package com.example.vuebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vuebackend.domain.BookAndAuthor;

public interface BookAndAuthorRepository extends JpaRepository<BookAndAuthor, Long>{
    
}
