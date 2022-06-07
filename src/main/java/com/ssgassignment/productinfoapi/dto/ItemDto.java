package com.ssgassignment.productinfoapi.dto;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ItemDto {
    private String itemName;
    private int itemPrice;
    private UserType itemType;
    private LocalDateTime itemDisplayStartDate;
    private LocalDateTime itemDisplayEndDate;
}
