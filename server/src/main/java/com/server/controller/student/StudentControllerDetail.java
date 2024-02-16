package com.server.controller.student;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.server.constant.API;
import com.server.dto.student.StudentRegisterDTO;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
   @GetMapping("/{id}")
   public ResponseEntity<StudentDetailDTO> get(@PathVariable Long id) {
      Log.info("Fetching student with id: {}",id);

      StudentDetailDTO res = this.studentService.get(id);

      Log.info("Fetched student: {}",res);
      return new ResponseEntity<>(res, HttpStatus.OK);
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

}
