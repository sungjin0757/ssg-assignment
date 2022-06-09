package com.ssgassignment.productinfoapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.ItemWithPromotionDto;
import com.ssgassignment.productinfoapi.dto.PromotionItemDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Data
public class ResponseItemWithPromotion {
    private Long itemId;
    private String itemName;
    private UserType itemType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime itemDisplayStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime itemDisplayEndDate;
    private ResponsePromotionWithPrice promotion;

    public ResponseItemWithPromotion(ItemWithPromotionDto dto) {
        this.itemId = dto.getItemId();
        this.itemName = dto.getItemName();
        this.itemType = dto.getItemType();
        this.itemDisplayStartDate = dto.getItemDisplayStartDate();
        this.itemDisplayEndDate = dto.getItemDisplayEndDate();
        PromotionItemDto p = updateOptimalPromotion(dto.getPromotionItems(), dto.getItemPrice());
        this.promotion = p!=null ? new ResponsePromotionWithPrice(p, dto.getItemPrice(),
                afterPromotion(dto.getItemPrice(), p.getDiscountAccount(), p.getDiscountRate())) : null;

    }

    private PromotionItemDto updateOptimalPromotion(List<PromotionItemDto> promotions, int itemPrice){
        PriorityQueue<PromotionItemDto> dq = new PriorityQueue<>(
                Comparator.comparingInt(o -> afterPromotion(itemPrice, o.getDiscountAccount(), o.getDiscountRate())));
        for (PromotionItemDto promotionItemDto : promotions) {
            dq.offer(promotionItemDto);
        }
        return dq.poll();
    }

    private int afterPromotion(int price, int discountAccount, Double discountRate){
        if(discountAccount ==0 && discountRate ==null){
            return price;
        }
        int priceAccount = price-discountAccount;
        if(discountRate == null){
            return priceAccount > 0 ? priceAccount : price;
        }
        int priceRate =(int) (((double)price)*(1.0 - discountRate));
        return priceAccount > priceRate ?
                (priceRate >0 ? priceRate : (priceAccount >0 ? priceAccount : price)) :
                (priceAccount >0 ? priceAccount : (priceRate >0 ? priceRate : price));
    }

}
