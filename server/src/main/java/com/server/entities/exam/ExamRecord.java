package com.server.entities.exam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.server.entities.student.StudentDetail;
import com.server.entities.student.StudentSemester;

import jakarta.persistence.*;
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
   private String message;
   private String solution;
   private String comment;
   private String result;

   @OneToMany(mappedBy = "examRecord", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<ExamTransaction> examTransactions = new ArrayList<>();

}
