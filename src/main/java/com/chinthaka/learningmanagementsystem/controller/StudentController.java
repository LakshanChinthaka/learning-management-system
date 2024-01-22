package com.chinthaka.learningmanagementsystem.controller;

import com.chinthaka.learningmanagementsystem.dto.paginated.PaginatedStudentResponseDto;
import com.chinthaka.learningmanagementsystem.dto.request.StudentSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.StudentResponseDto;
import com.chinthaka.learningmanagementsystem.dto.response.StudentWithCourseResponseDto;
import com.chinthaka.learningmanagementsystem.service.IStudentService;
import com.chinthaka.learningmanagementsystem.utils.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/student")
@CrossOrigin
public class StudentController {

    private final IStudentService StudentService;

    public StudentController(IStudentService StudentService) {
        this.StudentService = StudentService;
    }

    @PostMapping(path = "/save")
    public ResponseEntity<StandardResponse> saveStudent(
            @RequestBody final StudentSaveDto  studentSaveDto){
        final String data = StudentService.saveStudent(studentSaveDto);
        return new ResponseEntity<StandardResponse>(
               new StandardResponse(201,"Success",data),HttpStatus.CREATED
        );
    }
    @GetMapping(value = "/get-by-id",params = {"id"})
    public ResponseEntity<StandardResponse> getStudentById(@RequestParam(name = "id") Long StudentId){
        final StudentWithCourseResponseDto data = StudentService.getStudentById(StudentId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data),HttpStatus.OK
        );
    }

    @GetMapping(value = "/get-by-student-by-active-status",params = {"status","page","size"})
    public ResponseEntity<StandardResponse> getStudentById(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") @Max(20) int size,
            @RequestParam(name = "status") boolean activeStatus
    ){
        final PaginatedStudentResponseDto data = StudentService.getAllStudent(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data),HttpStatus.OK
        );
    }

    @PostMapping(value = "/assign-course-to-student",params = {"student_id","course_id"})
    public ResponseEntity<StandardResponse> assignCourseToStudent(
            @RequestParam(name = "student_id") long studentId,
            @RequestParam(name = "course_id") long courseId
    ){
        final String data =
                StudentService.assignCourseToStudent(studentId,courseId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data),HttpStatus.OK
        );
    }
}
