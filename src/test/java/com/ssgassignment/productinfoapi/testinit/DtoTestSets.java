package com.ssgassignment.productinfoapi.testinit;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.dto.UserDto;

import java.time.LocalDateTime;

public abstract class DtoTestSets {
    /**
     * item
     */
    public static final ItemDto ITEM_DTO1 = new ItemDto("name1",100, UserType.GENERAL,
            LocalDateTime.of(2022, 2, 1,0,0),
            LocalDateTime.of(2023, 3, 3,0,0));
    public static final ItemDto ITEM_DTO2= new ItemDto("name2",100, UserType.CORPORATE,
            LocalDateTime.of(2022, 2, 4,0,0),
            LocalDateTime.of(2022, 2, 5,0,0));
    public static final ItemDto ITEM_DTO3 = new ItemDto("name3",100, UserType.GENERAL,
            LocalDateTime.of(2022, 3, 4,0,0),
            LocalDateTime.of(2022, 8, 16,0,0));
    public static final ItemDto ITEM_DTO4 = new ItemDto("name3",100, UserType.GENERAL,
            LocalDateTime.of(2022, 3, 4,0,0),
            LocalDateTime.of(2021, 6, 16,0,0));
    public static final ItemDto ITEM_DTO5 = new ItemDto("name2",10000, UserType.CORPORATE,
            LocalDateTime.of(2022, 2, 4,0,0),
            LocalDateTime.of(2022, 8, 5,0,0));
    public static final ItemDto ITEM_DTO6 = new ItemDto("name2",10000, UserType.CORPORATE,
            LocalDateTime.of(2021, 2, 4,0,0),
            LocalDateTime.of(2021, 8, 5,0,0));

    /**
     * promotion
     */
    public static final PromotionDto PROMOTION_DTO1 = new PromotionDto(
            "name1", 1000, 0.1,
            LocalDateTime.of(2022, 2, 3,0,0),
            LocalDateTime.of(2022, 3, 3,0,0));
    public static final PromotionDto PROMOTION_DTO2 = new PromotionDto(
            "name2", 1000, 0.1,
            LocalDateTime.of(2022, 2, 3,0,0),
            LocalDateTime.of(2022, 8, 3,0,0));
    public static final PromotionDto PROMOTION_DTO3 = new PromotionDto(
            "nam3", 1000, 0.1,
            LocalDateTime.of(2022, 6, 3,0,0),
            LocalDateTime.of(2022, 8, 15,0,0));

    /**
     * user
     */
    public static final UserDto USER_DTO1 = new UserDto("name1", "1234", "abc", UserType.GENERAL);
    public static final UserDto USER_DTO2 = new UserDto("name2", "12334", "abc", UserType.CORPORATE);
    public static final UserDto USER_DTO3 = new UserDto("sungjin@naver.com", "1234", "abc", UserType.GENERAL);
}
