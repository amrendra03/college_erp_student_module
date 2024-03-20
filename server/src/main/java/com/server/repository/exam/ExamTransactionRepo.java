package com.server.repository.exam;

import com.server.entities.exam.ExamTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamTransactionRepo extends JpaRepository<ExamTransaction,Long> {
}
