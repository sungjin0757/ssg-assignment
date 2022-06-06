package com.ssgassignment.productinfoapi.domain.enumeration;

import org.springframework.security.core.GrantedAuthority;

public enum UserType implements GrantedAuthority{
    CORPORATE("ROLE_ADMIN"), GENERAL("ROLE_USER");

    private final String role;

    UserType(String role) {
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }
}
