package com.server.dto.student;

import lombok.Data;

@Data
public class StudentStatusDTO {

   private Long id;
   private String studentRollNo;
   private String subject;
   private String subjectCode;
   private String facultyName;
   private int present;
   private int absent;
   private int submitted;
   private int notSubmitted;
}
