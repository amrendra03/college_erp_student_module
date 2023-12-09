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
public class FeeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feeRecordId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentDetail studentDetail; // Changed the type to StudentDetail

    private double feeAmount;
    private double fineAmount;
    private Date recordDate;
    // Constructors, getters, and setters can be added as needed
}
