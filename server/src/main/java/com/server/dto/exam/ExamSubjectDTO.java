package com.server.dto.exam;

import lombok.Data;

@Data
public class ExamSubjectDTO {
    private Long id;
    private String subjectCode;
    private String subjectName;
    private String cost;
}
