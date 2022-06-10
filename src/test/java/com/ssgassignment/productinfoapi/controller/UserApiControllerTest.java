package com.ssgassignment.productinfoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssgassignment.productinfoapi.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.controller.exception.advice.ExceptionControllerAdvice;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.exception.NotFoundUserException;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import com.ssgassignment.productinfoapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class UserApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    UserApiController userApiController;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    Map<String, String> params1;
    Map<String, String> params2;
    Map<String, String> params3;
    Map<String, String> params4;
    Map<String, String> params5;
    Map<String, String> params6;
    UserDto userDto1;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(userApiController)
                .setControllerAdvice(ExceptionControllerAdvice.class)
                .build();
        params1 = new HashMap<>();
        params1.put("email","sungjin@naver.com");
        params1.put("password", "1234");
        params1.put("name", "hong");
        params1.put("userType", "GENERAL");

        params2 = new HashMap<>();
        params3 = new HashMap<>();
        params3.put("email","3");
        params3.put("password", "1234");
        params3.put("name", "hong");
        params3.put("userType", "123");

        userDto1 = new UserDto("sungjin@naver.com", "1234", "abc", UserType.GENERAL);
        params4 = new HashMap<>();
        params4.put("email","sungjin@naver.com");
        params4.put("password","1234");

        params5 = new HashMap<>();
        params5.put("email","sungjin@naver.com");
        params5.put("password","124");

        params6 = new HashMap<>();
        params6.put("email","sungjn@naver.com");
        params6.put("password","1234");
    }

    @Test
    @DisplayName("Controller Save Test")
    void 유저_저장_테스트() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.post(UrlConstants.USER_BASE+UrlConstants.SAVE)
                        .content(objectMapper.writeValueAsString(params1))
                        .contentType(new MediaType(
                                MediaType.APPLICATION_JSON.getType(),
                                MediaType.APPLICATION_JSON.getSubtype(),
                                Charset.forName("utf8")
                        ))
        )
                .andExpect(status().isCreated())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.USER_BASE+UrlConstants.SAVE)
                                .content(objectMapper.writeValueAsString(params2))
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());

        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.USER_BASE+UrlConstants.SAVE)
                                .content(objectMapper.writeValueAsString(params3))
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());

    }

    @Test
    @DisplayName("Controller Withdraw Test")
    void withdraw_테스트() throws Exception {
        Long userId = userService.join(userDto1);

        mockMvc.perform(
                        MockMvcRequestBuilders.delete(UrlConstants.USER_BASE
                                        +UrlConstants.USER_WITHDRAW+"/"+userId)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        Assertions.assertEquals(userRepository.findById(userId), Optional.empty());
    }

    @Test
    @DisplayName("Controller Login Test")
    void login_테스트() throws Exception{
        userService.join(userDto1);
        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.USER_BASE+UrlConstants.LOGIN)
                                .content(objectMapper.writeValueAsString(params2))
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.USER_BASE+UrlConstants.LOGIN)
                                .content(objectMapper.writeValueAsString(params4))
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.USER_BASE+UrlConstants.LOGIN)
                                .content(objectMapper.writeValueAsString(params5))
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.USER_BASE+UrlConstants.LOGIN)
                                .content(objectMapper.writeValueAsString(params6))
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

}