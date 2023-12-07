package com.server.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.server.dto.ResponseStudent;
import com.server.dto.StudentDto;
import com.server.entities.Student;
import com.server.exception.ResourceNotFoundException;
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
   public StudentDto update(StudentDto studentDto, Long rollNo) {

      Student st = this.studentRepo.findById(rollNo)
            .orElseThrow(() -> new ResourceNotFoundException("Student Not found", "Roll No", rollNo));

      if (studentDto.getName() != st.getName())
         st.setName(studentDto.getName());
      if (studentDto.getCourse() != st.getCourse())
         st.setCourse(studentDto.getCourse());
      if (studentDto.getFee() != st.getFee())
         st.setFee(studentDto.getFee());
      if (studentDto.getFine() != st.getFee())
         st.setFine(studentDto.getFine());
      if (studentDto.getScore() != st.getScore())
         st.setScore(studentDto.getScore());
      if (studentDto.getSem() != st.getScore())
         st.setSem(studentDto.getSem());

      this.studentRepo.save(st);
      return this.modelMapper.map(st, StudentDto.class);
   }

   @Override
   public StudentDto get(Long rollNo) {

      Student st = this.studentRepo.findById(rollNo)
            .orElseThrow(() -> new ResourceNotFoundException("Student Not found", "Roll No", rollNo));

      return this.modelMapper.map(st, StudentDto.class);
   }

   @Override
   public ResponseStudent getAll(int pageNumber, int pageSize, String sortBy) {

      Pageable page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy).descending());

      Page<Student> stPage = this.studentRepo.findAll(page);

      List<Student> allStudent = stPage.getContent();

      List<StudentDto> stDto = allStudent.stream().map((st) -> this.modelMapper.map(st, StudentDto.class))
            .collect(Collectors.toList());

      ResponseStudent st = new ResponseStudent();

      st.setContent(stDto);
      st.setPageNumber(stPage.getNumber());
      st.setPageSize(stPage.getSize());
      st.setTotalPage(stPage.getTotalPages());
      st.setTotalElement(stPage.getTotalElements());
      st.setLastPage(stPage.isLast());

      return st;

   }

   @Override
   public void delete(Long rollNo) {
      Student st = studentRepo.findById(rollNo)
            .orElseThrow(() -> new ResourceNotFoundException("Studetn not Found", "RollNumber", rollNo));
      System.out.println(st);
      this.studentRepo.delete(st);
   }

}
