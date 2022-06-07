package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.exception.DuplicateEmailException;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@EnableJpaAuditing
class UserServiceTest {
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    UserDto userDto1;
    UserDto userDto2;

    @BeforeEach
    void setUp(){
        userDto1 = new UserDto("name1", "1234", "abc", UserType.GENERAL);
        userDto2 = new UserDto("name2", "1234", "abc", UserType.CORPORATE);
    }

    @Test
    @DisplayName("회원 가입 테스트")
    void 회원가입_테스트(){
        Long saveUser1 = userService.join(userDto1);
        Long saveUser2 = userService.join(userDto2);
        User findUser1 = userRepository.findById(saveUser1).get();
        User findUser2 = userRepository.findById(saveUser2).get();

        Assertions.assertAll(()->{
            Assertions.assertEquals(saveUser1, findUser1.getUserId());
            Assertions.assertEquals(saveUser2, findUser2.getUserId());
        });
    }

    @Test
    @DisplayName("DuplicateEmailException 테스트")
    void DuplicateEmailException_테스트(){
        Assertions.assertThrows(DuplicateEmailException.class,()->{
           userService.join(userDto1);
           userService.join(userDto1);
        });
    }
}