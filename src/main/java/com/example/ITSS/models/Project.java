package com.example.ITSS.models;

import com.example.ITSS.models.enums.StatusProject;
import jakarta.persistence.*;
import lombok.*;

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

    private Long leaderId;

    private String name;

    private String description;

    private String startDate;

    private String endDate;

    @Enumerated(EnumType.STRING)
    private StatusProject status;  // Enum for project status (e.g., IN_PROGRESS, COMPLETED)

    @ManyToOne
    @JoinColumn(name = "user_created_id")
    private User userCreated;

    private String createdAt;


    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Prediction> progress;

    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Task> tasks;

}
