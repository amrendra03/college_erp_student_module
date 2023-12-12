package com.server.service.student;

import java.util.List;

import com.server.dto.student.StudentDetailDTO;

public interface StudentService {

    StudentDetailDTO creat(StudentDetailDTO x);

    StudentDetailDTO update(StudentDetailDTO dto, Long id);

    StudentDetailDTO get(Long id);

    List<StudentDetailDTO> getAll();

    void delete(Long id);

}
