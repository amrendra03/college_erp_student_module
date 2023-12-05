package com.server.dto;

import lombok.Data;

@Data
public class CourseDto {
   private Long id;
   private String courseName;
   private String batch;
   private String duration;
}
