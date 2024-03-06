package com.server.service.student;

import java.util.List;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentCourseRegistrationDTO;
import com.server.dto.student.StudentDetailDTO;
import com.server.dto.student.StudentRegisterDTO;

public interface StudentService {

    StudentDetailDTO creat(StudentRegisterDTO x);

    StudentDetailDTO update(StudentDetailDTO dto, Long id);

    StudentDetailDTO get(String token);

    List<StudentDetailDTO> getAll();

    void delete(Long id);

    ApiResponse studentRegisterCourse(StudentCourseRegistrationDTO req);
    ApiResponse studentRegisterCourseDelete(StudentCourseRegistrationDTO req);
    List<StudentCourseRegistrationDTO> getAllRegisteredCourses(String roll);

}
