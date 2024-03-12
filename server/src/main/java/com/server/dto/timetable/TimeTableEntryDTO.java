package com.server.dto.timetable;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class TimeTableEntryDTO {

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
    private String facultyName;
}