package com.server.entities.student;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StudentSubject {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long subjectId;

   private String subjectName;
   private String facultyName;

   @ManyToOne
   @JoinColumn(name = "student_semester_id")
   private StudentSemester studentSemester;

}
