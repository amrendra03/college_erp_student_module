package com.server.dto.student;

import lombok.Data;

@Data
public class StudentSubjectDto {

   private Long subjectId;
   private String subjectName;
   private String facultyName;
   private int semesterNumber;

   private Long courseId;
   private Long studentSemesterId;

}