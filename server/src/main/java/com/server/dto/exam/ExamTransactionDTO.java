package com.server.dto.exam;

import lombok.Data;

import java.util.Date;

@Data
public class ExamTransactionDTO {
    private Long id;
    private Date transactionDateTime;
    private String transactionType;
    private String status;
    private int subjects;
    private double amount;
    private Long examRecord;
}
