package com.example.vuebackend.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.Address;
import com.example.vuebackend.domain.Gender;
import com.example.vuebackend.domain.User;
import com.example.vuebackend.domain.UserHistory;

@SpringBootTest
@Transactional
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private UserHistoryRepository userHistoryRepository;

    @Autowired
    private EntityManager entityManager;


    @Test
    void userHistoryTest(){
        User user = new User();

        user.setEmail("martin-new@gmail.com");
        user.setName("martin");

        userRepository.save(user);

        user.setName("martin-new");

        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);
    }

    @Test
    void userRelationTest(){
        User user = new User();
        user.setName("martin");
        user.setEmail("martin@gmail.com");
        user.setGender(Gender.MALE);
        userRepository.save(user);

        user.setName("Daniel");
        userRepository.save(user);

        user.setEmail("daniel@gmail.com");
        userRepository.save(user);

        userHistoryRepository.findAll().forEach(System.out::println);

        // List<UserHistory> result = userHistoryRepository.findByUserId(
        //     userRepository.findByEmail("daniel@gmail.com").getId());
        
        List<UserHistory> result = userRepository.findByEmail("daniel@gmail.com").getUserHistories();

        result.forEach(System.out::println);

        System.out.println("UserHistory.getUser() :  " + userHistoryRepository.findAll().get(0).getUser());
    }

    

    @Test
    void embedTest(){
        userRepository.findAll().forEach(System.out::println);

        User user = new User();
        user.setName("Tonny");
        user.setHomeAddress(new Address("Busan-city", "Kijang-district", "Kijang road 82-8", "48311"));
        user.setCompanyAddress(new Address("busan-city", "haeundae -district", "daewoo2cha 205-401", "28311"));
        
        userRepository.save(user);

        User user1 = new User();
        user1.setName("Joshua");
        user1.setHomeAddress(null);
        user1.setCompanyAddress(null);

        userRepository.save(user1);

        User user2 = new User();
        user2.setName("Jordan");
        user2.setHomeAddress(new Address());
        user2.setCompanyAddress(new Address());

        userRepository.save(user2);

        entityManager.clear();
        /**
         * 여기서 확인해보면 homeAddress가 null이거나
         * homeAddress안에 내용물이 null이거나 다르게 찍히는데
         * entity cache때문에 그런것
         * entityManger.clear() 실행후에는 똑같이 homeAddress =null로 찍힌다
         */
        userRepository.findAll().forEach(System.out::println);
        userRepository.findAllRawRecord().forEach(a ->System.out.println(a.values()));
        
        assertAll(
            ()->assertThat(userRepository.findById(7L).get().getHomeAddress()).isNull(),
            ()->assertThat(userRepository.findById(8L).get().getHomeAddress()).isInstanceOf(Address.class)
        );
    }

}
