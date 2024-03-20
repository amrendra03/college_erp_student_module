package com.server.service.exam;

import com.server.dto.exam.ExamResultDTO;
import com.server.dto.exam.ExamStudentSubjectDTO;
import com.server.dto.exam.ExamSubjectDTO;
import com.server.dto.exam.ExamTransactionDTO;
import com.server.entities.exam.ExamRecord;
import com.server.entities.exam.ExamStudentSubject;
import com.server.entities.exam.ExamSubject;
import com.server.entities.exam.ExamTransaction;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.exam.ExamStudentSubjectRepo;
import com.server.repository.exam.ExamSubjectRepo;
import com.server.repository.exam.ExamTransactionRepo;
import com.server.repository.student.ExamRecordRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ExamFormServiceIMPL implements ExamFormService {

    private final ExamRecordRepository examRecordRepository;
    private final ExamStudentSubjectRepo examStudentSubjectRepo;
    private final ExamTransactionRepo examTransactionRepo;
    private final ExamSubjectRepo examSubjectRepo;
    private Logger log = LoggerFactory.getLogger(ExamFormServiceIMPL.class);
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public ExamFormServiceIMPL(ExamRecordRepository examRecordRepository, ExamStudentSubjectRepo examStudentSubjectRepo, ExamTransactionRepo examTransactionRepo, ExamSubjectRepo examSubjectRepo) {
        this.examRecordRepository = examRecordRepository;
        this.examStudentSubjectRepo = examStudentSubjectRepo;
        this.examTransactionRepo = examTransactionRepo;
        this.examSubjectRepo = examSubjectRepo;
    }

    @Override
    public Map<String, String> getStatus(Long examId) {
        Map<String, String> res = new HashMap<>();
        try {
            log.info("Processing to get status");
            Optional<ExamRecord> examRecord = examRecordRepository.findById(examId);
            if (examRecord.isPresent() && examRecord.get().getStatus().equals("payment")) {
                String rollNo = examRecord.get().getStudentDetail().getRollNo();
                List<ExamStudentSubject> subjectList = examStudentSubjectRepo.findAllByRollNo(rollNo);

                Double cost = 0.0;
                int count = 0;
                for (ExamStudentSubject i : subjectList) {
                    if (i.getStatus().equals("payment")) {
                        Double x = Double.parseDouble(i.getCost());
                        cost += x;
                        count += 1;
                    }
                }

                res.put("cost", String.valueOf(cost));
                res.put("status", examRecord.get().getStatus());
                res.put("subjects", String.valueOf(count));
                res.put("message", "Proceed to Payment Step");
                log.info("Successfully done.");
                return res;

            } else if (examRecord.isPresent() && examRecord.get().getStatus().equals("correction")) {
                log.info("Correction request");
                String rollNo = examRecord.get().getStudentDetail().getRollNo();
                List<ExamStudentSubject> subjectList = examStudentSubjectRepo.findAllByRollNo(rollNo);

                Double cost = 0.0;
                int count = 0;
                for (ExamStudentSubject i : subjectList) {
                    if (i.getStatus().equals("correction")) {
                        Double x = Double.parseDouble(i.getCost());
                        cost += x;
                        count += 1;
                    }
                }

                res.put("cost", String.valueOf(cost));
                res.put("status", examRecord.get().getStatus());
                res.put("subjects", String.valueOf(count));
                res.put("date", examRecord.get().getProgressDate().toString());
                res.put("message", "Proceed to Correction Step");
                log.info("Successfully done.");
                return res;
            } else if (examRecord.isPresent() && examRecord.get().getStatus().equals("verification")) {
                log.info("Verification request");
                String rollNo = examRecord.get().getStudentDetail().getRollNo();
                List<ExamStudentSubject> subjectList = examStudentSubjectRepo.findAllByRollNo(rollNo);

                // count the subject number and total cost
                Double cost = 0.0;
                int count = 0;
                for (ExamStudentSubject i : subjectList) {
                    if (i.getStatus().equals("verification")) {
                        Double x = Double.parseDouble(i.getCost());
                        cost += x;
                        count += 1;
                    }
                }

                res.put("cost", String.valueOf(cost));
                res.put("status", examRecord.get().getStatus());
                res.put("subjects", String.valueOf(count));
                res.put("date", examRecord.get().getProgressDate().toString());
                res.put("message", "Proceed to Verification Step");

                log.info("Successfully done.");
                return res;
            } else if (examRecord.isPresent() && examRecord.get().getStatus().equals("start")) {
                res.put("status", examRecord.get().getStatus());
                res.put("message", "Proceed to Start step");
                log.info("Payment completed", examRecord.get().getStatus());

                return res;
            } else {
                res.put("message", "Exam Not found");
                log.info("not found: {}", examId);
                return res;
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public ExamTransactionDTO paymentProcessing(Long examId, Map<String, String> req) {

        try {
            log.info("Processing the payment...");
            Optional<ExamRecord> examRecord = examRecordRepository.findById(examId);
            if (examRecord.isPresent() && examRecord.get().getStatus().equals("payment")) {
                String rollNo = examRecord.get().getStudentDetail().getRollNo();
                List<ExamStudentSubject> subjectList = examStudentSubjectRepo.findAllByRollNo(rollNo);

                Double cost = 0.0;
                int count = 0;
                for (ExamStudentSubject i : subjectList) {
                    if (i.getStatus().equals("payment")) {
                        Double x = Double.parseDouble(i.getCost());
                        cost += x;
                        count += 1;
                    }
                }

                ExamTransaction transaction = new ExamTransaction();

                transaction.setAmount(cost);
                transaction.setExamRecord(examRecord.get());
                transaction.setTransactionType(req.get("type"));
                transaction.setStatus("Completed");
                transaction.setSubjects(count);

                transaction = examTransactionRepo.save(transaction);
                log.info("Successfully completed payment: {}", transaction.getId());
                examRecord.get().setStatus("correction");
                examRecordRepository.save(examRecord.get());
// changing the student subject list status
                List<ExamStudentSubject> subList = examStudentSubjectRepo.findAllByStatusAndRollNo("payment", rollNo);

                for (ExamStudentSubject i : subList) {
                    i.setStatus("correction");
                }
                examStudentSubjectRepo.saveAll(subList);

                ExamTransactionDTO res = new ExamTransactionDTO();

                res.setId(transaction.getId());
                res.setExamRecord(transaction.getExamRecord().getProgressId());
                res.setAmount(transaction.getAmount());
                res.setTransactionType(transaction.getTransactionType());
                res.setStatus(transaction.getStatus());
                res.setTransactionDateTime(transaction.getTransactionDateTime());
                res.setSubjects(transaction.getSubjects());

                return res;
            } else {
                log.info("Data not found !!");
                throw new ResourceNotFoundException("Data not found", "exam Id", examId);
            }

        } catch (Exception ex) {
            log.info("Failed to process payment !!");
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public Map<String, String> correctionProcessing(String rollNo, Long examId, Map<String, Boolean> req) {
        try {
            Map<String, String> res = new HashMap<>();
            log.info("processing the correction step...");
            log.info(rollNo);
            log.info("{}", req.size());
            List<String> subCode = new ArrayList<>();

            // iterate the request data
            for (String i : req.keySet()) {
//                log.info(i);
                String[] pair = i.split("/");
                // check data existence and fetched
                ExamStudentSubject studentSubject = examStudentSubjectRepo.findByRollNoAndSubjectCodeAndStatus(rollNo, pair[0], "correction");
                // if not exist in student exam subject list then create and save
                if (studentSubject == null) {
                    ExamSubject examSubject = examSubjectRepo.findBySubjectCode(pair[0]);
                    if (examSubject == null) {
                        res.put("message", "Entered subject not found");
                        return res;
                    }
                    ExamStudentSubject stExamSub = modelMapper.map(examSubject, ExamStudentSubject.class);
                    stExamSub.setStatus("verification");
                    stExamSub.setRollNo(rollNo);
                    stExamSub.setMessage("Added during Correction");
                    examStudentSubjectRepo.save(stExamSub);

                    continue;
                }
                studentSubject.setStatus("verification");
                examStudentSubjectRepo.save(studentSubject);
            }

            log.info("OK");
            Optional<ExamRecord> examRecord = examRecordRepository.findById(examId);
            examRecord.get().setStatus("verification");
            examRecordRepository.save(examRecord.get());
            log.info("Exam recorde status updated successfully ");

            res.put("message", "Successfully done proceed to next step");
            res.put("status", "verification");
            return res;
        } catch (Exception ex) {
            log.info(ex.getMessage());
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public Map<String, String> getVerification(String rollNo) {
        try {
            log.info("Processing the verification step of exam form...");
            Map<String, String> res = new HashMap<>();

            List<ExamStudentSubject> subjectList = examStudentSubjectRepo.findAllByStatusAndRollNo("verification", rollNo);
            int count = 0;
            for (ExamStudentSubject i : subjectList) {
                res.put("subject-" + count, i.getSubjectCode() + "/ " + i.getSubjectName());
                count += 1;
            }
            log.info("Successfully fetched the verification data");
            return res;
        } catch (Exception ex) {
            log.info("Verification data not found: {}", rollNo);
            throw new ResourceNotFoundException("Verification detail not found for the given data", "Roll No:" + rollNo, 0L);
        }
    }

    @Override
    public List<ExamStudentSubjectDTO> getExamSchedule(Map<String, String> req) {
        try{
            log.info("subject schedule date processing exam step schedule");
            List<ExamStudentSubjectDTO> res = new ArrayList<>();
            for(String key: req.keySet()){
                String pair[]= key.split("/");
//                log.info(key);
                ExamSubject temp = examSubjectRepo.findBySubjectCode(pair[0]);
                res.add(modelMapper.map(temp,ExamStudentSubjectDTO.class));
                log.info(temp.toString());
            }
            log.info("Successfully fetched the subject schedule.");
            return res;
        }catch(Exception ex){
            throw  new ResourceNotFoundException("Subject data not found","given list is not found",0L);
        }
    }

    @Override
    public   List<ExamResultDTO>  getResultByExamId(Long examId, String rollNo) {

        try{
            log.info("Processing the Result step of exam form...");
//            Map<ExamSubjectDTO,ExamStudentSubjectDTO> res = new HashMap<>();
            List<ExamResultDTO> res = new ArrayList<>();
            List<ExamStudentSubject> studentSubjectList = examStudentSubjectRepo.findAllByStatusAndRollNo("result",rollNo);

            for(ExamStudentSubject i: studentSubjectList){
                ExamSubject examSubject =examSubjectRepo.findBySubjectCode(i.getSubjectCode());
//                res.put(modelMapper.map(examSubject,ExamSubjectDTO.class),modelMapper.map(i,ExamStudentSubjectDTO.class));
                ExamResultDTO data =new ExamResultDTO();
                data.setExamSubjectDTO(modelMapper.map(examSubject,ExamSubjectDTO.class));
                data.setStudentSubjectDTO(modelMapper.map(i,ExamStudentSubjectDTO.class));
                res.add(data);

                log.info("examSub: {}",examSubject);
                log.info("studentSub: {}",i);

            }
            log.info("Successfully completed processing");
            return res;
        }catch (Exception ex){
            throw new ResourceNotFoundException("Result list not found","Exam ID",examId);
        }
    }

}
