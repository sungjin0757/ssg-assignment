package com.ssgassignment.productinfoapi.vo;

import com.ssgassignment.productinfoapi.domain.enumeration.UserStat;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.Data;

@Data
public class ResponseUser {
    private Long userId;
    private String email;
    private String name;
    private String userType;
    private String userStat;

    public ResponseUser(Long userId, String email, String name, UserType userType, UserStat userStat) {
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.userType = userType.equals(UserType.GENERAL) ? "일반 회원" : "기업 회원";
        this.userStat = userStat.equals(UserStat.DISABLED) ? "탈퇴" : "정상";
    }
}
