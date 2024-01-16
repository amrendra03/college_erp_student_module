package com.server.entities.student;

import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StudentCourseDetail {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long courseId;

   private String duration;

//   @Column(name = "Name")
   private String courseName;

//   @Column(name = "Branch")
   private String courseBranch;

   @OneToMany(mappedBy = "studentCourseDetail", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StudentSemester> studentSemesters;

   @OneToMany(mappedBy = "studentCourseDetail", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StudentCourseRegistration> studentCourseRegistrations;
}
