package com.chinthaka.learningmanagementsystem.dto.paginated;

import com.chinthaka.learningmanagementsystem.dto.query.getCourseAndSubjectDetails;
import com.chinthaka.learningmanagementsystem.dto.response.StudentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedStudentResponseDto {
    List<StudentResponseDto> studentResponseDtos;
    private int pageCount;
    private long pageDataCount;
    private long totalSubject;
}
