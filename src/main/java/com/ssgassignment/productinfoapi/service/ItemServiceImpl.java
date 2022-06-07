package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.domain.enumeration.UserStat;
import com.ssgassignment.productinfoapi.dto.ItemDto;
import com.ssgassignment.productinfoapi.exception.DisabledUserException;
import com.ssgassignment.productinfoapi.exception.NotFoundUserException;
import com.ssgassignment.productinfoapi.repository.ItemRepository;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{
    private final ItemRepository itemRepository;
    private final UserRepository userRepository;

    @Override
    public List<ItemDto> OrderAbleItems(Long userId) {
        User findUser = userRepository.findById(userId).orElseThrow(()->{
            throw new NotFoundUserException("해당 회원이 존재하지 않습니다");
        });
        checkDisableUser(findUser);
        return itemRepository.findOrdableItems(findUser.getUserType()).stream().map(i -> new ItemDto(i.getItemName(),
                i.getItemPrice(), i.getItemDisplayStartDate(), i.getItemDisplayEndDate()))
                .collect(Collectors.toList());
    }

    private void checkDisableUser(User user){
        if(user.getUserStat().equals(UserStat.DISABLED)){
            throw new DisabledUserException("탈퇴한 회원은 상품을 조회할 수 없습니다.");
        }
    }
}