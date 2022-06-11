package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.common.exception.DuplicateEmailException;
import com.ssgassignment.productinfoapi.common.exception.LoginFailException;
import com.ssgassignment.productinfoapi.common.exception.NotFoundUserException;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import com.ssgassignment.productinfoapi.vo.RequestLogin;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public Long join(UserDto userDto) {
        checkDuplicateUser(userDto.getEmail());
        return userRepository.save(User.newInstance(userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()), userDto.getName(),
                userDto.getUserType())).getUserId();

    }

    @Transactional
    @Override
    public void withdrawUser(Long userId) {
        try{
            userRepository.deleteById(userId);
        }catch(Exception e){
            throw new NotFoundUserException("해당 사용자는 존재하지 않습니다.");
        }
    }

    @Transactional
    @Override
    public UserDto login(RequestLogin requestLogin) {
        User user = userRepository.findByEmail(requestLogin.getEmail()).orElseThrow(() -> {
            throw new NotFoundUserException("해당 사용자는 존재하지 않습니다.");
        });
        if(!matchPassword(user.getPassword(), requestLogin.getPassword())){
            throw new LoginFailException("비밀 번호가 맞지 않습니다.");
        }
        return new UserDto(user.getEmail(), user.getPassword(), user.getName(), user.getUserType());
    }

    private boolean matchPassword(String userPassword, String loginPassword){
        if(passwordEncoder.matches(loginPassword, userPassword)){
            return true;
        }
        return false;
    }

    private void checkDuplicateUser(String email){
        if(userRepository.findByEmail(email).isPresent()){
            throw new DuplicateEmailException();
        }
    }
}
