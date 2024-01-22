package com.chinthaka.learningmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;


@Entity
//@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "Assigment_details")
public class AssignmentDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_details_id", length = 20)
    private Long AssignmentDetailsId;

    @Column(name = "file_type")
    private String fileType;

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "submit_data", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime submitDate;

    @Lob
    @Column(name = "file",length = 20971520)
    private byte[] data;

    @ManyToOne
    @JoinColumn(name = "assignment_id")
    private Assignment assignment;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public AssignmentDetails(Long assignmentDetailsId, String fileType, String fileName, LocalDateTime submitDate, byte[] data, Assignment assignment, Student student) {
        AssignmentDetailsId = assignmentDetailsId;
        this.fileType = fileType;
        this.fileName = fileName;
        this.submitDate = submitDate;
        this.data = data;
        this.assignment = assignment;
        this.student = student;
    }


    public AssignmentDetails(Object o, String contentType, String originalFilename, byte[] bytes, Assignment assignment, Student student) {
    }


}
