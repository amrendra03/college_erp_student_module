package com.server.dto.exam;

import lombok.Data;

import java.util.Date;

@Data
public class ExamSubjectDTO {
    private Long id;
    private String subjectCode;
    private String subjectName;
    private String cost;
    private Date date;

    private Long internalMarks;
    private Long externalMarks;
}
