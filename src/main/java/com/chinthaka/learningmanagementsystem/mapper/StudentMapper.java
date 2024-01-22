package com.chinthaka.learningmanagementsystem.mapper;

import com.chinthaka.learningmanagementsystem.dto.request.StudentSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.StudentResponseDto;
import com.chinthaka.learningmanagementsystem.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    StudentMapper studentMapper = Mappers.getMapper( StudentMapper.class );

    Student studentSaveDtoToEntity(StudentSaveDto studentSaveDto);

    @Mapping(target = "address.dateOfModify", ignore = true)
    StudentResponseDto entityToStudentGetResponseDto(Student student);

    List<StudentResponseDto> PageListStudentListToStudentResponseDro(Page<Student> students);

}
