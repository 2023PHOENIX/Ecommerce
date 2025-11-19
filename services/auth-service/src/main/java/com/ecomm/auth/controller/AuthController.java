package com.ecomm.auth.controller;


import com.ecomm.auth.model.User;
import com.ecomm.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthRepository authRepository;
    @GetMapping("/health")
    public User hello(){

        User user = new User();
        user.setUserName("phoenix");
        user.setPassword("1phoenix");
        user.setEmail("a@gmail.com");
        return authRepository.save(user);
    }
}
