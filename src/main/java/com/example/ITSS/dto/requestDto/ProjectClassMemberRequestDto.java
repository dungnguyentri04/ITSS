package com.example.ITSS.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectClassMemberRequestDto {
    private Long projectId;

    private String username;

    private String nameGithub;

    private String role;
}
