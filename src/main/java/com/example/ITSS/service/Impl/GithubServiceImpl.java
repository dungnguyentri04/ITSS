package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.responseDto.GitContributionResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Class;
import com.example.ITSS.models.GitContribution;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.repositories.ClassRepository;
import com.example.ITSS.repositories.GitContributionRepository;
import com.example.ITSS.repositories.ProjectClassMemberRepository;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.service.GithubService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class GithubServiceImpl implements GithubService {
    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private GitContributionRepository gitContributionRepository;

    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<GitContributionResponseDto> getGithubData(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        List<ProjectClassMember> projectClassMembers = projectClassMemberRepository.findByProjectId(projectId);
        Map<String, String> githubNameToUsernameMap = projectClassMembers.stream()
                .filter(member -> member.getNameGithub() != null && member.getUsername() != null)
                .collect(Collectors.toMap(
                        ProjectClassMember::getNameGithub,
                        ProjectClassMember::getUsername
                ));

        List<GitContributionResponseDto> gitContributionResponseDtos = gitContributionRepository.findByProjectIdAndGithubLink(projectId, project.getGithubLink())
                .stream()
                .map(gitContribution -> {
                    GitContributionResponseDto gitContributionResponseDto = modelMapper.map(gitContribution, GitContributionResponseDto.class);
                    String username = githubNameToUsernameMap.get(gitContribution.getNameGithub());
                    if (username != null) {
                        gitContributionResponseDto.setUsername(username);
                    }
                    return gitContributionResponseDto;
                }).toList();

        return gitContributionResponseDtos;
    }

    @Override
    public String updateGithubData(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        String githubLink = project.getGithubLink();
        String token = project.getToken();
        if (githubLink == null || token == null) {
            throw new NotFoundException("Can't get github data");
        }
        List<Map<String, Object>> result = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "token " + token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        Set<String> existingShaCommit = gitContributionRepository.findCommitHashesByProjectId(projectId);

        //get list sha commit
        List<String> listShaCommit = new ArrayList<>();
        try {
            listShaCommit = getListShaCommit(githubLink, entity, restTemplate, existingShaCommit);
        }
        catch (Exception e) {
            return "Can't get github data";
        }
        for (String shaCommit : listShaCommit) {
            try {
                GitContribution gitContribution = getCommitData(githubLink, entity, restTemplate, shaCommit,  projectId);
                gitContributionRepository.save(gitContribution);
            } catch (Exception e) {
                continue;
            }
        }
        return "Update github data successfully";
    }

    private GitContribution getCommitData(String githubLink, HttpEntity<String> entity, RestTemplate restTemplate, String shaCommit, Long projectId) {
        String pullsUrl = String.format("https://api.github.com/repos/%s/commits/%s", githubLink, shaCommit);
        ResponseEntity<Map<String, Object>> pullsResponse = restTemplate.exchange(
                pullsUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}
        );
        Map<String, Object> response = pullsResponse.getBody(); // <Map>
        Map<String, Object> commitData = (Map<String, Object>) response.get("commit"); // <Map>
        Map<String, Object> stats = (Map<String, Object>) response.get("stats");
        Map<String, Object> author = (Map<String, Object>) commitData.get("author"); // <Map>
        String authorName = (String) author.get("name");
        String date = (String) author.get("date");
        String message = (String) commitData.get("message");
        Number additions = (Number) stats.get("additions");
        Number deletions = (Number) stats.get("deletions");
        Long linesAdded = additions.longValue();
        Long linesRemoved = deletions.longValue();
        GitContribution gitContribution = gitContributionRepository.findByCommitHashAndProjectId(shaCommit, projectId);
        if (gitContribution == null) {
            gitContribution = new GitContribution();
            gitContribution.setProjectId(projectId);
        }
        gitContribution.setCommitHash(shaCommit);
        gitContribution.setNameGithub(authorName);
        ZonedDateTime zonedDateTime = ZonedDateTime.parse(date);
        gitContribution.setCommitDate(zonedDateTime.toLocalDate());
        gitContribution.setLinesAdded(linesAdded);
        gitContribution.setLinesRemoved(linesRemoved);
        gitContribution.setMessage(message);
        gitContribution.setGithubLink(githubLink);
        return gitContribution;
    }



    private List<String> getListShaCommit(String githubLink, HttpEntity<String> entity, RestTemplate restTemplate, Set<String> existingShaCommit) {
        String pullsUrl = String.format("https://api.github.com/repos/%s/commits", githubLink);
        ResponseEntity<List<Map<String, Object>>> pullsResponse = restTemplate.exchange(
                pullsUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );
        return pullsResponse.getBody().stream()
                .map(response -> (String) response.get("sha"))
                .toList();
    }
}
