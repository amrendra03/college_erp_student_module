package com.server.repository.timetable;

import com.server.entities.timetable.TimeTableEntry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimeTableEntryRepository extends JpaRepository<TimeTableEntry, Long> {

    List<TimeTableEntry> findByCourseIdAndDay(Long courseId, String day);

    List<TimeTableEntry> findByFacultyName(String facultyName);
    List<TimeTableEntry> findByCourseId(Long courseId);

    List<TimeTableEntry> findByDay(String day);
    List<TimeTableEntry> findByCourseIdAndSection(Long courseId, String section);


}