package com.example.ITSS.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRequestDto {
    private String projectName;

    private String description;

    private String startDate;

    private String endDate;

    private Long leaderId;

    private Long userCreatedId;
}
