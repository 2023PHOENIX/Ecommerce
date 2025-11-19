package com.ecomm.auth.service;

import com.ecomm.auth.model.User;
import com.ecomm.auth.repository.AuthRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private AuthRepository authRepository;

    public String addUser(User user){
        return "helo";
    }

}
