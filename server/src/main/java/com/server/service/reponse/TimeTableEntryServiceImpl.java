package com.server.service.reponse;

import com.server.dto.timetable.TimeTableEntryDTO;
import com.server.entities.timetable.TimeTableEntry;
import com.server.repository.timetable.TimeTableEntryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TimeTableEntryServiceImpl implements TimeTableEntryService {

    private final TimeTableEntryRepository timeTableEntryRepository;

    private Logger log = LoggerFactory.getLogger(TimeTableEntry.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public TimeTableEntryServiceImpl(TimeTableEntryRepository timeTableEntryRepository) {
        this.timeTableEntryRepository = timeTableEntryRepository;
    }

    @Override
    public TimeTableEntryDTO getTimeTableEntryById(Long id) {
        log.info("Fetching time table entry with ID: {}", id);

        Optional<TimeTableEntry> optionalTimeTableEntry = timeTableEntryRepository.findById(id);

        if (optionalTimeTableEntry.isPresent()) {
            TimeTableEntry timeTableEntry = optionalTimeTableEntry.get();
            return modelMapper.map(timeTableEntry,TimeTableEntryDTO.class);
        } else {
            String errorMessage = "TimeTableEntry not found with id: " + id;
            log.error(errorMessage);
            throw new EntityNotFoundException(errorMessage);
        }
    }

    @Override
    public List<TimeTableEntryDTO> getAllTimeTableEntries() {
        List<TimeTableEntry> timeTableEntries = timeTableEntryRepository.findAll();
        return timeTableEntries.stream()
                .map(x->modelMapper.map(x, TimeTableEntryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeTableEntryDTO> getTimeTableEntriesByCourseId(Long courseId) {
        List<TimeTableEntry> timeTableEntries = timeTableEntryRepository.findByCourseId(courseId);
        return timeTableEntries.stream()
                .map(x->modelMapper.map(x, TimeTableEntryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeTableEntryDTO> getTimeTableEntriesByDay(String day) {
        List<TimeTableEntry> timeTableEntries = timeTableEntryRepository.findByDay(day);
        return timeTableEntries.stream()
                .map(x->modelMapper.map(x,TimeTableEntryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TimeTableEntryDTO> getTimeTableEntriesByFacultyName(String facultyName) {
        List<TimeTableEntry> timeTableEntries = timeTableEntryRepository.findByFacultyName(facultyName);
        return timeTableEntries.stream()
                .map(x->modelMapper.map(x,TimeTableEntryDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TimeTableEntryDTO saveTimeTableEntry(TimeTableEntryDTO timeTableEntryDTO) {
        log.info("Saving time table entry: {}", timeTableEntryDTO);

        try {
            TimeTableEntry timeTableEntry = modelMapper.map(timeTableEntryDTO, TimeTableEntry.class);
            log.info("{}", timeTableEntry);
            TimeTableEntry savedTimeTableEntry = timeTableEntryRepository.save(timeTableEntry);
            TimeTableEntryDTO result = modelMapper.map(savedTimeTableEntry,TimeTableEntryDTO.class);
            log.info("Saved time table entry: {}", result);
            return result;
        } catch (Exception e) {
            String errorMessage = "Error saving time table entry: " + timeTableEntryDTO;
            log.error(errorMessage);
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void deleteTimeTableEntry(Long id) {
        timeTableEntryRepository.deleteById(id);
    }

    // Add other methods and helper methods as needed

//    private TimeTableEntryDTO convertToDTO(TimeTableEntry timeTableEntry) {
//        // Convert TimeTableEntry entity to TimeTableEntryDTO
//        // Implement the conversion logic based on your requirements
//        return new TimeTableEntryDTO(
//                timeTableEntry.getId(),
//                timeTableEntry.getCourseId(),
//                timeTableEntry.getSubjectCode(),
//                timeTableEntry.getSubjectName(),
//                timeTableEntry.getSection(),
//                timeTableEntry.getDay(),
//                timeTableEntry.getTime(),
//                timeTableEntry.getClassroom(),
//                timeTableEntry.getStartTime(),
//                timeTableEntry.getEndTime(),
//                timeTableEntry.getFacultyName()
//        );
//    }

    @Override
    public List<TimeTableEntryDTO> getTimeTableEntriesByCourseIdAndSection(Long courseId, String section) {
        log.info("Fetching time table entries for course with ID {} and section: {}", courseId, section);

        List<TimeTableEntry> timeTableEntries = timeTableEntryRepository.findByCourseIdAndSection(courseId, section);

        if (!timeTableEntries.isEmpty()) {
            List<TimeTableEntryDTO> result = timeTableEntries.stream()
                    .map(x->modelMapper.map(x,TimeTableEntryDTO.class))
                    .collect(Collectors.toList());
            log.info("Fetched {} time table entries for course with ID {} and section: {}", result.size(), courseId, section);
            return result;
        } else {
            String errorMessage = "No time table entries found for course with ID " + courseId + " and section " + section;
            log.error(errorMessage);
            throw new EntityNotFoundException(errorMessage);
        }
    }

    @Override
    public List<TimeTableEntryDTO> saveAllTimeTableEntries(List<TimeTableEntryDTO> timeTableEntryDTOList) {
        log.info("Saving a list of time table entries");

        try {
            List<TimeTableEntry> savedEntries = timeTableEntryDTOList.stream().map(x -> modelMapper.map(x, TimeTableEntry.class)).collect(Collectors.toList());

            for (TimeTableEntry i : savedEntries) {
                TimeTableEntry res = this.timeTableEntryRepository.save(i);
                if (res != null) {
                    i.setId(res.getId());
                }
            }

            List<TimeTableEntryDTO> savedEntriesDTO = savedEntries.stream()
                    .map(x -> modelMapper.map(x, TimeTableEntryDTO.class))
                    .collect(Collectors.toList());

            log.info("Successfully saved {} time table entries", savedEntriesDTO.size());
            return savedEntriesDTO;
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }


}
