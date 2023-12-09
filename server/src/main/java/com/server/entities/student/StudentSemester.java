package com.server.entities.student;

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
public class StudentSemester {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long semesterId;

   private int semesterNumber;

   @ManyToOne
   @JoinColumn(name = "student_course_id")
   private StudentCourseDetail studentCourseDetail;

   @OneToMany(mappedBy = "studentSemester", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StudentSubject> studentSubjects;

}
