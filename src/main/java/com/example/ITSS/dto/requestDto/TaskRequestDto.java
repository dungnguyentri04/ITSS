package com.example.ITSS.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRequestDto {
    private Long projectId;

    private String assignee;

    private String createdBy;

    private String title;

    private String description;

    private String status;

    private LocalDate deadline;

    private String priority;
}
