package com.server.dto.student;

import java.util.List;

import lombok.Data;

@Data
public class StudentCourseDetailDTO {
   private Long courseId;
   private String duration;

   private String courseName;
   private String courseBranch;

   private List<StudentSemesterDTO> studentSemesters;
   private List<StudentCourseRegistrationDTO> studentCourseRegistrations;
}
