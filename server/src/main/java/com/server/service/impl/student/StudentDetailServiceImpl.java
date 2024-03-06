package com.server.service.impl.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentCourseRegistrationDTO;
import com.server.dto.student.StudentRegisterDTO;
import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentCourseRegistration;
import com.server.exception.ResourceNotFoundException;
import com.server.exception.custom.UserExistAlready;
import com.server.jwt.JwtToken;
import com.server.repository.course.StudentCourseDetailRepo;
import com.server.repository.student.StudentCourseRegistrationRepo;
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
   private StudentCourseRegistrationRepo studentCourseRegistrationRepo;

   @Autowired
   private StudentCourseDetailRepo studentCourseDetailRepo;
   @Autowired
   private ModelMapper modelMapper;

   @Autowired
   private BCryptPasswordEncoder bCryptPasswordEncoder;

   private static  final Logger log = LoggerFactory.getLogger(StudentDetailServiceImpl.class);

   @Autowired
   private JwtToken jwtToken;
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

      List<StudentDetail> students=(this.studentRepo.findAllByEmail(dto.getEmail()));

      if(students.size()>1 && id!=students.get(0).getStudentId()){
         throw new UserExistAlready("User already exist by the email please enter another email: "+dto.getEmail());
      }


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
         existingStudent.setEmail(existingStudent.getEmail());
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
   public StudentDetailDTO get(String token) {
//      Optional<StudentDetail> optionalStudent = this.studentRepo.findById(id);
      String email = this.jwtToken.getUsernameFromToken(token);
     StudentDetail optionalStudent =  this.studentRepo.findByEmail(email);

      if (optionalStudent!=null) {
         return this.modelMapper.map(optionalStudent, StudentDetailDTO.class);
      } else {
         log.warn("Student with ID {} not found.", email);
         throw new ResourceNotFoundException("Student not found.", "email: "+email,0L);
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

   @Override
   public ApiResponse studentRegisterCourse(StudentCourseRegistrationDTO req) {
      log.info("Student Registration in Course Req: {}",req);
      if (this.studentCourseRegistrationRepo.findByStudentDetailRollNoAndStudentCourseDetailCourseId(req.getStudentRollNo(), req.getStudentCourseDetailId()).isPresent()) {

         log.info("Student Registration done already in Course Req: {}",req);
         return new ApiResponse("Student Already Registered with this course", false);
      } else {
         StudentDetail st = this.studentRepo.findByRollNo(req.getStudentRollNo());
         Optional<StudentCourseDetail> cor = this.studentCourseDetailRepo.findById(req.getStudentCourseDetailId());

         log.info("Student Registration Student Detail found: {}",st);

         log.info("Student Registration Course Detail found: {}",cor.get());
         // Check if StudentDetail and StudentCourseDetail exist
         if (st == null || !cor.isPresent() ) {
            return new ApiResponse("Invalid Student or Course details", false);
         }
         else if(req.getStudentDetailId() != st.getStudentId()){
            return new ApiResponse("Roll No and Student ID not Identical.", false);
         }

         StudentCourseRegistration res = new StudentCourseRegistration();
         res.setStudentDetail(st);
         res.setStudentCourseDetail(cor.get());
         res.setRollNo(req.getStudentRollNo());

         // Save the StudentCourseRegistration entity
         this.studentCourseRegistrationRepo.save(res);

         return new ApiResponse("Registration successfully completed. ID: " + res.getRegistrationId(), true);
      }
   }

   @Override
   public ApiResponse studentRegisterCourseDelete(StudentCourseRegistrationDTO req) {
      StudentCourseRegistration res = this.studentCourseRegistrationRepo.findById(req.getRegistrationId())
              .orElseThrow();

      if(res!=null)
      {
         this.studentCourseRegistrationRepo.delete(res);
      }else{
         return new ApiResponse("Not found Student not registered with this course: "+req.getRegistrationId(),false);
      }
    return  new ApiResponse("Student Registration deleted Successfully: "+res.getRegistrationId(),true);
   }

   @Override
   public List<StudentCourseRegistrationDTO> getAllRegisteredCourses(String roll) {
      log.info("Getting courses for student with roll number: {}", roll);

      List<StudentCourseRegistration> courseList = studentCourseRegistrationRepo.findByRollNo(roll);
      log.info("Registered courses found: {}", courseList.size());

      if (courseList.isEmpty()) {
         throw new ResourceNotFoundException("Student has not registered for any course", roll, 0L);
      }

      return courseList.stream()
              .map(course -> modelMapper.map(course, StudentCourseRegistrationDTO.class))
              .collect(Collectors.toList());
   }

}
