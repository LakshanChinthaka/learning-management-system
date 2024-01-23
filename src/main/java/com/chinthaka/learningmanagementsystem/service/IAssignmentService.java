package com.chinthaka.learningmanagementsystem.service;


import com.chinthaka.learningmanagementsystem.dto.request.AssigmentAddDto;
import com.chinthaka.learningmanagementsystem.dto.response.AssigmentResponseDto;
import com.chinthaka.learningmanagementsystem.entity.AssignmentDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IAssignmentService {

    String addAssignment(AssigmentAddDto assigmentAddDto);


    String deleteAssignment(Long assigmentId);

    List<AssigmentResponseDto> filterByCourseAndAssigmentName(Long courseId, String assigmentName);
}
