package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.Item;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item,Long> {
}
