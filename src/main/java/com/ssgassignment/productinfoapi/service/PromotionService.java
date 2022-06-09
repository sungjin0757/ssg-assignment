package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.dto.PromotionDto;

public interface PromotionService {
    Long savePromotion(PromotionDto promotionDto);
    void deletePromotion(Long promotionId);
}
