package com.ssgassignment.productinfoapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ResponseItem {
    private Long itemId;
    private String itemName;
    private int itemPrice;
    private String itemType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime itemDisplayStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime itemDisplayEndDate;

    public ResponseItem(Long itemId, String itemName, int itemPrice, UserType itemType, LocalDateTime itemDisplayStartDate,
                        LocalDateTime itemDisplayEndDate) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemType = itemType == UserType.GENERAL ? "일반" : "기업 회원 상품";
        this.itemDisplayStartDate = itemDisplayStartDate;
        this.itemDisplayEndDate = itemDisplayEndDate;
    }
}
