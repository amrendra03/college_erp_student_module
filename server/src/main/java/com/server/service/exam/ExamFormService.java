package com.server.service.exam;


import com.server.dto.exam.ExamResultDTO;
import com.server.dto.exam.ExamStudentSubjectDTO;
import com.server.dto.exam.ExamSubjectDTO;
import com.server.dto.exam.ExamTransactionDTO;
import com.server.entities.exam.ExamStudentSubject;

import java.util.List;
import java.util.Map;

public interface ExamFormService {

    Map<String,String> getStatus(Long examId);

    ExamTransactionDTO paymentProcessing(Long examId,Map<String,String> req);
    Map<String,String> correctionProcessing(String rollNo,Long examId,Map<String,Boolean> req);

    Map<String ,String> getVerification(String rollNo);

    List<ExamStudentSubjectDTO> getExamSchedule(Map<String,String> req);

    List<ExamResultDTO>  getResultByExamId(Long examId, String rollNo);



}
