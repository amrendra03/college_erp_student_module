package com.server.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class StudentResponseDto {
    private String name;
    private String phoneNo;
    private String course;
    private String rollNo;
    private LocalDateTime registrationDate;
    private String enrollNo;
    private int totalSubject;
    private String duration;
    private int totalSem;
    private String completed;
    private String current;
    private Long courseId;

}
