package com.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.StudentDto;
import com.server.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

   @Autowired
   private StudentService studentService;

   // Create Student

   @PostMapping("/")
   public ResponseEntity<StudentDto> create(@RequestBody StudentDto studentDto) {
      StudentDto response = this.studentService.create(studentDto);
      return new ResponseEntity<StudentDto>(response, HttpStatus.CREATED);
   }
}
