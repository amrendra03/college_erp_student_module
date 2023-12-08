package com.server.controller;

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
import com.server.dto.CourseDto;
import com.server.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

   @Autowired
   private CourseService courseService;

   // create

   @PostMapping("/")
   public ResponseEntity<CourseDto> create(@RequestBody CourseDto courseDto) {

      CourseDto x = this.courseService.create(courseDto);

      return new ResponseEntity<>(x, HttpStatus.CREATED);
   }

   // Update
   @PutMapping("/{id}")
   public ResponseEntity<CourseDto> update(@PathVariable Long id, @RequestBody CourseDto courseDto) {

      CourseDto course = this.courseService.update(courseDto, id);

      return new ResponseEntity<>(course, HttpStatus.OK);
   }

   // get single course
   @GetMapping("/{id}")
   public ResponseEntity<CourseDto> get(@PathVariable Long id) {

      CourseDto course = this.courseService.get(id);
      return new ResponseEntity<>(course, HttpStatus.OK);
   }

   // list get
   @GetMapping("/")
   public ResponseEntity<List<CourseDto>> getAll() {
      List<CourseDto> x = this.courseService.getAll();
      return new ResponseEntity<>(x, HttpStatus.OK);
   }

   // delete
   @DeleteMapping("/{id}")
   public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
      this.courseService.delete(id);
      return new ResponseEntity<>(new ApiResponse("Delete successfully Course", true), HttpStatus.OK);
   }

}
