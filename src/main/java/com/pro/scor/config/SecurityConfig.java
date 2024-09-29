package com.pro.scor.config;

import com.pro.scor.services.implementation.SecurityCustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // Create user and login using java code with in memory authentication
    // Uncomment below code to use in memory authentication
    //@Bean
    /* public UserDetailsService userDetailsService(){
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
    }*/

    // User implements UserDetails, so we can use UserDetailsService
    // to get user details from the database

    @Autowired
    private SecurityCustomUserDetailsService userDetailsService;

    // For database authentication using DAO Authentication Provider
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // UserDetailsService is used to get user details from the CRUD database
        // We provided our custom UserDetailsService implementation,
        // which will fetch user details from the database
        provider.setUserDetailsService(userDetailsService);
        // PasswordEncoder is used to encode and decode password
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Configure the httpSecurity object to set the security rules
        httpSecurity.authorizeHttpRequests(authorizedRequest -> {
            authorizedRequest.requestMatchers("/user/**").authenticated();
            authorizedRequest.anyRequest().permitAll();
        });

        // Configure the httpSecurity object to set the default login page
        httpSecurity.formLogin(Customizer.withDefaults());

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
