package com.server.service.fee;

import com.server.dto.ApiResponse;
import com.server.dto.fee.FeeTransactionDTO;

import java.util.List;

public interface PaymentService {
    FeeTransactionDTO create(FeeTransactionDTO req);

    FeeTransactionDTO update(FeeTransactionDTO req);

    List<FeeTransactionDTO> getAll(String rollNo);

    FeeTransactionDTO get(Long feeId);


    ApiResponse delete(Long feeID);

}
