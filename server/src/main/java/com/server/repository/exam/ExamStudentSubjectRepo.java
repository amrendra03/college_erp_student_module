package com.server.repository.exam;

import com.server.entities.exam.ExamStudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamStudentSubjectRepo extends JpaRepository<ExamStudentSubject, Long> {
    // You can add custom query methods here if needed
    List<ExamStudentSubject> findAllBySubjectCodeAndRollNo(String code,String rollNo);
    List<ExamStudentSubject> findAllByRollNo(String rollNo);
}