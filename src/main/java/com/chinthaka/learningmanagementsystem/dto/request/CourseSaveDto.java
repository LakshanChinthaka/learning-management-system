package com.chinthaka.learningmanagementsystem.dto.request;


import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;



@NoArgsConstructor
@AllArgsConstructor
@Data
public class CourseSaveDto {
    private Long courseId;
    private String courseName;
    private String code;
    private String description;
    private CourseType type;
    private CourseMedium medium;
    private String duration;
    private boolean certificate;
    private boolean activeStatus;
    private LocalDateTime createData;
    private LocalDateTime lastModifyData;
}
