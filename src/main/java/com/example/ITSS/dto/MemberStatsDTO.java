package com.example.ITSS.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberStatsDTO {
    private String name;

    private Long memberId;

    private Long projectId;

    private Double ovalAverageScore;

    private EvaluationInfo evaluationInfo;

    private TaskInfo taskInfo;

    private String githubName;

    private long totalTaskProject;

    @Data
    public static class EvaluationInfo {
        private Long numberOfEvaluations;
        private Double averageQualityScore;
        private Double averageSpiritScore;
        private Double averageCommunicationScore;
        private Double averageTeamworkScore;
        private Double averageScore;
    }

    @Data
    public static class TaskInfo {
        private long numberOfTasks;
        private long highTasks;
        private long mediumTasks;
        private long lowTasks;
        private long highTasksCompleted;
        private long mediumTasksCompleted;
        private long lowTasksCompleted;
        private long highTasksNotCompleted;
        private long mediumTasksNotCompleted;
        private long lowTasksNotCompleted;
        private long lateTasks;
        private long taskCompleted;
        private long taskNotCompleted;
        private Double averageScore;
    }
}
