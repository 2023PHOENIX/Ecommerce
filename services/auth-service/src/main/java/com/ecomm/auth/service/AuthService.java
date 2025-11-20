package com.ecomm.auth.service;

import com.ecomm.auth.exception.AppException;
import com.ecomm.auth.model.User;
import com.ecomm.auth.repository.AuthRepository;
import com.ecomm.dto.request.UserLoginDTO;
import com.ecomm.dto.request.UserRegisterDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;


    public User registerUser(UserRegisterDTO userRegisterDTO)  {
        if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            throw new AppException("password matching validation failed", HttpStatus.UNAUTHORIZED);
        }
        User user = new User(userRegisterDTO.getUsername(), userRegisterDTO.getEmail(), userRegisterDTO.getPassword());
        return authRepository.save(user);
    }

    public Boolean loginUser(UserLoginDTO userLoginDTO){
        return authRepository.findByUserName(userLoginDTO.getUsername()).isPresent();
    }


}
