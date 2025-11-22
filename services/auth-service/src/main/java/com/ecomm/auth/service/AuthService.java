package com.ecomm.auth.service;

import com.ecomm.auth.exception.AppException;
import com.ecomm.auth.exception.InvalidPasswordException;
import com.ecomm.auth.exception.UserAlreadyExistException;
import com.ecomm.auth.jwt.JwtService;
import com.ecomm.auth.model.User;
import com.ecomm.auth.repository.AuthRepository;
import com.ecomm.dto.request.UserLoginDTO;
import com.ecomm.dto.request.UserRegisterDTO;
import com.ecomm.dto.response.UserLoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    @Value("${security.jwt.expiration}")
    private long expirationTime;

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    public Boolean registerUser(UserRegisterDTO userRegisterDTO) {
        if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            throw new AppException("password matching validation failed", HttpStatus.BAD_REQUEST);
        }

        if(authRepository.findByUserNameOrEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail()).isPresent()){
           throw new UserAlreadyExistException("Please provide unique email and username");
        }
        // note: convert the password into hashed password.
        String hashedPassword = passwordEncoder.encode(userRegisterDTO.getPassword());
        User user = new User(userRegisterDTO.getUsername(), userRegisterDTO.getEmail(), hashedPassword);
        authRepository.save(user);
        return true;
    }

    public UserLoginResponse loginUser(UserLoginDTO userLoginDTO){

        Optional<User> userOptional =  authRepository.findByUserName(userLoginDTO.getUsername());
        User user = userOptional.orElseThrow(() -> new InvalidPasswordException("Invalid User"));

        boolean passwordMatches = passwordEncoder.matches(userLoginDTO.getPassword(),user.getPassword());
        if(!passwordMatches){
            throw new InvalidPasswordException("Invalid password");
        }
        // note: we need to json web token with it expiration date.
        String token = jwtService.generateJwtToken(user.getUserName());
        return new UserLoginResponse(token,expirationTime);
    }

    public Boolean validateJwt(String token){
        log.atInfo().log("this is validation token:: {}",token);
        return jwtService.isValidToken(token);
    }


}
