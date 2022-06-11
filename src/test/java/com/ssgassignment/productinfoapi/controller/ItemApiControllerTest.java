package com.ssgassignment.productinfoapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssgassignment.productinfoapi.common.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.common.exception.advice.ExceptionControllerAdvice;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.service.ItemService;
import com.ssgassignment.productinfoapi.service.PromotionService;
import com.ssgassignment.productinfoapi.service.UserService;
import com.ssgassignment.productinfoapi.testinit.DtoTestSets;
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
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
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
        promotionDto1 = DtoTestSets.PROMOTION_DTO1;
        promotionDto2 = DtoTestSets.PROMOTION_DTO2;
        promotionDto3 = DtoTestSets.PROMOTION_DTO3;

        itemDto1 = DtoTestSets.ITEM_DTO6;
        itemDto2 = DtoTestSets.ITEM_DTO5;
        itemDto3 = DtoTestSets.ITEM_DTO3;
        itemDto4 = DtoTestSets.ITEM_DTO4;
        userDto1 = DtoTestSets.USER_DTO1;
        userDto2 = DtoTestSets.USER_DTO2;

    }

    private void initDb(){
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
        initDb();
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
                                UrlConstants.ITEM_BASE+UrlConstants.USER+"/-99")
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
        initDb();
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
        itemId1 = itemService.saveItem(itemDto1);
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