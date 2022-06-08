package com.ssgassignment.productinfoapi.dto;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.PromotionItem;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class ItemWithPromotionDto {
    private String itemName;
    private int itemPrice;
    private UserType itemType;
    private LocalDateTime itemDisplayStartDate;
    private LocalDateTime itemDisplayEndDate;
    private List<PromotionItemDto> promotionItems;

    public ItemWithPromotionDto(Item item){
        this.itemName = item.getItemName();
        this.itemPrice = item.getItemPrice();
        this.itemType = item.getItemType();
        this.itemDisplayStartDate = item.getItemDisplayStartDate();
        this.itemDisplayEndDate = item.getItemDisplayEndDate();
        this.promotionItems = item.getPromotionItems().stream().map(i -> new PromotionItemDto(i))
                .collect(Collectors.toList());
    }
}
