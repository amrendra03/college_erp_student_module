package com.server.dto.student;

import java.util.List;

import lombok.Data;

@Data
public class StudentSemesterDTO {
   private Long semesterId;
   private int semesterNumber;
   private Long studentCourseDetailId; // Reference to the associated StudentCourseDetail
   private List<StudentSubjectDto> studentSubjects;
}
