package com.example.ITSS.models;

import com.example.ITSS.models.enums.TaskPriority;
import com.example.ITSS.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "assignee_id")
    private User assignee;

    private String createdBy;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;  // Enum for task status (e.g., PENDING, IN_PROGRESS, COMPLETED)

    private TaskPriority priority;

    private LocalDate deadline;

    private LocalDate updatedAt;

    private LocalDate createdAt;

    private LocalDate completedAt;
}
