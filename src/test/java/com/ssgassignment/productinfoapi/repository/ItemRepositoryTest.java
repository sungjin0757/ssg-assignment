package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@Transactional
@EnableJpaAuditing
class ItemRepositoryTest {
    @Autowired
    ItemRepository itemRepository;

    @Test
    @DisplayName("생성일자 자동생성")
    void 생성일자_자동생성(){
        Item item = itemRepository.save(Item.newInstance("name",100, LocalDateTime.MIN,
                LocalDateTime.MAX));
        Assertions.assertAll(()->{
            Assertions.assertNotNull(item.getCreatedDate());
            Assertions.assertNotNull(item.getUpdatedDate());
        });
    }
}