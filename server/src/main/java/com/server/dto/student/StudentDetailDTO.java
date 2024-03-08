package com.server.dto.student;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class StudentDetailDTO {
   private Long studentId;


   @NotBlank(message = "Name must not be blank")
   private String name;

   private String score;

   @NotBlank(message = "Email must not be blank")
   @Email(message = "Invalid email format")
   private String email;

   @NotBlank(message = "Phone must not be blank")
   @Pattern(regexp = "\\d{10}", message = "Phone number must be 10 digits")
   private String phone;

   @NotNull(message = "Date of Birth must not be null")
   @Past(message = "Date of Birth must be in the past")
   @JsonFormat(pattern = "yyyy-MM-dd")
   private Date DOB;

   public String role;

   private String rollNo;

   private String enrollmentNo;

   private Date registrationDate;

   private List<StudentCourseRegistrationDTO> studentCourseRegistrations;
}
