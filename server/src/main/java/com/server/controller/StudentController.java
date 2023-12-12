package com.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.server.constant.AppConstant;
import com.server.dto.ApiResponse;
import com.server.dto.ResponseStudent;
import com.server.dto.StudentDto;
import com.server.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

   @Autowired
   private StudentService studentService;

   // Create Student

   @PostMapping("/create")
   public ResponseEntity<StudentDto> create(@RequestBody StudentDto studentDto) {
      StudentDto response = this.studentService.create(studentDto);
      return new ResponseEntity<StudentDto>(response, HttpStatus.CREATED);
   }

   // delete student
   @DeleteMapping(value = "/delete/{rollNo}")
   public ResponseEntity<ApiResponse> delete(@PathVariable Long rollNo) {
      System.out.println(rollNo);
      this.studentService.delete(rollNo);
      return new ResponseEntity<>(new ApiResponse("Student deleted successfully", true), HttpStatus.OK);
   }

   // update student
   @PutMapping("/update/{rollNo}")
   public ResponseEntity<StudentDto> update(@RequestBody StudentDto st, @PathVariable Long rollNo) {

      System.out.println(st);
      System.out.println(rollNo);

      StudentDto res = this.studentService.update(st, rollNo);

      return new ResponseEntity<>(res, HttpStatus.CREATED);
   }

   // get student
   @GetMapping("/get/{rollNo}")
   public ResponseEntity<StudentDto> get(@PathVariable Long rollNo) {

      StudentDto res = this.studentService.get(rollNo);
      System.out.println(rollNo);
      System.out.println(res);

      return new ResponseEntity<>(res, HttpStatus.OK);
   }

   // get all student
   @GetMapping("/all")
   public ResponseEntity<ResponseStudent> getAll(
         @RequestParam(value = "pageNumber", defaultValue = AppConstant.PAGE_NUMBER, required = false) Integer pageNumber,
         @RequestParam(value = "pageSize", defaultValue = AppConstant.PAGE_SIZE, required = false) Integer pageSize,
         @RequestParam(value = "sortBy", defaultValue = AppConstant.SORT_BY, required = false) String sortBy) {

      ResponseStudent res = this.studentService.getAll(pageNumber, pageSize, sortBy);

      // System.out.println("from get all student
      // .....................................");

      return new ResponseEntity<>(res, HttpStatus.OK);
   }

}
