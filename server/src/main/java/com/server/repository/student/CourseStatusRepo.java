package com.server.repository.student;

import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseStatusRepo extends JpaRepository<StudentSemester,Long> {
    Optional<StudentSemester> findByStudentCourseDetail_CourseIdAndSemesterId(Long studentCourseDetailId, Long semesterId);

    List<StudentSemester> findAllByStudentCourseDetail(StudentCourseDetail studentCourseDetail);


}
