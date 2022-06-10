package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.vo.RequestLogin;

import java.util.List;

public interface UserService {
    Long join(UserDto userDto);
    void withdrawUser(Long userId);
    UserDto login(RequestLogin requestLogin);
}
