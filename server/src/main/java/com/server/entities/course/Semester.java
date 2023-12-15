package com.server.entities.course;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Semester {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long semesterId;

   private int semesterNumber;

   @ManyToOne
   @JoinColumn(name = "course_id")
   private CourseDetail courseDetail;

   @OneToMany(mappedBy = "semester", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<Subject> subjects;

   // Constructors, getters, and setters can be added as needed
}
