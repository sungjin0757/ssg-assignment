package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.dto.ItemDto;

import java.util.List;

public interface ItemService {
    List<ItemDto> OrderAbleItems(Long userId);
}
