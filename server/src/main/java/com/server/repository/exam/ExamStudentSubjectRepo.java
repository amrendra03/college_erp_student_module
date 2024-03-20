package com.server.repository.exam;

import com.server.entities.exam.ExamStudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamStudentSubjectRepo extends JpaRepository<ExamStudentSubject, Long> {
    // You can add custom query methods here if needed

    // find all because if student have back lock or passed same subject and its recode marks
    List<ExamStudentSubject> findAllBySubjectCodeAndRollNo(String code,String rollNo);
    ExamStudentSubject findByRollNoAndSubjectCodeAndStatus(String rollNo, String code, String status);
    List<ExamStudentSubject> findAllByRollNo(String rollNo);
    List<ExamStudentSubject> findAllByStatusAndRollNo(String status,String rollNo);


}