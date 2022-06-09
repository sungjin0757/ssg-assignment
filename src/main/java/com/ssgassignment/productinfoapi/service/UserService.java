package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.dto.UserDto;

import java.util.List;

public interface UserService {
    Long join(UserDto userDto);
    void withdrawUser(Long userId);
}
