package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.dto.query.getCourseAndSubjectDetails;
import com.chinthaka.learningmanagementsystem.entity.Course;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import com.chinthaka.learningmanagementsystem.entity.SubjectAssign;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface SubjectAssignRepo extends JpaRepository<SubjectAssign, Long> {

    SubjectAssign findByCourseAndSubject(Course getCourse, Subject getSubject);

    @Query(value = "SELECT s.subject_id AS subjectId ,s.subject_name AS subjectName " +
            "FROM subject_assign sa " +
            "JOIN subject s ON sa.subject_id =s.subject_id " +
            "WHERE  sa.course_id = :courseId"
            ,nativeQuery = true)
    List<getCourseAndSubjectDetails> findGetSubjectDetails(Long courseId);


    @Query(value = "SELECT sa.subject_id AS subjectId, s.subject_name AS subjectName " +
            "FROM subject_assign sa " +
            "JOIN subject s ON s.subject_id = sa.subject_id " +
            "JOIN course c " +
            "ON sa.course_id = c.course_id " +
            "WHERE sa.course_id = :courseId AND  c.active_status =:activeStatus",
            nativeQuery = true)
    List<getCourseAndSubjectDetails> GetAllSubjectAssignDetails(boolean activeStatus, Long courseId);


}
