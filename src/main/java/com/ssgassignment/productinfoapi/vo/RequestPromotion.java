package com.ssgassignment.productinfoapi.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class RequestPromotion {
    @NotNull(message = "PromotionName Cannot Be Null")
    private String promotionName;
    private Integer discountAccount;
    private Double discountRate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime promotionStartDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    @Schema(example = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime promotionEndDate;
}
