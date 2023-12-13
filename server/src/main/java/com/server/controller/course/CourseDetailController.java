package com.server.controller.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.server.dto.course.CourseDetailDTO;
import com.server.service.course.CourseDetailService;

@RestController
@RequestMapping("/cour1")
public class CourseDetailController {

   @Autowired
   private CourseDetailService courseDetailService;

   @PostMapping("/")
   public ResponseEntity<CourseDetailDTO> create(@RequestBody CourseDetailDTO course) {

      return new ResponseEntity<>(this.courseDetailService.create(course), HttpStatus.CREATED);
   }

   @PutMapping("/{id}")
   public ResponseEntity<CourseDetailDTO> update(@RequestBody CourseDetailDTO course, @PathVariable Long id) {
      return new ResponseEntity<>(this.courseDetailService.update(course, id), HttpStatus.OK);
   }

   @GetMapping("/{id}")
   public ResponseEntity<CourseDetailDTO> get(@PathVariable Long id) {

      return new ResponseEntity<>(this.courseDetailService.get(id), HttpStatus.OK);
   }

   @GetMapping("/")
   public ResponseEntity<List<CourseDetailDTO>> getAll() {
      return new ResponseEntity<>(this.courseDetailService.getAll(), HttpStatus.OK);
   }

}
