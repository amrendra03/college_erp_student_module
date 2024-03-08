package com.server.dto.student;

import java.util.Date;

import lombok.Data;

@Data
public class StudentSemesterProgressDTO {
   private Long progressId;
   private Long studentDetailId;
   private Long studentSemesterId;
   private String status;
   private String name;
   private double gpa;
   private Date progressDate;
}
