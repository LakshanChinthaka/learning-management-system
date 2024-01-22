package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.entity.Assignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface AssignmentRepo extends JpaRepository<Assignment,Long> {

    boolean existsByCode(String code);

    Assignment findByAndCode(String assignmentCode);
}
