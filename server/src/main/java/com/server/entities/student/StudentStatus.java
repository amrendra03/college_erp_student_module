package com.server.entities.student;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StudentStatus {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "student_detail_id")
   private StudentDetail studentDetail;

   private String subject;
   private String subjectCode;
   private String facultyName;

   private int present;
   private int absent;

   private int submitted;
   private int notSubmited;

}
