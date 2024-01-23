package com.chinthaka.learningmanagementsystem.service.impl;

import com.chinthaka.learningmanagementsystem.dto.paginated.PaginatedStudentResponseDto;
import com.chinthaka.learningmanagementsystem.dto.query.GetCourseDetails;
import com.chinthaka.learningmanagementsystem.dto.request.StudentSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.StudentResponseDto;
import com.chinthaka.learningmanagementsystem.dto.response.StudentWithCourseResponseDto;
import com.chinthaka.learningmanagementsystem.entity.Course;
import com.chinthaka.learningmanagementsystem.entity.CourseAssign;
import com.chinthaka.learningmanagementsystem.entity.Student;
import com.chinthaka.learningmanagementsystem.entity.Subject;
import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;
import com.chinthaka.learningmanagementsystem.enums.Gender;
import com.chinthaka.learningmanagementsystem.exception.AlreadyExistException;
import com.chinthaka.learningmanagementsystem.exception.HandleException;
import com.chinthaka.learningmanagementsystem.exception.NotFoundException;
import com.chinthaka.learningmanagementsystem.mapper.StudentMapper;
import com.chinthaka.learningmanagementsystem.repo.AddressRepo;
import com.chinthaka.learningmanagementsystem.repo.CourseAssignRepo;
import com.chinthaka.learningmanagementsystem.repo.CourseRepo;
import com.chinthaka.learningmanagementsystem.repo.StudentRepo;
import com.chinthaka.learningmanagementsystem.service.IStudentService;
import com.chinthaka.learningmanagementsystem.utils.EntityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

import static com.chinthaka.learningmanagementsystem.utils.EntityUtils.getStudentDetails;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {

    private final StudentRepo studentRepo;
    private final StudentMapper studentMapper;
    private final CourseRepo courseRepo;
    private final CourseAssignRepo courseAssignRepo;

    public StudentServiceImpl(StudentRepo studentRepo, StudentMapper studentMapper, CourseRepo courseRepo, CourseAssignRepo courseAssignRepo) {
        this.studentRepo = studentRepo;
        this.studentMapper = studentMapper;
        this.courseRepo = courseRepo;
        this.courseAssignRepo = courseAssignRepo;
    }

    @Override
    @Transactional
    public String saveStudent(StudentSaveDto studentSaveDto) {
        if (null == studentSaveDto) {
            throw new NotFoundException("Customer details not provide");
        }
        if (studentRepo.existsByNic(studentSaveDto.getNic())) {
            throw new AlreadyExistException("Student Already Registered");
        }
        try {
            final Student student = studentMapper.studentSaveDtoToEntity(studentSaveDto);
            final long studentId = studentRepo.save(student).getStudentId();
            return "Student Id-" + studentId + " Successfully Registered";
        } catch (Exception e) {
            throw new HandleException("Something went wrong during student registration");
        }
    }

    @Override
    public StudentWithCourseResponseDto getStudentById(long StudentId) {
        final Student student = EntityUtils.getStudentDetails(StudentId,studentRepo);
        final GetCourseDetails courseDetails = courseAssignRepo.getCourseDetails(StudentId);
        final StudentResponseDto convertSubject = studentMapper.entityToStudentGetResponseDto(student);
        return new StudentWithCourseResponseDto(convertSubject, courseDetails);
    }

    @Override
    public PaginatedStudentResponseDto getAllStudent(boolean activeStatus, int page, int size) {
        final Page<Student> students = studentRepo.findByActiveStatus(activeStatus, PageRequest.of(page, size));
        if (!students.isEmpty()) {
            return new PaginatedStudentResponseDto(
                    studentMapper.PageListStudentListToStudentResponseDro(students),
                    students.getTotalPages(),
                    students.getNumberOfElements(),
                    students.getTotalElements()
            );
        }
        throw new NotFoundException("No Student Data");
    }

    @Override
    public String assignCourseToStudent(long studentId, long courseId) {
        final Student student = EntityUtils.getStudentDetails(studentId,studentRepo);
        final Course course = EntityUtils.getCourseDetails(courseId,courseRepo);
        if (courseAssignRepo.existsByCourseAndStudent(course, student)) {
            throw new AlreadyExistException("Course already assigned to the student");
        }
        try {
            CourseAssign courseAssign = new CourseAssign(
                    student,
                    course,
                    LocalDateTime.now()
            );
            courseAssignRepo.save(courseAssign);
            return "Course assigned successfully.";
        } catch (Exception e) {
            throw new HandleException("Something went wrong during student enroll");
        }
    }

    @Override
    public List<StudentResponseDto> filterByStatusAndGender(boolean activeStatus, Gender gender) {
        final List<Student> students = studentRepo.findByActiveStatusAndGender(activeStatus, gender);
        if (!students.isEmpty()) {
            return studentMapper.EntityListToStudentResponseDto(students);
        }
        throw new NotFoundException("No data found");
    }

}



