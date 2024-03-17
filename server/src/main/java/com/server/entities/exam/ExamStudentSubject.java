package com.server.entities.exam;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Entity
@Data
public class ExamStudentSubject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String subjectCode;
    private String subjectName;
    private String cost;
    private String status;
    private String marks;
    private String message;
    @NotNull(message = "Student rollNo must be filled ")
    private String rollNo;
}
