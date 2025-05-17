package com.example.ITSS.service;

import java.util.List;
import java.util.Map;

public interface GithubService {
    public List<Map<String ,Object>> getGithubData(Long projectId);
}
