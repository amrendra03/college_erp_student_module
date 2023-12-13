package com.server.service.impl.course;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.course.CourseDetailDTO;
import com.server.entities.course.CourseDetail;
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
   public CourseDetailDTO update(CourseDetailDTO courseDetailDTO, Long id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'update'");
   }

   @Override
   public CourseDetailDTO get(Long id) {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'get'");
   }

   @Override
   public List<CourseDetailDTO> getAll() {
      // TODO Auto-generated method stub
      throw new UnsupportedOperationException("Unimplemented method 'getAll'");
   }

}
