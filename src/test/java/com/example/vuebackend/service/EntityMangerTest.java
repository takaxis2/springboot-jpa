package com.example.vuebackend.service;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.User;
import com.example.vuebackend.repository.UserRepository;

@SpringBootTest
@Transactional
public class EntityMangerTest {
    @Autowired
    private  EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;
    
    @Test
    void entityManagerTest(){
        System.out.println(entityManager.createQuery("select u from User u").getResultList());
    }

    @Test
    void cacheFindTest(){
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
        System.out.println(userRepository.findById(1L).get());
    }

    @Test
    void cacheFindTest2(){
        User user =userRepository.findById(1L).get();
        user.setName("asdasdasd");

        userRepository.save(user);

        user.setEmail("asdasd@gmail.com");

        userRepository.save(user);

        System.out.println(">>>> 1 : " + userRepository.findById(1L).get()); // 영속성컨텍스트는 바뀜 실제 db는 안바뀜

        userRepository.flush();

        System.out.println(">>>> 1 : " + userRepository.findById(1L).get()); // 영속성컨텍스트는 바뀜 실제 db도 반영되어 바뀜
    }


}
