package com.server.entities.student;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class StudentDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private String name;

    private String email;

    private String phone;

    private Date DOB;

    private String rollNo;
    private String enrollmentNo;
    private Date registrationDate;

    @OneToMany(mappedBy = "studentDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentCourseRegistration> studentCourseRegistrations;

    @OneToMany(mappedBy = "studentDetail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentStatus> statuses;

}
