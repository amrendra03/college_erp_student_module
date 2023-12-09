package com.server.entities.course;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class CourseDetail {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long courseId;

   private String duration;

   @OneToMany(mappedBy = "courseDetail", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Semester> semesters;

   // Constructors, getters, and setters can be added as needed
}
