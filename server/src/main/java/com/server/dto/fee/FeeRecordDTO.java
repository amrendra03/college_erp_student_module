package com.server.dto.fee;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

@Data
public class FeeRecordDTO {

    private Long feeRecordId;

    @NotNull(message = "Student detail ID must not be null")
    @Positive(message = "Student detail ID must be a positive number")
    private Long studentDetailId;

    @NotNull(message = "Fee amount must be a positive number")
    private double feeAmount;

    @PositiveOrZero(message = "Fine amount must be a positive number or zero")
    private double fineAmount;


    private String status;

    @NotNull(message = "Record date must not be null")
    private Date recordDate;
}
