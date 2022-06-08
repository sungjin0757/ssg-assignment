package com.ssgassignment.productinfoapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class PromotionDto {
    private String promotionName;
    private Integer discountAccount;
    private Double discountRate;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;
}
