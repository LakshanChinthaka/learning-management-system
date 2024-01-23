package com.chinthaka.learningmanagementsystem.service;

import com.chinthaka.learningmanagementsystem.dto.request.AssignSubjectToCourseDto;
import com.chinthaka.learningmanagementsystem.dto.request.CourseSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.CourseResponseDto;
import com.chinthaka.learningmanagementsystem.dto.response.GetCourseDetailsDto;
import com.chinthaka.learningmanagementsystem.dto.response.getAllCourseDetailsResponseDto;
import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;

import java.util.List;

public interface ICourseService {
    String addCourse(CourseSaveDto courseSaveDto);

    String assignSubjectToCourse(AssignSubjectToCourseDto assignSubjectToCourseDto);

    String deleteAssignSubject(Long subjectId, Long courseId);

    GetCourseDetailsDto getCourseById(Long courseId);

    getAllCourseDetailsResponseDto getCourseWithSubject(boolean activeStatus);

    List<CourseResponseDto> filterByAllTypes(boolean activeStatus, boolean certificate, CourseMedium courseMedium, CourseType coursetype);
}
