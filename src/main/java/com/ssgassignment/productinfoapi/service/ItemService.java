package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.ItemWithPromotionDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> orderAbleItems(Long userId);
    Long saveItem(ItemDto itemDto);
    ItemWithPromotionDto findItemWithPromotions(Long itemId);
}
