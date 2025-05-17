package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDto {
    private Long id;

    private String assignee;

    private String createdBy;

    private Long projectId;

    private String title;

    private String description;

    private String status;

    private LocalDate deadline;

    private LocalDate createdAt;

    private LocalDate updatedAt;
}
