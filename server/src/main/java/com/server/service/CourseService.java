package com.server.service;

import java.util.List;

import com.server.dto.CourseDto;

public interface CourseService {

   CourseDto create(CourseDto courseDto);

   CourseDto update(CourseDto CourseDto, Long id);

   CourseDto get(Long id);

   List<CourseDto> getAll();

   void delete(Long id);

}
