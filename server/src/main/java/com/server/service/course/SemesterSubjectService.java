package com.server.service.course;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentSubjectDto;
import com.server.entities.student.StudentSubject;

import java.util.List;

public interface SemesterSubjectService {

    StudentSubjectDto create(Long courseId,Long semId, StudentSubjectDto sub);

    StudentSubjectDto update(Long courseId,int semId,Long subId,StudentSubjectDto courseDetailDTO);

    StudentSubjectDto get(Long courseId,int semId,Long subId);

    List<StudentSubjectDto> getAll(Long courseId,int semId);

    ApiResponse delete(Long courseId, int semId, Long subId);

}
