package com.server.dto.exam;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class ExamRecordDTO {


   private Long progressId;

   @NotNull(message = "Student detail ID must not be null")
   @Positive(message ="Student detail ID must be a positive number")
   private Long studentDetailId;

   @NotBlank(message =  " Status must not be blank")
   private String status;

   @Positive(message = "GPA must be a Positive number or zero")
   private double gpa;
   private Long courseId;

   @NotNull(message = "Progress date must not be null")
   private Date progressDate;
   private String name;
   private String message;
   private String solution;
   private String comment;
   private String result;
}
