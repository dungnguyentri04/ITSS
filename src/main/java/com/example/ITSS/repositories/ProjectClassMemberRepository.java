package com.example.ITSS.repositories;

import com.example.ITSS.models.Class;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectClassMemberRepository extends JpaRepository<ProjectClassMember, Long> {
    public List<ProjectClassMember> findByClassroomId(Long classId);

    public List<ProjectClassMember> findByProjectId(Long projectId);

    public List<ProjectClassMember> findByUserId(Long userId);

    public ProjectClassMember findByUserAndClassroom(User user, Class aClass);

    @Query("SELECT COUNT(pcm) FROM ProjectClassMember pcm WHERE pcm.classroom.id = :classId")
    long countByClassId(Long classId);

    @Query("SELECT COUNT(pcm) FROM ProjectClassMember pcm WHERE pcm.projectId = :projectId")
    long countByProjectId(Long projectId);

    public ProjectClassMember findByProjectIdAndUserId(Long projectId, Long userId);
}
