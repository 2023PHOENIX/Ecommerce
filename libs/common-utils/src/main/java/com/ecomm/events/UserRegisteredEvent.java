package com.ecomm.events;


import lombok.Data;

import java.time.Instant;

@Data
public class UserRegisteredEvent {
    private String userId;
    private String email;
    private String role;
    private Instant instant = Instant.now();
}
