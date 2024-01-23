package com.chinthaka.learningmanagementsystem.mapper;


import com.chinthaka.learningmanagementsystem.dto.response.AssigmentResponseDto;
import com.chinthaka.learningmanagementsystem.entity.Assignment;
import org.mapstruct.Mapper;
import java.util.List;
@Mapper(componentModel = "spring")
public interface AssignmentMapper {

//    Assignment AssigmentAddDtoToEntity(AssigmentAddDto assigmentAddDto);
    List<AssigmentResponseDto> EntityListToAssigmentResponseDto(List<Assignment> assignments);
}
