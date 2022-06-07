package com.ssgassignment.productinfoapi.dto;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
    private String email;
    private String password;
    private String name;
    private UserType userType;
}
