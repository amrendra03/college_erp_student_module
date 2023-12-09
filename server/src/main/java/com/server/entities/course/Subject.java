package com.server.entities.course;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
class Subject {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long subjectId;

   private String subjectName;
   private String facultyName;

   @ManyToOne
   @JoinColumn(name = "semester_id")
   private Semester semester;

   // Constructors, getters, and setters can be added as needed
}