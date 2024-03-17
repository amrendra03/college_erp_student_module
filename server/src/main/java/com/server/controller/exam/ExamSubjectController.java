package com.server.controller.exam;

import com.server.dto.exam.ExamSubjectDTO;
import com.server.service.exam.ExamSubjectService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/exam/subjects")
public class ExamSubjectController {

    private final ExamSubjectService examSubjectService;
    private static final Logger logger = LoggerFactory.getLogger(ExamSubjectController.class);

    @Autowired
    public ExamSubjectController(ExamSubjectService examSubjectService) {
        this.examSubjectService = examSubjectService;
    }

    @GetMapping
    public List<ExamSubjectDTO> getAllSubjects() {
        logger.info("Fetching all subjects");
        return examSubjectService.getAllSubjects();
    }

    @GetMapping("/{id}")
    public ExamSubjectDTO getSubjectById(@PathVariable Long id) {
        logger.info("Fetching subject with id: {}", id);
        return examSubjectService.getSubjectById(id);
    }

    @PostMapping
    public ExamSubjectDTO addSubject(@RequestBody ExamSubjectDTO subjectDTO) {
        logger.info("Adding a new subject");
        return examSubjectService.addSubject(subjectDTO);
    }

    @PostMapping("/batch")
    public List<ExamSubjectDTO> saveSubjects(@RequestBody List<ExamSubjectDTO> subjectDTOList) {
        logger.info("Request to save list of subject in Exam Subject.");
        return examSubjectService.saveSubjects(subjectDTOList);
    }

    @PutMapping("/{id}")
    public ExamSubjectDTO updateSubject(@PathVariable Long id, @RequestBody ExamSubjectDTO subjectDTO) {
        logger.info("Updating subject with id: {}", id);
        return examSubjectService.updateSubject(id, subjectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteSubject(@PathVariable Long id) {
        logger.info("Deleting subject with id: {}", id);
        examSubjectService.deleteSubject(id);
    }
}
