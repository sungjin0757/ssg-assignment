package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {

}
