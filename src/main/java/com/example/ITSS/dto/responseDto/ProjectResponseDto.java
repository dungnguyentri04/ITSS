package com.example.ITSS.dto.responseDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectResponseDto {
    private String projectName;

    private String description;

    private String startDate;

    private String endDate;

    private Long leaderId;

    private Long createdBy;

    private String createdAt;

    //status
}
