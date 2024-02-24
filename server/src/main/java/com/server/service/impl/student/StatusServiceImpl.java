package com.server.service.impl.student;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentStatusDTO;
import com.server.entities.student.StudentStatus;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.student.StatusRepo;
import com.server.service.student.StatusService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatusServiceImpl implements StatusService {


    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StatusRepo statusRepo;

    public static final Logger log= LoggerFactory.getLogger(StatusServiceImpl.class);

    @Override
    public StudentStatusDTO create(StudentStatusDTO req) {

        log.info("Request Processing...");
        StudentStatus status = this.modelMapper.map(req,StudentStatus.class);
        StudentStatus res = this.statusRepo.save(status);
        log.info("Request Saved in DataBase: {}",res);

        return this.modelMapper.map(res,StudentStatusDTO.class);
    }

    @Override
    public StudentStatusDTO update(StudentStatusDTO req) {
        log.info("Request processing...");
        Long id = req.getId();
        try{
            Optional<StudentStatus> status = this.statusRepo.findById(req.getId());
            StudentStatus res = status.get();
            StudentStatus res2 = this.modelMapper.map(req,StudentStatus.class);
            res=res2;
            res = this.statusRepo.save(res);
            log.info("Successfully update the Status: {}",res);
            return this.modelMapper.map(res,StudentStatusDTO.class);

        }catch (Exception ex){
                throw new ResourceNotFoundException("Resource not found","Status ID",id);
        }
    }

    @Override
    public ApiResponse delete(StudentStatusDTO req) {
        return null;
    }

    @Override
    public List<StudentStatusDTO> getAllStatus() {
        return null;
    }
}
