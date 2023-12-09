package com.server.dto.fee;

import java.util.Date;

import lombok.Data;

@Data
public class FeeTransactionDTO {
   private Long transactionId;
   private Long studentDetailId;
   private double amount;
   private Date transactionDate;
   private String description;
}
