package com.server.service.course;

import com.server.dto.student.StudentSubjectDto;
import com.server.entities.student.StudentSubject;

import java.util.List;

public interface SemesterSubjectService {

    StudentSubject create(Long id, StudentSubjectDto sem);

    StudentSubject update(StudentSubjectDto courseDetailDTO, Long id);

    StudentSubject get(Long subId);

    List<StudentSubject> getAll(Long semId);

    void delete(Long id);
}
