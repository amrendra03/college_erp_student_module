package com.server.controller.student;


import com.server.constant.API;
import com.server.dto.ApiResponse;
import com.server.dto.student.StudentSemesterDTO;
import com.server.dto.student.StudentSemesterProgressDTO;
import com.server.service.student.StudentCourseStatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(API.student + "/courses/{rollNo}/semesters")
public class StudentCourseStatusController {

    private final Logger log = LoggerFactory.getLogger(StudentCourseStatusController.class);

    @Autowired
    private StudentCourseStatusService statusService;

    @PostMapping
    public ResponseEntity<StudentSemesterProgressDTO> createSemesterProgress(@RequestBody StudentSemesterProgressDTO request,@PathVariable String rollNo) {
        log.info("Request to create semester progress: {}", request);
        StudentSemesterProgressDTO response = statusService.create(request,rollNo);
        log.info("Successfully created semester progress: {}", response);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<StudentSemesterProgressDTO> updateSemesterProgress(@RequestBody StudentSemesterProgressDTO request) {
        log.info("Request to update semester progress: {}", request.getProgressId());
        StudentSemesterProgressDTO response = statusService.update(request);
        log.info("Successfully updated semester progress: {}", response);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{semesterId}")
    public ResponseEntity<ApiResponse> deleteSemesterProgress(@PathVariable String rollNo, @PathVariable Long semesterId) {
        log.info("Requesting to delete semester progress: {}/{}", rollNo, semesterId);
        ApiResponse response = statusService.delete(rollNo, semesterId);
        log.info("Deleted semester progress: {}/{}", rollNo, semesterId);
        return new ResponseEntity<>(response, response.isSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping
    public ResponseEntity<List<StudentSemesterProgressDTO>> getAllSemesterProgress(@PathVariable String rollNo) {
        log.info("Request to get all semester progress for rollNo: {}", rollNo);
        List<StudentSemesterProgressDTO> response = statusService.getAll(rollNo);
        log.info("Successfully retrieved all semester progress for rollNo: {}", rollNo);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
