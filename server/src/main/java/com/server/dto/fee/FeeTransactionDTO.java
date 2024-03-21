package com.server.dto.fee;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class FeeTransactionDTO {
    private Long transactionId;

    @NotNull(message = "Student detail must be not null")
    @Positive(message = "Studetn detail Id must be a positive number")
    private Long studentDetailId;

    @Positive(message = "Transaction amount must be a positive number")
    private double amount;

    @NotNull(message = "Transaction date must not be null")
    private Date transactionDate;

    private String description;

    @NotBlank(message = "Status must not be blank")
    private String status;
    private String type;

    private String comment;

    private  String name;
}
