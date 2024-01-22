package com.chinthaka.learningmanagementsystem.service;

import com.chinthaka.learningmanagementsystem.dto.paginated.PaginatedSubjectResponseDto;
import com.chinthaka.learningmanagementsystem.dto.request.SubjectSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.SubjectResponseByIdDto;

public interface ISubjectService {
    String addSubject(SubjectSaveDto subjectSaveDto);
    SubjectResponseByIdDto getBySubjectId(long subjectId);
    PaginatedSubjectResponseDto getAllSubject(boolean activeStatus, int page, int size);
}
