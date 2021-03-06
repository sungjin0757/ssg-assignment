package com.ssgassignment.productinfoapi.controller;

import com.ssgassignment.productinfoapi.common.constatants.SecurityConstants;
import com.ssgassignment.productinfoapi.common.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.common.exception.ParameterException;
import com.ssgassignment.productinfoapi.service.PromotionService;
import com.ssgassignment.productinfoapi.vo.RequestPromotion;
import com.ssgassignment.productinfoapi.vo.ResponsePromotion;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping(UrlConstants.PROMOTION_BASE)
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConstants.SWAGGER_KEY)
public class PromotionApiController {
    private final PromotionService promotionService;

    @Operation(summary = "프로모션 등록")
    @PostMapping(UrlConstants.SAVE)
    public ResponseEntity<ResponsePromotion> createPromotion(@Valid @RequestBody RequestPromotion requestPromotion,
                                                             BindingResult result){
        String promotionName = requestPromotion.getPromotionName();
        Integer discountAccount = requestPromotion.getDiscountAccount();
        Double discountRate = requestPromotion.getDiscountRate();
        LocalDateTime promotionStartDate = requestPromotion.getPromotionStartDate();
        LocalDateTime promotionEndDate = requestPromotion.getPromotionEndDate();

        if(discountRate == null && discountAccount == null){
            result.reject("BothAccountAndRate","할인 금액과 할인율은 둘 다 0이 될 수 없습니다.");
        }
        if(result.hasErrors()){
            throw new ParameterException(result);
        }

        Long promotionId = promotionService.savePromotion(new PromotionDto(promotionName, discountAccount, discountRate,
                promotionStartDate, promotionEndDate));
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponsePromotion(promotionId, promotionName,
                discountAccount, discountRate, promotionStartDate, promotionEndDate));
    }

    @Operation(summary = "프로모션 삭제")
    @DeleteMapping(UrlConstants.DELETE+UrlConstants.ID)
    public ResponseEntity<String> deletePromotion(@PathVariable("id")Long id){
        promotionService.deletePromotion(id);

        return ResponseEntity.status(HttpStatus.OK).body("Delete Completed");
    }
}
