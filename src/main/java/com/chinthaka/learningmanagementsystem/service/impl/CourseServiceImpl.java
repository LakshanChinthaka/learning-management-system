package com.chinthaka.learningmanagementsystem.service.impl;

import com.chinthaka.learningmanagementsystem.dto.query.getCourseAndSubjectDetails;
import com.chinthaka.learningmanagementsystem.dto.request.AssignSubjectToCourseDto;
import com.chinthaka.learningmanagementsystem.dto.request.CourseSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.GetCourseDetailsDto;
import com.chinthaka.learningmanagementsystem.dto.response.getAllCourseDetailsResponseDto;
import com.chinthaka.learningmanagementsystem.entity.Course;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import com.chinthaka.learningmanagementsystem.entity.SubjectAssign;
import com.chinthaka.learningmanagementsystem.exception.AlreadyExistException;
import com.chinthaka.learningmanagementsystem.exception.HandleException;
import com.chinthaka.learningmanagementsystem.exception.NotFoundException;
import com.chinthaka.learningmanagementsystem.mapper.CourseMapper;
import com.chinthaka.learningmanagementsystem.mapper.SubjectMapper;
import com.chinthaka.learningmanagementsystem.repo.CourseRepo;
import com.chinthaka.learningmanagementsystem.repo.SubjectAssignRepo;
import com.chinthaka.learningmanagementsystem.repo.SubjectRepo;
import com.chinthaka.learningmanagementsystem.service.ICourseService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepo courseRepo;
    private final SubjectRepo subjectRepo;
    private final SubjectAssignRepo subjectAssignRpo;
    private final CourseMapper courseMapper;

    public CourseServiceImpl(CourseRepo courseRepo, SubjectRepo subjectRepo, SubjectAssignRepo subjectAssignRpo, CourseMapper courseMapper) {
        this.courseRepo = courseRepo;
        this.subjectRepo = subjectRepo;
        this.subjectAssignRpo = subjectAssignRpo;
        this.courseMapper = courseMapper;
    }

    @Override
    public String addCourse(CourseSaveDto courseSaveDto) {
        if (null == courseSaveDto) {
            throw new NotFoundException("Course details not provide");
        }
        if (courseRepo.existsByCourseNameAndCode(courseSaveDto.getCourseName(), courseSaveDto.getCode())) {
            throw new AlreadyExistException("Course Already Added");
        }
        try {
            final Course course = courseMapper.courseSaveDtoToEntity(courseSaveDto);
            courseRepo.save(course);
            return courseSaveDto.getCourseName() + " Successfully Registered";
        } catch (Exception e) {
            throw new HandleException("Something went wrong during add course");
        }
    }


    @Override
    public String assignSubjectToCourse(AssignSubjectToCourseDto assignSubjectToCourseDto) {
        Course course = courseRepo.findById(assignSubjectToCourseDto.getCourse())
                .orElseThrow(() -> new NotFoundException("Course Not found"));
        for (Long x : assignSubjectToCourseDto.getSubjectId()) {
            if (!subjectRepo.existsById(x)) {
                throw new NotFoundException("Subject Id-" + x + " not found");
            }
        }

        try {
            final List<Subject> subjects = subjectRepo.findAllById(assignSubjectToCourseDto.getSubjectId());
            List<SubjectAssign> subjectAssigns = subjects
                    .stream()
                    .map(subject -> {
                        SubjectAssign subjectAssign = new SubjectAssign();
                        subjectAssign.setAssignData(LocalDateTime.now());
                        subjectAssign.setCourse(course);
                        subjectAssign.setSubject(subject);
                        return subjectAssign;
                    }).collect(Collectors.toList());
            subjectAssignRpo.saveAll(subjectAssigns);
            return "Successfully Assign subject";
        } catch (Exception e) {
            throw new HandleException("Something went to wrong during subject assign to course");
        }
    }

    @Override
    public String deleteAssignSubject(Long subjectId, Long courseId) {
        final Course getCourse = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found"));
        final Subject getSubject = subjectRepo.findById(subjectId)
                .orElseThrow(() -> new NotFoundException("Subject not found"));
        try {
            final SubjectAssign subjectAssign = subjectAssignRpo.findByCourseAndSubject(getCourse, getSubject);
            subjectAssignRpo.delete(subjectAssign);
            return "Successfully removed the " + getSubject.getSubjectName()
                    + " subject of the " + getCourse.getCourseName() + " course";
        } catch (Exception e) {
            throw new HandleException("Something went wrong during removing subject");
        }
    }

    @Override
    public GetCourseDetailsDto getCourseById(Long courseId) {
        final Course course = courseRepo.findById(courseId)
                .orElseThrow(() -> new NotFoundException("Course not found"));
        List<getCourseAndSubjectDetails> subjectIds = subjectAssignRpo.findGetSubjectDetails(courseId);
        return new GetCourseDetailsDto(
                courseMapper.EntityToCourseResponseDto(course),
                subjectIds
        );
    }

    @Override
    public getAllCourseDetailsResponseDto getCourseWithSubject(boolean activeStatus) {
        final List<Course> getCourses = courseRepo.findAll();
        List<GetCourseDetailsDto> courseDetailsList = new ArrayList<>();
        if (!getCourses.isEmpty()) {
            for (Course c : getCourses) {
                List<getCourseAndSubjectDetails> subjectDetails = subjectAssignRpo
                        .GetAllSubjectAssignDetails(activeStatus, c.getCourseId());
                GetCourseDetailsDto courseDetails = new GetCourseDetailsDto(
                        courseMapper.EntityToCourseResponseDto(c),
                        subjectDetails
                );
                courseDetailsList.add(courseDetails);
            }
            return new getAllCourseDetailsResponseDto(courseDetailsList);
        } else {
            throw new NotFoundException("Course not found");
        }
    }

}
