package com.example.ITSS.dto.requestDto;

import com.example.ITSS.models.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectMemberRequestDto {
    private Long projectId;

    private String username;

    private String nameGithub;

    private String role;
}
