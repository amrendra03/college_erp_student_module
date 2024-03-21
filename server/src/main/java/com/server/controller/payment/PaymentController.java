package com.server.controller.payment;


import com.server.dto.ApiResponse;
import com.server.dto.fee.FeeTransactionDTO;
import com.server.service.fee.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    //    '/' post
//    '/{paymentID}' delete, put, get A
//    '/{studentID}' get all
    @PostMapping("/")
    public ResponseEntity<?> createThePayment(@RequestBody FeeTransactionDTO req) {
        FeeTransactionDTO res = paymentService.create(req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<?> updatePayment(@RequestBody FeeTransactionDTO req) {
        FeeTransactionDTO res = paymentService.update(req);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{paymentID}")
    public ResponseEntity<?> getPayment(@PathVariable Long paymentID) {
        FeeTransactionDTO res = paymentService.get(paymentID);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{rollNo}")
    public ResponseEntity<?> getAllPayment(@PathVariable String rollNo) {
        List<FeeTransactionDTO> res = paymentService.getAll(rollNo);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{paymentId}")
    public ResponseEntity<?> deletePayment(@PathVariable Long paymentId) {
        ApiResponse res = paymentService.delete(paymentId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}
