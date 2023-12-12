package com.server.service.impl.student;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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

   // create
   @Override
   public StudentDetailDTO creat(StudentDetailDTO x) {

      StudentDetail st = this.modelMapper.map(x, StudentDetail.class);

      StudentDetail y = this.studentRepo.save(st);

      return this.modelMapper.map(y, StudentDetailDTO.class);
   }

   // update
   @Override
   public StudentDetailDTO update(StudentDetailDTO dto, Long id) {
      Optional<StudentDetail> x = this.studentRepo.findById(id);

      StudentDetail y = x.get();

      if (y.getName() != dto.getName())
         y.setName(dto.getName());

      return this.modelMapper.map(x, StudentDetailDTO.class);
   }

   // get
   @Override
   public StudentDetailDTO get(Long id) {
      Optional<StudentDetail> x = this.studentRepo.findById(id);

      return this.modelMapper.map(x.get(), StudentDetailDTO.class);
   }

   // get all
   @Override
   public List<StudentDetailDTO> getAll() {
      List<StudentDetail> x = this.studentRepo.findAll();

      List<StudentDetailDTO> y = x.stream().map((x2) -> this.modelMapper.map(x2, StudentDetailDTO.class))
            .collect(Collectors.toList());

      return y;
   }

   // delete
   @Override
   public void delete(Long id) {
      Optional<StudentDetail> x = this.studentRepo.findById(id);
      this.studentRepo.delete(x.get());
   }

}
