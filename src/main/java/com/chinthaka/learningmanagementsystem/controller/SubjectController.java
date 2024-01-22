package com.chinthaka.learningmanagementsystem.controller;

import com.chinthaka.learningmanagementsystem.dto.paginated.PaginatedSubjectResponseDto;
import com.chinthaka.learningmanagementsystem.dto.request.SubjectSaveDto;
import com.chinthaka.learningmanagementsystem.dto.response.SubjectResponseByIdDto;
import com.chinthaka.learningmanagementsystem.service.ISubjectService;
import com.chinthaka.learningmanagementsystem.utils.StandardResponse;
import jakarta.validation.constraints.Max;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/subject")
@CrossOrigin
public class SubjectController {

    private final ISubjectService subjectService;

    public SubjectController(ISubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping(path = "/add-subject")
    public ResponseEntity<StandardResponse> addSubject(
            @RequestBody final SubjectSaveDto subjectSaveDto){
        final String data = subjectService.addSubject(subjectSaveDto);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(201,"Success",data), HttpStatus.CREATED
        );
    }
    @GetMapping(path = "/get-by-id",params = {"id"})
    public ResponseEntity<StandardResponse> getBySubjectId(@RequestParam(name = "id") long subjectId ){
        final SubjectResponseByIdDto data = subjectService.getBySubjectId(subjectId);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data), HttpStatus.OK
        );
    }

    @GetMapping(path = "/get-all-subject-by-active-status",params = {"status","page","size"})
    public ResponseEntity<StandardResponse> getAllSubject(
            @RequestParam(name = "page") int page,
            @RequestParam(name = "size") @Max(20) int size,
            @RequestParam(name = "status") boolean activeStatus
    ){
        final PaginatedSubjectResponseDto data = subjectService.getAllSubject(activeStatus,page,size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200,"Success",data), HttpStatus.OK
        );
    }
}
