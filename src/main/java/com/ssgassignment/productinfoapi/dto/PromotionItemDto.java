package com.ssgassignment.productinfoapi.dto;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.PromotionItem;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PromotionItemDto {
    private String promotionName;
    private Integer discountAccount;
    private Double discountRate;
    private LocalDateTime promotionStartDate;
    private LocalDateTime promotionEndDate;

    public PromotionItemDto(PromotionItem promotionItem){
        Promotion promotion = promotionItem.getPromotion();
        this.promotionName = promotion.getPromotionName();
        this.discountAccount = promotion.getDiscountAccount();
        this.discountRate = promotion.getDiscountRate();
        this.promotionStartDate = promotion.getPromotionStartDate();
        this.promotionEndDate = promotion.getPromotionEndDate();
    }
}