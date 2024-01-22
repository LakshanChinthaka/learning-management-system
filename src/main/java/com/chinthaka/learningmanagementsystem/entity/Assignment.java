package com.chinthaka.learningmanagementsystem.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "assignment_id")
    private Long assignmentId;

    @Column(name = "assignment_code",length = 100,unique = true,nullable = false)
    private String code;

    @Column(name = "assignment_name",length = 100, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "subject_id")
    private Subject subject;

    public Assignment(String code, String name, Course course, Subject subject) {
    }

}
