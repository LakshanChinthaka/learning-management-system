package com.chinthaka.learningmanagementsystem.dto.response;

import com.chinthaka.learningmanagementsystem.dto.query.getCourseAndSubjectDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class GetCourseDetailsDto {
    private CourseResponseDto course;
    List<getCourseAndSubjectDetails> assignSubject;
}
