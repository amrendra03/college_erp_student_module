package com.server.repository.exam;

import com.server.entities.exam.ExamSubject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamSubjectRepo extends JpaRepository<ExamSubject,Long> {
    ExamSubject findBySubjectCode(String code);
    boolean existsBySubjectCode(String subjectCode);
}
