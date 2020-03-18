package com.sbt.backbone.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@EnableFeignClients
public class BackboneClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackboneClientApplication.class, args);
    }
}

