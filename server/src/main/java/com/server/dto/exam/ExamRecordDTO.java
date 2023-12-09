package com.server.dto.exam;

import java.util.Date;

import lombok.Data;

@Data
public class ExamRecordDTO {
   private Long examRecordId;
   private Long studentDetailId;
   private Long studentSemesterId;
   private String status;
   private double gpa;
   private Date progressDate;
}
