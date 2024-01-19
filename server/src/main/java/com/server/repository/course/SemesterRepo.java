package com.server.repository.course;

import com.server.entities.student.StudentSemester;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SemesterRepo extends JpaRepository<StudentSemester,Long> {

}