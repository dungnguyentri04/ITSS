package com.example.ITSS.dto.responseDto;

import com.example.ITSS.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentResponseDto {
    private Long id;

    private String title;

    private double size;

    private Long taskId;

    private String name;

    private String description;

    private String url;

    private String uploaderName;

    private Long uploaderId;

    private Long projectId;

    private String type;

    private LocalDate createdAt;

    private LocalDate updatedAt;

    private String note;
}
