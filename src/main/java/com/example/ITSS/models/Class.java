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
public class Class {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String subject;

    private String description;

    private String userCreatedName;

    private LocalDate createdAt;

    @OneToMany(mappedBy = "class", cascade = CascadeType.ALL)
    private List<ProjectClassMember> projectClassMembers;

    @OneToMany(mappedBy = "class", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();
}
