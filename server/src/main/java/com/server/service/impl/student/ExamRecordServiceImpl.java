package com.server.service.impl.student;

import com.server.dto.exam.ExamRecordDTO;
import com.server.entities.exam.ExamRecord;
import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentCourseRegistration;
import com.server.entities.student.StudentDetail;
import com.server.entities.student.StudentSemester;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.course.StudentCourseDetailRepo;
import com.server.repository.course.StudentSemesterRepo;
import com.server.repository.student.ExamRecordRepository;
import com.server.repository.student.StudentCourseRegistrationRepo;
import com.server.repository.student.StudentDetailRepo;
import com.server.service.student.ExamRecordService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamRecordServiceImpl implements ExamRecordService {

    private static final Logger logger = LoggerFactory.getLogger(ExamRecordServiceImpl.class);
    private final ExamRecordRepository examRecordRepository;
    private final ModelMapper modelMapper;
    @Autowired
    private StudentDetailRepo studentDetailRepo;

    @Autowired
    private StudentCourseDetailRepo studentCourseDetailRepo;

    @Autowired
    private StudentCourseRegistrationRepo studentCourseRegistrationRepo;

    @Autowired
    public ExamRecordServiceImpl(ExamRecordRepository examRecordRepository, ModelMapper modelMapper) {
        this.examRecordRepository = examRecordRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExamRecordDTO> getAllExamRecords(String email) {
        try {
            StudentDetail studentDetail = studentDetailRepo.findByEmail(email);
            List<ExamRecord> examRecords = examRecordRepository.findAllByStudentDetail(studentDetail);
            logger.info("Retrieved all exam records.");
            return examRecords.stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            throw new ResourceNotFoundException("Exam recorde not found", "student", 0L);
        }
    }

    @Override
    public ExamRecordDTO getExamRecordById(Long id) {
        Optional<ExamRecord> optionalExamRecord = examRecordRepository.findById(id);
        if (optionalExamRecord.isPresent()) {
            logger.info("Retrieved exam record with ID: {}", id);
            return convertToDTO(optionalExamRecord.get());
        } else {
            logger.warn("Exam record with ID {} not found.", id);
            return null;
        }
    }

    @Override
    public ExamRecordDTO addExamRecord(ExamRecordDTO examRecordDTO) {
        try {
            logger.info("Processing the exam add recorde...");
            Optional<StudentDetail> studentDetail = studentDetailRepo.findById(examRecordDTO.getStudentDetailId());
            if (studentDetail.isPresent()) {
                logger.info("student found.");
                List<StudentCourseRegistration> course = studentCourseRegistrationRepo.findByRollNo(studentDetail.get().getRollNo());
                boolean flag = false;
                for (StudentCourseRegistration i : course) {
                    if (i.getStudentCourseDetail().getCourseId() == examRecordDTO.getCourseId()) {
                        examRecordDTO.setName(i.getStudentCourseDetail().getCourseName()+"/Sem"+examRecordDTO.getName());
                        flag = true;
                    }
                }
                if (flag) {

                    ExamRecord res = examRecordRepository.save(modelMapper.map(examRecordDTO, ExamRecord.class));
                    return convertToDTO(res);
                } else {
                    throw new ResourceNotFoundException("You have not registered to the course", "Course ID", examRecordDTO.getCourseId());
                }
            } else {
                throw new ResourceNotFoundException("Student data not found", "", examRecordDTO.getStudentDetailId());
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public ExamRecordDTO updateExamRecord(Long id, ExamRecordDTO examRecordDTO) {
        try {

            Optional<ExamRecord> optionalExamRecord = examRecordRepository.findById(id);
            if (optionalExamRecord.isPresent()) {
                ExamRecord examRecord = optionalExamRecord.get();
                // Update fields
                examRecord.setStatus(examRecordDTO.getStatus());
                examRecord.setGpa(examRecordDTO.getGpa());
                examRecord.setProgressDate(examRecordDTO.getProgressDate());
                examRecord = examRecordRepository.save(examRecord);
                logger.info("Updated exam record with ID: {}", id);
                return convertToDTO(examRecord);
            } else {
                logger.warn("Exam record with ID {} not found. Update failed.", id);
                throw new ResourceNotFoundException("Exam Data not found", "exam id", id); // Record not found
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void deleteExamRecord(Long id) {
        if (examRecordRepository.existsById(id)) {
            examRecordRepository.deleteById(id);
            logger.info("Deleted exam record with ID: {}", id);
        } else {
            logger.warn("Exam record with ID {} not found. Deletion failed.", id);
            throw new ResourceNotFoundException("Exam record not found", "Exam id", id);
        }
    }

    private ExamRecordDTO convertToDTO(ExamRecord examRecord) {
        return modelMapper.map(examRecord, ExamRecordDTO.class);
    }

    private ExamRecord convertToEntity(ExamRecordDTO examRecordDTO) {
        return modelMapper.map(examRecordDTO, ExamRecord.class);
    }
}
