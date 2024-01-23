package com.chinthaka.learningmanagementsystem.utils;

import com.chinthaka.learningmanagementsystem.entity.Assignment;
import com.chinthaka.learningmanagementsystem.entity.Course;
import com.chinthaka.learningmanagementsystem.entity.Student;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import com.chinthaka.learningmanagementsystem.exception.NotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

public class EntityUtils {
    //Reusable code
    public static Student getStudentDetails(long studentId,JpaRepository<Student,Long> studentRepo){
        return studentRepo.findById(studentId)
                .orElseThrow(()-> new NotFoundException("Student Id- "+ studentId + " not found"));
    }

    public static Course getCourseDetails(long courseId, JpaRepository<Course,Long> courseRepo){
        return courseRepo.findById(courseId)
                .orElseThrow(()-> new NotFoundException("Student Id- "+ courseId + " not found"));
    }

    public static Subject getSubjectDetails(long subjectId, JpaRepository<Subject,Long> subjectRepo){
        return subjectRepo.findById(subjectId)
                .orElseThrow(()-> new NotFoundException("Subject Id- "+ subjectId + " not found"));
    }

    public static Assignment getAssigmentDetails(long assigmentId, JpaRepository<Assignment,Long> assigmentRepo){
        return assigmentRepo.findById(assigmentId)
                .orElseThrow(()-> new NotFoundException("Assigment Id- "+ assigmentId + " not found"));
    }
}
