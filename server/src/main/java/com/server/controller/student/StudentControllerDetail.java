package com.server.controller.student;

import java.util.*;

import com.server.constant.API;
import com.server.dto.student.StudentCourseRegistrationDTO;
import com.server.dto.student.StudentRegisterDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentDetailDTO;
import com.server.service.student.StudentService;

@RestController
@RequestMapping(API.student)
@Validated
public class StudentControllerDetail {

   @Autowired
   private StudentService studentService;

   private  static  final Logger Log = LoggerFactory.getLogger(StudentControllerDetail.class);

   // create
   // JWT Controller

   // update
   @PutMapping("/{id}")
   public ResponseEntity<StudentDetailDTO> update(@RequestBody StudentDetailDTO st, @PathVariable Long id) {

      Log.info("Updating student with Id{}: {}",id,st);
      StudentDetailDTO res = this.studentService.update(st, id);

      Log.info("Update student:{}",res);
      return new ResponseEntity<>(res, HttpStatus.OK);
   }

   // get
   @GetMapping("/detail")
   public ResponseEntity<StudentDetailDTO> get(HttpServletRequest request) {
      Log.info("Fetching student..........");
      String token = this.extractTokenFromRequest(request);
      StudentDetailDTO res = this.studentService.get(token);

      Log.info("Successfully fetched student: {}",res);
      return new ResponseEntity<>(res, HttpStatus.OK);
   }

   private String extractTokenFromRequest(HttpServletRequest request) {
      String bearerToken = request.getHeader("Authorization");
      if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
         return bearerToken.substring(7);
      }
      return null;
   }

   // get all
   @GetMapping("/")
   public ResponseEntity<List<StudentDetailDTO>> getAll() {

      Log.info(" Fetching all student");
      List<StudentDetailDTO> res = this.studentService.getAll();

      Log.info("Fetched {} students",res);
      return new ResponseEntity<>(res, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

      Log.info("Deleting student with id: {}",id);
      this.studentService.delete(id);

      Log.info("Deleted student with Id: {}",id);

      return new ResponseEntity<>(new ApiResponse("Deleted Student successfully", true), HttpStatus.OK);
   }

   @PostMapping("/course/registration")
   public ResponseEntity<ApiResponse> registerCourse(@RequestBody StudentCourseRegistrationDTO req){
      Log.info("Student {} registeration for course {} is Processing",req.getStudentDetailId(),req.getStudentCourseDetailId());
      ApiResponse res  = this.studentService.studentRegisterCourse(req);
      Log.info("Successfully {} Registered to Course {}",req.getStudentDetailId(),req.getStudentCourseDetailId());
      if(res.isSuccess()==false){
         return new ResponseEntity<>(res,HttpStatus.CONFLICT);
      }
      return new ResponseEntity<>(res,HttpStatus.CREATED);
   }
   @GetMapping("/course/registration/{id}")
   public ResponseEntity<?> getRegisteredCourse(@PathVariable String id) {
      Log.info("Get Registered Course List");
      List<StudentCourseRegistrationDTO> res = this.studentService.getAllRegisteredCourses(id);
      Log.info("Successfully got registered list for Course. Size: {}", res.size());
      Collections.sort(res, Comparator.comparing(StudentCourseRegistrationDTO::getType));

      return new ResponseEntity<>(res, HttpStatus.OK);
   }

   @DeleteMapping("/course/registration")
   public ResponseEntity<ApiResponse> registerCourseDelete(@RequestBody StudentCourseRegistrationDTO req){
      Log.info("Student {} registration {} Deleting Processing",req.getStudentDetailId(),req.getRegistrationId());
      ApiResponse res  = this.studentService.studentRegisterCourseDelete(req);
      Log.info("Successfully {} Registration deleted id {}",req.getStudentDetailId(),req.getRegistrationId());
      if(res.isSuccess()==false){
         return new ResponseEntity<>(res,HttpStatus.NOT_FOUND);
      }
      return new ResponseEntity<>(res,HttpStatus.CREATED);
   }
}
