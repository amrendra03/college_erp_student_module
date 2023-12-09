package com.server.entities.fee;

import java.util.Date;

import com.server.entities.student.StudentDetail;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class FeeTransaction {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long transactionId;

   @ManyToOne
   @JoinColumn(name = "student_id")
   private StudentDetail studentDetail;

   private double amount;
   private Date transactionDate;
   private String description;
}
