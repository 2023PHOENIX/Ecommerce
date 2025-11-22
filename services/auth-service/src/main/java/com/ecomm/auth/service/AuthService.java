package com.ecomm.auth.service;

import com.ecomm.auth.exception.AppException;
import com.ecomm.auth.exception.DataAccessException;
import com.ecomm.auth.exception.InvalidPasswordException;
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
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    @Value("${security.jwt.expiration}")
    private String expirationTime;

    private final AuthRepository authRepository;
    private final JwtService jwtService;


    public User registerUser(UserRegisterDTO userRegisterDTO) {
        if(!userRegisterDTO.getPassword().equals(userRegisterDTO.getConfirmPassword())){
            throw new AppException("password matching validation failed", HttpStatus.UNAUTHORIZED);
        }

        if(authRepository.findByEmail(userRegisterDTO.getEmail()).isPresent() || authRepository.findByUserName(userRegisterDTO.getUsername()).isPresent()){
           throw new DataAccessException("Please provide unique email and username");
        }
        // note: convert the password into hashed password.
        String hashedPassword = BCrypt.hashpw(userRegisterDTO.getPassword(),BCrypt.gensalt());

        User user = new User(userRegisterDTO.getUsername(), userRegisterDTO.getEmail(), hashedPassword);
        return authRepository.save(user);
    }

    public UserLoginResponse loginUser(UserLoginDTO userLoginDTO){

        Optional<User> userOptional =  authRepository.findByUserName(userLoginDTO.getUsername());
        User user = userOptional.orElseThrow(() -> new InvalidPasswordException("Invalid User"));

        boolean passwordMatches = BCrypt.checkpw(userLoginDTO.getPassword(),user.getPassword());
        if(!passwordMatches){
            throw new InvalidPasswordException("Invalid password");
        }
        // note: we need to json web token with it expiration date.
        String token = jwtService.generateJwtToken(user.getUserName());
        return new UserLoginResponse(token,expirationTime);
    }



}
