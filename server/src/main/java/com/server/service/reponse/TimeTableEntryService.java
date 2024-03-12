package com.server.service.reponse;

import com.server.dto.timetable.TimeTableEntryDTO;

import java.util.List;

public interface TimeTableEntryService {

    TimeTableEntryDTO getTimeTableEntryById(Long id);

    List<TimeTableEntryDTO> getAllTimeTableEntries();

    List<TimeTableEntryDTO> getTimeTableEntriesByCourseId(Long courseId);

    List<TimeTableEntryDTO> getTimeTableEntriesByDay(String day);

    List<TimeTableEntryDTO> getTimeTableEntriesByFacultyName(String facultyName);

    TimeTableEntryDTO saveTimeTableEntry(TimeTableEntryDTO timeTableEntryDTO);

    void deleteTimeTableEntry(Long id);

    List<TimeTableEntryDTO> getTimeTableEntriesByCourseIdAndSection(Long courseId, String section);


    List<TimeTableEntryDTO> saveAllTimeTableEntries(List<TimeTableEntryDTO> timeTableEntryDTOList);


    // Add other methods as needed
}