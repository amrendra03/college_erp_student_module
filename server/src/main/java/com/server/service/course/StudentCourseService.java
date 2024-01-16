package com.server.service.course;

import com.server.dto.student.StudentCourseDetailDTO;
import com.server.dto.student.StudentSemesterDTO;

import java.util.List;

public interface StudentCourseService {


    StudentCourseDetailDTO create(StudentCourseDetailDTO courseDetailDTO);

    StudentCourseDetailDTO update(StudentCourseDetailDTO courseDetailDTO, Long id);

    StudentCourseDetailDTO get(Long id);

    List<StudentCourseDetailDTO> getAll();

    void delete(Long id);

    StudentSemesterDTO addSem(Long id, StudentSemesterDTO sem);

    void deleteSem(Long id, Long semId);

}
