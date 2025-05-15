package com.example.ITSS.models;

import com.example.ITSS.models.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    private Long assigneeId;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;  // Enum for task status (e.g., PENDING, IN_PROGRESS, COMPLETED)

    private String createdAt;
}
