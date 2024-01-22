package com.chinthaka.learningmanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubjectResponseByIdDto {
    private long subjectId;
    private String subjectName;

}
