package com.chinthaka.learningmanagementsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class AssignSubjectToCourseDto {
    private Long assignId;
    private LocalDateTime assignData;
    Long course;
    List<Long> subjectId;
}

