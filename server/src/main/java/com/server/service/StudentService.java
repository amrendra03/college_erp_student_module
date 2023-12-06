package com.server.service;

import com.server.dto.ResponseStudent;
import com.server.dto.StudentDto;

public interface StudentService {

   StudentDto create(StudentDto studentDto);

   StudentDto update(StudentDto studentDto, Long rollNo);

   StudentDto get(Long rollNo);

   ResponseStudent getAll(int pageNUmber, int pageSize, String sortBy);

   void delete(Long rollNo);

}
