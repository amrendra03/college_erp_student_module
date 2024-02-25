package com.server.repository.student;

import com.server.entities.student.StudentDetail;
import com.server.entities.student.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatusRepo extends JpaRepository<StudentStatus,Long> {
    List<StudentStatus> findByStudentDetail(StudentDetail studentDetail);
}
