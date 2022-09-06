package com.example.vuebackend.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.vuebackend.domain.User;
import com.example.vuebackend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;


    @Transactional
    public void put(){
        User user = new User();
        user.setName("new User");
        user.setEmail("newUser@gmail.com");

        // userRepository.save(user);
        entityManager.persist(user);
        // entityManager.detach(user);
        /**
         * 준영속상태(detach), 엔티티를 더이상 영속성 컨텍스트에서 관리하지 않음, 밑에 내용이 반영되지 않음
         * 엔티티 메니저의 merge메소드를 호출하면 데이터 반영이 일어난다
         * clear나 close도 비슷한 기능, 좀더 파괴적인? clear전엔 flush로 변경내역을 다 반영하는것을 권고
         */

        user.setName("newUserAfterPersist");
        entityManager.merge(user);
        // 엔티티 매니저가 자동으로 업데이트 문을 트랜잭션 끝날때 돌려줘서 반영된다
        // 영속성 컨텍스트가 제공해주는 더티체크 기능
        /**
         * 엔티티 객체는 처음 컨텍스트에 로드될때 해당 정보를 일종의 스냅샷으로 복사해서 가지고 있는다 
         * 그후 변경 내역을 db에 적용해야하는 시점(flush, transaction이 종룔되고 커밋되는 시점, jpql쿼리가 실행되는 시점)에
         * 해당 스냅샷과 현재 엔티티를 비교해서 변경된 내역을 추가적으로 (코드가 없다 하더라도) db에 반영
         * 대량의 엔티티를 다룰 경우 성능저하가 발생하기도함
         */
        
        //  entityManager.flush();
        //  entityManager.clear();

         entityManager.remove(user);
         // 더이상 해당 엔티티 사용 불가능, delete
    }

}
