package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.PromotionItem;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.repository.PromotionRepository;
import com.ssgassignment.productinfoapi.testinit.DtoTestSets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest
@Transactional
class PromotionServiceTest {
    @Autowired
    PromotionService promotionService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    PromotionRepository promotionRepository;

    PromotionDto promotionDto1;
    PromotionDto promotionDto2;
    PromotionDto promotionDto3;
    Item item1;
    Item item2;
    Item item3;

    @BeforeEach
    void setUp(){
        promotionDto1 = DtoTestSets.PROMOTION_DTO1;
        promotionDto2 = DtoTestSets.PROMOTION_DTO2;
        promotionDto3 = DtoTestSets.PROMOTION_DTO3;
        item1 = Item.newInstance("name1",100, UserType.GENERAL,
                LocalDateTime.of(2022, 2, 1,0,0),
                LocalDateTime.of(2023, 3, 3,0,0));
        item2 = Item.newInstance("name2",100, UserType.CORPORATE,
                LocalDateTime.of(2022, 2, 4,0,0),
                LocalDateTime.of(2022, 2, 5,0,0));
        item3 = Item.newInstance("name3",100, UserType.GENERAL,
                LocalDateTime.of(2022, 3, 4,0,0),
                LocalDateTime.of(2022, 6, 16,0,0));
    }

    @Test
    @DisplayName("savePromotion 테스트")
    void save_promotion_테스트(){
        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);

        Long promotionId1 = promotionService.savePromotion(promotionDto1);
        Long promotionId2 = promotionService.savePromotion(promotionDto2);
        Long promotionId3 = promotionService.savePromotion(promotionDto3);

        Promotion promotion1 = promotionRepository.findById(promotionId1).get();
        Promotion promotion2 =promotionRepository.findById(promotionId2).get();
        Promotion promotion3 =promotionRepository.findById(promotionId3).get();

        Item findItem1 = itemRepository.findById(item1.getItemId()).get();
        Item findItem2 = itemRepository.findById(item2.getItemId()).get();
        Item findItem3 = itemRepository.findById(item3.getItemId()).get();

        Assertions.assertAll(()->{
            Assertions.assertEquals(promotion1.getPromotionId(),promotionId1);
            Assertions.assertEquals(promotion2.getPromotionId(),promotionId2);
            Assertions.assertEquals(promotion3.getPromotionId(),promotionId3);

            Assertions.assertEquals(findItem1.getPromotionItems().size(),3);
            Assertions.assertEquals(findItem2.getPromotionItems().size(),2);
            Assertions.assertEquals(findItem3.getPromotionItems().size(),2);

            for (PromotionItem promotionItem : findItem2.getPromotionItems()) {
                Assertions.assertNotEquals(promotionItem, promotion2);
            }
            for (PromotionItem promotionItem : findItem3.getPromotionItems()) {
                Assertions.assertNotEquals(promotionItem, promotion1);
            }
        });
    }
}