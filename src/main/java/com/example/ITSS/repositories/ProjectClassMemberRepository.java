package com.example.ITSS.repositories;

import com.example.ITSS.models.ProjectClassMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectClassMemberRepository extends JpaRepository<ProjectClassMember, Long> {
    public List<ProjectClassMember> findByProjectId(Long projectId);

    public List<ProjectClassMember> findByUserId(Long userId);
}
