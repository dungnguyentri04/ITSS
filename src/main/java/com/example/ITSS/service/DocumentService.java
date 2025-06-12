package com.example.ITSS.service;

import com.example.ITSS.dto.requestDto.DocumentRequestDto;
import com.example.ITSS.dto.responseDto.DocumentResponseDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DocumentService {
    public DocumentResponseDto uploadDocument(DocumentRequestDto documentRequestDto);

    public ResponseEntity<?> downloadDocument(Long documentId);

    public String deleteDocument(Long documentId);

    public List<DocumentResponseDto> getDocumentByProjectId(Long projectId);
}
