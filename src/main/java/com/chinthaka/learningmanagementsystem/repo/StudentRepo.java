package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.entity.Student;
import com.chinthaka.learningmanagementsystem.enums.Gender;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface StudentRepo extends JpaRepository<Student,Long> {

    boolean existsByNic(String nic);

    Page<Student> findByActiveStatus(boolean activeStatus, Pageable pageable);

    List<Student> findByActiveStatusAndGender(boolean activeStatus, Gender gender);
}
