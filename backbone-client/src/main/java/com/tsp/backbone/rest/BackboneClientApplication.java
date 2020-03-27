package com.tsp.backbone.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BackboneClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackboneClientApplication.class, args);
    }
}

