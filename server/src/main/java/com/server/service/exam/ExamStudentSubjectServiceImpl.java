package com.server.service.exam;

import com.server.dto.ApiResponse;
import com.server.dto.exam.ExamStudentSubjectDTO;
import com.server.entities.exam.ExamRecord;
import com.server.entities.exam.ExamStudentSubject;
import com.server.entities.exam.ExamSubject;
import com.server.entities.student.StudentDetail;
import com.server.exception.ResourceNotFoundException;
import com.server.exception.custom.UserExistAlready;
import com.server.jwt.JwtToken;
import com.server.repository.exam.ExamStudentSubjectRepo;
import com.server.repository.exam.ExamSubjectRepo;
import com.server.repository.student.ExamRecordRepository;
import com.server.repository.student.StudentDetailRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ExamStudentSubjectServiceImpl implements ExamStudentSubjectService {

    private static final Logger logger = LoggerFactory.getLogger(ExamStudentSubjectServiceImpl.class);
    private final ExamStudentSubjectRepo examStudentSubjectRepo;
    private final ModelMapper modelMapper;

    private final ExamSubjectRepo examSubjectRepo;

    private final ExamRecordRepository examRecordRepository;
    private final StudentDetailRepo studentDetailRepo;

    private final JwtToken jwtToken;
    @Autowired
    public ExamStudentSubjectServiceImpl(ExamStudentSubjectRepo examStudentSubjectRepo, ModelMapper modelMapper, ExamSubjectRepo examSubjectRepo, ExamRecordRepository examRecordRepository, StudentDetailRepo studentDetailRepo, JwtToken jwtToken) {
        this.examStudentSubjectRepo = examStudentSubjectRepo;
        this.modelMapper = modelMapper;
        this.examSubjectRepo = examSubjectRepo;
        this.examRecordRepository = examRecordRepository;
        this.studentDetailRepo = studentDetailRepo;
        this.jwtToken = jwtToken;
    }

    @Override
    public List<ExamStudentSubjectDTO> getAllExamStudentSubjects() {
        List<ExamStudentSubject> examStudentSubjects = examStudentSubjectRepo.findAll();
        logger.info("Retrieved all exam student subjects from the database.");
        return examStudentSubjects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExamStudentSubjectDTO getExamStudentSubjectById(Long id) {
        Optional<ExamStudentSubject> optionalExamStudentSubject = examStudentSubjectRepo.findById(id);
        if (optionalExamStudentSubject.isPresent()) {
            ExamStudentSubject examStudentSubject = optionalExamStudentSubject.get();
            logger.info("Retrieved exam student subject with id: {}", id);
            return convertToDTO(examStudentSubject);
        } else {
            logger.warn("Exam student subject with id {} not found.", id);
            return null;
        }
    }

    @Override
    public ApiResponse addExamStudentSubject(Map<String, Boolean> requestBody,String token) {
        try {
            logger.info("Processing to save the subject request");
            String email = jwtToken.getUsernameFromToken(token);
            StudentDetail student= studentDetailRepo.findByEmail(email);

            Map<String, String> res = new HashMap<>();
            boolean flag = true;
            Long examId = null;

            // Iterate over the request body to process each and get the rollNo, examId and subject Cost
            for (Map.Entry<String, Boolean> entry : requestBody.entrySet()) {
                String key = entry.getKey();
                Boolean value = entry.getValue();
                String[] pair = key.split("/");

                logger.info("Key: {}, Value: {}", pair[0], value);

                // If the key is "rollNo", set the roll number
                 if (pair[0].equals("examId")) { // If the key is "examId", set the exam ID
//                    if(Long.parseLong(pair[1])>0)
                    examId = Long.parseLong(pair[1]);
                } else {
                    // Check if the subject exists in the database
                    ExamSubject sub = examSubjectRepo.findBySubjectCode(pair[0]);
                    if (sub == null) {
                        flag = false;
                        throw new ResourceNotFoundException("Selected subject not found in registered course", "Subject code: " + pair[0], 0L);
                    } else {
                        res.put(key, sub.getCost());
                    }
                }
            }

            logger.info(res.toString());
            // If no errors occurred during processing
            if (flag) {
                // Create a list to store ExamStudentSubject entities
                List<ExamStudentSubject> subjects = new ArrayList<>();

                // Iterate over the collected subject data
                for (Map.Entry<String, String> entry : res.entrySet()) {
                    String subjectKey = entry.getKey();
                    String[] subjectData = subjectKey.split("/");
                    String subjectCode = subjectData[0];
                    String subjectName = subjectData[1];

                    // Check if the subject has already been passed by the student
                    List<ExamStudentSubject> existedSub = examStudentSubjectRepo.findAllBySubjectCodeAndRollNo(subjectCode, student.getRollNo());

                    for(ExamStudentSubject x: existedSub){
                        if (x != null && x.getStatus().equals("Passed")) {
                            throw new UserExistAlready("Subject Status passed: " + subjectCode);
                        }
                    }

                    // Create a new ExamStudentSubject entity and populate its fields
                    ExamStudentSubject subject = new ExamStudentSubject();
                    subject.setSubjectCode(subjectCode);
                    subject.setSubjectName(subjectName);
                    subject.setCost(entry.getValue());
                    subject.setRollNo(student.getRollNo());
                    subject.setStatus("payment");
                    logger.info(subject.toString());
                    subjects.add(subject);
                }

                // Save all the subjects in the database
                examStudentSubjectRepo.saveAll(subjects);

                // Fetch the corresponding exam record
                Optional<ExamRecord> examRecord = examRecordRepository.findById(examId);
                examRecord.get().setStatus("payment");
                examRecordRepository.save(examRecord.get());
//                ExamRecordDTO record = modelMapper.map(examRecord, ExamRecordDTO.class);
//                logger.info("{}", record);
                return new ApiResponse("Successfully saved selected subjects proceed to:-/payment", true);
            } else {
                logger.info("Subject code not found");
                return new ApiResponse("Subject code not found", true);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Failed: " + ex.getMessage());
        }
    }

    @Override
    public ExamStudentSubjectDTO updateExamStudentSubject(Long id, ExamStudentSubjectDTO examStudentSubjectDTO) {
        Optional<ExamStudentSubject> optionalExamStudentSubject = examStudentSubjectRepo.findById(id);
        if (optionalExamStudentSubject.isPresent()) {
            ExamStudentSubject examStudentSubject = optionalExamStudentSubject.get();
            // Update the fields
            examStudentSubject.setSubjectCode(examStudentSubjectDTO.getSubjectCode());
            examStudentSubject.setSubjectName(examStudentSubjectDTO.getSubjectName());
            examStudentSubject.setCost(examStudentSubjectDTO.getCost());
            examStudentSubject.setStatus(examStudentSubjectDTO.getStatus());
            examStudentSubject.setMarks(examStudentSubjectDTO.getMarks());
            examStudentSubject.setMessage(examStudentSubjectDTO.getMessage());
            // Save the updated entity
            examStudentSubject = examStudentSubjectRepo.save(examStudentSubject);
            logger.info("Updated exam student subject with id: {}", id);
            return convertToDTO(examStudentSubject);
        } else {
            logger.warn("Exam student subject with id {} not found. Update failed.", id);
            return null;
        }
    }

    @Override
    public void deleteExamStudentSubject(Long id) {
        if (examStudentSubjectRepo.existsById(id)) {
            examStudentSubjectRepo.deleteById(id);
            logger.info("Deleted exam student subject with id: {}", id);
        } else {
            logger.warn("Exam student subject with id {} not found. Deletion failed.", id);
        }
    }

    @Override
    public List<ExamStudentSubjectDTO> saveExamStudentSubjects(List<ExamStudentSubjectDTO> examStudentSubjectDTOList) {

        List<ExamStudentSubject> examStudentSubjects = examStudentSubjectDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        examStudentSubjects = examStudentSubjectRepo.saveAll(examStudentSubjects);
        return examStudentSubjects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ExamStudentSubjectDTO convertToDTO(ExamStudentSubject examStudentSubject) {
        return modelMapper.map(examStudentSubject, ExamStudentSubjectDTO.class);
    }

    private ExamStudentSubject convertToEntity(ExamStudentSubjectDTO examStudentSubjectDTO) {
        return modelMapper.map(examStudentSubjectDTO, ExamStudentSubject.class);
    }
}

