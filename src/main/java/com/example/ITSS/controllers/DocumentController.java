package com.example.ITSS.controllers;

import com.example.ITSS.config.OurUserInfoDetail;
import com.example.ITSS.dto.ApiResponse;
import com.example.ITSS.dto.requestDto.DocumentRequestDto;
import com.example.ITSS.dto.responseDto.DocumentResponseDto;
import com.example.ITSS.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class DocumentController {
    @Autowired
    private DocumentService documentService;

    @PostMapping("/documents/upload")
    public ResponseEntity<?> uploadDocument(@ModelAttribute @Valid DocumentRequestDto documentRequestDto, Authentication authentication) {
        OurUserInfoDetail ourUserDetailService = (OurUserInfoDetail) authentication.getPrincipal();
        documentRequestDto.setUploaderId(ourUserDetailService.getId());
        DocumentResponseDto documentResponseDto = documentService.uploadDocument(documentRequestDto);
        ApiResponse<DocumentResponseDto> apiResponse = new ApiResponse<>();
        apiResponse.setData(documentResponseDto);
        apiResponse.setMessage("Document uploaded successfully");
        apiResponse.setStatus("success");
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/documents/download")
    public ResponseEntity<?> downloadDocument(@RequestParam("documentId") Long documentId){
        return documentService.downloadDocument(documentId);
    }

    @GetMapping("/documents/getDocumentByProjectId")
    public ResponseEntity<?> getDocumentByProjectId(@RequestParam("projectId") Long projectId){
        List<DocumentResponseDto> documentResponseDtoList = documentService.getDocumentByProjectId(projectId);
        ApiResponse<List<DocumentResponseDto>> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Document get successfully");
        apiResponse.setData(documentResponseDtoList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @DeleteMapping("/documents/deleteDocument")
    public ResponseEntity<?> deleteDocument(@RequestParam("documentId") Long documentId){
        String documentResponseDto = documentService.deleteDocument(documentId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setStatus("success");
        apiResponse.setMessage("Document deleted successfully");
        apiResponse.setData(documentResponseDto);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
}
