package com.server.service.impl.course;


import com.server.dto.student.StudentCourseDetailDTO;
import com.server.dto.student.StudentSemesterDTO;

import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentSemester;
import com.server.exception.ResourceNotFoundException;

import com.server.repository.course.StudentCourseDetailRepo;
import com.server.repository.course.StudentSemesterRepo;
import com.server.service.course.StudentCourseService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseDetailRepo courseDetailRepo;

    @Autowired
    private StudentSemesterRepo semesterRepo;

    @Autowired
    private ModelMapper modelMapper;


    private static  final Logger log = LoggerFactory.getLogger(StudentCourseServiceImpl.class);

    @Override
    public StudentCourseDetailDTO create(StudentCourseDetailDTO courseDetailDTO) {

        log.info("Creating a new course detail: {}", courseDetailDTO);

        try {
            StudentCourseDetail course = modelMapper.map(courseDetailDTO, StudentCourseDetail.class);
            StudentCourseDetail savedCourse = courseDetailRepo.save(course);

            log.info("Created course detail: {}", savedCourse);
            return modelMapper.map(savedCourse, StudentCourseDetailDTO.class);
        } catch (Exception e) {
            log.error("Error creating course", e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    @Override
    public StudentCourseDetailDTO update(StudentCourseDetailDTO updatedCourseDetail, Long id) {

        log.info("Updating course detail with ID {}: {}", id, updatedCourseDetail);

        try {
            StudentCourseDetail existingCourseDetail = courseDetailRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Course id not found !.", "Course id", id));

            updateCourseDetails(existingCourseDetail, updatedCourseDetail);

            StudentCourseDetailDTO updatedDTO = modelMapper.map(courseDetailRepo.save(existingCourseDetail), StudentCourseDetailDTO.class);
            log.info("Updated course detail: {}", updatedDTO);
            return updatedDTO;
        } catch (Exception e) {
            log.error("Error updating course detail with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }


    @Override
    public StudentCourseDetailDTO get(Long id) {
        log.info("Fetching course detail with ID: {}", id);

        try {
            StudentCourseDetail course = courseDetailRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Course id not found !.", "Course id", id));

            log.info("Fetched course detail: {}", course);
            return modelMapper.map(course, StudentCourseDetailDTO.class);
        } catch (Exception e) {
            log.error("Error fetching course detail with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    @Override
    public List<StudentCourseDetailDTO> getAll() {
        log.debug("Fetching all course details");

        try {
            List<StudentCourseDetail> courseList = courseDetailRepo.findAll();
            List<StudentCourseDetailDTO> dtoList = courseList.stream()
                    .map(course -> modelMapper.map(course, StudentCourseDetailDTO.class))
                    .collect(Collectors.toList());

            log.debug("Fetched {} course details", dtoList.size());
            return dtoList;
        } catch (Exception e) {
            log.error("Error fetching all course details", e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting course detail with ID: {}", id);

        try {
            StudentCourseDetail course = courseDetailRepo.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Course not found !.", "Course Id", id));

            courseDetailRepo.delete(course);

            log.info("Deleted course detail with ID: {}", id);
        } catch (Exception e) {
            log.error("Error deleting course detail with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    @Override
    public StudentSemesterDTO addSem(Long id, StudentSemesterDTO sem) {

        log.info("Adding semester to course with ID {}: {}", id, sem);

        try {
            StudentSemester x = semesterRepo.save(modelMapper.map(sem, StudentSemester.class));

            log.info("Added semester: {}", x);
            return modelMapper.map(x, StudentSemesterDTO.class);
        } catch (Exception e) {
            log.error("Error adding semester to course with ID: {}", id, e);
            throw e; // Rethrow the exception for global exception handling
        }
    }

    @Override
    public void deleteSem(Long id, Long semId) {
        log.info("Deleting semester with ID {} from course with ID: {}", semId, id);

        try {
            StudentSemester semester = semesterRepo.findById(semId)
                    .orElseThrow(() -> new ResourceNotFoundException("Semester not found", "Course id ", id));

            semesterRepo.delete(semester);

            log.info("Deleted semester with ID {} from course with ID: {}", semId, id);
        } catch (Exception e) {
            log.error("Error deleting semester with ID {} from course with ID: {}", semId, id, e);
            throw e; // Rethrow the exception for global exception handling
        }

    }

    private void updateCourseDetails(StudentCourseDetail existingCourseDetail, StudentCourseDetailDTO updatedCourseDetail) {
        if (!Objects.equals(existingCourseDetail.getCourseName(), updatedCourseDetail.getCourseName())) {
            existingCourseDetail.setCourseName(updatedCourseDetail.getCourseName());
        }

        if (existingCourseDetail.getDuration() != updatedCourseDetail.getDuration()) {
            existingCourseDetail.setDuration(updatedCourseDetail.getDuration());
        }
    }

}
