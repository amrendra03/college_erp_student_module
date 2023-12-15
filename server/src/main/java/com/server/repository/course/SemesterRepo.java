package com.server.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.course.Semester;

public interface SemesterRepo extends JpaRepository<Semester, Long> {

}