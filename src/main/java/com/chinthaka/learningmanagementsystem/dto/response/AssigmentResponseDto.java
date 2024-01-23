package com.chinthaka.learningmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssigmentResponseDto {
    private Long assignmentId;
    private String code;
    private String name;
    private long courseId;
    private String courseName;

}
