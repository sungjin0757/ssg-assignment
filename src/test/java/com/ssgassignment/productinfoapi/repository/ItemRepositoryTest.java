package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
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

@DataJpaTest
@Transactional
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    Item item1;
    Item item2;
    Item item3;

    @BeforeEach
    void setUp(){
        item1 = Item.newInstance("name1",100, UserType.GENERAL,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 3, 3,0,0));
        item2 = Item.newInstance("name2",100, UserType.CORPORATE,
                LocalDateTime.of(2022, 2, 3,0,0),
                LocalDateTime.of(2022, 8, 3,0,0));
        item3 = Item.newInstance("name3",100, UserType.GENERAL,
                LocalDateTime.of(2022, 6, 3,0,0),
                LocalDateTime.of(2022, 8, 15,0,0));
    }

    @Test
    @DisplayName("생성일자 자동생성")
    void 생성일자_자동생성(){
        Item item = itemRepository.save(item1);
        Assertions.assertAll(()->{
            Assertions.assertNotNull(item.getCreatedDate());
            Assertions.assertNotNull(item.getUpdatedDate());
        });
    }

    @Test
    @DisplayName("주문 가능 상품 조회")
    void 주문_가능_상품_조회_테스트(){
        Item saveItem1 = itemRepository.save(item1);
        Item saveItem2 = itemRepository.save(item2);
        Item saveItem3 = itemRepository.save(item3);

        List<Item> ordableItems1 = itemRepository.findOrdableItems(UserType.GENERAL);
        List<Item> ordableItems2 = itemRepository.findOrdableItems(UserType.CORPORATE);

        Assertions.assertAll(()->{
           Assertions.assertEquals(ordableItems1.size(),generalItemInTime());
           Assertions.assertEquals(ordableItems2.size(),generalItemInTime() + corporateItemInTime());
           for (Item item : ordableItems1) {
               Assertions.assertEquals(item.getItemType(), UserType.GENERAL);
               Assertions.assertNotEquals(item, saveItem1);
               Assertions.assertNotEquals(item, saveItem2);
           }
           for (Item item : ordableItems2) {
               Assertions.assertNotEquals(item, saveItem1);
           }
        });
    }

    @Test
    @DisplayName("findEnableItems 테스트")
    void find_Enable_Items_테스트(){
        Item saveItem1 = itemRepository.save(item1);
        Item saveItem2 = itemRepository.save(item2);
        Item saveItem3 = itemRepository.save(item3);

        List<Item> enableItems1 = itemRepository.findPromotionableItems(
                LocalDateTime.of(2021, 6, 2, 0, 0),
                LocalDateTime.of(2022, 12, 2, 0, 0));
        List<Item> enableItems2 = itemRepository.findPromotionableItems(
                LocalDateTime.of(2022, 6, 2, 0, 0),
                LocalDateTime.of(2022, 12, 2, 0, 0));
        List<Item> enableItems3 = itemRepository.findPromotionableItems(
                LocalDateTime.of(2021, 6, 2, 0, 0),
                LocalDateTime.of(2022, 6, 2, 0, 0));

        Assertions.assertAll(()->{
            Assertions.assertEquals(enableItems1.size(),3);
            Assertions.assertEquals(enableItems2.size(),2);
            Assertions.assertEquals(enableItems3.size(),2);
            for (Item item : enableItems2) {
                Assertions.assertNotEquals(item, item1);
            }
            for (Item item : enableItems3) {
                Assertions.assertNotEquals(item, item3);
            }
        });
    }

    private boolean checkTime(LocalDateTime start, LocalDateTime end){
        LocalDateTime now = LocalDateTime.now();
        if(start.isAfter(now) || end.isBefore(now)){
            return false;
        }
        return true;
    }

    private int generalItemInTime(){
        int cnt = 0;
        if(checkTime(item1.getItemDisplayStartDate(),item1.getItemDisplayEndDate())){
            cnt++;
        }
        if(checkTime(item3.getItemDisplayStartDate(),item3.getItemDisplayEndDate())){
            cnt++;
        }
        return cnt;
    }

    private int corporateItemInTime(){
        if(checkTime(item2.getItemDisplayStartDate(),item2.getItemDisplayEndDate())){
            return 1;
        }
        return 0;
    }
}