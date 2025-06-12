package com.example.ITSS.repositories;

import com.example.ITSS.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    public List<Document> findByProjectId(Long projectId);
}
