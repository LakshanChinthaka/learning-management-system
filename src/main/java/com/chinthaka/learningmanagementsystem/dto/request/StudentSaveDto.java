package com.chinthaka.learningmanagementsystem.dto.request;

import com.chinthaka.learningmanagementsystem.entity.Address;
import com.chinthaka.learningmanagementsystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class StudentSaveDto {
    private long studentId;
    private String firstName;
    private String lastname;
    private Gender gender;
    private String contactNo;
    private String nic;
    private String email;
    private boolean activeStatus;
    private Date dateOfRegistration;
    private Date dateOfModify;
    private Address address;
}
