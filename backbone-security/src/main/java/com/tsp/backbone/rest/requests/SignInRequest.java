package com.tsp.backbone.rest.requests;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SignInRequest {
    @NotBlank
    private String password;
    @NotBlank
    private String login;
}
