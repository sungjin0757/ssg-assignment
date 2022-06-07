package com.ssgassignment.productinfoapi.repository;

import com.ssgassignment.productinfoapi.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User,Long> {
    Optional<User> findByEmail(String email);
}
