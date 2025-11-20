package com.ecomm.auth.controller;


import com.ecomm.auth.model.User;
import com.ecomm.auth.service.AuthService;
import com.ecomm.dto.exception.ApiResponseDTO;
import com.ecomm.dto.request.UserLoginDTO;
import com.ecomm.dto.request.UserRegisterDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Object>> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
//            return ResponseEntity.accepted().body(authService.registerUser(userRegisterDTO));
        User user = authService.registerUser(userRegisterDTO);
        ApiResponseDTO<Object> responseDTO = ApiResponseDTO.builder().status(HttpStatus.ACCEPTED.value()).data(user).build();
        return ResponseEntity.accepted().body(responseDTO);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<Object>> login(@RequestBody UserLoginDTO userLoginDTO){
        Boolean userData = authService.loginUser(userLoginDTO);
        ApiResponseDTO<Object> responseDTO = ApiResponseDTO.builder().status(HttpStatus.OK.value()).data(userData).build();
        return ResponseEntity.ok().body(responseDTO);
    }
//
//    @GetMapping("/validate")
//    public ValidationInfo validate(@RequestHeader("Authorization") String authorization){
//
//    }


}
