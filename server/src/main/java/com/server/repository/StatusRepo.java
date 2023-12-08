package com.server.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.Status;

public interface StatusRepo extends JpaRepository<Status, Long> {

   List<Status> findByStudentRollNo(Long rollNo);

}
