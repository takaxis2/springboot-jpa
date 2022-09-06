package com.example.vuebackend.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.vuebackend.domain.User;

public interface UserRepository extends JpaRepository<User,Long>{

    User findByEmail(String email);

    @Query(value = "select * from users", nativeQuery = true)
    List<Map<String, Object>> findAllRawRecord();
    
}
