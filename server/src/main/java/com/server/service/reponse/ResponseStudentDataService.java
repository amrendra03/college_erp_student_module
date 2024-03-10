package com.server.service.reponse;

import com.server.dto.StudentResponseDto;

public interface ResponseStudentDataService {

    StudentResponseDto getStudentData(String email);
}
