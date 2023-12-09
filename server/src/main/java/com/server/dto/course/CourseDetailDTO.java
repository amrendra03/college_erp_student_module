package com.server.dto.course;

import java.util.List;

import lombok.Data;

@Data
public class CourseDetailDTO {
   private Long courseId;
   private String duration;
   private List<SemesterDTO> semesters;
}
