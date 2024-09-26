package com.pro.scor.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "social_links")
@Table(name = "social_links")
public class SocialLinks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String socLink;
    private String platform;

    @ManyToOne
    private User user;

    @ManyToOne
    private Content content;

}
