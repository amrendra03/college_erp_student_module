package com.server.dto.course;

import java.util.List;

import lombok.Data;

@Data
public class SemesterDTO {
   private Long semesterId;
   private int semesterNumber;
   private Long courseDetailId; // Reference to the associated CourseDetail
   private List<SubjectDTO> subjects;

}
