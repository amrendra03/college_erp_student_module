package com.server.dto.student;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentCourseRegistrationDTO {
   private Long registrationId;

   @NotBlank(message = "Must be not Blank Student ID !!")
   private Long studentDetailId;

   @NotBlank(message = "Must be not Blank Course ID !!")
   private Long studentCourseDetailId;

   @NotBlank(message = "Type cannot be blank")
   @Pattern(regexp = "^(PG|UG|DP)$", message = "Type must be one of: PG, UG, DP")
   private String type;

   @NotBlank(message = "Must be not Blank Roll no !!")
   private String studentRollNo;
}
