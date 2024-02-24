package com.server.repository.student;

import com.server.entities.student.StudentCourseRegistration;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentCourseRegistrationRepo extends JpaRepository<StudentCourseRegistration,Long> {

    Optional<StudentCourseRegistration> findByStudentDetailRollNoAndStudentCourseDetailCourseId(String rollNo, Long courseId);

}
