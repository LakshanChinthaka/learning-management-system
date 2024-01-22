package com.chinthaka.learningmanagementsystem.service.impl;


import com.chinthaka.learningmanagementsystem.dto.request.SubjectSaveDto;
import com.chinthaka.learningmanagementsystem.dto.paginated.PaginatedSubjectResponseDto;
import com.chinthaka.learningmanagementsystem.dto.response.SubjectResponseByIdDto;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import com.chinthaka.learningmanagementsystem.exception.NotFoundException;
import com.chinthaka.learningmanagementsystem.mapper.SubjectMapper;
import com.chinthaka.learningmanagementsystem.repo.SubjectRepo;
import com.chinthaka.learningmanagementsystem.service.ISubjectService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class SubjectServiceImpl implements ISubjectService {

    private final SubjectRepo subjectRepo;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepo subjectRepo, SubjectMapper subjectMapper) {
        this.subjectRepo = subjectRepo;
        this.subjectMapper = subjectMapper;
    }

    @Override
    public String addSubject(SubjectSaveDto subjectSaveDto) {
        if (!subjectRepo.existsBySubjectName(subjectSaveDto.getSubjectName())) {
            final Subject subject = subjectMapper.subjectSaveDtoToEntity(subjectSaveDto);
            subjectRepo.save(subject);
            return subjectSaveDto.getSubjectName() + " subject is Successfully Added";
        }
        throw new NotFoundException(subjectSaveDto.getSubjectName() + " subject is already exist");
    }

    @Override
    public SubjectResponseByIdDto getBySubjectId(long subjectId) {
        if (subjectRepo.existsById(subjectId)) {
            final Subject subject = subjectRepo.findById(subjectId).orElse(null);
            return subjectMapper.EntityToSubjectResponseByIdDto(subject);
        }
        throw new NotFoundException("Subject Id-" + subjectId + " not found");
    }
    @Override
    public PaginatedSubjectResponseDto getAllSubject( boolean activeStatus, int page, int size) {
        final Page<Subject> subjects = subjectRepo.findByActiveStatus(activeStatus,PageRequest.of(page, size));
        if (!subjects.isEmpty()) {
            return new PaginatedSubjectResponseDto(
                    subjectMapper.EntityToListSubjectResponseByIdDto(subjects),
                    subjects.getTotalPages(),
                    subjects.getNumberOfElements(),
                    subjects.getTotalElements()
            );
        }
        throw new NotFoundException("No Subject Data");
    }
}
