package com.server.dto.exam;

import lombok.Data;

import java.util.Date;

@Data
public class ExamStudentSubjectDTO {
    private Long id;
    private String subjectCode;
    private String subjectName;
    private String cost;
    private String status;
    private String marks;
    private String message;
    private Date date;

    private Long internalMarks;
    private Long externalMarks;
    private String result;
}
