package com.server.entities.student;

import java.util.List;

import com.server.entities.enums.SemesterStatus;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class StudentSemester {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long semesterId;

   private int semesterNumber;

   @Enumerated(EnumType.STRING)
   private SemesterStatus status;

   @ManyToOne
   @JoinColumn(name = "student_course_id")
   private StudentCourseDetail studentCourseDetail;

   @OneToMany(mappedBy = "studentSemester", cascade = CascadeType.ALL, orphanRemoval = true)
   private List<StudentSubject> studentSubjects;

}
