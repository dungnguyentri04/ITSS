package com.example.ITSS.repositories;

import com.example.ITSS.models.GitContribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface GitContributionRepository extends JpaRepository<GitContribution, Long> {
    public boolean existsByCommitHashAndProjectId(String commitHash, Long projectId);

    @Query("SELECT g.commitHash FROM GitContribution g WHERE g.projectId = :projectId")
    Set<String> findCommitHashesByProjectId(@Param("projectId") Long projectId);

    List<GitContribution> findByProjectId(Long projectId);

    GitContribution findByCommitHashAndProjectId(String commitHash, Long projectId);

    List<GitContribution> findByProjectIdAndGithubLink(Long projectId, String githubLink);
}
