package com.chinthaka.learningmanagementsystem.entity;

import com.chinthaka.learningmanagementsystem.enums.CourseMedium;
import com.chinthaka.learningmanagementsystem.enums.CourseType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "course_Name", length = 100, nullable = false)
    private String courseName;

    @Column(name = "course_code", length = 10, nullable = false,unique = true)
    private String code;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private CourseType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "medium", nullable = false)
    private CourseMedium medium;

    @Column(name = "duration", nullable = false)
    private String duration;

    @Column(name = "certificate", columnDefinition = "TINYINT default 1")
    private boolean certificate;

    @Column(name = "active_status", columnDefinition = "TINYINT default 1")
    private boolean activeStatus;

    @Column(name = "create_data", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime createData;

    @Column(name = "last_modify_date", columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDateTime lastModifyData;

    @OneToMany(mappedBy = "course")
    Set<SubjectAssign> subjectAssigns;

    @OneToMany(mappedBy = "course")
    List<Assignment> assignments;

}
