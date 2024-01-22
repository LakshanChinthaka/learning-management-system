package com.chinthaka.learningmanagementsystem.dto.request;

import lombok.*;


import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SubjectSaveDto {
    private long subjectId;
    private String subjectName;
    private LocalDateTime createData;
    private boolean activeStatus;

}
