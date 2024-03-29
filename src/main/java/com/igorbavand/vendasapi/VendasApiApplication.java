package com.igorbavand.vendasapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class VendasApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(VendasApiApplication.class, args);
    }
}
