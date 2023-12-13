package com.server.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
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

}
