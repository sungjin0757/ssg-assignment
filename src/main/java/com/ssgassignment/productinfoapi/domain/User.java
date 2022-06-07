package com.ssgassignment.productinfoapi.domain;

import com.ssgassignment.productinfoapi.domain.enumeration.UserStat;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import lombok.Getter;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Table(name= "users")
public class User extends AbstractDataTraceEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_type", nullable = false)
    private UserType userType;
    @Enumerated(EnumType.STRING)
    private UserStat userStat;

    private User( String email, String password, String name, UserType userType, UserStat userStat) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.userType = userType;
        this.userStat = userStat;
    }

    public static User newInstance(String email, String password, String name, UserType userType) {
        return new User(email, password, name, userType, UserStat.ENABLED);
    }

    @Override
    public boolean equals(Object o) {
        if(o == this)
            return true;
        if(!(o instanceof User))
            return false;
        User user=(User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(email,user.email)
                && Objects.equals(password, user.password) && Objects.equals(name, user.name);
    }

    @Override
    public int hashCode() {
        int result = userId == null ? 0 : userId.hashCode();
        result = 31 * result + email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
