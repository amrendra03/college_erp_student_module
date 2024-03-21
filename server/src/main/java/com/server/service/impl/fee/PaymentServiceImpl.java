package com.server.service.impl.fee;

import com.server.dto.ApiResponse;
import com.server.dto.fee.FeeTransactionDTO;
import com.server.entities.fee.FeeTransaction;
import com.server.entities.student.StudentDetail;
import com.server.exception.ResourceNotFoundException;
import com.server.exception.custom.DataOperationException;
import com.server.repository.fee.PaymentRepo;
import com.server.repository.student.StudentDetailRepo;
import com.server.service.fee.PaymentService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final StudentDetailRepo studentDetailRepo;
    private final PaymentRepo paymentRepo;
    private Logger log = LoggerFactory.getLogger(PaymentServiceImpl.class);
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public PaymentServiceImpl(StudentDetailRepo studentDetailRepo, PaymentRepo paymentRepo) {
        this.studentDetailRepo = studentDetailRepo;
        this.paymentRepo = paymentRepo;
    }

    @Override
    public FeeTransactionDTO create(FeeTransactionDTO req) {
        try {
            log.info("Processing the payment create...");
            FeeTransaction res = modelMapper.map(req, FeeTransaction.class);
            Optional<StudentDetail> studentDetail = studentDetailRepo.findById(req.getStudentDetailId());
            if (studentDetail.isPresent()) {
                res.setStudentDetail(studentDetail.get());
                res = paymentRepo.save(res);
                log.info("Payment successfully save.");
                return modelMapper.map(res, FeeTransactionDTO.class);
            } else {
                log.info("Student data not found");
                throw new ResourceNotFoundException("Student data not found", "Student Id", req.getStudentDetailId());
            }
        } catch (Exception ex) {
            log.info("Exception: {}", ex.getMessage());
            throw new DataOperationException(ex.getMessage());
        }
    }

    @Override
    public FeeTransactionDTO update(FeeTransactionDTO req) {
        try {
            log.info("updating the payment data");
            Optional<FeeTransaction> payment = paymentRepo.findById(req.getTransactionId());
            if (payment.isPresent()) {

                payment.get().setStatus(req.getStatus());
                payment.get().setType(req.getType());
                payment.get().setComment(req.getComment());
                payment.get().setDescription(req.getDescription());
                payment.get().setName(req.getName());

                FeeTransaction res = paymentRepo.save(payment.get());

                log.info("Successfully update the payment data");
                return modelMapper.map(res, FeeTransactionDTO.class);
            } else {
                log.info("Failed to update because data not found");
                throw new ResourceNotFoundException("Payment data not found", "payment Id", req.getTransactionId());
            }
        } catch (Exception ex) {
            log.info("Failed to update because data not found");
            throw new ResourceNotFoundException("Payment data not found", "payment Id", req.getTransactionId());
        }
    }

    @Override
    public List<FeeTransactionDTO> getAll(String rollNo) {
        try {
            log.info("Processing the  all payment list by student ID");
            StudentDetail studentDetail = studentDetailRepo.findByRollNo(rollNo);
            List<FeeTransaction> paymentList = paymentRepo.findAllByStudentDetail(studentDetail);
            List<FeeTransactionDTO> res = paymentList.stream().map(x -> modelMapper.map(x, FeeTransactionDTO.class)).collect(Collectors.toList());
            log.info("Successfully fetched payment list");
            return res;
        } catch (Exception ex) {
            log.info("Failed to fetching the payment list");
            throw new ResourceNotFoundException("Resource not  found", "Student rollNo: " + rollNo, 0L);
        }
    }

    @Override
    public FeeTransactionDTO get(Long feeId) {
        try {
            log.info("Fetching the payment data...");

            Optional<FeeTransaction> res = paymentRepo.findById(feeId);
            if (res.isPresent()) {

                log.info("Successfully fetched the payment data");
                return modelMapper.map(res.get(), FeeTransactionDTO.class);
            } else {
                log.info("Failed to get payment ");
                throw new ResourceNotFoundException("Resource not found ", "Payment id", feeId);
            }

        } catch (Exception ex) {
            log.info("Failed to get payment");
            throw new DataOperationException(ex.getMessage());
        }

    }

    @Override
    public ApiResponse delete(Long feeID) {
        try {
            log.info("Processing to delete payment");
            Optional<FeeTransaction> payment = paymentRepo.findById(feeID);
            if (payment.isPresent()) {
                log.info("Successfully deleted the payment");
                paymentRepo.delete(payment.get());
                return new ApiResponse("Successfully deleted", true);
            } else {
                log.info("Resource not found");
                throw new ResourceNotFoundException("Payemnt data not found", "payment id", feeID);
            }
        } catch (Exception ex) {
            log.info("failed the deletion process");
            throw new RuntimeException(ex.getMessage());
        }
    }

}
