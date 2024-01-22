package com.chinthaka.learningmanagementsystem.dto.response;

import com.chinthaka.learningmanagementsystem.dto.query.GetCourseDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentWithCourseResponseDto {
    StudentResponseDto student;
    GetCourseDetails course;

}
