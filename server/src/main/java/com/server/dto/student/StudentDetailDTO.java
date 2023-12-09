package com.server.dto.student;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class StudentDetailDTO {
   private Long studentId;
   private String name;
   private String rollNo;
   private String enrollmentNo;
   private Date registrationDate;
   private List<StudentCourseRegistrationDTO> studentCourseRegistrations;
}
