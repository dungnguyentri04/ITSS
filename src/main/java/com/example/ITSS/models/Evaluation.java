package com.example.ITSS.models;

import jakarta.persistence.*;
import lombok.*;

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
    @JoinColumn(name = "evaluator_id")
    private User evaluator;

    @ManyToOne
    @JoinColumn(name = "evaluated_id")
    private User evaluated;

    private String comment;

    private float score;

    private Date createdAt;

    private Date updatedAt;
}
