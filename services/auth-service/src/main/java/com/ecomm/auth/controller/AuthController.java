package com.ecomm.auth.controller;


import com.ecomm.auth.service.AuthService;
import com.ecomm.dto.exception.ApiResponseDTO;
import com.ecomm.dto.request.UserLoginDTO;
import com.ecomm.dto.request.UserRegisterDTO;
import com.ecomm.dto.response.UserLoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponseDTO<Object>> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        Boolean registerUser = authService.registerUser(userRegisterDTO);
        ApiResponseDTO<Object> responseDTO = ApiResponseDTO.builder().status(HttpStatus.ACCEPTED.value()).data(registerUser).build();
        return ResponseEntity.accepted().body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDTO<Object>> login(@RequestBody @Valid UserLoginDTO userLoginDTO){
        UserLoginResponse userLoginResponse = authService.loginUser(userLoginDTO);
        ApiResponseDTO<Object> responseDTO = ApiResponseDTO.builder().status(HttpStatus.OK.value()).data(userLoginResponse).build();
        return ResponseEntity.ok().body(responseDTO);
    }


    @GetMapping("/validate")
    public ResponseEntity<ApiResponseDTO<Object>> validateJwt(@RequestHeader("Authorization") String authorization){
        Boolean isTokenValid = false;
        if(authorization != null && authorization.startsWith("Bearer ")){
            String token = authorization.substring(7);
            isTokenValid = authService.validateJwt(token);
            log.atInfo().log("token is :: {}",isTokenValid);
        }
        ApiResponseDTO<Object> responseDTO = ApiResponseDTO.builder().status(HttpStatus.OK.value()).data(isTokenValid?"Token Valid" : "Invalid").build();
        return ResponseEntity.ok().body(responseDTO);
    }


}
