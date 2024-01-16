package com.server.controller.course;

import com.server.dto.ApiResponse;

import com.server.dto.student.StudentCourseDetailDTO;
import com.server.dto.student.StudentSemesterDTO;
import com.server.service.course.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student/course")
public class StudentCourseController {


    @Autowired
    private StudentCourseService courseDetailService;

    // add new course
    @PostMapping("/")
    public ResponseEntity<StudentCourseDetailDTO> create(@RequestBody StudentCourseDetailDTO course) {

        System.out.println(course);
        return new ResponseEntity<>(this.courseDetailService.create(course), HttpStatus.CREATED);
    }

    // update course
    @PutMapping("/{id}")
    public ResponseEntity<StudentCourseDetailDTO> update(@RequestBody StudentCourseDetailDTO course, @PathVariable Long id) {
        return new ResponseEntity<>(this.courseDetailService.update(course, id), HttpStatus.OK);
    }

    // get course by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentCourseDetailDTO> get(@PathVariable Long id) {

        return new ResponseEntity<>(this.courseDetailService.get(id), HttpStatus.OK);
    }

    // get all course list
    @GetMapping("/")
    public ResponseEntity<List<StudentCourseDetailDTO>> getAll() {
        return new ResponseEntity<>(this.courseDetailService.getAll(), HttpStatus.OK);
    }

    // delete course list by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {
        System.out.println("from deleted...");
        this.courseDetailService.delete(id);

        return new ResponseEntity<>(new ApiResponse("Course Successfully delete", true), HttpStatus.OK);
    }

    // add sem in course
    @PostMapping("/{id}/sem/")
    public ResponseEntity<StudentSemesterDTO> addSem(@PathVariable Long id, @RequestBody StudentSemesterDTO sem) {

        System.out.println("from add sem ");
        System.out.println(id);

        return new ResponseEntity<>(this.courseDetailService.addSem(id, sem), HttpStatus.CREATED);
    }

    // delete sem
    @DeleteMapping("/{id}/sem/{semId}")
    public ResponseEntity<ApiResponse> deleteSem(@PathVariable Long id, @PathVariable Long semId) {
        this.courseDetailService.deleteSem(id, semId);
        return new ResponseEntity<>(new ApiResponse("Successfully deleted Sem", true), HttpStatus.OK);
    }

}
