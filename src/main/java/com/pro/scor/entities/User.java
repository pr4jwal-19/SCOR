package com.pro.scor.entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    private String userId;

    @Column(nullable = false)
    private String sUserName;

    @Column(unique = true, nullable = false)
    private String email;

    @Getter(AccessLevel.NONE)
    private String password;

    @Column(length = 10000)
    private String about;

    @Column(length = 10000)
    private String profilePicLink;

    @Column(length = 10, unique = true)
    private String phoneNo;

    private boolean isPhoneVerified = false;
    private boolean isEmailVerified = false;
    private boolean isUserVerified = false;

    private boolean isAccountNonExpired = true;
    @Getter(AccessLevel.NONE)
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;
    private boolean isEnabled = true;

    // Self, Google , GitHub
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @Builder.Default
    private Providers provider = Providers.SELF;

    private String providerUsrId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Content> contentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLinks> socLinksFutureVisit = new ArrayList<>();

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roleList = new ArrayList<>();

    // Returns the collection of roles of the user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert the roles [USER, ADMIN] to SimpleGrantedAuthority["USER", "ADMIN"]
        // This is required by Spring Security
        Collection<SimpleGrantedAuthority> roles = roleList.stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        return roles;
    }

    // Checks for email as username for authentication
    @Override
    public String getUsername() {
        return this.email;
    }

    // Returns the password of the user
    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

}
