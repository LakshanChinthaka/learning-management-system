package com.chinthaka.learningmanagementsystem.mapper;

import com.chinthaka.learningmanagementsystem.entity.Subject;
import com.chinthaka.learningmanagementsystem.entity.SubjectAssign;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectAssignMapper {

    List<SubjectAssign> SubjectEntityListToSubjectAssignDto(List<Subject> subjects);


}
