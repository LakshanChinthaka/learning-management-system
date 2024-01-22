package com.chinthaka.learningmanagementsystem.dto.request;

import com.chinthaka.learningmanagementsystem.entity.Course;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AssigmentAddDto {
    private Long assignmentId;
    private String code;
    private String name;
    private long course;
    private long subject;
}
