package com.server.repository.student;

import org.springframework.data.jpa.repository.JpaRepository;

import com.server.entities.student.StudentDetail;
import org.springframework.data.jpa.repository.Query;

public interface StudentDetailRepo extends JpaRepository<StudentDetail, Long> {

    @Query("SELECT MAX(s.rollNo) FROM StudentDetail s")
    String findLatestRollNo();

//    @Query("SELECT MAX(CAST(s.enrollmentNo AS UNSIGNED)) FROM StudentDetail s")
    @Query("SELECT MAX(s.enrollmentNo) FROM StudentDetail s")
    String findLatestEnrollmentNo();

    StudentDetail findByEmail(String email);

    StudentDetail findByRollNo(String rollNo);

}