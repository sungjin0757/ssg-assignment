package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.Promotion;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
class PromotionRepositoryTest {
    @Autowired
    PromotionRepository promotionRepository;

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
    }

    @Test
    @DisplayName("생성일자 자동생성")
    void 생성일자_자동생성(){
        Promotion promotion = promotionRepository.save(promotion1);
        Assertions.assertAll(()->{
            Assertions.assertNotNull(promotion.getCreatedDate());
            Assertions.assertNotNull(promotion.getUpdatedDate());
        });
    }

    @Test
    @DisplayName("findEnablePromotions 테스트")
    void findEnablePromotions_테스트(){
        Promotion pm1 = promotionRepository.save(promotion1);
        Promotion pm2 = promotionRepository.save(promotion2);
        Promotion pm3 = promotionRepository.save(promotion3);

        List<Promotion> enablePromotions1 = promotionRepository.findEnablePromotions(
                LocalDateTime.of(2021, 6, 2,0,0),
                LocalDateTime.of(2022, 12, 2,0,0));
        List<Promotion> enablePromotions2 = promotionRepository.findEnablePromotions(
                LocalDateTime.of(2022, 6, 2,0,0),
                LocalDateTime.of(2022, 12, 2,0,0));
        List<Promotion> enablePromotions3 = promotionRepository.findEnablePromotions(
                LocalDateTime.of(2021, 6, 2,0,0),
                LocalDateTime.of(2022, 6, 2,0,0));

        Assertions.assertAll(()->{
            Assertions.assertEquals(enablePromotions1.size(),3);
            Assertions.assertEquals(enablePromotions2.size(),2);
            Assertions.assertEquals(enablePromotions3.size(),2);

            for (Promotion promotion : enablePromotions2) {
                Assertions.assertNotEquals(promotion, promotion1);
            }
            for (Promotion promotion : enablePromotions3) {
                Assertions.assertNotEquals(promotion, promotion3);
            }
        });
    }
}