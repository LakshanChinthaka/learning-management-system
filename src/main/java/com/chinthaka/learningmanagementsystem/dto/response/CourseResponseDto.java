package com.chinthaka.learningmanagementsystem.dto.response;

import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseResponseDto {
    private Long courseId;
    private String courseName;
    private String code;
    private String description;
    private CourseType type;
    private CourseMedium medium;
    private String duration;
    private boolean certificate;
}
