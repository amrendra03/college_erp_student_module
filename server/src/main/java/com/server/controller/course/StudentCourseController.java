package com.server.controller.course;

import com.server.constant.API;
import com.server.dto.ApiResponse;

import com.server.dto.student.StudentCourseDetailDTO;
import com.server.dto.student.StudentSemesterDTO;
import com.server.service.course.StudentCourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(API.student+API.course)
public class StudentCourseController {


    @Autowired
    private StudentCourseService courseDetailService;


    private static final Logger log = LoggerFactory.getLogger(StudentCourseController.class);

    // add new course
    @PostMapping(API.BASE)
    public ResponseEntity<StudentCourseDetailDTO> create(@RequestBody StudentCourseDetailDTO course) {

        log.info("Received request to create new course: {}", course);

        try {
            StudentCourseDetailDTO createdCourse = this.courseDetailService.create(course);
            log.info("Created course: {}", createdCourse);
            return new ResponseEntity<>(createdCourse, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating course", e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    // update course
    @PutMapping("/{id}")
    public ResponseEntity<StudentCourseDetailDTO> update(@RequestBody StudentCourseDetailDTO course, @PathVariable Long id) {
        log.info("Received request to update course with ID {}: {}", id, course);

        try {
            StudentCourseDetailDTO updatedCourse = this.courseDetailService.update(course, id);
            log.info("Updated course: {}", updatedCourse);
            return new ResponseEntity<>(updatedCourse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating course with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    // get course by id
    @GetMapping("/{id}")
    public ResponseEntity<StudentCourseDetailDTO> get(@PathVariable Long id) {

        log.debug("Received request to fetch course with ID: {}", id);

        try {
            StudentCourseDetailDTO fetchedCourse = this.courseDetailService.get(id);
            log.debug("Fetched course: {}", fetchedCourse);
            return new ResponseEntity<>(fetchedCourse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching course with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    // get all course list
    @GetMapping(API.BASE)
    public ResponseEntity<List<StudentCourseDetailDTO>> getAll() {

        log.debug("Received request to fetch all courses");

        try {
            List<StudentCourseDetailDTO> courseList = this.courseDetailService.getAll();
            log.debug("Fetched {} course details", courseList.size());
            return new ResponseEntity<>(courseList, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error fetching all courses", e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    // delete course list by id
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Long id) {

        log.info("Received request to delete course with ID: {}", id);

        try {
            this.courseDetailService.delete(id);
            log.info("Deleted course with ID: {}", id);
            return new ResponseEntity<>(new ApiResponse("Course Successfully deleted", true), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting course with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    // add sem in course
    @PostMapping("/{id}"+API.sem+API.BASE)
    public ResponseEntity<StudentSemesterDTO> addSem(@PathVariable Long id, @RequestBody StudentSemesterDTO sem) {

        log.info("Received request to add semester to course with ID {}: {}", id, sem);

        try {
            StudentSemesterDTO addedSemester = this.courseDetailService.addSem(id, sem);
            log.info("Added semester: {}", addedSemester);
            return new ResponseEntity<>(addedSemester, HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error adding semester to course with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    // delete sem
    @DeleteMapping("/{id}"+API.sem+"/{semId}")
    public ResponseEntity<ApiResponse> deleteSem(@PathVariable Long id, @PathVariable Long semId) {
        log.info("Received request to delete semester with ID {} from course with ID: {}", semId, id);

        try {
            this.courseDetailService.deleteSem(id, semId);
            log.info("Deleted semester with ID {} from course with ID: {}", semId, id);
            return new ResponseEntity<>(new ApiResponse("Successfully deleted Sem", true), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error deleting semester with ID {} from course with ID: {}", semId, id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

}
