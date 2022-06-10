package com.ssgassignment.productinfoapi.controller;

import com.ssgassignment.productinfoapi.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.domain.enumeration.UserStat;
import com.ssgassignment.productinfoapi.domain.enumeration.UserType;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.exception.ParameterException;
import com.ssgassignment.productinfoapi.security.JwtUtil;
import com.ssgassignment.productinfoapi.security.property.JwtPrincipal;
import com.ssgassignment.productinfoapi.service.UserService;
import com.ssgassignment.productinfoapi.vo.JwtResponse;
import com.ssgassignment.productinfoapi.vo.RequestLogin;
import com.ssgassignment.productinfoapi.vo.RequestUser;
import com.ssgassignment.productinfoapi.vo.ResponseUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlConstants.USER_BASE)
@RequiredArgsConstructor
public class UserApiController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @PostMapping(UrlConstants.SAVE)
    public ResponseEntity<ResponseUser> createUser(@Valid @RequestBody RequestUser requestUser,
                                                   BindingResult result){
        if(result.hasErrors()){
            throw new ParameterException(result);
        }
        Long userId = userService.join(new UserDto(requestUser.getEmail(), requestUser.getPassword(),
                requestUser.getName(), requestUser.getUserType()));

        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseUser(userId,
                requestUser.getEmail(), requestUser.getName(), requestUser.getUserType(), UserStat.ENABLED));
    }

    @DeleteMapping(UrlConstants.USER_WITHDRAW+UrlConstants.ID)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.withdrawUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("Delete Completed");
    }

    @PostMapping(UrlConstants.LOGIN)
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody RequestLogin requestLogin,
                                             BindingResult result){
        if(result.hasErrors()){
            throw new ParameterException(result);
        }
        UserDto loginUser = userService.login(requestLogin);
        String token = jwtUtil.generateToken(JwtPrincipal.fromDto(loginUser));

        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token));
    }
}
