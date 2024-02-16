package com.server.service.impl.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentRegisterDTO;
import com.server.exception.ResourceNotFoundException;
import com.server.exception.custom.UserExistAlready;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.server.dto.student.StudentDetailDTO;
import com.server.entities.student.StudentDetail;
import com.server.repository.student.StudentDetailRepo;
import com.server.service.student.StudentService;

@Service
public class StudentDetailServiceImpl implements StudentService {

   @Autowired
   private StudentDetailRepo studentRepo;

   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   private static  final Logger log = LoggerFactory.getLogger(StudentDetailServiceImpl.class);

   // create
   @Override
   public StudentDetailDTO creat(StudentRegisterDTO req) {

      req.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));

      log.info("Received Student registration request: {}", req);
      StudentDetail st = this.modelMapper.map(req, StudentDetail.class);

      log.info("Save Student registration response: {}", st);

      if(this.studentRepo.findByEmail(st.getEmail())!=null){
         throw new UserExistAlready("Email Already exist");
      }

      StudentDetail res = this.studentRepo.save(st);

      return this.modelMapper.map(res, StudentDetailDTO.class);
   }

   // update
   @Override
   public StudentDetailDTO update(StudentDetailDTO dto, Long id) {

      log.info("Processing Update Student ID {}:",id);
      Optional<StudentDetail> optionalStudent = this.studentRepo.findById(id);

      if (optionalStudent.isPresent()) {
         StudentDetail existingStudent = optionalStudent.get();

         //RollNo and EnrollmentNo set
         if (existingStudent.getRollNo() == null && existingStudent.getEnrollmentNo() == null) {
            log.info("Student Roll no and Enrollment no not Assigned...");

            String latestRollNo = studentRepo.findLatestRollNo();
            String latestEnrollmentNo = studentRepo.findLatestEnrollmentNo();

            existingStudent.setRollNo(generateRollOrEnrollmentNo(latestRollNo, "RN10000"));
            existingStudent.setEnrollmentNo(generateRollOrEnrollmentNo(latestEnrollmentNo, "EN10000"));

            log.info("Assigned RollNo: {} and EnrollmentNo: {} to the student.", existingStudent.getRollNo(), existingStudent.getEnrollmentNo());
         } else {
            log.info("Student Roll no and Enrollment No already Assigned");
         }

         log.info("Ready to save in Table Student: {}",existingStudent);

         existingStudent.setName(dto.getName());
         existingStudent.setEmail(dto.getEmail());
         existingStudent.setPhone(dto.getPhone());
         existingStudent.setDOB(dto.getDOB());

         StudentDetail res=null;
         res = this.studentRepo.save(existingStudent);
         log.debug("Response after saved in Table: {}",res);

         return this.modelMapper.map(res, StudentDetailDTO.class);
      } else {
         log.warn("Student with ID {} not found for update.", id);
         throw new ResourceNotFoundException("Student not found for update.", "Student ID", id);
      }
   }

   private String generateRollOrEnrollmentNo(String latestValue, String defaultValue) {
      if (latestValue == null) {
         log.debug("Latest value is null.");
         return defaultValue;
      } else {
         log.debug("Found latest value: {}", latestValue);
         return alphaString(latestValue);
      }
   }

   // Handle Alpha numeric value in String for Roll No nad Enrollment No
   public  String alphaString(String input){

      String alphaPart = input.replaceAll("[^A-Za-z]", "");
      String numericPart = input.replaceAll("[^0-9]", "");

      // Increment the numeric part
      int numericValue = Integer.parseInt(numericPart);
      numericValue++;

      // Combine alpha and incremented numeric parts
      String result = alphaPart + numericValue;
      return result;
   }

   // get
   @Override
   public StudentDetailDTO get(Long id) {
      Optional<StudentDetail> optionalStudent = this.studentRepo.findById(id);

      if (optionalStudent.isPresent()) {
         return this.modelMapper.map(optionalStudent.get(), StudentDetailDTO.class);
      } else {
         log.warn("Student with ID {} not found.", id);
         throw new ResourceNotFoundException("Student not found.", "Student ID", id);
      }
   }

   // get all
   @Override
   public List<StudentDetailDTO> getAll() {
      List<StudentDetail> x = this.studentRepo.findAll();

      List<StudentDetailDTO> res = x.stream().map((x2) -> this.modelMapper.map(x2, StudentDetailDTO.class))
            .collect(Collectors.toList());

      return res;
   }

   // delete
   @Override
   public void delete(Long id) {
      Optional<StudentDetail> optionalStudent = this.studentRepo.findById(id);

      if (optionalStudent.isPresent()) {
         this.studentRepo.delete(optionalStudent.get());
      } else {
         log.warn("Student with ID {} not found for deletion.", id);
         throw new ResourceNotFoundException("Student not found for deletion.", "Student ID", id);
      }
   }

}
