package com.server.repository.course;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.course.CourseDetail;

public interface CourseDetailRepo extends JpaRepository<CourseDetail, Long> {

}
