package com.pro.scor.config;

import com.pro.scor.entities.Providers;
import com.pro.scor.entities.User;
import com.pro.scor.helper.AppConstants;
import com.pro.scor.repo.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Component
public class OAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepo userRepo;

    Logger logger = LoggerFactory.getLogger(OAuthSuccessHandler.class);

    // Redirect to the dashboard page after successful login
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication)
            throws IOException {

        logger.info("User logged in successfully");

        // Identify the Provider
        var auth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
        String authorizedClientRegistrationId = auth2AuthenticationToken.getAuthorizedClientRegistrationId();

        var oAuth2User = (DefaultOAuth2User) authentication.getPrincipal();
        oAuth2User.getAttributes().forEach((k,v) -> {
            logger.info("{} -> {}", k, v);
        });

        // set user details
        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setRoleList(List.of(AppConstants.ROLE_USER));
        user.setUserVerified(true);
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);


        // for Google and GitHub

        if (authorizedClientRegistrationId.equalsIgnoreCase("google")){

            // Google - if a Google user is logging in,
            // we will get the user details from the Google.
            user.setEmail(Objects.requireNonNull(oAuth2User.getAttribute("email")).toString());
            user.setSUserName(Objects.requireNonNull(oAuth2User.getAttribute("name")).toString());
            // not every provider will provide the picture
            user.setProfilePicLink(oAuth2User.getAttribute("picture") != null ? oAuth2User.getAttribute("picture").toString() : null);
            user.setProvider(Providers.GOOGLE);
            user.setProviderUsrId(oAuth2User.getName());

        } else if (authorizedClientRegistrationId.equalsIgnoreCase("github")) {

            // GitHub - if a GitHub user is logging in,
            // we will get the user details from the GitHub.

            // because sometimes some users may not have a public email
            user.setEmail(oAuth2User.getAttribute("email") != null ? oAuth2User.getAttribute("email").toString() : oAuth2User.getAttribute("login").toString()+"@gmail.com");
            user.setSUserName(oAuth2User.getAttribute("login"));
            // replace null with some default image in the future
            user.setProfilePicLink(oAuth2User.getAttribute("avatar_url") != null ? oAuth2User.getAttribute("avatar_url").toString() : null);
            user.setProvider(Providers.GITHUB);
            user.setProviderUsrId(oAuth2User.getName());

        } else {
            logger.info("Unknown provider - {}", authorizedClientRegistrationId);
        }

        // Save the user to the database.
        // If the user is already present, then don't save it again
        User dbUser = userRepo.findByEmail(user.getEmail()).orElse(null);
        if (dbUser == null){
            userRepo.save(user);
            logger.info("User saved to the database - {}", user.getEmail());
        }

        // Redirect to the dashboard page
        response.sendRedirect("/user/dashboard");

    }

}
