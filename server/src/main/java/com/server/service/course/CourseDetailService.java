package com.server.service.course;

import java.util.List;

import com.server.dto.course.CourseDetailDTO;

public interface CourseDetailService {

   CourseDetailDTO create(CourseDetailDTO courseDetailDTO);

   CourseDetailDTO update(CourseDetailDTO courseDetailDTO, Long id);

   CourseDetailDTO get(Long id);

   List<CourseDetailDTO> getAll();

   void delete(Long id);

}