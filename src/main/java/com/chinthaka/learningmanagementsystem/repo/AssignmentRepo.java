package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.entity.Assignment;
import com.chinthaka.learningmanagementsystem.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface AssignmentRepo extends JpaRepository<Assignment,Long> {

    boolean existsByCode(String code);

    Assignment findByAndCode(String assignmentCode);

    List<Assignment> findByCourseAndNameIgnoreCase(Course courseId, String assigmentName);
}
