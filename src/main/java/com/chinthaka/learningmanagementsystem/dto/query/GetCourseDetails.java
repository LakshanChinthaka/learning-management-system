package com.chinthaka.learningmanagementsystem.dto.query;

import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;
import com.fasterxml.jackson.annotation.JsonFormat;


public interface GetCourseDetails {
    Long getCourseId();
    String getCode();
    CourseType getType();
    CourseMedium getMedium();
    String getDuration();
    String getCourseName();
}
