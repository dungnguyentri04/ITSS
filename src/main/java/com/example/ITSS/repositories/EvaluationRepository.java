package com.example.ITSS.repositories;

import com.example.ITSS.models.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EvaluationRepository extends JpaRepository<Evaluation, Long> {
    @Query("""
    SELECT 
        COUNT(e), 
        AVG(e.qualityScore), 
        AVG(e.spiritScore), 
        AVG(e.communicationScore), 
        AVG(e.teamworkScore)
    FROM Evaluation e
    WHERE e.evaluated.id = :evaluatedId
""")
    public Object getEvaluationStatsByEvaluatedId(@Param("evaluatedId") Long evaluatedId);

    public List<Evaluation> findByEvaluatedId(Long evaluatedId);

    public List<Evaluation> findByProjectId(Long projectId);
}
