package com.example.ITSS.repositories;

import com.example.ITSS.models.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Class, Long> {
    boolean existsByCodeClass(String codeClass);

    Class findByCodeClass(String codeClass);

    @Query("SELECT c FROM Class c WHERE c.id = :id")
    Class findClassById(@Param("id") Long id);

    @Query("SELECT c FROM Class c WHERE c.id = :id AND c.teacher.id = :teacherId")
    Class findClassByIdAndTeacherId(@Param("id") Long id, @Param("teacherId") Long teacherId);

    @Query("SELECT c FROM Class c WHERE c.id = :id AND c.teacher.id = :teacherId AND c.subject.id = :subjectId")
    Class findClassByIdAndTeacherIdAndSubjectId(@Param("id") Long id, @Param("teacherId") Long teacherId, @Param("subjectId") Long subjectId);

    @Query("SELECT c FROM Class c WHERE c.id = :id AND c.subject.id = :subjectId")
    Class findClassByIdAndSubjectId(@Param("id") Long id, @Param("subjectId") Long subjectId);

    @Query("SELECT c FROM Class c WHERE c.id = :id AND c.teacher.id = :teacherId AND c.subject.id = :subjectId AND c.classroom.id = :classroomId")
    Class findClassByIdAndTeacherIdAndSubjectIdAndClassroomId(@Param("id") Long id, @Param("teacherId") Long teacherId, @Param("subjectId") Long subjectId, @Param("classroomId") Long classroomId);

}
