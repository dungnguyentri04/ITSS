package com.example.ITSS.dto.responseDto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GitContributionResponseDto {
        private Long id;

        private Long projectId;

        private String commitHash;

        private Long userId;

        private String nameGithub;

        private String username;

        private LocalDate commitDate;

        private Long linesAdded;

        private Long linesRemoved;

        private LocalDate createdAt;

        private LocalDate updatedAt;

        private String message;

        private String githubLink;
}

