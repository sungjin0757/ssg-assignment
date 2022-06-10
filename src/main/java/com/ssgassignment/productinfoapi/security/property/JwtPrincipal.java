package com.ssgassignment.productinfoapi.security.property;

import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.UserDto;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class JwtPrincipal {
    private String email;
    private String password;
    private String name;
    private UserType userType;

    public static JwtPrincipal fromClaims(Claims claims){
        return new JwtPrincipal((String) claims.get("email"), (String) claims.get("password"),
                (String) claims.get("name"), claims.get("role").equals("GENERAL") ? UserType.GENERAL : UserType.CORPORATE);
    }

    public static JwtPrincipal fromDto(UserDto userDto){
        return new JwtPrincipal(userDto.getEmail(), userDto.getPassword(), userDto.getName(), userDto.getUserType());
    }
}
