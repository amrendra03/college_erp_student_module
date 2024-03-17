package com.server.dto.exam;

import lombok.Data;

@Data
public class ExamStudentSubjectDTO {
    private Long id;
    private String subjectCode;
    private String subjectName;
    private String cost;
    private String status;
    private String marks;
    private String message;
}
