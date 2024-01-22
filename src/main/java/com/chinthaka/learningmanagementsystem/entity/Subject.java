package com.chinthaka.learningmanagementsystem.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "subject_id")
    private long subjectId;

    @Column(name = "subject_name",unique = true)
    private String subjectName;

    @Column(name = "create_data", columnDefinition = "DATETIME")
    private LocalDateTime createData;

    @Column(name = "active_status", columnDefinition = "TINYINT default 1")
    private boolean activeStatus;

    @OneToMany(mappedBy = "subject")
    Set<SubjectAssign> subjectAssigns;

    @OneToMany(mappedBy = "subject")
    List<Assignment> assignments;
}
