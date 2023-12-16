package com.server.service.impl.course;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.server.dto.course.CourseDetailDTO;
import com.server.dto.course.SemesterDTO;
import com.server.entities.course.CourseDetail;
import com.server.entities.course.Semester;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.course.CourseDetailRepo;
import com.server.repository.course.SemesterRepo;
import com.server.service.course.CourseDetailService;

@Service
public class CourseDetailServiceImpl implements CourseDetailService {

   @Autowired
   private CourseDetailRepo courseDetailRepo;

   @Autowired
   private SemesterRepo semesterRepo;

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

   @Override
   public void delete(Long id) {
      CourseDetail course = this.courseDetailRepo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Course not found !.", "Course Id", id));
      this.courseDetailRepo.delete(course);
   }

   @Override
   public SemesterDTO addSem(Long id, SemesterDTO sem) {

      Semester x = this.semesterRepo.save(this.modelMapper.map(sem, Semester.class));

      return this.modelMapper.map(x, SemesterDTO.class);

   }

   @Override
   public void deleteSem(Long id, Long semId) {
      Semester course = this.semesterRepo.findById(semId)
            .orElseThrow(() -> new ResourceNotFoundException("Semester not found", "Course id ", id));

      this.semesterRepo.delete(course);

   }

}
