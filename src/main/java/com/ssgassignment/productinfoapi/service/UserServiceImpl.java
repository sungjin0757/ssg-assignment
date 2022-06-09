package com.ssgassignment.productinfoapi.service;

import com.ssgassignment.productinfoapi.domain.User;
import com.ssgassignment.productinfoapi.dto.UserDto;
import com.ssgassignment.productinfoapi.exception.DuplicateEmailException;
import com.ssgassignment.productinfoapi.exception.NotFoundUserException;
import com.ssgassignment.productinfoapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Transactional
    @Override
    public Long join(UserDto userDto) {
        checkDuplicateUser(userDto.getEmail());
        return userRepository.save(User.newInstance(userDto.getEmail(),
                userDto.getPassword(), userDto.getName(), userDto.getUserType())).getUserId();

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

    private void checkDuplicateUser(String email){
        if(userRepository.findByEmail(email).isPresent()){
            throw new DuplicateEmailException();
        }
    }
}
