package com.example.ITSS.repositories;

import com.example.ITSS.models.ProjectMember;
import com.example.ITSS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectMemberRepository extends JpaRepository<ProjectMember, Long> {
    public List<ProjectMember> findByProjectId(Long projectId);

    public List<ProjectMember> findByUserId(Long userId);
}
