package com.server.repository.course;

import com.server.entities.student.StudentSubject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterSubjectRepo extends JpaRepository<StudentSubject,Long> {

    StudentSubject findByCourseIdAndSemesterNumberAndSubjectId(Long courseId,int semesterNumber,Long subjectId);
    List<StudentSubject> findAllByCourseIdAndSemesterNumber(Long courseId, int semesterNumber);


}
