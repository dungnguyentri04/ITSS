package com.example.ITSS.repositories;

import com.example.ITSS.models.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {
    public List<Document> findByProjectId(Long projectId);

    public List<Document> findByUploaderId(Long uploaderId);

    public List<Document> findByProjectIdAndUploaderId(Long projectId, Long uploaderId);

    public List<Document> findByProjectIdAndUploaderIdAndType(Long projectId, Long uploaderId, Document.DocumentType type);

    public List<Document> findByProjectIdAndType(Long projectId, Document.DocumentType type);

    public List<Document> findByProjectIdAndTypeAndUploaderId(Long projectId, Document.DocumentType type, Long uploaderId);

    public List<Document> findByProjectIdAndTypeAndUploaderIdAndTaskId(Long projectId, Document.DocumentType type, Long uploaderId, Long taskId);

    public List<Document> findByProjectIdAndTypeAndTaskId(Long projectId, Document.DocumentType type, Long taskId);
}
