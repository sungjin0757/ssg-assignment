package com.ssgassignment.productinfoapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestItem {
    @NotNull(message = "itemNaem Cannot Be Null")
    private String itemName;
    @NotNull(message = "itemPrice Cannot Be Null")
    private int itemPrice;
    @NotNull(message = "itemType Cannot Be NUll")
    @Schema(example = "Press CORPORATE Or GENERAL")
    private UserType itemType;
    @NotNull(message = "itemDisplayStartDate Cannot Be Null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime itemDisplayStartDate;
    @NotNull(message = "itemDisplayEndDate Cannot Be Null")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime itemDisplayEndDate;
}
