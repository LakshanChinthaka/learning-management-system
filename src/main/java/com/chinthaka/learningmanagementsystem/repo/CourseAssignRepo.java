package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.dto.query.GetCourseDetails;
import com.chinthaka.learningmanagementsystem.entity.Course;
import com.chinthaka.learningmanagementsystem.entity.CourseAssign;
import com.chinthaka.learningmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CourseAssignRepo extends JpaRepository<CourseAssign,Long> {

    boolean existsByCourseAndStudent(Course course, Student student);

    @Query(value = "SELECT c.course_id AS courseId, c.course_code AS code, c.type AS type, c.medium AS medium, c.duration AS duration,  c.course_name AS courseName FROM course_assign ca JOIN course c ON ca.course_id = c.course_id WHERE ca.student_id = :id AND ca.course_id = c.course_id", nativeQuery = true)
    GetCourseDetails getCourseDetails(long id);
}

