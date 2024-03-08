package com.server.dto.student;

import java.util.List;

import com.server.entities.enums.SemesterStatus;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;


@Data
public class StudentSemesterDTO {
   private Long semesterId;
   private int semesterNumber;
   private Long studentCourseDetailId;
   @Enumerated(EnumType.STRING)
   private SemesterStatus status;// Reference to the associated StudentCourseDetail
   private List<StudentSubjectDto> studentSubjects;
}
