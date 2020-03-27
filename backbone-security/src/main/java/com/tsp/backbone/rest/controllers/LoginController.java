package com.tsp.backbone.rest.controllers;

import com.tsp.backbone.db.entities.Role;
import com.tsp.backbone.db.entities.User;
import com.tsp.backbone.db.repositories.RoleRepository;
import com.tsp.backbone.db.repositories.UserRepository;
import com.tsp.backbone.rest.enums.RoleNames;
import com.tsp.backbone.rest.requests.SignInRequest;
import com.tsp.backbone.rest.requests.SignUpRequest;
import com.tsp.backbone.rest.responses.ApiResponse;
import com.tsp.backbone.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private AuthenticationManager authManager;
    private JwtTokenProvider tokenProvider;
    private PasswordEncoder encoder;

    @PostMapping("/signin")
    public ResponseEntity signIn(@RequestBody SignInRequest request) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = tokenProvider.generateToken(auth);

        return new ResponseEntity<>(new ApiResponse(true, jwt), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity signUp(@RequestBody SignUpRequest request) {
        String userLogin = request.getLogin();

        if (userRepository.existsByLogin(userLogin)) {
            return new ResponseEntity<>(
                    new ApiResponse(false, String.format("User with login '%s' already exist!", userLogin)),
                            HttpStatus.BAD_REQUEST);
        }

        User user = new User();
        user.setLogin(userLogin);
        user.setPassword(encoder.encode(request.getPassword()));

        Role role = roleRepository.findByRole(RoleNames.USER)
                .orElseThrow(() -> new SecurityException(String.format("Role '%s' not exist", RoleNames.USER.name())));

        user.setRoles(Collections.singleton(role));

        userRepository.save(user);

        return new ResponseEntity<>(new ApiResponse(true, "User successfully created"), HttpStatus.OK);
    }


    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setAuthManager(AuthenticationManager authManager) {
        this.authManager = authManager;
    }

    @Autowired
    public void setTokenProvider(JwtTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }
}
