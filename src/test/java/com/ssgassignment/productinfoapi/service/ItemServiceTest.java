package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.ItemWithPromotionDto;
import com.ssgassignment.productinfoapi.common.exception.DisabledUserException;
import com.ssgassignment.productinfoapi.common.exception.NotFoundItemException;
import com.ssgassignment.productinfoapi.common.exception.NotFoundUserException;
import com.ssgassignment.productinfoapi.common.exception.NotValidTimeException;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.repository.PromotionRepository;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import com.ssgassignment.productinfoapi.testinit.DtoTestSets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@SpringBootTest
@Transactional
class ItemServiceTest {
    @Autowired
    ItemService itemService;
    @Autowired
    ItemRepository itemRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    PromotionRepository promotionRepository;

    User user1;
    User user2;
    User user3;
    Promotion promotion1;
    Promotion promotion2;
    Promotion promotion3;
    ItemDto itemDto1;
    ItemDto itemDto2;
    ItemDto itemDto3;
    ItemDto itemDto4;

    @BeforeEach
    void setUp(){
        user1 = User.newInstance("sungjin@naver.com", "1234", "Hong", UserType.GENERAL);
        user2 = User.newInstance("sungjin12@naver.com", "1234", "Hong", UserType.GENERAL);
        user3 = User.newInstance("sungjin123@naver.com", "1234", "Hong", UserType.GENERAL);
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
        itemDto1 = DtoTestSets.ITEM_DTO1;
        itemDto2 = DtoTestSets.ITEM_DTO2;
        itemDto3 = DtoTestSets.ITEM_DTO3;
        itemDto4 = DtoTestSets.ITEM_DTO4;
    }

    @Test
    @DisplayName("orderAbleItems Exception Test")
    void orderAbleItems_예외_테스트(){
        user3.userWithDrawl();
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);

        Assertions.assertAll(()->{
            Assertions.assertThrows(DisabledUserException.class, ()->{
                itemService.orderAbleItems(user3.getUserId());
            });
            Assertions.assertThrows(NotFoundUserException.class, ()->{
                itemService.orderAbleItems(-99l);
            });
        });
    }

    @Test
    @DisplayName("Item Save Test")
    void 아이템_저장_테스트(){
        promotionRepository.save(promotion1);
        promotionRepository.save(promotion2);
        promotionRepository.save(promotion3);

        Long itemId1 = itemService.saveItem(itemDto1);
        Long itemId2 = itemService.saveItem(itemDto2);
        Long itemId3 = itemService.saveItem(itemDto3);

        Assertions.assertAll(()->{
            Assertions.assertEquals(itemRepository.findById(itemId1).get().getPromotionItems().size(),5);
            Assertions.assertEquals(itemRepository.findById(itemId2).get().getPromotionItems().size(),3);
            Assertions.assertEquals(itemRepository.findById(itemId3).get().getPromotionItems().size(),4);
            Assertions.assertThrows(NotValidTimeException.class, ()->{
                itemService.saveItem(itemDto4);
            });
        });
    }

    @Test
    @DisplayName("findItemWithPromotions Test")
    void findItemWithPromotions_테스트(){
        promotionRepository.save(promotion1);
        promotionRepository.save(promotion2);
        promotionRepository.save(promotion3);

        Long itemId1 = itemService.saveItem(itemDto1);
        Long itemId2 = itemService.saveItem(itemDto2);
        Long itemId3 = itemService.saveItem(itemDto3);

        ItemWithPromotionDto findItem1 = itemService.findItemWithPromotions(itemId1);
        ItemWithPromotionDto findItem3 = itemService.findItemWithPromotions(itemId3);

        Assertions.assertAll(()->{
            Assertions.assertThrows(NotValidTimeException.class, ()->{
                itemService.findItemWithPromotions(itemId2);
            });
            Assertions.assertThrows(NotFoundItemException.class,()->{
               itemService.findItemWithPromotions(-99l) ;
            });
            Assertions.assertEquals(findItem1.getPromotionItems().size(),4);
            Assertions.assertEquals(findItem3.getPromotionItems().size(),4);
        });
    }

    @Test
    @DisplayName("DeleteItem Test")
    void deleteItem_테스트(){
        Assertions.assertThrows(NotFoundItemException.class, ()->{
           itemService.deleteItem(-99l);
        });

        Long itemId = itemService.saveItem(itemDto1);
        itemService.deleteItem(itemId);

        Assertions.assertEquals(itemRepository.findById(itemId), Optional.empty());
    }
}