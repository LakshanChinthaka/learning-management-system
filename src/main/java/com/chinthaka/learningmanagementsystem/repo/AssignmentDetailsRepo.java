package com.chinthaka.learningmanagementsystem.repo;

import com.chinthaka.learningmanagementsystem.entity.Assignment;
import com.chinthaka.learningmanagementsystem.entity.AssignmentDetails;
import com.chinthaka.learningmanagementsystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentDetailsRepo extends JpaRepository<AssignmentDetails, Long> {

    boolean existsByStudent(Student student);

    boolean existsByFileNameAndStudentAndAssignmentAndData(String filename, Student student, Assignment assignment, byte[] bytes);

    List<AssignmentDetails> findAllBy();
}
