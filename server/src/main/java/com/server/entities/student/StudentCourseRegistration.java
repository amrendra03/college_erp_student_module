package com.server.entities.student;

import com.server.dto.student.StudentCourseRegistrationDTO;
import jakarta.persistence.*;
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

    @Column(columnDefinition = "VARCHAR(255) CHECK (type IN ('PG', 'UG', 'DP'))")
    private String type;

    private String rollNo;

}
