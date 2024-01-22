package com.chinthaka.learningmanagementsystem.controller;


import com.chinthaka.learningmanagementsystem.service.IAssigmentDetailsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/v1/assignment-details")
@CrossOrigin
public class AssignmentDetailsController {

    private final IAssigmentDetailsService assigmentDetailsService;

    public AssignmentDetailsController(IAssigmentDetailsService assigmentDetailsService) {
        this.assigmentDetailsService = assigmentDetailsService;
    }

    @PostMapping(value = "upload/",params = {"file","assDetails_id","student-id","assigment-id"})
    public ResponseEntity<?> uploadAssigment(
            @RequestParam("file") MultipartFile file,
            @RequestParam("assDetails_id") long assDetailsId,
            @RequestParam("student-id") long studentId,
            @RequestParam("assigment-id") long assignmentId
            ) throws IOException {
        String uploadImage = assigmentDetailsService.uploadAssigment(file, assDetailsId,studentId,assignmentId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }
    @GetMapping("download/{assignment-id}")
    public ResponseEntity<?> downloadAssigmentFile(@PathVariable("assignment-id")  long assignmentId){

        byte[]  fileData = assigmentDetailsService.downloadFileByAssignmentId(assignmentId);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileData);


    }

}
