package com.chinthaka.learningmanagementsystem.controller;

import com.chinthaka.learningmanagementsystem.dto.request.AssigmentAddDto;
import com.chinthaka.learningmanagementsystem.service.IAssignmentService;
import com.chinthaka.learningmanagementsystem.utils.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


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
}
