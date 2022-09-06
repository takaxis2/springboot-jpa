package com.example.vuebackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vuebackend.domain.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher,Long>{
    
}
