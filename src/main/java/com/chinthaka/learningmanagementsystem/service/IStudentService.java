package com.chinthaka.learningmanagementsystem.service;

import com.chinthaka.learningmanagementsystem.dto.paginated.PaginatedStudentResponseDto;
import com.chinthaka.learningmanagementsystem.dto.request.StudentSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.StudentResponseDto;
import com.chinthaka.learningmanagementsystem.dto.response.StudentWithCourseResponseDto;
import com.chinthaka.learningmanagementsystem.enums.Gender;

import java.util.List;

public interface IStudentService {

    String saveStudent(StudentSaveDto studentSaveDto);

    StudentWithCourseResponseDto getStudentById(long StudentId);


    PaginatedStudentResponseDto getAllStudent(boolean activeStatus, int page, int size);

    String assignCourseToStudent(long studentId, long courseId);

    List<StudentResponseDto> filterByStatusAndGender(boolean activeStatus, Gender gender);
}
