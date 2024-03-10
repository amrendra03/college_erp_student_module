package com.server.service.impl.student;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentStatusDTO;
import com.server.entities.student.StudentDetail;
import com.server.entities.student.StudentStatus;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.student.StatusRepo;
import com.server.repository.student.StudentDetailRepo;
import com.server.service.student.StatusService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StatusServiceImpl implements StatusService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private StatusRepo statusRepo;
    @Autowired
    private StudentDetailRepo studentDetailRepo;

    public static final Logger log= LoggerFactory.getLogger(StatusServiceImpl.class);

    @Override
    public StudentStatusDTO create(StudentStatusDTO req) {

        log.info("Request Processing...");
        StudentDetail st;
        try{
             st= this.studentDetailRepo.findByRollNo(req.getStudentRollNo());
        }catch (Exception ex){
            Long id = Long.parseLong(req.getStudentRollNo());
            throw new ResourceNotFoundException("Student not found","Roll No", id);
        }
        StudentStatus status = this.modelMapper.map(req,StudentStatus.class);
        status.setStudentDetail(st);
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
            StudentDetail st = this.studentDetailRepo.findByRollNo(req.getStudentRollNo());
            if (!status.isPresent()){
                throw  new ResourceNotFoundException("Status not found","ID", req.getId());
            }
            StudentStatus res;
            StudentStatus res2 = this.modelMapper.map(req,StudentStatus.class);
            res=res2;
            res.setStudentDetail(st);
            res = this.statusRepo.save(res);
            log.info("Successfully update the Status: {}",res);
            return this.modelMapper.map(res,StudentStatusDTO.class);

        }catch (Exception ex){
                throw new ResourceNotFoundException("Resource not found","Status ID",id);
        }
    }

    @Override
    public ApiResponse delete(StudentStatusDTO req) {
        log.info("Processing the deletion of status...");
        try{
            Optional<StudentStatus> res = this.statusRepo.findById(req.getId());
            this.statusRepo.delete(res.get());
            log.info("Successfully deleted status.");
        }catch (Exception ex){
            log.info("Not found the status !!");
            return new ApiResponse("Resource not found",false);
        }
        return new ApiResponse("Successfully deleted",true);
    }

    @Override
    public List<StudentStatusDTO> getAllStatus(String rollNo) {
        log.info("Processing get All Status...");
        List<StudentStatusDTO> res;
        try{
            StudentDetail st = this.studentDetailRepo.findByRollNo(rollNo);
            if (st==null){
                throw new ResourceNotFoundException("Student not found","Roll No",Long.parseLong(rollNo));
            }
            List<StudentStatus> allStatus = this.statusRepo.findByStudentDetail(st);
            res = allStatus.stream().map(x->this.modelMapper.map(x,StudentStatusDTO.class)).collect(Collectors.toList());
            log.info("Successfully get all the status ");
//            res.forEach(x-> log.info("status: {}",x));
        }catch (Exception exception){
            throw new ResourceNotFoundException("Student not found","Roll No",Long.parseLong(rollNo));
        }

        return res;
    }
}
