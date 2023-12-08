package com.server.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.CourseDto;
import com.server.entities.Course;
import com.server.repository.CourseRepo;
import com.server.service.CourseService;

@Service
public class CourseServiceImpl implements CourseService {

   @Autowired
   private CourseRepo courseRepo;

   @Autowired
   private ModelMapper modelMapper;

   @Override
   public CourseDto create(CourseDto courseDto) {

      Course x = this.modelMapper.map(courseDto, Course.class);
      Course y = this.courseRepo.save(x);

      return this.modelMapper.map(y, CourseDto.class);
   }

   @Override
   public CourseDto update(CourseDto courseDto, Long id) {

      Optional<Course> x = this.courseRepo.findById(id);
      Course y = x.get();

      if (courseDto.getBatch() != y.getBatch())
         y.setBatch(courseDto.getBatch());
      if (courseDto.getCourseName() != y.getBatch())
         y.setCourseName(courseDto.getCourseName());
      if (courseDto.getDuration() != y.getDuration())
         y.setDuration(courseDto.getDuration());

      Course z = this.courseRepo.save(y);

      return this.modelMapper.map(z, CourseDto.class);
   }

   @Override
   public CourseDto get(Long id) {
      Optional<Course> x = this.courseRepo.findById(id);

      return this.modelMapper.map(x.get(), CourseDto.class);
   }

   @Override
   public List<CourseDto> getAll() {
      List<Course> x = this.courseRepo.findAll();

      List<CourseDto> y = x.stream().map((x2) -> this.modelMapper.map(x2, CourseDto.class))
            .collect(Collectors.toList());
      return y;
   }

   @Override
   public void delete(Long id) {

      Optional<Course> x = this.courseRepo.findById(id);

      Course y = x.get();
      this.courseRepo.delete(y);
   }

}
