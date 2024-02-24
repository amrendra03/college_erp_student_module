package com.server.repository.student;

import com.server.entities.student.StudentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepo extends JpaRepository<StudentStatus,Long> {
}
