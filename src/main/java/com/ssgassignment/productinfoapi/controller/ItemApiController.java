package com.ssgassignment.productinfoapi.controller;

import com.ssgassignment.productinfoapi.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.ItemWithPromotionDto;
import com.ssgassignment.productinfoapi.service.ItemService;
import com.ssgassignment.productinfoapi.vo.RequestItem;
import com.ssgassignment.productinfoapi.vo.ResponseItem;
import com.ssgassignment.productinfoapi.vo.ResponseItemWithPromotion;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(UrlConstants.ITEM_BASE)
@RequiredArgsConstructor
public class ItemApiController {
    private final ItemService itemService;

    @PostMapping(UrlConstants.SAVE)
    public ResponseEntity<ResponseItem> createItem(@Valid @RequestBody RequestItem requestItem){
        String itemName = requestItem.getItemName();
        int itemPrice = requestItem.getItemPrice();
        UserType itemType = requestItem.getItemType();
        LocalDateTime itemDisplayStartDate = requestItem.getItemDisplayStartDate();
        LocalDateTime itemDisplayEndDate = requestItem.getItemDisplayEndDate();

        Long itemId = itemService.saveItem(new ItemDto(itemName, itemPrice,
                itemType, itemDisplayStartDate, itemDisplayEndDate));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseItem(itemId, itemName,
                itemPrice, itemType, itemDisplayStartDate, itemDisplayEndDate));
    }

    @DeleteMapping(UrlConstants.DELETE+UrlConstants.ID)
    public ResponseEntity<String> deleteItem(@PathVariable("id")Long id){
        itemService.deleteItem(id);

        return ResponseEntity.status(HttpStatus.OK).body("Delete Completed");
    }

    @GetMapping(UrlConstants.USER+UrlConstants.ID)
    public ResponseEntity<List<ResponseItem>> itemInfos(@PathVariable("id") Long userId){
        List<ItemDto> itemDtos = itemService.orderAbleItems(userId);

        return ResponseEntity.status(HttpStatus.OK).body(itemDtos.stream()
                .map(i -> new ResponseItem(i.getItemId(), i.getItemName(),
                i.getItemPrice() ,i.getItemType(),
                i.getItemDisplayStartDate(), i.getItemDisplayEndDate())).collect(Collectors.toList()));
    }

    @GetMapping(UrlConstants.PROMOTION+UrlConstants.ID)
    public ResponseEntity<ResponseItemWithPromotion> itemWithPromotionInfos(@PathVariable("id") Long itemId){
        ItemWithPromotionDto findItem = itemService.findItemWithPromotions(itemId);

        return ResponseEntity.status(HttpStatus.OK).body(new ResponseItemWithPromotion(findItem));
    }

}
