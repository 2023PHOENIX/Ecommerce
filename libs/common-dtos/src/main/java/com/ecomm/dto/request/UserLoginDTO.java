package com.ecomm.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserLoginDTO {
    @NotNull(message = "username can't be empty")
    private String username;
    @NotNull(message = "password can't be empty")
    private String password;
}
