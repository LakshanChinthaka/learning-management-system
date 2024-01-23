package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.dto.response.CourseResponseDto;
import com.chinthaka.learningmanagementsystem.entity.Course;;
import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface CourseRepo extends JpaRepository<Course,Long> {

    boolean existsByCourseNameAndCode(String courseName, String code);

    Page<Course> findByActiveStatus(boolean activeStatus, Pageable pageable);

    List<Course> findByActiveStatusAndCertificateAndMediumAndType(boolean activeStatus, boolean certificate, CourseMedium courseMedium, CourseType coursetype);
}
