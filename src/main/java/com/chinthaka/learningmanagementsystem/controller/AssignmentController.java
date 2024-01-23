package com.chinthaka.learningmanagementsystem.controller;

import com.chinthaka.learningmanagementsystem.dto.request.AssigmentAddDto;
import com.chinthaka.learningmanagementsystem.dto.response.AssigmentResponseDto;
import com.chinthaka.learningmanagementsystem.dto.response.GetCourseDetailsDto;
import com.chinthaka.learningmanagementsystem.service.IAssignmentService;
import com.chinthaka.learningmanagementsystem.utils.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/assignment")
@CrossOrigin
public class AssignmentController {

    private final IAssignmentService assignmentService;

    public AssignmentController(IAssignmentService assignmentService) {
        this.assignmentService = assignmentService;
    }

    @PostMapping(path = "/add")
    public ResponseEntity<StandardResponse> addAssignment(
            @RequestBody final AssigmentAddDto assigmentAddDto) {
        final String data = assignmentService.addAssignment(assigmentAddDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Success", data), HttpStatus.OK
        );
    }

    @DeleteMapping(path = "/delete-assigment",params = {"assigment_id"})
    public ResponseEntity<StandardResponse> deleteAssignment(
            @RequestParam(name = "assigment_id") Long assigmentId
    ){
        String data = assignmentService.deleteAssignment(assigmentId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data), HttpStatus.OK
        );
    }

    @GetMapping(path = "/filter-by-course-id-and-name",params = {"course_id","name"})
    public ResponseEntity<StandardResponse> filterByCourseAndAssigmentName(
            @RequestParam(name = "course_id") Long courseId,
               @RequestParam(name = "name") String assigmentName
    ){
        final List<AssigmentResponseDto> data = assignmentService.filterByCourseAndAssigmentName(courseId,assigmentName);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data), HttpStatus.OK
        );
    }
}
