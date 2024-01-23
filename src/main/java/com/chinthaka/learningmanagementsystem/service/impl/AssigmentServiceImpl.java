package com.chinthaka.learningmanagementsystem.service.impl;

import com.chinthaka.learningmanagementsystem.dto.request.AssigmentAddDto;
import com.chinthaka.learningmanagementsystem.dto.response.AssigmentResponseDto;
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
import com.chinthaka.learningmanagementsystem.utils.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AssigmentServiceImpl implements IAssignmentService {

    private final AssignmentRepo assignmentRepo;
    private final CourseRepo courseRepo;
    private final SubjectRepo subjectRepo;

    public AssigmentServiceImpl(AssignmentRepo assignmentRepo, CourseRepo courseRepo, SubjectRepo subjectRepo) {
        this.assignmentRepo = assignmentRepo;
        this.courseRepo = courseRepo;
        this.subjectRepo = subjectRepo;
    }

    @Override
    public String addAssignment(AssigmentAddDto assigmentAddDto) {
        if (null == assigmentAddDto) {
            throw new NotFoundException("No details provided");
        }
        if (assignmentRepo.existsByCode(assigmentAddDto.getCode())) {
            throw new AlreadyExistException("Assignment Already Exist");
        }
        final Course course = EntityUtils.getCourseDetails(assigmentAddDto.getCourse(),courseRepo);
        final Subject subject = EntityUtils.getSubjectDetails(assigmentAddDto.getSubject(),subjectRepo);
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
        } catch (Exception e) {
            throw new HandleException("Something went wrong during add assigment" + e);
        }

    }

    @Override
    public String deleteAssignment(Long assigmentId) {
        if (assignmentRepo.existsById(assigmentId)) {
            assignmentRepo.deleteById(assigmentId);
            return "Assigment Id-" + assigmentId + " Delete Successfully";
        }
        throw new NotFoundException("Assigment not found");
    }

    @Override
    public List<AssigmentResponseDto> filterByCourseAndAssigmentName(Long courseId, String assigmentName) {
        final Course course = EntityUtils.getCourseDetails(courseId,courseRepo);
        final List<Assignment> assignments = assignmentRepo.findByCourseAndNameIgnoreCase(course, assigmentName);
        if (assignments != null && !assignments.isEmpty()) {
            return assignments.stream()
                    .map(assignment -> new AssigmentResponseDto(
                            assignment.getAssignmentId(),
                            assignment.getCode(),
                            assignment.getName(),
                            courseId,
                            course.getCourseName()
                    ))
                    .collect(Collectors.toList());
        }
        throw new NotFoundException("no data found");
    }
}
