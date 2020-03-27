package com.tsp.backbone.initialization;

import com.tsp.backbone.db.entities.Role;
import com.tsp.backbone.db.entities.User;
import com.tsp.backbone.db.repositories.RoleRepository;
import com.tsp.backbone.db.repositories.UserRepository;
import com.tsp.backbone.rest.enums.RoleNames;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.Collections;

public class EmojicCommandLineRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;
    private PasswordEncoder encoder;

    @Override
    public void run(String... args) throws Exception {
        // Init default Roles
        roleRepository.saveAll(Arrays.asList(new Role(RoleNames.ADMIN), new Role(RoleNames.USER)));
        // Init Default Users
        userRepository.save(
                new User(
                        -1L,
                        "defaultAdmin",
                        encoder.encode("123qwe"),
                        null,
                        null,
                        Collections.singleton(
                                roleRepository
                                .findByRole(RoleNames.ADMIN)
                                .orElseThrow(
                                        () -> new SecurityException("Admin role not exist!")
                                )
                        )
                )
        );
    }
}
