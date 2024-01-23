package com.chinthaka.learningmanagementsystem.controller;


import com.chinthaka.learningmanagementsystem.dto.request.AssignSubjectToCourseDto;
import com.chinthaka.learningmanagementsystem.dto.request.CourseSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.CourseResponseDto;
import com.chinthaka.learningmanagementsystem.dto.response.GetCourseDetailsDto;
import com.chinthaka.learningmanagementsystem.dto.response.getAllCourseDetailsResponseDto;
import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;
import com.chinthaka.learningmanagementsystem.service.ICourseService;
import com.chinthaka.learningmanagementsystem.utils.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/course")
@CrossOrigin
public class CourseController {

    private final ICourseService courseService;

    public CourseController(ICourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<StandardResponse> addCourse(
            @RequestBody final CourseSaveDto courseSaveDto) {
        final String data = courseService.addCourse(courseSaveDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", data), HttpStatus.OK
        );
    }

    @PostMapping(path = "/assign-subject")
    public ResponseEntity<StandardResponse> assignSubjectToCourse(
            @RequestBody final AssignSubjectToCourseDto assignSubjectToCourseDto) {
        final String data = courseService.assignSubjectToCourse(assignSubjectToCourseDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", data), HttpStatus.OK
        );
    }
    @DeleteMapping(path = "/delete-assign-subject",params = {"subject_id","course_id"})
    public ResponseEntity<StandardResponse> deleteAssignSubject(
            @RequestParam(name = "subject_id") Long subjectId,
            @RequestParam(name = "course_id") Long courseId
    ){
       String data = courseService.deleteAssignSubject(subjectId,courseId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data), HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-course-by-id",params = {"courseId"})
    public ResponseEntity<StandardResponse> getCourseById(
            @RequestParam(name = "courseId") Long courseId
    ){
        final GetCourseDetailsDto data = courseService.getCourseById(courseId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data), HttpStatus.OK
        );
    }

    @GetMapping(value = "/get-course-with-subject",params = {"status"})
    public ResponseEntity<StandardResponse> getCourseWithSubject(
            @RequestParam(name = "status") boolean activeStatus
    ){
        final getAllCourseDetailsResponseDto data = courseService
                .getCourseWithSubject(activeStatus);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data),HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-course-by-all-types",params = {"status","certificate","medium","type"})
    public ResponseEntity<StandardResponse> filterByAllTypes(
            @RequestParam(name = "status",required = false,defaultValue = "1") boolean activeStatus,
            @RequestParam(name = "certificate",required = false,defaultValue = "1") boolean certificate,
            @RequestParam(name = "medium") CourseMedium courseMedium,
            @RequestParam(name = "type") CourseType coursetype
    ){
        final List<CourseResponseDto> data = courseService
                .filterByAllTypes(activeStatus,certificate,courseMedium,coursetype);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data), HttpStatus.OK
        );
    }
}