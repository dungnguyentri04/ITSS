package com.example.ITSS.service;

import com.example.ITSS.dto.responseDto.GitContributionResponseDto;
import com.example.ITSS.models.GitContribution;

import java.util.List;
import java.util.Map;

public interface GithubService {
    public List<GitContributionResponseDto> getGithubData(Long projectId);

    public String updateGithubData(Long projectId);
}
