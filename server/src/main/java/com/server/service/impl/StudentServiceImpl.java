package com.server.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.StudentDto;
import com.server.entities.Student;
import com.server.repository.StudentRepo;
import com.server.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

   @Autowired
   private StudentRepo studentRepo;

   @Autowired
   private ModelMapper modelMapper;

   @Override
   public StudentDto create(StudentDto studentDto) {
      Student student = this.modelMapper.map(studentDto, Student.class);
      Student savedStudent = this.studentRepo.save(student);

      return this.modelMapper.map(savedStudent, StudentDto.class);
   }

   @Override
   public StudentDto update(StudentDto studentDto) {

      throw new UnsupportedOperationException("Unimplemented method 'update'");
   }

   @Override
   public StudentDto get(Long rollNo) {

      throw new UnsupportedOperationException("Unimplemented method 'get'");
   }

   @Override
   public List<StudentDto> getAll() {

      throw new UnsupportedOperationException("Unimplemented method 'getAll'");
   }

   @Override
   public Void delete(StudentDto studentDto) {

      throw new UnsupportedOperationException("Unimplemented method 'delete'");
   }

}
