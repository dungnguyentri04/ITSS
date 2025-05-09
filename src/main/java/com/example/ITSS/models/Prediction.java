package com.example.ITSS.models;

import com.example.ITSS.enums.RiskLevel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Prediction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;  // Mối quan hệ Nhiều - Một với Project

    private LocalDateTime estimatedCompletion;
    // Thời gian dự kiến hoàn thành
    private LocalDateTime actualCompletion;
    // Thời gian thực tế hoàn thành
    private float progressPercentage;  // Tỷ lệ tiến độ (0-100%)

    @Enumerated(EnumType.STRING)
    private RiskLevel delayRiskLevel;  // Mức độ rủi ro trì hoãn (e.g., CAO, TRUNG BÌNH, THẤP)

    private String delayReason;  // Lý do trì hoãn nếu có

    private LocalDateTime createdAt;
    // Thời gian tạo dự đoán
    private LocalDateTime updatedAt;  // Thời gian cập nhật dự đoán
}
