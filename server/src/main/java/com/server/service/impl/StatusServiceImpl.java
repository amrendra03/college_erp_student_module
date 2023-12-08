package com.server.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.StatusDto;
import com.server.entities.Status;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.StatusRepo;
import com.server.repository.StudentRepo;
import com.server.service.StatusService;

@Service
public class StatusServiceImpl implements StatusService {

   @Autowired
   private StatusRepo statusRepo;

   @Autowired
   private StudentRepo studentRepo;

   @Autowired
   public ModelMapper modelMapper;

   @Override
   public StatusDto create(StatusDto statusDto) {

      Status x = this.modelMapper.map(statusDto, Status.class);
      Status st = this.statusRepo.save(x);

      return this.modelMapper.map(st, StatusDto.class);
   }

   @Override
   public StatusDto update(StatusDto statusDto, Long id) {
      Optional<Status> x = this.statusRepo.findById(id);

      Status y = x.get();

      if (statusDto.getSubject() != y.getSubject())
         y.setSubject(statusDto.getSubject());
      if (statusDto.getSubjectCode() != y.getSubjectCode())
         y.setSubjectCode(statusDto.getSubjectCode());
      if (statusDto.getFacultyName() != y.getFacultyName())
         y.setFacultyName(statusDto.getFacultyName());
      if (statusDto.getPresent() != y.getPresent())
         y.setPresent(statusDto.getPresent());
      if (statusDto.getAbsent() != y.getAbsent())
         y.setAbsent(statusDto.getAbsent());
      if (statusDto.getSubmitted() != y.getSubmitted())
         y.setSubmitted(statusDto.getSubmitted());
      if (statusDto.getNotSubmitted() != y.getNotSubmited())
         y.setNotSubmited(statusDto.getNotSubmitted());

      if (statusDto.getStudentRollNo() != 0 && statusDto.getStudentRollNo() != null)
         y.setStudent(this.studentRepo.findByRollNo(statusDto.getStudentRollNo()));
      StatusDto z = this.modelMapper.map(y, StatusDto.class);

      System.out.println(z);

      return z;
   }

   @Override
   public List<StatusDto> getAll(Long rollNo) {
      List<Status> x = this.statusRepo.findByStudentRollNo(rollNo);

      List<StatusDto> y = x.stream().map((x2) -> this.modelMapper.map(x2, StatusDto.class))
            .collect(Collectors.toList());

      return y;
   }

   @Override
   public void delete(Long id, Long rollNo) {
      List<Status> x = this.statusRepo.findByStudentRollNo(rollNo);

      for (Status i : x) {
         if (i.getId() == id) {
            this.statusRepo.delete(i);
            return;
         }
      }

      throw new ResourceNotFoundException("Status not found", "Roll no ,status id:" + rollNo, id);
   }

}
