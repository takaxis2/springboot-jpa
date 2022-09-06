package com.example.vuebackend.domain.listener;

import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import com.example.vuebackend.domain.User;
import com.example.vuebackend.domain.UserHistory;
import com.example.vuebackend.repository.UserHistoryRepository;
import com.example.vuebackend.support.BeanUtils;

public class UserEntityListener {
    
    // @Autowired
    // private UserHistoryRepository userHistoryRepository;
    // private final UserHistoryRepository userHistoryRepository; //requiredArgConstructor 어노테이션 필요
    // 위에꺼 다 사용 못함, 스프링과 jpa에서 bean을 생성하는 순서가 다름

    // @PostUpdate
    // @PostPersist
    @PostPersist
    @PostUpdate
    public void postPersistAndPostUpdate(Object o){

        UserHistoryRepository userHistoryRepository = BeanUtils.getBean(UserHistoryRepository.class);

        User user =(User)o;

        UserHistory userHistory = new UserHistory();
        // userHistory.setUserId(user.getId());
        userHistory.setName(user.getName());
        userHistory.setEmail(user.getEmail());
        userHistory.setUser(user);
        userHistory.setHomeAddress(user.getHomeAddress());
        userHistory.setCompanyAddress(user.getCompanyAddress());

        userHistoryRepository.save(userHistory);
    }
}
