package com.server.controller.student;

import java.util.List;

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
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentDetailDTO;
import com.server.service.student.StudentService;

@RestController
@RequestMapping("/st1")
public class StudentControllerDetail {

   @Autowired
   private StudentService studentService;

   // create
   @PostMapping("/")
   public ResponseEntity<StudentDetailDTO> create(@RequestBody StudentDetailDTO st) {

      StudentDetailDTO x = this.studentService.creat(st);

      return new ResponseEntity<>(x, HttpStatus.CREATED);
   }

   // update
   @PutMapping("/{id}")
   public ResponseEntity<StudentDetailDTO> update(@RequestBody StudentDetailDTO st, @PathVariable Long id) {

      StudentDetailDTO x = this.studentService.update(st, id);

      return new ResponseEntity<>(x, HttpStatus.OK);
   }

   // get
   @GetMapping("/{id}")
   public ResponseEntity<StudentDetailDTO> get(@PathVariable Long id) {
      StudentDetailDTO st = this.studentService.get(id);
      return new ResponseEntity<>(st, HttpStatus.OK);
   }

   // get all
   @GetMapping("/")
   public ResponseEntity<List<StudentDetailDTO>> getAll() {
      List<StudentDetailDTO> st = this.studentService.getAll();
      return new ResponseEntity<>(st, HttpStatus.OK);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

      this.studentService.delete(id);
      return new ResponseEntity<>(new ApiResponse("Deleted Student successfully", true), HttpStatus.OK);
   }

}
