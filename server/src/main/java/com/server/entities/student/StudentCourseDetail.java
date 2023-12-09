package com.server.entities.student;

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
public class StudentCourseDetail {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long courseId;

   private String duration;

   @OneToMany(mappedBy = "studentCourseDetail", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StudentSemester> studentSemesters;

   @OneToMany(mappedBy = "studentCourseDetail", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StudentCourseRegistration> studentCourseRegistrations;
}
