package com.chinthaka.learningmanagementsystem.service.impl;

import com.chinthaka.learningmanagementsystem.entity.Assignment;
import com.chinthaka.learningmanagementsystem.entity.AssignmentDetails;
import com.chinthaka.learningmanagementsystem.entity.Student;
import com.chinthaka.learningmanagementsystem.exception.AlreadyExistException;
import com.chinthaka.learningmanagementsystem.exception.HandleException;
import com.chinthaka.learningmanagementsystem.exception.NotFoundException;
import com.chinthaka.learningmanagementsystem.repo.AssignmentDetailsRepo;
import com.chinthaka.learningmanagementsystem.repo.AssignmentRepo;
import com.chinthaka.learningmanagementsystem.repo.StudentRepo;
import com.chinthaka.learningmanagementsystem.service.IAssigmentDetailsService;
import com.chinthaka.learningmanagementsystem.utils.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Service
public class AssignmentDetailsServiceImpl implements IAssigmentDetailsService {

    private final AssignmentDetailsRepo assignmentDetailsRepo;
    private final StudentRepo studentRepo;
    private final AssignmentRepo assignmentRepo;

    public AssignmentDetailsServiceImpl(AssignmentDetailsRepo assignmentDetailsRepo, StudentRepo studentRepo, AssignmentRepo assignmentRepo) {
        this.assignmentDetailsRepo = assignmentDetailsRepo;
        this.studentRepo = studentRepo;
        this.assignmentRepo = assignmentRepo;
    }

    private static final long MAX_ALLOWED_SIZE = 5 * 1024 * 1024;

    @Override
    public String uploadAssigment(MultipartFile file, long assDetailsId, long studentId, long assignmentId) throws IOException {
        final Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new NotFoundException("Student not found"));
        final Assignment assignment = assignmentRepo.findById(assignmentId)
                .orElseThrow(() -> new NotFoundException("assigment not found"));
        if (assignment == null) {
            throw new NotFoundException("Assigment not found");
        }
        if (file.getBytes().length > MAX_ALLOWED_SIZE) {
            return "File size exceeds the maximum allowed size.";
        }
        try (InputStream inputStream = file.getInputStream()) {
            String filename = file.getOriginalFilename();
            FileUtils.compressImage(inputStream.readAllBytes());
            if (assignmentDetailsRepo.existsByFileNameAndStudentAndAssignmentAndData(filename, student, assignment, FileUtils.compressImage(inputStream.readAllBytes()))) {
                throw new AlreadyExistException("Assigment Already submitted");
            } else {
                AssignmentDetails a = new AssignmentDetails(
                        assDetailsId,
                        file.getContentType(),
                        file.getOriginalFilename(),
                        LocalDateTime.now(),
                        FileUtils.compressImage(inputStream.readAllBytes()),
                        assignment,
                        student
                );
                assignmentDetailsRepo.save(a);
                return "File uploaded successfully: " + file.getOriginalFilename();
            }
        } catch (IOException e) {
            throw new HandleException("Something went wrong during submit assigment");
        }

    }
    @Override
    public byte[] downloadFileByAssignmentId(long assignmentId) {
        if (!assignmentDetailsRepo.existsById(assignmentId)){
            throw new NotFoundException("Assigment Id not found");
        }
        Optional<AssignmentDetails> dbFileData = assignmentDetailsRepo.findById(assignmentId);
        if (dbFileData.isPresent()) {
            return FileUtils.decompressImage(
                    dbFileData
                            .map(AssignmentDetails::getData)
                            .orElse(new byte[0]));
        } else {
            throw new NotFoundException("File not found");
        }
    }

}
