package com.server.repository.student;

import com.server.entities.exam.ExamRecord;
import com.server.entities.student.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRecordRepository extends JpaRepository<ExamRecord, Long> {
    // You can add custom query methods here if needed
    List<ExamRecord> findAllByStudentDetail(StudentDetail student);
}
