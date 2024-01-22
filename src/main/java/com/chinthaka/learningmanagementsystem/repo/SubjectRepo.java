package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface SubjectRepo extends JpaRepository<Subject,Long> {

    boolean existsBySubjectName(String subjectName);

    Page<Subject> findByActiveStatus(boolean activeStatus, Pageable pageable);

}
