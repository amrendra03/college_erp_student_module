package com.server.dto.course;

import lombok.Data;

@Data
public class SubjectDTO {
   private Long subjectId;
   private String subjectName;
   private String facultyName;
   private Long semesterId;
}
