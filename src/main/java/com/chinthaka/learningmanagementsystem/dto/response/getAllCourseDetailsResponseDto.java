package com.chinthaka.learningmanagementsystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class getAllCourseDetailsResponseDto {
    List<GetCourseDetailsDto> allSubjectAndCourse;
}
