package com.server.controller.student;

import com.server.constant.API;
import com.server.dto.exam.ExamRecordDTO;
import com.server.jwt.JwtToken;
import com.server.service.student.ExamRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(API.student+"/exam/record")
public class ExamRecordController {

    private final ExamRecordService examRecordService;

    @Autowired
    private JwtToken jwtToken;

    @Autowired
    public ExamRecordController(ExamRecordService examRecordService) {
        this.examRecordService = examRecordService;
    }

    @GetMapping
    public List<ExamRecordDTO> getAllExamRecords(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String email= jwtToken.getUsernameFromToken(token);
        return examRecordService.getAllExamRecords(email);
    }

    @GetMapping("/{id}")
    public ExamRecordDTO getExamRecordById(@PathVariable Long id) {
        return examRecordService.getExamRecordById(id);
    }

    @PostMapping
    public ExamRecordDTO addExamRecord(@RequestBody ExamRecordDTO examRecordDTO) {
        return examRecordService.addExamRecord(examRecordDTO);
    }

    @PutMapping("/{id}")
    public ExamRecordDTO updateExamRecord(@PathVariable Long id, @RequestBody ExamRecordDTO examRecordDTO) {
        return examRecordService.updateExamRecord(id, examRecordDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteExamRecord(@PathVariable Long id) {
        examRecordService.deleteExamRecord(id);
    }
}
