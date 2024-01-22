package com.chinthaka.learningmanagementsystem.mapper;


import com.chinthaka.learningmanagementsystem.dto.request.SubjectSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.SubjectResponseByIdDto;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SubjectMapper {

    Subject subjectSaveDtoToEntity(SubjectSaveDto subjectSaveDto);
    List<SubjectResponseByIdDto> EntityToSubjectResponseDto(List<Subject> subjects);
    SubjectResponseByIdDto EntityToSubjectResponseByIdDto(Subject subject);
    List<SubjectResponseByIdDto> EntityToListSubjectResponseByIdDto(Page<Subject> subjects);

}
