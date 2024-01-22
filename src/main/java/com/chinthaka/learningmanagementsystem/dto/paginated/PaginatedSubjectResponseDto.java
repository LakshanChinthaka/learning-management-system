package com.chinthaka.learningmanagementsystem.dto.paginated;

import com.chinthaka.learningmanagementsystem.dto.response.SubjectResponseByIdDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedSubjectResponseDto {
    List<SubjectResponseByIdDto> subjectResponseByIdDtos;
    private int pageCount;
    private long pageDataCount;
    private long totalSubject;
}
