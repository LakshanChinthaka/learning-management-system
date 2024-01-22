package com.chinthaka.learningmanagementsystem.dto.response;

import com.chinthaka.learningmanagementsystem.entity.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectAssignDto {
    private Subject subject;
}
