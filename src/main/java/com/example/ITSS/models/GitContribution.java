package com.example.ITSS.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class GitContribution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long projectId;

    @Column(unique = true)
    private String commitHash;

    private Long memberId;

    private Long userId;

    private String nameGithub;

    private String username;

    private LocalDate commitDate;

    private Long linesAdded;

    private Long linesRemoved;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String message;

    private String githubLink;
}
