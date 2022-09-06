package com.example.vuebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vuebackend.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
    
}
