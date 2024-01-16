package com.server.repository.course;

import com.server.entities.student.StudentCourseDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentCourseDetailRepo extends JpaRepository<StudentCourseDetail,Long> {
}
