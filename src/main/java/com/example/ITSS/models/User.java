package com.example.ITSS.models;

import com.example.ITSS.models.enums.UserRole;
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userName;

    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "assignee", cascade = CascadeType.ALL)
    private List<Task> taskList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contribution> contributionList = new ArrayList<>();

    @OneToMany(mappedBy = "evaluator", cascade = CascadeType.ALL)
    private List<Evaluation> evaluatorList = new ArrayList<>();

    @OneToMany(mappedBy = "evaluated", cascade = CascadeType.ALL)
    private List<Evaluation> evaluatedList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectClassMember> projectClassMemberList = new ArrayList<>();

    private String email;

//    private String phoneNumber;

    private LocalDate created_at;

    private LocalDate updated_at;

}
