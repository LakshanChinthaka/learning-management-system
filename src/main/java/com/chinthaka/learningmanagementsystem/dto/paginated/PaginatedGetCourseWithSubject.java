package com.chinthaka.learningmanagementsystem.dto.paginated;

import com.chinthaka.learningmanagementsystem.dto.query.getCourseAndSubjectDetails;
import com.chinthaka.learningmanagementsystem.dto.response.GetCourseDetailsDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(force = true)
@Data
public class PaginatedGetCourseWithSubject {
    private final List<GetCourseDetailsDto> getCourseDetails;
    List<getCourseAndSubjectDetails> subjectDetails;
    private int pageCount;
    private long pageDataCount;
    private long totalSubject;
}
