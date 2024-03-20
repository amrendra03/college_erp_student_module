package com.server.entities.exam;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ExamTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDateTime;

    private String transactionType;

    private String status;

    private double amount;

    private int subjects;

    // Reference to ExamRecord
    @ManyToOne
    @JoinColumn(name = "record_id")
    private ExamRecord examRecord;

    @PrePersist
    public void prePersist() {
        this.transactionDateTime = new Date();
    }
}
