package com.pro.scor.helper;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

import java.util.Objects;

public class LoggedUserHelper {

    public static String getEmailOfLoggedInUser(Authentication authentication) {

        if (authentication instanceof OAuth2AuthenticationToken oAuth2AuthenticationToken){
            // sign in with OAuth - Google or GitHub
            var clientID = oAuth2AuthenticationToken.getAuthorizedClientRegistrationId();
            var user = (DefaultOAuth2User)authentication.getPrincipal();
            // sign in with OAuth - Google
            if (clientID.equalsIgnoreCase("google")){
                // there is no username for Google, so we will use email
                return Objects.requireNonNull(user.getAttribute("email")).toString();
            } else if (clientID.equalsIgnoreCase("github")){
                // sign in with OAuth - GitHub
                // username is the login in GitHub
                return user.getAttribute("email") != null ? Objects.requireNonNull(user.getAttribute("email")).toString() : Objects.requireNonNull(user.getAttribute("login")).toString()+"@gmail.com";
            }

            return "unknown";

        } else {
            // sign in with email and password,
            // therefore, return the email
            return authentication.getName();
        }
    }

}
