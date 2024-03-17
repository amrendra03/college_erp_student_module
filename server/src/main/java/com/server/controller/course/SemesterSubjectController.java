package com.server.controller.course;

import com.server.constant.API;
import com.server.dto.ApiResponse;
import com.server.dto.student.StudentSubjectDto;
import com.server.repository.course.SemesterSubjectRepo;
import com.server.service.course.SemesterSubjectService;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(API.student + "/course")
@Validated
public class SemesterSubjectController {

    private Logger log = LoggerFactory.getLogger(SemesterSubjectController.class);

    @Autowired
    private SemesterSubjectService semesterSubjectService;


    @PostMapping("/{courseId}/sem/{semNum}/subject")
    public ResponseEntity<?> addSubject(@PathVariable Long courseId, @PathVariable Long semNum, @RequestBody StudentSubjectDto studentSubjectDto) {
        log.info("Request to save subject course id {}, semId {}", courseId, semNum);
        StudentSubjectDto res = this.semesterSubjectService.create(courseId, semNum, studentSubjectDto);
        if (res == null) {
            log.info("failed saved. ");
            return new ResponseEntity<>(new ApiResponse("Semester not found", false), HttpStatus.BAD_REQUEST);
        }
        log.info("Successfully saved. ");
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}/semester/{semId}/subject/{subId}")
    public ResponseEntity<?> update(@PathVariable Long courseId, @PathVariable int semId, @PathVariable Long subId, @RequestBody StudentSubjectDto studentSubjectDto) {

        StudentSubjectDto res = this.semesterSubjectService.update(courseId, semId, subId, studentSubjectDto);
        if (res == null) {
            return new ResponseEntity<>(new ApiResponse("Semester not found", false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}/semester/{semId}/subject/{subId}")
    public ResponseEntity<?> get(@PathVariable Long courseId, @PathVariable int semId, @PathVariable Long subId) {
        log.info("Request to get the subject by course id {}, sem {}, subject id {}", courseId, semId, subId);
        StudentSubjectDto res = this.semesterSubjectService.get(courseId, semId, subId);
        if (res == null) {
            return new ResponseEntity<>(new ApiResponse("Subject not found", false), HttpStatus.BAD_REQUEST);
        }
        log.info("Successfully response data {}", res);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/{courseId}/semester/{semId}/subject")
    public ResponseEntity<?> getAll(@PathVariable Long courseId, @PathVariable Long semId) {

        List<StudentSubjectDto> res = this.semesterSubjectService.getAll(courseId, semId);
        if (res == null) {
            return new ResponseEntity<>(new ApiResponse("Subject not found", false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/semester/{semId}/subject/{subId}")
    public ResponseEntity<?> delete(@PathVariable @NotNull(message = "Enter Valid Course Id") Long courseId,
                                    @PathVariable @Min(value = 1, message = "Value must be positive") int semId,
                                    @PathVariable @NotNull(message = "Enter Valid Semester Id") Long subId) {

        ApiResponse res = this.semesterSubjectService.delete(courseId, semId, subId);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    @GetMapping("/all/{courseId}")
    public ResponseEntity<?>getAllByCourseId(@PathVariable Long courseId){
        List<StudentSubjectDto> res=semesterSubjectService.getAllByCourseId(courseId);
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
