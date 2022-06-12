package com.ssgassignment.productinfoapi.common;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.repository.PromotionRepository;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class InitDb {

    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final PromotionRepository promotionRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){


        User lee = User.newInstance("lee@naver.com", passwordEncoder.encode("123"),
                "이수경", UserType.GENERAL);
        lee.userWithDrawl();
        userRepository.save(lee);
        userRepository.save(User.newInstance("choi@naver.com", passwordEncoder.encode("123"),
                "최상면", UserType.CORPORATE));
        userRepository.save(User.newInstance("kang@naver.com", passwordEncoder.encode("123"),
                "강재석", UserType.GENERAL));
        userRepository.save(User.newInstance("kim@naver.com", passwordEncoder.encode("123"),
                "김구현", UserType.GENERAL));


        Promotion promotion1 = promotionRepository.save(Promotion.newInstance("2022 쓱데이", 1000, 0, LocalDateTime.of(2022, 5, 1, 0, 0, 0),
                LocalDateTime.of(2022, 7, 1, 23, 59, 59)));
        Promotion promotion2 = promotionRepository.save(Promotion.newInstance("스타벅스몰 오픈기념", 0, 0.05, LocalDateTime.of(2021, 1, 5, 0, 0, 0),
                LocalDateTime.of(2022, 12, 31, 23, 59, 59)));
        Promotion promotion3 = promotionRepository.save(Promotion.newInstance("2021 쓱데이", 2000, 0, LocalDateTime.of(2021, 1, 1, 0, 0, 0),
                LocalDateTime.of(2021, 1, 31, 23, 59, 59)));

        Item item1 = Item.newInstance("노브랜드 버터링", 20000, UserType.GENERAL, LocalDateTime.of(2022, 1, 1, 0, 0, 0),
                LocalDateTime.of(2023, 1, 1, 23, 59, 59));
        Item item2 = Item.newInstance("매일 아침 우유", 1000, UserType.GENERAL, LocalDateTime.of(2021, 1, 1, 0, 0, 0),
                LocalDateTime.of(2023, 5, 5, 23, 59, 59));
        Item item3 = Item.newInstance("나이키 운동화", 40000, UserType.CORPORATE, LocalDateTime.of(2020, 1, 1, 0, 0, 0),
                LocalDateTime.of(2023, 12, 31, 23, 59, 59));
        Item item4 = Item.newInstance("스타벅스 써머 텀블러", 15000, UserType.GENERAL, LocalDateTime.of(2021, 1, 1, 0, 0, 0),
                LocalDateTime.of(2021, 8, 1, 23, 59, 59));
        Item item5 = Item.newInstance("크리스마스 케이크", 30000, UserType.GENERAL, LocalDateTime.of(2022, 12, 24, 0, 0, 0),
                LocalDateTime.of(2023, 12, 31, 23, 59, 59));

        List<Promotion> promotions1 = new ArrayList<>();
        promotions1.add(promotion1);
        promotions1.add(promotion3);
        item1.addPromotionItems(promotions1);
        item2.addPromotionItems(promotions1);
        item3.addPromotionItems(promotions1);
        List<Promotion> promotions2 = new ArrayList<>();
        promotions2.add(promotion1);
        promotions2.add(promotion2);
        item4.addPromotionItems(promotions2);
        List<Promotion> promotions3 = new ArrayList<>();
        promotions3.add(promotion1);
        item5.addPromotionItems(promotions3);

        itemRepository.save(item1);
        itemRepository.save(item2);
        itemRepository.save(item3);
        itemRepository.save(item4);
        itemRepository.save(item5);

    }
}
