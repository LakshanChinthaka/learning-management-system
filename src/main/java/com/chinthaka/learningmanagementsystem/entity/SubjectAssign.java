package com.chinthaka.learningmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SubjectAssign {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long  assignId;

    @Column(name = "assign_data", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime assignData;

    @ManyToOne
    @JoinColumn(name = "course_id")
    Course course;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    Subject subject;
}
