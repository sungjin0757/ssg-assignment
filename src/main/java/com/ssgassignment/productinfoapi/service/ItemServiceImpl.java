package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.Promotion;
import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.domain.enumeration.UserStat;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.dto.ItemWithPromotionDto;
import com.ssgassignment.productinfoapi.common.exception.DisabledUserException;
import com.ssgassignment.productinfoapi.common.exception.NotFoundItemException;
import com.ssgassignment.productinfoapi.common.exception.NotFoundUserException;
import com.ssgassignment.productinfoapi.common.exception.NotValidTimeException;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.repository.PromotionRepository;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import com.ssgassignment.productinfoapi.service.util.CheckTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final PromotionRepository promotionRepository;

    @Override
    public List<ItemDto> orderAbleItems(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(()->{
            throw new NotFoundUserException("해당 회원이 존재하지 않습니다");
        });
        checkDisableUser(findUser);
        return itemRepository.findOrdableItems(findUser.getUserType()).stream().map(i -> new ItemDto(i.getItemId(),
                        i.getItemName(),
                i.getItemPrice(), i.getItemType(), i.getItemDisplayStartDate(), i.getItemDisplayEndDate()))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long saveItem(ItemDto itemDto) {
        CheckTime.checkDateTime(itemDto.getItemDisplayStartDate(), itemDto.getItemDisplayEndDate());
        Item item = Item.newInstance(itemDto.getItemName(), itemDto.getItemPrice(), itemDto.getItemType(),
                itemDto.getItemDisplayStartDate(), itemDto.getItemDisplayEndDate());
        List<Promotion> enablePromotions = promotionRepository.findEnablePromotions(
                item.getItemDisplayStartDate(), item.getItemDisplayEndDate());
        item.addPromotionItems(enablePromotions);

        return itemRepository.save(item).getItemId();
    }

    @Override
    public ItemWithPromotionDto findItemWithPromotions(Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> {
            throw new NotFoundItemException("해당 아이템이 존재하지 않습니다.");
        });
        checkCurrentDate(item.getItemDisplayStartDate(), item.getItemDisplayEndDate());

        return new ItemWithPromotionDto(item);
    }

    @Transactional
    @Override
    public void deleteItem(Long itemId) {
        try{
            itemRepository.deleteById(itemId);
        }catch (Exception e){
            throw new NotFoundItemException("해당 아이템이 존재하지 않습니다.");
        }
    }

    private void checkDisableUser(User user){
        if(user.getUserStat().equals(UserStat.DISABLED)){
            throw new DisabledUserException("탈퇴한 회원은 상품을 조회할 수 없습니다.");
        }
    }

    private void checkCurrentDate(LocalDateTime startDate, LocalDateTime endDate){
        LocalDateTime now = LocalDateTime.now();
        if(startDate.isAfter(now) || endDate.isBefore(now)){
            throw new NotValidTimeException("해당 아이템의 조회 기간이 아닙니다.");
        }
    }

}
