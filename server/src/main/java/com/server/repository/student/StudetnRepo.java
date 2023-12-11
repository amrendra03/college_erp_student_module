package com.server.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.student.StudentDetail;

public interface StudetnRepo extends JpaRepository<StudentDetail, Long> {

}