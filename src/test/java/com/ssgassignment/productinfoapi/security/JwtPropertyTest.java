package com.ssgassignment.productinfoapi.security;

import com.ssgassignment.productinfoapi.security.property.JwtProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtPropertyTest {
    @Autowired
    JwtProperty jwtProperty;

    @Test
    @DisplayName("Constructor Binding Test")
    void ConstructorBinding_테스트(){
        Assertions.assertAll(()->{
            Assertions.assertEquals(jwtProperty.getSecret(),"spring-boot-ssg-backend-assginment-security-jwt-secret-key");
            Assertions.assertEquals(jwtProperty.getExpiration(), 30l);
        });
    }
}
