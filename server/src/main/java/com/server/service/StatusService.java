package com.server.service;

import java.util.List;

import com.server.dto.StatusDto;

public interface StatusService {

   StatusDto create(StatusDto studentDto);

   StatusDto update(StatusDto studentDto, Long rollNo);

   List<StatusDto> getAll(Long rollNo);

   void delete(Long id, Long rollNo);
}
