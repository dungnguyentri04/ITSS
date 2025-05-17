package com.example.ITSS.service.Impl;

import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Project;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.service.GithubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.context.LifecycleAutoConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GithubServiceImpl implements GithubService {
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Map<String, Object>> getGithubData(Long projectId) {
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

        //get contributors
        List<String> contributors = getContributors(githubLink, entity, restTemplate);

        //get Data commit
        for (String contributor : contributors) {
            Map<String, Object> contributorCommit = new HashMap<>();
            contributorCommit.put("contributor", contributor);
            List<Map<String, Object>> listCommitsData = new ArrayList<>();
            List<String> listShaCommit = getListShaCommit(githubLink, entity, restTemplate, contributor);
            for (String sha : listShaCommit) {
                Map<String, Object> commitData = lisCommitData(githubLink, entity, restTemplate, sha);
                listCommitsData.add(commitData);
            }
            contributorCommit.put("commits", listCommitsData);
            result.add(contributorCommit);
        }
        return result;
    }

    private List<String> getContributors(String githubLink, HttpEntity<String> entity, RestTemplate restTemplate) {
        String pullsUrl = githubLink + "/contributors";
        List<String> result = null;
        ResponseEntity<List<Map<String, Object>>> pullsResponse = restTemplate.exchange(
                pullsUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );
        return pullsResponse.getBody().stream().map(
                response -> {
                    String login = (String) response.get("login");
                    return login;
                }
        ).toList();
    }

    private Map<String, Object> lisCommitData(String githubLink, HttpEntity<String> entity, RestTemplate restTemplate, String sha) {
        String pullsUrl = githubLink + "/commits/" + sha;
        ResponseEntity<Map<String, Object>> pullsResponse = restTemplate.exchange(
                pullsUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Map<String, Object>>() {}); // <Map>
        Map<String, Object> dataResponse = pullsResponse.getBody();
        Map<String, Object> commitData = (Map<String, Object>) dataResponse.get("commit"); // <Map>
        Map<String, Object> stats = (Map<String, Object>) commitData.get("stats"); // <Map>
        dataResponse.put("message", commitData.get("message"));
        dataResponse.put("comment_count", commitData.get("comment_count"));
        dataResponse.put("line_change", stats.get("total"));
        dataResponse.put("additions", stats.get("additions"));
        dataResponse.put("deletions", stats.get("deletions"));
        return dataResponse;
    }

    private List<String> getListShaCommit(String githubLink, HttpEntity<String> entity, RestTemplate restTemplate, String author) {
        String pullsUrl = githubLink + "/commits?author=" + author;
        ResponseEntity<List<Map<String, Object>>> pullsResponse = restTemplate.exchange(
                pullsUrl,
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Map<String, Object>>>() {}
        );
        return pullsResponse.getBody().stream().map(
                response -> {
                    String sha = (String) response.get("sha");
                    return sha;
                }
        ).toList();
    }


}
