package com.example.ITSS.service;

import com.example.ITSS.dto.MemberStatsDTO;
import com.example.ITSS.dto.MemberStatsDTO.TaskInfo;
import com.example.ITSS.dto.MemberStatsDTO.EvaluationInfo;
import com.example.ITSS.models.Project;
import com.example.ITSS.models.ProjectClassMember;
import com.example.ITSS.models.Task;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.TaskPriority;
import com.example.ITSS.models.enums.TaskStatus;
import com.example.ITSS.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class MemberStatService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EvaluationRepository evaluationRepository;

    @Autowired
    private ProjectClassMemberRepository projectClassMemberRepository;

    @Autowired
    private UserRepository UserRepository;

    public List<MemberStatsDTO> getMemberStats(Long projectId) {
        Project project = projectRepository.findById(projectId).orElseThrow(
                () -> new RuntimeException("Project not found")
        );
        List<ProjectClassMember> projectClassMembers = projectClassMemberRepository.findByProjectId(projectId);
        List<MemberStatsDTO> memberStatsDTOList = new ArrayList<>();
        for (ProjectClassMember projectClassMember : projectClassMembers) {
            User user = projectClassMember.getUser();
            Long userId = user.getId();
            MemberStatsDTO memberStatsDTO = new MemberStatsDTO();
            memberStatsDTO.setMemberId(projectClassMember.getId());
            memberStatsDTO.setName(projectClassMember.getUsername());
            memberStatsDTO.setProjectId(project.getId());

            //task info
            TaskInfo taskInfo = getTaskInfo(userId, projectId);
            memberStatsDTO.setTaskInfo(taskInfo);

            //evaluation
            EvaluationInfo evaluationInfo = getEvaluation(userId);
            memberStatsDTO.setEvaluationInfo(evaluationInfo);

            memberStatsDTOList.add(memberStatsDTO);
        }
        return memberStatsDTOList;
    }



    private TaskInfo getTaskInfo(Long assigneeId, Long projectId) {
        // Lấy danh sách task của user

        long highTasksCompleted = taskRepository.countByPriorityAndStatus(assigneeId, TaskPriority.HIGH, TaskStatus.FINISHED, projectId);
        long mediumTasksCompleted = taskRepository.countByPriorityAndStatus(assigneeId, TaskPriority.MEDIUM, TaskStatus.FINISHED, projectId);
        long lowTasksCompleted = taskRepository.countByPriorityAndStatus(assigneeId, TaskPriority.LOW, TaskStatus.FINISHED, projectId);
        long highTasksNotCompleted = taskRepository.countByPriorityAndStatus(assigneeId, TaskPriority.HIGH, TaskStatus.NOT_FINISHED, projectId);
        long mediumTasksNotCompleted = taskRepository.countByPriorityAndStatus(assigneeId, TaskPriority.MEDIUM, TaskStatus.NOT_FINISHED, projectId);
        long lowTasksNotCompleted = taskRepository.countByPriorityAndStatus(assigneeId, TaskPriority.LOW, TaskStatus.NOT_FINISHED, projectId);
        long highTasksProject = taskRepository.countByProjectIdAndStatusAndPriority(projectId, TaskStatus.FINISHED, TaskPriority.HIGH);
        long mediumTasksProject = taskRepository.countByProjectIdAndStatusAndPriority(projectId, TaskStatus.FINISHED, TaskPriority.MEDIUM);
        long lowTasksProject = taskRepository.countByProjectIdAndStatusAndPriority(projectId, TaskStatus.FINISHED, TaskPriority.LOW);


        TaskInfo taskInfo = new TaskInfo();
//        taskInfo.setHighTasks(taskRepository.countByPriority(assigneeId, TaskPriority.HIGH));
//        taskInfo.setMediumTasks(taskRepository.countByPriority(assigneeId, TaskPriority.MEDIUM));
//        taskInfo.setLowTasks(taskRepository.countByPriority(assigneeId, TaskPriority.LOW));
        taskInfo.setNumberOfTasks(taskRepository.countByAssignee(assigneeId));
        taskInfo.setHighTasksCompleted(highTasksCompleted);
        taskInfo.setMediumTasksCompleted(mediumTasksCompleted);
        taskInfo.setLowTasksCompleted(lowTasksCompleted);
        taskInfo.setHighTasksNotCompleted(highTasksNotCompleted);
        taskInfo.setMediumTasksNotCompleted(mediumTasksNotCompleted);
        taskInfo.setLowTasksNotCompleted(lowTasksNotCompleted);
        taskInfo.setLateTasks(taskRepository.countOverdateTask(LocalDate.now(), assigneeId, projectId));
        taskInfo.setTaskCompleted(highTasksCompleted + mediumTasksCompleted + lowTasksCompleted);
        taskInfo.setTaskNotCompleted(highTasksNotCompleted + mediumTasksNotCompleted + lowTasksNotCompleted);

        long rawScore = highTasksCompleted * 3 + mediumTasksCompleted * 2 + lowTasksCompleted;
        long minusScore = highTasksNotCompleted  + mediumTasksNotCompleted  + lowTasksNotCompleted;
        long totalScore = highTasksProject * 3 + mediumTasksProject * 2 + lowTasksProject;
        double averageScore = ((double) rawScore - minusScore) / totalScore * 40;
        if (averageScore < 0) {
            averageScore = 0;
        }
        else if (averageScore > 10) {
            averageScore = 10;
        }
        taskInfo.setAverageScore(averageScore);
        return taskInfo;
    }

    private EvaluationInfo getEvaluation(Long assigneeId) {
        EvaluationInfo evaluation = new EvaluationInfo();
        Object[] result = (Object[]) evaluationRepository.getEvaluationStatsByEvaluatedId(assigneeId);
//        Long numberOfEvaluations = ((Number) result[0]).longValue();
        Double averageQualityScore = (Double) result[1];
        Double averageSpiritScore = (Double) result[2];
        Double averageCommunicationScore = (Double) result[3];
        Double averageTeamworkScore = (Double) result[4];
//        evaluation.setNumberOfEvaluations(numberOfEvaluations);
        evaluation.setAverageQualityScore(averageQualityScore);
        evaluation.setAverageSpiritScore(averageSpiritScore);
        evaluation.setAverageCommunicationScore(averageCommunicationScore);
        evaluation.setAverageTeamworkScore(averageTeamworkScore);
        evaluation.setAverageScore((averageQualityScore + averageSpiritScore + averageCommunicationScore + averageTeamworkScore) / 4 * 2);
        return evaluation;
    }
}
