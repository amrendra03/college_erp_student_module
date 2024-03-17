package com.server.entities.exam;

import java.util.Date;

import com.server.entities.student.StudentDetail;
import com.server.entities.student.StudentSemester;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class ExamRecord {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long progressId;

   @ManyToOne
   @JoinColumn(name = "student_id")
   private StudentDetail studentDetail;

   private Long courseId;
   private String status; // Status of the student in the current semester (e.g., "In Progress",// "Completed")
   private double gpa; // Grade Point Average for the current semester
   private Date progressDate;

   private String name;

}
