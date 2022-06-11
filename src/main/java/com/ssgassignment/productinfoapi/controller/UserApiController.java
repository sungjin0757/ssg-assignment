package com.ssgassignment.productinfoapi.controller;

import com.ssgassignment.productinfoapi.common.constatants.SecurityConstants;
import com.ssgassignment.productinfoapi.common.constatants.UrlConstants;
import com.ssgassignment.productinfoapi.domain.enumeration.UserStat;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.common.exception.ParameterException;
import com.ssgassignment.productinfoapi.security.JwtUtil;
import com.ssgassignment.productinfoapi.security.property.JwtPrincipal;
import com.ssgassignment.productinfoapi.service.UserService;
import com.ssgassignment.productinfoapi.vo.JwtResponse;
import com.ssgassignment.productinfoapi.vo.RequestLogin;
import com.ssgassignment.productinfoapi.vo.RequestUser;
import com.ssgassignment.productinfoapi.vo.ResponseUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(UrlConstants.USER_BASE)
@RequiredArgsConstructor
@SecurityRequirement(name = SecurityConstants.SWAGGER_KEY)
public class UserApiController {
    private final UserService userService;
    private final JwtUtil jwtUtil;

    @Operation(summary = "회원 가입")
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

    @Operation(summary = "회원 탈퇴")
    @DeleteMapping(UrlConstants.USER_WITHDRAW+UrlConstants.ID)
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id){
        userService.withdrawUser(id);

        return ResponseEntity.status(HttpStatus.OK).body("Delete Completed");
    }

    @Operation(summary = "로그인")
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
