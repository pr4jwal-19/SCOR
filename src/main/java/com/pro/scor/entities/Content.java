package com.pro.scor.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "contents")
public class Content {

    @Id
    private String contentId;

    private String category;

    @Column(nullable = false)
    private String title;

    @Column(length = 1024)
    private String description;

    @Column(length = 10000)
    private String body;

    @Column(length = 10000)
    private String code;

    private String codeType;

    private String tags;

    private String linkUrl;

    private String imageLink;

    @Column(nullable = false)
    private String createdAt;

    private String updatedAt;

    private boolean isFavorite;
    private boolean isReadLater;
    private boolean isRecentlyViewed;

    @ManyToOne
    private User user;

    // Content can have multiple social links embedded
    @OneToMany(mappedBy = "content", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private List<SocialLinks> socialLinks = new ArrayList<>();

}
