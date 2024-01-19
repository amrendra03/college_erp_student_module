package com.server.repository.course;

import com.server.entities.student.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SemesterSubject extends JpaRepository<StudentSubject,Long> {


}
