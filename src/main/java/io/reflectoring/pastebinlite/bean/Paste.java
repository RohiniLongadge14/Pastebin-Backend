package io.reflectoring.pastebinlite.bean;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "pastes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Paste {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private Instant createdAt;

    private Instant expiresAt;

    private Integer maxViews;

    private Integer views;
}
