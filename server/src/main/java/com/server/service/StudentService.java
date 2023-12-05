package com.server.service;

import java.util.List;

import com.server.dto.StudentDto;

public interface StudentService {

   StudentDto create(StudentDto studentDto);

   StudentDto update(StudentDto studentDto);

   StudentDto get(Long rollNo);

   List<StudentDto> getAll();

   Void delete(StudentDto studentDto);

}
