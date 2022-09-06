package com.example.vuebackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.vuebackend.domain.UserHistory;

public interface UserHistoryRepository extends JpaRepository<UserHistory, Long>{

    List<UserHistory> findByUserId(Long userId);
}
