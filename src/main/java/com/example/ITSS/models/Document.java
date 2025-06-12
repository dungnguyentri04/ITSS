package com.example.ITSS.models;

import com.example.ITSS.models.enums.DocumentType;
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
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private double size;

    private Long taskId;

    private String name;

    private String description;

    private String url;

    @ManyToOne
    @JoinColumn(name = "uploader_id")
    private User uploader;

    private Long projectId;

    @Enumerated(EnumType.STRING)
    private DocumentType type;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
