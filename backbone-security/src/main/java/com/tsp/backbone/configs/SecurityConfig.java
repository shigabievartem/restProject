package com.tsp.backbone.configs;

import com.tsp.backbone.rest.enums.RoleNames;
import com.tsp.backbone.security.BackboneUserDetailsService;
import com.tsp.backbone.security.JwtAuthenticationEntryPoint;
import com.tsp.backbone.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true
)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private BackboneUserDetailsService userDetailsService;
    private JwtAuthenticationEntryPoint unauthorizedHandler;

    @Value("${spring.h2.console.path}")
    private String h2consolePath;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

        auth.inMemoryAuthentication()
                .withUser("defaultAdmin")
                .password("123qwe")
                .authorities(RoleNames.ADMIN.name());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers()
                .frameOptions()
                    .disable()
        .and()
            .cors()
        .and()
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers(
                        "/**"
                )
                    .permitAll()
                .anyRequest()
                    .authenticated()
        .and()
            .httpBasic()
        .and()
            .exceptionHandling()
                .authenticationEntryPoint(unauthorizedHandler);

        http.addFilterBefore(getAuthFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter getAuthFilter() {
        return new JwtAuthenticationFilter();
    }

    @Autowired
    public void setUserDetailsService(BackboneUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    public void setUnauthorizedHandler(JwtAuthenticationEntryPoint unauthorizedHandler) {
        this.unauthorizedHandler = unauthorizedHandler;
    }
}
