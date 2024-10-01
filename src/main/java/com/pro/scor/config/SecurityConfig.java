package com.pro.scor.config;

import com.pro.scor.services.implementation.SecurityCustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
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

    @Autowired
    private OAuthSuccessHandler oAuthSuccessHandler;

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

    // To Manage routes and security rules using HttpSecurity
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        // Configure the httpSecurity object to set the security rules
        httpSecurity.authorizeHttpRequests(authorizedRequest -> {
            authorizedRequest.requestMatchers("/user/**").authenticated();
            authorizedRequest.anyRequest().permitAll();
        });

        // Configure the httpSecurity object to set the default login page
        //httpSecurity.formLogin(Customizer.withDefaults());

        // Custom login page using FormLoginConfigurer - login
        httpSecurity.formLogin(login -> {

            // Set the login page URL
            // loginPage() method is used to set the "login" page URL
            // loginProcessingUrl() method is used to set the URL where the login form will be submitted
            // successForwardUrl() method is used to set the URL
            // where the user will be redirected after successful login
            login.loginPage("/login");

            login.loginProcessingUrl("/authenticate")
                    .defaultSuccessUrl("/user/dashboard")
                    .failureForwardUrl("/login?error=true");

            login.usernameParameter("email");
            login.passwordParameter("password");

        });

        // logoutSuccessUrl("/login?logout=true") is used to set the URL
        // logout is passed as a query parameter
        // then we can show a message on the login page using this query parameter - logout
        httpSecurity.logout(logoutProcessing -> {
            logoutProcessing.logoutUrl("/logout")
                    .logoutSuccessUrl("/login?logout=true");

        });

        // OAuth2 Configurations
        httpSecurity.oauth2Login(oauth -> {
            oauth.loginPage("/login");
            oauth.successHandler(oAuthSuccessHandler);
        });


        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
