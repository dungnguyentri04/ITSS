package com.example.ITSS.repositories;

import com.example.ITSS.models.Task;
import com.example.ITSS.models.User;
import com.example.ITSS.models.enums.TaskPriority;
import com.example.ITSS.models.enums.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findByAssigneeId(Long assigneeId);

    // Tổng số task
    @Query("SELECT COUNT(t) FROM Task t WHERE t.assignee.id = :assigneeId")
    long countByAssignee(@Param("assigneeId") Long assigneeId);

    // Tổng task theo độ ưu tiên
    @Query("SELECT COUNT(t) FROM Task t WHERE t.assignee.id = :assigneeId AND t.priority = :priority AND t.project.id = :projectId")
    long countByPriority(@Param("assigneeId") Long assigneeId, @Param("priority") TaskPriority priority, @Param("projectId") Long projectId);

    // Tổng task theo độ ưu tiên và trạng thái
    @Query("SELECT COUNT(t) FROM Task t WHERE t.assignee.id = :assigneeId AND t.priority = :priority AND t.status = :status AND t.project.id = :projectId")
    long countByPriorityAndStatus(@Param("assigneeId") Long assigneeId,
                                  @Param("priority") TaskPriority priority,
                                  @Param("status") TaskStatus status,
                                  @Param("projectId") Long projectId);

    // Tổng task chưa hoàn thành theo ưu tiên
    @Query("SELECT COUNT(t) FROM Task t WHERE t.assignee.id = :assigneeId AND t.priority = :priority AND t.status <> :status AND t.project.id = :projectId")
    long countByPriorityAndNotStatus(@Param("assigneeId") Long assigneeId,
                                     @Param("priority") TaskPriority priority,
                                     @Param("status") TaskStatus status,
                                     @Param("projectId") Long projectId);

    @Query("SELECT COUNT(t) FROM Task t WHERE t.assignee.id = :assigneeId AND t.deadline < :date AND t.project.id = :projectId")
    long countOverdateTask(@Param("date") LocalDate date, @Param("assigneeId") Long assigneeId, @Param("projectId") Long projectId);

    @Query("SELECT COUNT(t) FROM Task t where t.project.id = :projectId")
    long countByProjectId(@Param("projectId") Long projectId);

    @Query("SELECT COUNT(t) FROM Task t where t.project.id = :projectId AND t.status = :status")
    long countByProjectIdAndStatus(@Param("projectId") Long projectId, @Param("status") TaskStatus status);

    @Query("SELECT COUNT(t) FROM Task t where t.project.id = :projectId AND t.status = :status AND t.priority = :priority")
    long countByProjectIdAndStatusAndPriority(Long projectId, TaskStatus status, TaskPriority priority);

}
