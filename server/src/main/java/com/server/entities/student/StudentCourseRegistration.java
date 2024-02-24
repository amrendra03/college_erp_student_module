package com.server.entities.student;

import com.server.dto.student.StudentCourseRegistrationDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class StudentCourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registrationId;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private StudentDetail studentDetail;

    @ManyToOne
    @JoinColumn(name = "student_course_id")
    private StudentCourseDetail studentCourseDetail;

    private String RollNo;


}
