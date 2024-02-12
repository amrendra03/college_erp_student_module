package com.server.dto.exam;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExamRecordDTO {


   private Long examRecordId;

   @NotNull(message = "Student detail ID must not be null")
   @Positive(message ="Student detail ID must be a positive number")
   private Long studentDetailId;

   @NotNull(message = " Student semester ID must be not null")
   @Positive(message = " Student semester ID must be positive number")
   private Long studentSemesterId;

   @NotBlank(message =  " Status must not be blank")
   private String status;

   @Positive(message = "GPA must be a Positive number or zero")
   private double gpa;

   @NotNull(message = "Progress date must not be null")
   private Date progressDate;
}
