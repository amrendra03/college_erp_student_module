package com.server.service.impl.course;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.course.CourseDetailDTO;
import com.server.entities.course.CourseDetail;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.course.CourseDetailRepo;
import com.server.service.course.CourseDetailService;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {

   @Autowired
   private CourseDetailRepo courseDetailRepo;

   @Autowired
   private ModelMapper modelMapper;

   @Override
   public CourseDetailDTO create(CourseDetailDTO courseDetailDTO) {
      CourseDetail course = this.modelMapper.map(courseDetailDTO, CourseDetail.class);
      this.courseDetailRepo.save(course);
      return this.modelMapper.map(this.courseDetailRepo.save(course), CourseDetailDTO.class);
   }

   @Override
   public CourseDetailDTO update(CourseDetailDTO x, Long id) {
      CourseDetail y = this.courseDetailRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course id not found !.", "Course id", id));

      if (x.getCourseName() != y.getCourseName())
         y.setCourseName(x.getCourseName());
      if (x.getDuration() != y.getDuration())
         y.setDuration(x.getDuration());

      return this.modelMapper.map(this.courseDetailRepo.save(y), CourseDetailDTO.class);
   }

   @Override
   public CourseDetailDTO get(Long id) {
      CourseDetail course = this.courseDetailRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course id not found !.", "Course id", id));

      return this.modelMapper.map(course, CourseDetailDTO.class);

   }

   @Override
   public List<CourseDetailDTO> getAll() {
      List<CourseDetail> list = this.courseDetailRepo.findAll();

      List<CourseDetailDTO> dtoList = list.stream().map((x) -> this.modelMapper.map(x, CourseDetailDTO.class))
            .collect(Collectors.toList());

      return dtoList;
   }

}
