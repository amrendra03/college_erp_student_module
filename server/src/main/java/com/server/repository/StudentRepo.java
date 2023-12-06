package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.Student;

public interface StudentRepo extends JpaRepository<Student, Long> {

   Student findByRollNo(Long rollNo);
}
