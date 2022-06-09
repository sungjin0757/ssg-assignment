package com.ssgassignment.productinfoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssgassignment.productinfoapi.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.controller.exception.advice.ExceptionControllerAdvice;
import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.service.ItemService;
import com.ssgassignment.productinfoapi.service.PromotionService;
import com.ssgassignment.productinfoapi.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableJpaAuditing
@AutoConfigureMockMvc
@Transactional
class ItemApiControllerTest {
    @Autowired
    ItemApiController itemApiController;
    @Autowired
    ItemService itemService;
    @Autowired
    PromotionService promotionService;
    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @Autowired
    UserService userService;
    @Autowired
    ItemRepository itemRepository;

    Map<String, String> params1;
    Map<String, String> params2;
    Map<String, String> params3;
    PromotionDto promotionDto1;
    PromotionDto promotionDto2;
    PromotionDto promotionDto3;
    ItemDto itemDto1;
    ItemDto itemDto2;
    ItemDto itemDto3;
    ItemDto itemDto4;
    Long itemId1;
    Long itemId2;
    Long itemId3;
    Long promotionId1;
    Long promotionId2;
    Long promotionId3;
    UserDto userDto1;
    UserDto userDto2;
    Long userId1;
    Long userId2;

    @BeforeEach
    void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(itemApiController)
                .setControllerAdvice(ExceptionControllerAdvice.class)
                .build();
        params1 = new HashMap<>();
        params2 = new HashMap<>();
        params3 = new HashMap<>();

        params1.put("itemName", "name");
        params1.put("itemPrice", "1000");
        params1.put("itemType", "CORPORATE");
        params1.put("itemDisplayStartDate", "2018-12-15T10:00:00");
        params1.put("itemDisplayEndDate", "2019-12-15T10:00:00");

        params3.put("itemName", "name");
        params3.put("itemPrice", "1000");
        params3.put("itemType", "CORPORATE");
        params3.put("itemDisplayEndDate", "2018-12-15T10:00:00");
        params3.put("itemDisplayStartDate", "2019-12-15T10:00:00");
        promotionDto1 = new PromotionDto(
                "name1", 1000, 0.1,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 3, 3,0,0));
        promotionDto2 = new PromotionDto(
                "name2", 3000, 0.1,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 8, 3,0,0));
        promotionDto3 = new PromotionDto(
                "nam3", 1000, 0.2,
                LocalDateTime.of(2022, 6, 3,0,0),
                LocalDateTime.of(2022, 6, 15,0,0));

        itemDto1 = new ItemDto("name1",10000, UserType.GENERAL,
                LocalDateTime.of(2022, 2, 1,0,0),
                LocalDateTime.of(2022, 3, 3,0,0));
        itemDto2 = new ItemDto("name2",10000, UserType.CORPORATE,
                LocalDateTime.of(2022, 2, 4,0,0),
                LocalDateTime.of(2022, 8, 5,0,0));
        itemDto3 = new ItemDto("name3",20000, UserType.GENERAL,
                LocalDateTime.of(2022, 3, 4,0,0),
                LocalDateTime.of(2022, 6, 16,0,0));
        itemDto4 = new ItemDto("name3",100, UserType.GENERAL,
                LocalDateTime.of(2022, 3, 4,0,0),
                LocalDateTime.of(2021, 6, 16,0,0));
        userDto1 = new UserDto("name1", "1234", "abc", UserType.GENERAL);
        userDto2 = new UserDto("name2", "1234", "abc", UserType.CORPORATE);

        itemId1 = itemService.saveItem(itemDto1);
        itemId2 = itemService.saveItem(itemDto2);
        itemId3 = itemService.saveItem(itemDto3);

        promotionId1 = promotionService.savePromotion(promotionDto1);
        promotionId2 = promotionService.savePromotion(promotionDto2);
        promotionId3 = promotionService.savePromotion(promotionDto3);

        userId1 = userService.join(userDto1);
        userId2 = userService.join(userDto2);
    }

    @Test
    @DisplayName("createItem Test")
    void createItem_테스트() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.post(UrlConstants.ITEM_BASE+UrlConstants.SAVE)
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
                        MockMvcRequestBuilders.post(UrlConstants.ITEM_BASE+UrlConstants.SAVE)
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
                        MockMvcRequestBuilders.post(UrlConstants.ITEM_BASE+UrlConstants.SAVE)
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
    @DisplayName("itemInfos Test")
    void itemInfos_테스트() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.get(UrlConstants.ITEM_BASE+UrlConstants.USER+"/"+userId1)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.get(UrlConstants.ITEM_BASE+UrlConstants.USER+"/"+userId2)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.get(
                                UrlConstants.ITEM_BASE+UrlConstants.USER+"/"+Math.max(userId1,userId2)+1)
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
    @DisplayName("itemWithPromotionInfos Test")
    void itemWithPromotionInfos_테스트() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.get(UrlConstants.ITEM_BASE+UrlConstants.PROMOTION+"/"+itemId1)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.get(UrlConstants.ITEM_BASE+UrlConstants.PROMOTION+"/"+itemId2)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.get(UrlConstants.ITEM_BASE+UrlConstants.PROMOTION+"/"+itemId3)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.get(UrlConstants.ITEM_BASE+UrlConstants.PROMOTION+"/-99")
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
    @DisplayName("deleteItem Test")
    void deleteItem_테스트() throws Exception{
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(UrlConstants.ITEM_BASE+UrlConstants.DELETE+"/"+itemId1)
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isOk())
                .andDo(print());
        mockMvc.perform(
                        MockMvcRequestBuilders.delete(UrlConstants.ITEM_BASE+UrlConstants.DELETE+"/-99")
                                .contentType(new MediaType(
                                        MediaType.APPLICATION_JSON.getType(),
                                        MediaType.APPLICATION_JSON.getSubtype(),
                                        Charset.forName("utf8")
                                ))
                )
                .andExpect(status().isBadRequest())
                .andDo(print());
        Assertions.assertEquals(itemRepository.findById(itemId1), Optional.empty());
    }

}