package com.ssgassignment.productinfoapi.domain;

import com.ssgassignment.productinfoapi.domain.enumeration.UserStat;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserTest {

    @Test
    @DisplayName("newInstanceTest")
    void newInstanceTest(){
        User user = User.newInstance("sungjin@naver.com", "1234", "Hong", UserType.GENERAL);

        org.junit.jupiter.api.Assertions.assertAll(()->{
            org.junit.jupiter.api.Assertions.assertEquals(user.getEmail(),"sungjin@naver.com");
            org.junit.jupiter.api.Assertions.assertEquals(user.getPassword(),"1234");
            org.junit.jupiter.api.Assertions.assertEquals(user.getName(),"Hong");
            org.junit.jupiter.api.Assertions.assertEquals(user.getUserType(),UserType.GENERAL);
            org.junit.jupiter.api.Assertions.assertEquals(user.getUserStat(), UserStat.ENABLED);
        });
    }

}