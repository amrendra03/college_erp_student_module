package com.server.dto;

import lombok.Data;

@Data
public class StudentDto {

   private Long rollNo;
   private String name;
   private String course;
   private int sem;
   private double fee;
   private double fine;
   private double score;

}
