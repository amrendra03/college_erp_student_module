package com.server.dto.student;

import java.util.List;

import lombok.Data;

@Data
public class StudentCourseDetailDTO {
   private Long courseId;
   private String duration;
   private List<StudentSemesterDTO> studentSemesters;
   private List<StudentCourseRegistrationDTO> studentCourseRegistrations;
}
