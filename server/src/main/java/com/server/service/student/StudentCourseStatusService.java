package com.server.service.student;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentSemesterDTO;
import com.server.dto.student.StudentSemesterProgressDTO;
import com.server.dto.student.StudentStatusDTO;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface StudentCourseStatusService {
    StudentSemesterProgressDTO create(StudentSemesterProgressDTO request,String rollNo);
    StudentSemesterProgressDTO update(StudentSemesterProgressDTO request);
    ApiResponse delete(String rollNo, Long semesterId );
    List<StudentSemesterProgressDTO> getAll(String studentId);

}
