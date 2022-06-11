package com.ssgassignment.productinfoapi.dto;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ItemDto {
    private Long itemId;
    private String itemName;
    private int itemPrice;
    private UserType itemType;
    private LocalDateTime itemDisplayStartDate;
    private LocalDateTime itemDisplayEndDate;

    public ItemDto(Long itemId, String itemName, int itemPrice, UserType itemType,
                   LocalDateTime itemDisplayStartDate, LocalDateTime itemDisplayEndDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.itemDisplayStartDate = itemDisplayStartDate;
        this.itemDisplayEndDate = itemDisplayEndDate;
    }

    public ItemDto(String itemName, int itemPrice, UserType itemType, LocalDateTime itemDisplayStartDate,
                   LocalDateTime itemDisplayEndDate) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType;
        this.itemDisplayStartDate = itemDisplayStartDate;
        this.itemDisplayEndDate = itemDisplayEndDate;
    }
}
