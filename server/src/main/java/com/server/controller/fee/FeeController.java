package com.server.controller.fee;

import com.server.constant.API;
import com.server.dto.fee.FeeRecordDTO;
import com.server.jwt.UsernameCheck;
import com.server.service.fee.FeeRecordService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(API.student+"/fee-records")
public class FeeController {

    private final Logger log = LoggerFactory.getLogger(FeeController.class);

    @Autowired
    private FeeRecordService feeRecordService;

    @Autowired
    private UsernameCheck usernameCheck;

    @PostMapping
    public ResponseEntity<FeeRecordDTO> createFeeRecord(@RequestBody FeeRecordDTO feeRecordDTO,  @RequestHeader("Authorization") String authorizationHeader) {
        log.debug("Creating Fee Record: {}", feeRecordDTO);
        FeeRecordDTO createdRecord=null;

        String token = authorizationHeader.replace("Bearer ", "");
        boolean flag = usernameCheck.checkUsernameWithStudentId(token,feeRecordDTO.getStudentDetailId());
        if(flag){
            log.info("token matched with student Id");
            createdRecord= feeRecordService.createFeeRecord(feeRecordDTO);
        }
        return new ResponseEntity<>(createdRecord, HttpStatus.CREATED);
    }

    @GetMapping("/{rollNo}")
    public ResponseEntity<?> getFeeRecordById(@PathVariable String rollNo,@RequestHeader("Authorization") String authorizationHeader) {
        log.debug("Fetching Fee Record by ID: {}", rollNo);
        List<FeeRecordDTO> feeRecordDTO=null;

        String token = authorizationHeader.replace("Bearer ", "");
        boolean flag = usernameCheck.checkUsernameWithRollNo(token,rollNo);
        if(flag) {
            log.info("token matched with student Id");
            feeRecordDTO=feeRecordService.getFeeRecordByRollNo(rollNo);
        }
        return new ResponseEntity<>(feeRecordDTO, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FeeRecordDTO>> getAllFeeRecords() {
        log.debug("Fetching all Fee Records");
        List<FeeRecordDTO> feeRecordDTOs = feeRecordService.getAllFeeRecords();
        return new ResponseEntity<>(feeRecordDTOs, HttpStatus.OK);
    }

    @PutMapping("/{feeRecordId}")
    public ResponseEntity<FeeRecordDTO> updateFeeRecord(
            @PathVariable Long feeRecordId,
            @RequestBody FeeRecordDTO feeRecordDTO,
            @RequestHeader("Authorization") String authorizationHeader
    ) {
        log.debug("Updating Fee Record with ID {}: {}", feeRecordId, feeRecordDTO);
        FeeRecordDTO updatedRecord =null;
        String token = authorizationHeader.replace("Bearer ", "");
        boolean flag = usernameCheck.checkUsernameWithStudentId(token,feeRecordDTO.getStudentDetailId());
        if(flag) {
            log.info("token matched with student Id");
            updatedRecord = feeRecordService.updateFeeRecord(feeRecordId, feeRecordDTO);
        }
        return new ResponseEntity<>(updatedRecord, HttpStatus.OK);
    }

    @DeleteMapping("/{feeRecordId}")
    public ResponseEntity<Void> deleteFeeRecord(@PathVariable Long feeRecordId) {
        log.debug("Deleting Fee Record with ID: {}", feeRecordId);
        feeRecordService.deleteFeeRecord(feeRecordId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}