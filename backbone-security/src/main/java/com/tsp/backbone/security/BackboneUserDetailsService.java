package com.tsp.backbone.security;

import com.tsp.backbone.db.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class BackboneUserDetailsService implements UserDetailsService {

    private UserRepository repository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return UserPrincipal.create(
                repository.findByLogin(login)
                        .orElseThrow(
                                () -> new SecurityException(String.format("User with login '%s' not exist", login))
                        )
        );
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        return UserPrincipal.create(
                repository.findById(id)
                        .orElseThrow(
                                () -> new SecurityException(String.format("User with id '%s' not exist", id))
                        )
        );
    }

    @Autowired
    public void setRepository(UserRepository repository) {
        this.repository = repository;
    }
}
