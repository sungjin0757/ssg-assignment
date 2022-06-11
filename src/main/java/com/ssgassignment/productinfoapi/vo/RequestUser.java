package com.ssgassignment.productinfoapi.vo;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class RequestUser {
    @NotNull(message = "Email Cannot Be Null")
    @Email
    private String email;
    @NotNull(message = "Password Cannot Be Null")
    @Size(min = 2, message = "Password Required More Than Two Characters")
    private String password;
    @NotNull(message = "Name Cannot Be Null")
    private String name;
    @NotNull(message = "UserType Cannot Be Null")
    @Schema(example = "Press CORPORATE Or GENERAL")
    private UserType userType;
}
