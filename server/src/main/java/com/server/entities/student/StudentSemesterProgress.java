package com.server.entities.student;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StudentSemesterProgress {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long progressId;

   @ManyToOne
   @JoinColumn(name = "student_id")
   private StudentDetail studentDetail;

   @ManyToOne
   @JoinColumn(name = "semester_id")
   private StudentSemester studentSemester;

   private String name;

   private String status; // Status of the student in the current semester (e.g., "In Progress",
                          // "Completed")
   private double gpa; // Grade Point Average for the current semester
   private Date progressDate;

}