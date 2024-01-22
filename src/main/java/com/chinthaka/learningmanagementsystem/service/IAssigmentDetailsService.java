package com.chinthaka.learningmanagementsystem.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface IAssigmentDetailsService {

    String uploadAssigment(MultipartFile file, long assDetailsId, long studentId, long assignmentId) throws IOException;

    byte[] downloadFileByAssignmentId(long assignmentId);

}
