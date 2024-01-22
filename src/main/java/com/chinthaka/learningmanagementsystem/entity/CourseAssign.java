package com.chinthaka.learningmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CourseAssign {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assign_id")
    private Long assignId;

    @Column(name = "date_of_assign", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime assignData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "course_id")
    private Course course;

    public CourseAssign(Student student, Course course, LocalDateTime now) {
        this.student = student;
        this.course = course;
        this.assignData = now;
    }
}
