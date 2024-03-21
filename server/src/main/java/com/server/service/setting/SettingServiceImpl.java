package com.server.service.setting;

import com.server.dto.student.StudentDetailDTO;
import com.server.entities.student.StudentDetail;
import com.server.exception.ResourceNotFoundException;
import com.server.exception.custom.DataOperationException;
import com.server.repository.student.StudentDetailRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

public class SettingServiceImpl implements SettingService {

    private ModelMapper modelMapper;
    private Logger log = LoggerFactory.getLogger(SettingServiceImpl.class);
    private StudentDetailRepo studentDetailRepo;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public StudentDetailDTO update(String email, StudentDetailDTO req) {

        try {
            log.info("Processing to update the student detail");

            StudentDetail student = studentDetailRepo.findByEmail(email);
            if (student == null) {
                throw new ResourceNotFoundException("Student data not found", "username", 0L);
            }
            student.setPassword(bCryptPasswordEncoder.encode(req.getPassword()));
            student.setPhone(req.getPhone());
            student.setName(req.getName());
            student = studentDetailRepo.save(student);
            log.info("Successfully updated");
            return modelMapper.map(student, StudentDetailDTO.class);
        } catch (Exception ex) {
            log.info("{}", ex.getMessage());
            throw new DataOperationException(ex.getMessage());
        }
    }
}
