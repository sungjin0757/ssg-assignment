package com.ssgassignment.productinfoapi.common;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import com.ssgassignment.productinfoapi.service.ItemService;
import com.ssgassignment.productinfoapi.service.PromotionService;
import com.ssgassignment.productinfoapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class initDb {

    private final UserService userService;
    private final ItemService itemService;
    private final PromotionService promotionService;
    private final UserRepository userRepository;

    @PostConstruct
    public void init(){
        Long userId = userService.join(new UserDto("lee@naver.com", "123", "이수경", UserType.GENERAL));
        userService.join(new UserDto("choi@naver.com", "123", "최상면", UserType.CORPORATE));
        userService.join(new UserDto("kang@naver.com", "123", "강재석", UserType.GENERAL));
        userService.join(new UserDto("kim@naver.com", "123", "김구현", UserType.GENERAL));

        User findUser = userRepository.findById(userId).get();
        findUser.userWithDrawl();
        userRepository.save(findUser);

        itemService.saveItem(new ItemDto("노브랜드 버터링",20000, UserType.GENERAL, LocalDateTime.of(2022,1,1,0,0,0),
                LocalDateTime.of(2023,1,1,23,59,59)));
        itemService.saveItem(new ItemDto("매일 아침 우유",1000, UserType.GENERAL, LocalDateTime.of(2021,1,1,0,0,0),
                LocalDateTime.of(2023,5,5,23,59,59)));
        itemService.saveItem(new ItemDto("나이키 운동화",40000, UserType.CORPORATE, LocalDateTime.of(2020,1,1,0,0,0),
                LocalDateTime.of(2023,12,31,23,59,59)));
        itemService.saveItem(new ItemDto("스타벅스 써머 텀블러",15000, UserType.GENERAL, LocalDateTime.of(2021,1,1,0,0,0),
                LocalDateTime.of(2021,8,1,23,59,59)));
        itemService.saveItem(new ItemDto("크리스마스 케이크",30000, UserType.GENERAL, LocalDateTime.of(2022,12,24,0,0,0),
                LocalDateTime.of(2023,12,31,23,59,59)));

        promotionService.savePromotion(new PromotionDto("2022 쓱데이",1000,null,LocalDateTime.of(2022,5,1,0,0,0),
                LocalDateTime.of(2022,7,1,23,59,59)));
        promotionService.savePromotion(new PromotionDto("스타벅스몰 오픈기념",null,0.05,LocalDateTime.of(2021,1,5,0,0,0),
                LocalDateTime.of(2022,12,31,23,59,59)));
        promotionService.savePromotion(new PromotionDto("2021 쓱데이",2000,null,LocalDateTime.of(2021,1,1,0,0,0),
                LocalDateTime.of(2021,1,31,23,59,59)));
    }
}
