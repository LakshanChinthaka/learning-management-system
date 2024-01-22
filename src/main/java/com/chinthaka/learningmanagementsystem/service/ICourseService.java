package com.chinthaka.learningmanagementsystem.service;

import com.chinthaka.learningmanagementsystem.dto.request.AssignSubjectToCourseDto;
import com.chinthaka.learningmanagementsystem.dto.request.CourseSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.GetCourseDetailsDto;
import com.chinthaka.learningmanagementsystem.dto.response.getAllCourseDetailsResponseDto;

public interface ICourseService {
    String addCourse(CourseSaveDto courseSaveDto);

    String assignSubjectToCourse(AssignSubjectToCourseDto assignSubjectToCourseDto);

    String deleteAssignSubject(Long subjectId, Long courseId);

    GetCourseDetailsDto getCourseById(Long courseId);

    getAllCourseDetailsResponseDto getCourseWithSubject(boolean activeStatus);
}
