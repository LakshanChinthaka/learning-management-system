package com.chinthaka.learningmanagementsystem.service;


import com.chinthaka.learningmanagementsystem.dto.request.AssigmentAddDto;
import com.chinthaka.learningmanagementsystem.entity.AssignmentDetails;
import org.springframework.web.multipart.MultipartFile;

public interface IAssignmentService {

    String addAssignment(AssigmentAddDto assigmentAddDto);


}
