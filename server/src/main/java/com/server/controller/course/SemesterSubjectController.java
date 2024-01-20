package com.server.controller.course;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentSubjectDto;
import com.server.repository.course.SemesterSubjectRepo;
import com.server.service.course.SemesterSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class SemesterSubjectController {

    @Autowired
    private SemesterSubjectService semesterSubjectService;


    @PostMapping("/{courseId}/{semId}/subject/")
    public ResponseEntity<?> addSubject(@PathVariable Long courseId, @PathVariable Long semId,@RequestBody StudentSubjectDto studentSubjectDto){

        StudentSubjectDto res = this.semesterSubjectService.create(courseId,semId,studentSubjectDto);
        if(res==null){
            return new ResponseEntity<>(new ApiResponse("Semester not found",false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

    @PutMapping("/{courseId}/semester/{semId}/subject/{subId}")
    public ResponseEntity<?> update(@PathVariable Long courseId,@PathVariable int semId, @PathVariable Long subId,@RequestBody StudentSubjectDto studentSubjectDto){

        StudentSubjectDto res = this.semesterSubjectService.update(courseId,semId,subId,studentSubjectDto);
        if(res==null){
            return new ResponseEntity<>(new ApiResponse("Semester not found",false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res,HttpStatus.CREATED);
    }

    @GetMapping("/{courseId}/semester/{semId}/subject/{subId}")
    public ResponseEntity<?> get(@PathVariable Long courseId,@PathVariable int semId, @PathVariable Long subId){

        StudentSubjectDto res = this.semesterSubjectService.get(courseId,semId,subId);
        if(res==null){
            return new ResponseEntity<>(new ApiResponse("Subject not found",false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @GetMapping("/{courseId}/semester/{semId}/subject")
    public ResponseEntity<?> getAll(@PathVariable Long courseId,@PathVariable int semId){

       List<StudentSubjectDto> res = this.semesterSubjectService.getAll(courseId,semId);
        if(res==null){
            return new ResponseEntity<>(new ApiResponse("Subject not found",false), HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(res,HttpStatus.OK);
    }

    @DeleteMapping("/{courseId}/semester/{semId}/subject/{subId}")
    public ResponseEntity<?> getAll(@PathVariable Long courseId,@PathVariable int semId,@PathVariable Long subId){

        ApiResponse res =this.semesterSubjectService.delete(courseId,semId,subId);

        return new ResponseEntity<>(res,HttpStatus.OK);
    }

}
