package com.server.controller.exam;


import com.server.dto.exam.ExamResultDTO;
import com.server.dto.exam.ExamStudentSubjectDTO;
import com.server.dto.exam.ExamSubjectDTO;
import com.server.dto.exam.ExamTransactionDTO;
import com.server.service.exam.ExamFormService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exam/form")
public class ExamFormController {

    private final ExamFormService examFormService;
    private Logger log = LoggerFactory.getLogger(ExamFormController.class);

    public ExamFormController(ExamFormService examFormService) {
        this.examFormService = examFormService;
    }


    @GetMapping("/{examId}")
    public ResponseEntity<?> getPaymentStatus(@PathVariable Long examId){
        Map<String ,String> res = examFormService.getStatus(examId);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
    @PostMapping("/{examId}/payment")
    public ResponseEntity<?> paymentProcessing(@RequestBody Map<String,String> req ,@PathVariable Long examId){
         ExamTransactionDTO res = examFormService.paymentProcessing(examId,req);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PostMapping("/{rollNo}/{examId}/correction")
    public ResponseEntity<?> updateStudentSubjectList(@PathVariable String rollNo,@PathVariable Long examId,@RequestBody Map<String,Boolean> req)
    {
        Map<String,String> res = examFormService.correctionProcessing(rollNo,examId,req);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/{rollNo}/verification")
    public ResponseEntity<?> getVerificationStatus(@PathVariable String rollNo){

        Map<String,String> res = examFormService.getVerification(rollNo);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @PostMapping("/schedule")
    public ResponseEntity<?> getExamSchedule(@RequestBody Map<String,String> req){
        List<ExamStudentSubjectDTO> res =examFormService.getExamSchedule(req);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/{examId}/{rollNo}/result")
    public ResponseEntity<?> getResult(@PathVariable Long examId,@PathVariable String rollNo){
        List<ExamResultDTO>  res = examFormService.getResultByExamId(examId,rollNo);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }
}
