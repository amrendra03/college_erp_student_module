package com.server.repository.student;

import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentDetail;
import com.server.entities.student.StudentSemesterProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentSemesterProgressRepo extends JpaRepository<StudentSemesterProgress,Long> {

    Optional<StudentSemesterProgress> findByStudentDetail_StudentIdAndStudentSemester_SemesterId(Long studentId, Long semesterId);

    List<StudentSemesterProgress> findByStudentDetail(StudentDetail studentDetail);



}
