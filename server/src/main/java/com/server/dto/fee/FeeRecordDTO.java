package com.server.dto.fee;

import java.util.Date;

import lombok.Data;

@Data
public class FeeRecordDTO {
    private Long feeRecordId;
    private Long studentDetailId;
    private double feeAmount;
    private double fineAmount;
    private Date recordDate;
}
