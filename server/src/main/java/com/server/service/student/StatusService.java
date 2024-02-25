package com.server.service.student;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentStatusDTO;

import java.util.List;

public interface StatusService {

    StudentStatusDTO create(StudentStatusDTO req);
    StudentStatusDTO update (StudentStatusDTO req);
    ApiResponse delete (StudentStatusDTO req);
    List<StudentStatusDTO> getAllStatus(String rollNo);

}
