package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.Item;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;


import java.util.List;

public interface ItemRepository extends CrudRepository<Item,Long> {
    @Query("select i from Item i where ((i.itemType = :item_type) OR (i.itemType = " +
            "com.ssgassignment.productinfoapi.domain.enumeration.UserType.GENERAL)) " +
            "AND (current_timestamp between i.itemDisplayStartDate AND i.itemDisplayEndDate)")
    List<Item> findOrdableItems(@Param("item_type")UserType itemType);
}
