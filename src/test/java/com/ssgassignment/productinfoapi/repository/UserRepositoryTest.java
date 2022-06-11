package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;

@DataJpaTest
@Transactional
class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    @DisplayName("생성일자 자동생성")
    void 생성일자_자동생성(){
        User user = userRepository.save(User.newInstance("sungjin@naver.com", "1234", "Hong",
                UserType.GENERAL));

        Assertions.assertAll(()->{
            Assertions.assertNotNull(user.getCreatedDate());
            Assertions.assertNotNull(user.getUpdatedDate());
        });
    }

}