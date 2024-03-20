package com.server.controller.exam;

import com.server.dto.ApiResponse;
import com.server.dto.exam.ExamStudentSubjectDTO;
import com.server.service.exam.ExamStudentSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/exam/student/subjects")
public class ExamStudentSubjectController {

    private final ExamStudentSubjectService examStudentSubjectService;

    private Logger log = LoggerFactory.getLogger(ExamStudentSubjectController.class);

    @Autowired
    public ExamStudentSubjectController(ExamStudentSubjectService examStudentSubjectService) {
        this.examStudentSubjectService = examStudentSubjectService;
    }

    @GetMapping("/roll_no/{rollNo}/status/{status}")
    public List<ExamStudentSubjectDTO> getAllExamStudentSubjects(@PathVariable String rollNo,@PathVariable String status) {
        log.info("getAll Exam Student Subjects...");
        return examStudentSubjectService.getAllExamStudentSubjects(rollNo,status);
    }

    @GetMapping("/{id}")
    public ExamStudentSubjectDTO getExamStudentSubjectById(@PathVariable Long id) {
        log.info("get Exam Student Subject by id...");
        return examStudentSubjectService.getExamStudentSubjectById(id);
    }

    @PostMapping
    public ResponseEntity<?> addExamStudentSubject(@RequestBody Map<String, Boolean> requestBody,@RequestHeader("Authorization") String authorization) {
        log.info("add Exam student subject...");
        String token = authorization.replace("Bearer ", "");
        ApiResponse res = examStudentSubjectService.addExamStudentSubject(requestBody,token);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ExamStudentSubjectDTO updateExamStudentSubject(@PathVariable Long id, @RequestBody ExamStudentSubjectDTO examStudentSubjectDTO) {
        log.info("update exam student subject...");
        return examStudentSubjectService.updateExamStudentSubject(id, examStudentSubjectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExamStudentSubject(@PathVariable Long id) {
        log.info("delete exam student subject...");
        examStudentSubjectService.deleteExamStudentSubject(id);
    }

    @PostMapping("/batch")
    public List<ExamStudentSubjectDTO> saveExamStudentSubjects(@RequestBody List<ExamStudentSubjectDTO> examStudentSubjectDTOList) {
        return examStudentSubjectService.saveExamStudentSubjects(examStudentSubjectDTOList);
    }
}
