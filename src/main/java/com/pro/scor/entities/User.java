package com.pro.scor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class User {

    @Id
    private String userId;

    @Column(nullable = false)
    private String userName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Column(length = 10000)
    private String about;

    @Column(length = 10000)
    private String profilePicLink;

    private String phoneNo;

    private boolean isPhoneVerified = false;
    private boolean isEmailVerified = false;
    private boolean isUserVerified = false;

    // Self, Google , GitHub
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Providers provider = Providers.SELF;
    private String providerUsrId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Content> contentList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLinks> socLinksFutureVisit = new ArrayList<>();

}
