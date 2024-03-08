package com.server.service.fee;

import com.server.dto.fee.FeeRecordDTO;

import java.util.List;

public interface FeeRecordService {
    FeeRecordDTO createFeeRecord(FeeRecordDTO feeRecordDTO);

    List<FeeRecordDTO> getFeeRecordByRollNo(String rollNo);

    List<FeeRecordDTO> getAllFeeRecords();

    FeeRecordDTO updateFeeRecord(Long feeRecordId, FeeRecordDTO feeRecordDTO);

    void deleteFeeRecord(Long feeRecordId);
}
