package com.example.ITSS.service.Impl;

import com.example.ITSS.dto.requestDto.DocumentRequestDto;
import com.example.ITSS.dto.responseDto.DocumentResponseDto;
import com.example.ITSS.exception.NotFoundException;
import com.example.ITSS.models.Document;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.Task;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.DocumentType;
import com.example.ITSS.repositories.DocumentRepository;
import com.example.ITSS.repositories.ProjectRepository;
import com.example.ITSS.repositories.TaskRepository;
import com.example.ITSS.repositories.UserRepository;
import com.example.ITSS.service.DocumentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public DocumentResponseDto uploadDocument(DocumentRequestDto documentRequestDto) {
        Project project = projectRepository.findById(documentRequestDto.getProjectId()).orElseThrow(
                () -> new NotFoundException("Project not found")
        );
        User uploader = userRepository.findById(documentRequestDto.getUploaderId()).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        Document document = modelMapper.map(documentRequestDto, Document.class);
        document.setUploader(uploader);
        document.setType(DocumentType.valueOf(documentRequestDto.getType()));

        //xu ly file
        MultipartFile file = documentRequestDto.getFile();
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

            // Xử lý tên file và extension
        String originalFilename = file.getOriginalFilename();
        String extension = "";

        int dotIndex = originalFilename.lastIndexOf('.');
        if (dotIndex >= 0) {
            extension = originalFilename.substring(dotIndex);
        }

        // Tạo tên file mới (UUID)
        String newFileName = UUID.randomUUID().toString() + "_" + originalFilename + extension;
        String filePath = uploadDir + "/" + newFileName;

        Path path = Paths.get(filePath);
        try {
            Files.copy(file.getInputStream(), path);
        } catch (Exception e) {
            throw new RuntimeException("Failed to upload file", e);
        }

        document.setName(newFileName);
        document.setUrl(filePath);
        document.setSize(file.getSize());
        document.setCreatedAt(LocalDate.now());

        documentRepository.save(document);
        // Lưu file vào đĩa

        DocumentResponseDto documentResponseDto = modelMapper.map(document, DocumentResponseDto.class);
        documentResponseDto.setUploaderId(uploader.getId());
        documentResponseDto.setUploaderName(uploader.getUserName());
        return documentResponseDto;
    }

    @Override
    public ResponseEntity<?> downloadDocument(Long documentId) {
        Document document = documentRepository.findById(documentId).orElseThrow(
                () -> new NotFoundException("Document not found")
        );
        try {
            String filename = document.getName();
            Path filePath = Paths.get(uploadDir).resolve(filename).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                throw new NotFoundException("Không tìm thấy file: " + filename);
            }

            // Tên file gốc để hiển thị khi tải về (bỏ phần UUID nếu muốn)
            String originalName = filename.substring(filename.indexOf("_") + 1);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + originalName + "\"")
                    .body(resource);

        } catch (MalformedURLException | NotFoundException e) {
            throw new RuntimeException("Không thể tải file: " +e);
        }
    }

    @Override
    public String deleteDocument(Long documentId) {
        Document document = documentRepository.findById(documentId).orElseThrow(
                () -> new NotFoundException("Document not found")
        );
        documentRepository.delete(document);
        return "Document with ID " + documentId + " deleted successfully";
    }

    @Override
    public List<DocumentResponseDto> getDocumentByProjectId(Long projectId) {
        List<Document> documents = documentRepository.findByProjectId(projectId);
        return documents.stream()
                .map(document -> {
                    DocumentResponseDto document1 = modelMapper.map(document, DocumentResponseDto.class);
                    document1.setUploaderId(document.getUploader().getId());
                    document1.setUploaderName(document.getUploader().getUserName());
                    DocumentType type = document.getType();
                    if (type.equals(DocumentType.TASK)) {
                        Task task = taskRepository.findById(document.getTaskId()).orElseThrow(
                                () -> new NotFoundException("Task not found")
                        );
                        document1.setNote(task.getTitle());
                    } else if (type.equals(DocumentType.REPORT)) {
                        document1.setNote("REPORT");
                    } else if (type.equals(DocumentType.GENERAL)) {
                        document1.setNote("GENERAL");
                    }
                    return document1;
                }).toList();
    }
}
