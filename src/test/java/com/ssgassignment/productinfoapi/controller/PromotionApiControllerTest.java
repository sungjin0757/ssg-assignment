package com.ssgassignment.productinfoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssgassignment.productinfoapi.common.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.common.exception.advice.ExceptionControllerAdvice;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.repository.PromotionRepository;
import com.ssgassignment.productinfoapi.service.PromotionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
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
@AutoConfigureMockMvc
@Transactional
class PromotionApiControllerTest {
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    PromotionApiController promotionApiController;
    @Autowired
    PromotionService promotionService;
    @Autowired
    PromotionRepository promotionRepository;

    Map<String, String> params1;
    Map<String, String> params2;
    PromotionDto promotionDto1;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(promotionApiController)
                .setControllerAdvice(ExceptionControllerAdvice.class)
                .build();
        params1 = new HashMap<>();
        params1.put("promotionName","name");
        params1.put("discountAccount", "100");
        params1.put("discountRate", "0.1");
        params1.put("promotionStartDate", "2018-12-15T10:00:00");
        params1.put("promotionEndDate", "2019-12-15T10:00:00");

        params2 = new HashMap<>();

        promotionDto1 = new PromotionDto(
                "name1", 1000, 0.1,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 3, 3,0,0));
    }

    @Test
    @DisplayName("createPromotion Test")
    void createPromotion_테스트() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.PROMOTION_BASE+UrlConstants.SAVE)
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
                        MockMvcRequestBuilders.post(UrlConstants.PROMOTION_BASE+UrlConstants.SAVE)
                                .content(objectMapper.writeValueAsString(params2))
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
    @DisplayName("deletePromotion Test")
    void deletePromtoion_테스트() throws Exception{
        Long promotionId = promotionService.savePromotion(promotionDto1);
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(UrlConstants.PROMOTION_BASE+UrlConstants.DELETE+"/"+promotionId)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        Assertions.assertEquals(promotionRepository.findById(promotionId), Optional.empty());
    }
}