package com.example.ITSS.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Evaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "reporter_id")
    private User reporter;

    @ManyToOne
    @JoinColumn(name = "evaluated_id")
    private User evaluated;

    private String comment;

    private float avarageScore;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private int qualityScore;

    private int spiritScore;

    private int communicationScore;

    private int teamworkScore;

    private String strongPoint;

    private String weakPoint;

    private String title;

    private String reportedName;
}
