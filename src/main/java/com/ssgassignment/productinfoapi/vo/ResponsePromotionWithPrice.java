package com.ssgassignment.productinfoapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssgassignment.productinfoapi.dto.PromotionItemDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponsePromotionWithPrice {
    private String promotionName;
    private Integer discountAccount;
    private Double discountRate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime promotionStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime promotionEndDate;
    private int prePromotionPrice;
    private int afterPromotionPrice;

    public ResponsePromotionWithPrice(PromotionItemDto dto, int itemPrice, int afterPromotionPrice) {
        this.promotionName = dto.getPromotionName();
        this.discountAccount = dto.getDiscountAccount();
        this.discountRate = dto.getDiscountRate();
        this.promotionStartDate = dto.getPromotionStartDate();
        this.promotionEndDate = dto.getPromotionEndDate();
        this.prePromotionPrice = itemPrice;
        this.afterPromotionPrice = afterPromotionPrice;
    }
}
