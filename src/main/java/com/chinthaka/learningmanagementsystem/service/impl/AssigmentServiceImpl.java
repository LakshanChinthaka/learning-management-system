package com.chinthaka.learningmanagementsystem.service.impl;

import com.chinthaka.learningmanagementsystem.dto.request.AssigmentAddDto;
import com.chinthaka.learningmanagementsystem.entity.Assignment;
import com.chinthaka.learningmanagementsystem.entity.AssignmentDetails;
import com.chinthaka.learningmanagementsystem.entity.Course;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import com.chinthaka.learningmanagementsystem.exception.AlreadyExistException;
import com.chinthaka.learningmanagementsystem.exception.HandleException;
import com.chinthaka.learningmanagementsystem.exception.NotFoundException;
import com.chinthaka.learningmanagementsystem.mapper.AssignmentMapper;
import com.chinthaka.learningmanagementsystem.repo.AssignmentDetailsRepo;
import com.chinthaka.learningmanagementsystem.repo.AssignmentRepo;
import com.chinthaka.learningmanagementsystem.repo.CourseRepo;
import com.chinthaka.learningmanagementsystem.repo.SubjectRepo;
import com.chinthaka.learningmanagementsystem.service.IAssignmentService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AssigmentServiceImpl implements IAssignmentService {

    private final AssignmentRepo assignmentRepo;
    private final AssignmentMapper assignmentMapper;
    private final CourseRepo courseRepo;
    private final SubjectRepo subjectRepo;
    private final AssignmentDetailsRepo assignmentDetailsRepo;

    public AssigmentServiceImpl(AssignmentRepo assignmentRepo, AssignmentMapper assignmentMapper, CourseRepo courseRepo, SubjectRepo subjectRepo, AssignmentDetailsRepo assignmentDetailsRepo) {
        this.assignmentRepo = assignmentRepo;
        this.assignmentMapper = assignmentMapper;
        this.courseRepo = courseRepo;
        this.subjectRepo = subjectRepo;
        this.assignmentDetailsRepo = assignmentDetailsRepo;
    }

    @Override
    public String addAssignment(AssigmentAddDto assigmentAddDto) {
        if (null == assigmentAddDto){
            throw new NotFoundException("No details provided");
        }
        if (assignmentRepo.existsByCode(assigmentAddDto.getCode())){
            throw new AlreadyExistException("Assignment Already Exist");
        }
        final Course course = courseRepo.findById(assigmentAddDto.getCourse())
                .orElseThrow(()->new NotFoundException("Course not found"));
        final Subject subject = subjectRepo.findById(assigmentAddDto.getSubject())
                .orElseThrow(()->new NotFoundException("Subject not found"));
        try {
            Assignment assignment = new Assignment(
                    assigmentAddDto.getAssignmentId(),
                    assigmentAddDto.getCode(),
                    assigmentAddDto.getName(),
                    course,
                    subject
            );
            assignmentRepo.save(assignment);
            return "Assigment is Successfully saved";
        }catch (Exception e){
            throw new HandleException("Something went wrong during add assigment"+e);
        }

    }
}
