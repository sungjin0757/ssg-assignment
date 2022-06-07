package com.ssgassignment.productinfoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ItemDto {
    private String itemName;
    private int itemPrice;
    private LocalDateTime itemDisplayStartDate;
    private LocalDateTime itemDisplayEndDate;
}
