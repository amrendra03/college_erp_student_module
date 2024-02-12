package com.server.dto.student;

import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

@Data
public class StudentDetailDTO {
   private Long studentId;


   private String name;

   private String email;

   private String phone;

   private String rollNo;

   private Date DOB;


   private String enrollmentNo;

   private Date registrationDate;

   private List<StudentCourseRegistrationDTO> studentCourseRegistrations;
}
