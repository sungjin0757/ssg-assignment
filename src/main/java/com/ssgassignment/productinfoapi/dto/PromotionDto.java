package com.ssgassignment.productinfoapi.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromotionDto {
    private String promotionName;
    private Integer discountAccount;
    private Double discountRate;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;

    public PromotionDto(String promotionName, Integer discountAccount, Double discountRate,
                        LocalDateTime promotionStartDate, LocalDateTime promotionEndDate) {
        this.promotionName = promotionName;
        this.discountAccount = discountAccount == null ? 0 : discountAccount;
        this.discountRate = discountRate == null ? 0 : discountRate;
        this.promotionStartDate = promotionStartDate;
        this.promotionEndDate = promotionEndDate;
    }
}
