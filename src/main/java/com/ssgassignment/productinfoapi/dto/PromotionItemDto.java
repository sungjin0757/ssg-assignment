package com.ssgassignment.productinfoapi.dto;

import com.ssgassignment.productinfoapi.domain.Promotion;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromotionItemDto {
    private String promotionName;
    private Integer discountAccount;
    private Double discountRate;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;

    public PromotionItemDto(Promotion promotion){
        this.promotionName = promotion.getPromotionName();
        this.discountAccount = promotion.getDiscountAccount();
        this.discountRate = promotion.getDiscountRate();
        this.promotionStartDate = promotion.getPromotionStartDate();
        this.promotionEndDate = promotion.getPromotionEndDate();
    }
}
