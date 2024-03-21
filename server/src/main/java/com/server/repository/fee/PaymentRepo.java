package com.server.repository.fee;

import com.server.entities.fee.FeeTransaction;
import com.server.entities.student.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepo extends JpaRepository<FeeTransaction ,Long> {

    List<FeeTransaction> findAllByStudentDetail(StudentDetail studentDetail);
}
