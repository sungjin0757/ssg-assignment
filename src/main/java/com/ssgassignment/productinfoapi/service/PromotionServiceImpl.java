package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.PromotionItem;
import com.ssgassignment.productinfoapi.dto.PromotionDto;
import com.ssgassignment.productinfoapi.exception.NotFoundPromotionException;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.repository.PromotionRepository;
import com.ssgassignment.productinfoapi.service.staticvals.CheckTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PromotionServiceImpl implements PromotionService{
    private final PromotionRepository promotionRepository;
    private final ItemRepository itemRepository;

    @Transactional
    @Override
    public Long savePromotion(PromotionDto promotionDto) {
        CheckTime.checkDateTime(promotionDto.getPromotionStartDate(), promotionDto.getPromotionEndDate());
        Promotion promotion = Promotion.newInstance(promotionDto.getPromotionName(),
                promotionDto.getDiscountAccount(), promotionDto.getDiscountRate(),
                promotionDto.getPromotionStartDate(), promotionDto.getPromotionEndDate());
        promotionRepository.save(promotion);
        List<Item> promotionableItems = itemRepository.findPromotionableItems(promotion.getPromotionStartDate(),
                promotion.getPromotionEndDate());
        for (Item item : promotionableItems) {
            item.getPromotionItems().add(PromotionItem.newInstance(item, promotion));
        }

        return promotion.getPromotionId();
    }

    @Override
    public void deletePromotion(Long promotionId) {
        try{
            promotionRepository.deleteById(promotionId);
        }catch (Exception e){
            throw new NotFoundPromotionException("해당 프로모션이 존재하지 않습니다.");
        }
    }
}
