package com.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.Course;

public interface CourseRepo extends JpaRepository<Course, Long> {

}
