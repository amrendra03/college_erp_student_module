package com.server.repository.fee;

import com.server.entities.fee.FeeRecord;
import com.server.entities.student.StudentDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeRecordRepo extends JpaRepository<FeeRecord ,Long> {
    List<FeeRecord> findAllByStudentDetail(StudentDetail studentDetail);

}
