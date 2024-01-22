package com.chinthaka.learningmanagementsystem.entity;

import com.chinthaka.learningmanagementsystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "student_id", length = 20)
    private Long studentId;

    @Column(name = "student_fname", length = 100, nullable = false)
    private String firstName;

    @Column(name = "student_laname", length = 100, nullable = false)
    private String lastname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "contact_no", length = 12, nullable = false)
    private String contactNo;

    @Column(name = "nic", length = 12, nullable = false, unique = true)
    private String nic;

    @Column(name = "email", length = 50, unique = true)
    private String email;

    @Column(name = "active_status", columnDefinition = "TINYINT default 1")
    private boolean activeStatus;

    @Column(name = "date_of_registration", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime dateOfRegistration;

    @Column(name = "date_modify_time", columnDefinition="DATETIME DEFAULT NOW()")
    private LocalDateTime dateOfModify;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "student")
    List<AssignmentDetails> assignmentDetails;


}
