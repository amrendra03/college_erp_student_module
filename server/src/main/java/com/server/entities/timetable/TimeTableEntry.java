package com.server.entities.timetable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class TimeTableEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long courseId;
    private String subjectCode;
    private String subjectName;
    private String section;
    private String day;
    private String time;
    private String classroom;
    private int startTime;
    private int endTime;
    private String facultyName;  // New field for faculty name
}