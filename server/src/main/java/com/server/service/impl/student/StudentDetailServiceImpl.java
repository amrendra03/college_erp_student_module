package com.server.service.impl.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.server.dto.student.StudentRegisterDTO;
import com.server.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.student.StudentDetailDTO;
import com.server.entities.student.StudentDetail;
import com.server.repository.student.StudentDetailRepo;
import com.server.service.student.StudentService;

@Service
public class StudentDetailServiceImpl implements StudentService {

   @Autowired
   private StudentDetailRepo studentRepo;

   @Autowired
   private ModelMapper modelMapper;

   private static  final Logger log = LoggerFactory.getLogger(StudentDetailServiceImpl.class);

   // create
   @Override
   public StudentDetailDTO creat(StudentRegisterDTO req) {

      log.info("Received Student registration request: {}", req);
      StudentDetail st = this.modelMapper.map(req, StudentDetail.class);

      log.info("Save Student registration response: {}", st);

      StudentDetail res = this.studentRepo.save(st);

      return this.modelMapper.map(res, StudentDetailDTO.class);
   }

   // update
   @Override
   public StudentDetailDTO update(StudentDetailDTO dto, Long id) {
      Optional<StudentDetail> x = this.studentRepo.findById(id);

      Optional<StudentDetail> optionalStudent = this.studentRepo.findById(id);

      if (optionalStudent.isPresent()) {
         StudentDetail existingStudent = optionalStudent.get();
         if (!Objects.equals(existingStudent.getName(), dto.getName())) {
            existingStudent.setName(dto.getName());
            this.studentRepo.save(existingStudent);
         }
         return this.modelMapper.map(existingStudent, StudentDetailDTO.class);
      } else {
         log.warn("Student with ID {} not found for update.", id);
         throw new ResourceNotFoundException("Student not found for update.", "Student ID", id);
      }
   }

   // get
   @Override
   public StudentDetailDTO get(Long id) {
      Optional<StudentDetail> optionalStudent = this.studentRepo.findById(id);

      if (optionalStudent.isPresent()) {
         return this.modelMapper.map(optionalStudent.get(), StudentDetailDTO.class);
      } else {
         log.warn("Student with ID {} not found.", id);
         throw new ResourceNotFoundException("Student not found.", "Student ID", id);
      }
   }

   // get all
   @Override
   public List<StudentDetailDTO> getAll() {
      List<StudentDetail> x = this.studentRepo.findAll();

      List<StudentDetailDTO> res = x.stream().map((x2) -> this.modelMapper.map(x2, StudentDetailDTO.class))
            .collect(Collectors.toList());

      return res;
   }

   // delete
   @Override
   public void delete(Long id) {
      Optional<StudentDetail> optionalStudent = this.studentRepo.findById(id);

      if (optionalStudent.isPresent()) {
         this.studentRepo.delete(optionalStudent.get());
      } else {
         log.warn("Student with ID {} not found for deletion.", id);
         throw new ResourceNotFoundException("Student not found for deletion.", "Student ID", id);
      }
   }

}
