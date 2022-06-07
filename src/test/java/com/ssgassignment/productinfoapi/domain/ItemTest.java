package com.ssgassignment.productinfoapi.domain;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    Item item1;
    Item item2;
    Promotion promotion1;
    Promotion promotion2;
    Promotion promotion3;

    @BeforeEach
    void setUp(){
        promotion1 = Promotion.newInstance(
                "name1", 1000, 0.1,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 3, 3,0,0));
        promotion2 = Promotion.newInstance(
                "name2", 1000, 0.1,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 8, 3,0,0));
        promotion3 = Promotion.newInstance(
                "nam3", 1000, 0.1,
                LocalDateTime.of(2022, 6, 3,0,0),
                LocalDateTime.of(2022, 6, 15,0,0));
        item1 = Item.newInstance("name1",100, UserType.GENERAL,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 3, 3,0,0));
        item2 = Item.newInstance("name2",100, UserType.CORPORATE,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 8, 3,0,0));
    }

    @Test
    @DisplayName("addPromotionItem 테스트")
    void addPromotionItem_테스트(){
        List<Promotion> promotions1 = new ArrayList<>();
        List<Promotion> promotions2 = new ArrayList<>();

        promotions1.add(promotion1);
        promotions2.add(promotion2);
        promotions2.add(promotion3);

        item1.addPromotionItems(promotions1);
        item2.addPromotionItems(promotions2);

        Assertions.assertAll(()->{
            Assertions.assertEquals(item1.getPromotionItems().size(),1);
            Assertions.assertEquals(item2.getPromotionItems().size(),2);
            for (PromotionItem promotionItem : item2.getPromotionItems()) {
                Assertions.assertNotEquals(promotionItem.getPromotion(), promotion1);
            }
        });
    }

}