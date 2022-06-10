package com.ssgassignment.productinfoapi.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestLogin {
    @NotNull(message = "Email Cannot Be Null")
    @Email
    private String email;
    @NotNull(message = "Password Cannot Be Null")
    @Size(min = 2, message = "Password Required More Than Two Characters")
    private String password;
}
