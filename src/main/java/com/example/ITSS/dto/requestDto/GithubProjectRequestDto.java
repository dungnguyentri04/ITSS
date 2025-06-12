package com.example.ITSS.dto.requestDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GithubProjectRequestDto {
    private String githubLink;

    private String token;
}
