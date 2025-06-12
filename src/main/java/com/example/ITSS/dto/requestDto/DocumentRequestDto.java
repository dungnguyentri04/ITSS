package com.example.ITSS.dto.requestDto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DocumentRequestDto {
    @NotNull(message = "title is required")
    private String title;

    private Long taskId;

    private String description;

    private Long projectId;

    @NotNull(message = "type is required")
    private String type;

    @NotNull(message = "file is required")
    private MultipartFile file;

    private Long uploaderId;
}
