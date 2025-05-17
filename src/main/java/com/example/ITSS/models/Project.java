package com.example.ITSS.models;

import com.example.ITSS.models.enums.StatusProject;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private String leaderName;

    private String userCreatedName;

    private String githubLink;

    private String token;

    private String nameOfMemberGithubs;

    @Enumerated(EnumType.STRING)
    private StatusProject status;  // Enum for project status (e.g., IN_PROGRESS, COMPLETED)

    private LocalDate createdAt;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks = new ArrayList<>();

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Contribution> contributions = new ArrayList<>();

}
