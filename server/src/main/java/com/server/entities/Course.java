package com.server.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Course {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   private String courseName;

   private String batch;

   private String duration;

   @OneToMany(mappedBy = "enrolledCourse", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Student> enrolledStudents;

}
