package com.server.service.impl.fee;

import com.server.dto.fee.FeeRecordDTO;
import com.server.entities.fee.FeeRecord;
import com.server.entities.student.StudentDetail;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.fee.FeeRecordRepo;
import com.server.repository.student.StudentDetailRepo;
import com.server.service.fee.FeeRecordService;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeRecordServiceImpl implements FeeRecordService {

    private final Logger log = LoggerFactory.getLogger(FeeRecordServiceImpl.class);

    @Autowired
    private FeeRecordRepo feeRecordRepo;

    @Autowired
    private ModelMapper modelMapper;// You need to add ModelMapper to your dependencies

    @Autowired
    private StudentDetailRepo studentDetailRepo;

    @Override
    public FeeRecordDTO createFeeRecord(FeeRecordDTO feeRecordDTO) {
        try {
            log.debug("Creating Fee Record: {}", feeRecordDTO);

            FeeRecord feeRecord = modelMapper.map(feeRecordDTO, FeeRecord.class);
            FeeRecord savedRecord = feeRecordRepo.save(feeRecord);

            FeeRecordDTO savedRecordDTO = modelMapper.map(savedRecord, FeeRecordDTO.class);
            log.debug("Fee Record created successfully: {}", savedRecordDTO);

            return savedRecordDTO;
        } catch (Exception e) {
            log.error("Error creating Fee Record", e);
            throw new RuntimeException("Error creating Fee Record");
        }
    }

    @Override
    public List<FeeRecordDTO> getFeeRecordByRollNo(String rollNo) {
        try {
            log.debug("Fetching Fee Record by Roll No: {}", rollNo);

            StudentDetail studentDetail = studentDetailRepo.findByRollNo(rollNo);

            if (studentDetail == null) {
                throw new EntityNotFoundException("Student not found with Roll No: " + rollNo);
            }

            List<FeeRecord> feeRecords = feeRecordRepo.findAllByStudentDetail(studentDetail);

            if (feeRecords.isEmpty()) {
                throw new EntityNotFoundException("No Fee Records found for student with Roll No: " + rollNo);
            }

            List<FeeRecordDTO> feeRecordDTO =  feeRecords.stream().map(x->modelMapper.map(x, FeeRecordDTO.class)).collect(Collectors.toList());
            log.debug("Fetched Fee Record: {}", feeRecordDTO);

            return feeRecordDTO;
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error fetching Fee Record", e);
            throw new ResourceNotFoundException("Error fetching Fee Record","RollNo: "+rollNo,0L);
        }
    }

    @Override
    public List<FeeRecordDTO> getAllFeeRecords() {
        log.debug("Fetching all Fee Records");

        List<FeeRecord> feeRecords = feeRecordRepo.findAll();
        List<FeeRecordDTO> feeRecordDTOs = feeRecords.stream()
                .map(record -> modelMapper.map(record, FeeRecordDTO.class))
                .collect(Collectors.toList());

        log.debug("Fetched all Fee Records: {}", feeRecordDTOs);

        return feeRecordDTOs;
    }

    @Override
    public FeeRecordDTO updateFeeRecord(Long feeRecordId, FeeRecordDTO feeRecordDTO) {
        log.debug("Updating Fee Record with ID {}: {}", feeRecordId, feeRecordDTO);

        if (!feeRecordRepo.existsById(feeRecordId)) {
            throw new RuntimeException("Fee Record not found with id: " + feeRecordId);
        }

        feeRecordDTO.setFeeRecordId(feeRecordId);
        FeeRecord updatedRecord = feeRecordRepo.save(modelMapper.map(feeRecordDTO, FeeRecord.class));

        FeeRecordDTO updatedRecordDTO = modelMapper.map(updatedRecord, FeeRecordDTO.class);
        log.debug("Fee Record updated successfully: {}", updatedRecordDTO);

        return updatedRecordDTO;
    }

    @Override
    public void deleteFeeRecord(Long feeRecordId) {
        try {
            log.debug("Deleting Fee Record with ID: {}", feeRecordId);

            if (!feeRecordRepo.existsById(feeRecordId)) {
                throw new EntityNotFoundException("Fee Record not found with id: " + feeRecordId);
            }

            feeRecordRepo.deleteById(feeRecordId);
            log.debug("Fee Record deleted successfully");
        } catch (EntityNotFoundException e) {
            log.warn(e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("Error deleting Fee Record", e);
            throw new RuntimeException("Error deleting Fee Record");
        }
    }
}