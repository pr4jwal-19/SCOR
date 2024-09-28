package com.pro.scor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {

    // Create user and login using java code with in memory authentication
    @Bean
    public UserDetailsService userDetailsService(){

        UserDetails user1 = User.withUsername("admin123")
                .passwordEncoder(password -> "{noop}"+password)
                .password("admin123")
                .roles("ADMIN", "USER")
                .build();
        UserDetails user2 = User.withUsername("temp-user1")
                .password("{noop}"+"temp@123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user1,user2);
    }
}
