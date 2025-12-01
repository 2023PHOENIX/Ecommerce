package com.ecomm.pct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {
        "com.ecomm.pct",
        "com.ecomm.kafka"
})
public class PctApplication {
    public static void main(String[] args) {
        SpringApplication.run(PctApplication.class);
    }
}
