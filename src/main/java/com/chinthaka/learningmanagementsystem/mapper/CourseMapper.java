package com.chinthaka.learningmanagementsystem.mapper;

import com.chinthaka.learningmanagementsystem.dto.request.CourseSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.CourseResponseDto;
import com.chinthaka.learningmanagementsystem.entity.Course;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

   Course courseSaveDtoToEntity(CourseSaveDto courseSaveDto);
   CourseResponseDto EntityToCourseResponseDto(Course course);
   List<CourseResponseDto> EntityToCourseResponseDto(List<Course> course);
   List<CourseResponseDto> PageEntityListToCourseResponseDto(Page<Course> courses);


}
