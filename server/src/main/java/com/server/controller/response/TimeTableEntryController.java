package com.server.controller.response;

import com.server.constant.API;
import com.server.dto.timetable.TimeTableEntryDTO;
import com.server.service.reponse.TimeTableEntryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(API.timeTable+"_a")
public class TimeTableEntryController {


    Logger log = LoggerFactory.getLogger(TimeTableEntryController.class);

    @Autowired
    private  TimeTableEntryService timeTableEntryService;
    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<TimeTableEntryDTO>> getTimeTableEntriesByCourseId(@PathVariable Long courseId) {
        log.info("Request received to get time table entries by course ID: {}", courseId);
        List<TimeTableEntryDTO> timeTableEntries = timeTableEntryService.getTimeTableEntriesByCourseId(courseId);
        log.info("Returned {} time table entries for course ID: {}", timeTableEntries.size(), courseId);
        return ResponseEntity.ok(timeTableEntries);
    }

    @GetMapping("/day/{day}")
    public ResponseEntity<List<TimeTableEntryDTO>> getTimeTableEntriesByDay(@PathVariable String day) {
        log.info("Request received to get time table entries by day: {}", day);
        List<TimeTableEntryDTO> timeTableEntries = timeTableEntryService.getTimeTableEntriesByDay(day);
        log.info("Returned {} time table entries for day: {}", timeTableEntries.size(), day);
        return ResponseEntity.ok(timeTableEntries);
    }

    @PostMapping("/add")
    public ResponseEntity<TimeTableEntryDTO> addTimeTableEntry(@RequestBody TimeTableEntryDTO timeTableEntryDTO) {
        log.info("Request received to add a new time table entry: {}", timeTableEntryDTO);
        TimeTableEntryDTO savedTimeTableEntry = timeTableEntryService.saveTimeTableEntry(timeTableEntryDTO);
        log.info("New time table entry added successfully: {}", savedTimeTableEntry);
        return new ResponseEntity<>(savedTimeTableEntry, HttpStatus.CREATED);
    }

    @GetMapping("/course/{courseId}/section/{section}")
    public ResponseEntity<List<TimeTableEntryDTO>> getTimeTableEntriesByCourseIdAndSection(
            @PathVariable Long courseId,
            @PathVariable String section
    ) {
        log.info("Request received to get time table entries by course ID {} and section: {}", courseId, section);
        List<TimeTableEntryDTO> timeTableEntries = timeTableEntryService.getTimeTableEntriesByCourseIdAndSection(courseId, section);
        log.info("Returned {} time table entries for course ID {} and section: {}", timeTableEntries.size(), courseId, section);
        return ResponseEntity.ok(timeTableEntries);
    }

    @PostMapping("/add-all")
    public ResponseEntity<List<TimeTableEntryDTO>> addAllTimeTableEntries(@RequestBody List<TimeTableEntryDTO> timeTableEntryDTOList) {
        List<TimeTableEntryDTO> savedEntries = timeTableEntryService.saveAllTimeTableEntries(timeTableEntryDTOList);
        return new ResponseEntity<>(savedEntries, HttpStatus.CREATED);
    }


}