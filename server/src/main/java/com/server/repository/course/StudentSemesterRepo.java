package com.server.repository.course;

import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentSemester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentSemesterRepo extends JpaRepository<StudentSemester,Long> {

    List<StudentSemester> findByStudentCourseDetail(StudentCourseDetail studentCourseDetail);


}
