package com.server.service.setting;

import com.server.dto.student.StudentDetailDTO;
import org.springframework.stereotype.Service;

@Service
public interface SettingService {

    StudentDetailDTO update(String email,StudentDetailDTO req);
}
