package com.server.service.impl.student;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentSemesterProgressDTO;
import com.server.entities.student.StudentDetail;
import com.server.entities.student.StudentSemesterProgress;
import com.server.exception.ResourceNotFoundException;
import com.server.exception.custom.DataOperationException;
import com.server.repository.student.StudentDetailRepo;
import com.server.repository.student.StudentSemesterProgressRepo;
import com.server.service.student.StudentCourseStatusService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentCourseStatusServiceImpl implements StudentCourseStatusService {

    private final Logger log = LoggerFactory.getLogger(StudentCourseStatusServiceImpl.class);

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentSemesterProgressRepo studentSemesterProgressRepo;

    @Autowired
    private StudentDetailRepo studentDetailRepo;

    @Override
    public StudentSemesterProgressDTO create(StudentSemesterProgressDTO req,String rollNo) {
        log.info("Processing to create semester progress: {}", req);
        try {
            StudentDetail studentDetail = studentDetailRepo.findByRollNo(rollNo);

            StudentSemesterProgress progress = modelMapper.map(req, StudentSemesterProgress.class);
            progress.setStudentDetail(studentDetail);

            StudentSemesterProgress savedProgress = studentSemesterProgressRepo.save(progress);
            StudentSemesterProgressDTO res = modelMapper.map(savedProgress, StudentSemesterProgressDTO.class);
            log.info("Successfully saved the semester progress");
            return res;
        } catch (Exception ex) {
            log.error("Error during semester progress creation: {}", ex.getMessage());
            throw new DataOperationException("Error during semester progress creation", ex);
        }
    }

    @Override
    public StudentSemesterProgressDTO update(StudentSemesterProgressDTO req) {
        log.info("Processing to update semester progress data: {}", req.getProgressId());
        try {
            StudentSemesterProgress progress = studentSemesterProgressRepo.findById(req.getProgressId())
                    .orElseThrow(() -> new ResourceNotFoundException("Semester progress not found", "Invalid data: ", req.getProgressId()));

            modelMapper.map(req, progress);
            StudentSemesterProgress updatedProgress = studentSemesterProgressRepo.save(progress);
            log.info("Successfully updated semester progress");
            return modelMapper.map(updatedProgress, StudentSemesterProgressDTO.class);
        } catch (Exception ex) {
            log.error("Error during semester progress update: {}", ex.getMessage());
            throw new DataOperationException("Error during semester progress update", ex);
        }
    }

    @Override
    public ApiResponse delete(String rollNo, Long semId) {
        log.info("Processing to delete semester progress: {}", semId);
        try {
            StudentDetail studentDetail  = studentDetailRepo.findByRollNo(rollNo);
            StudentSemesterProgress progress = studentSemesterProgressRepo
                    .findByStudentDetail_StudentIdAndStudentSemester_SemesterId(studentDetail.getStudentId(), semId)
                    .orElseThrow(() -> new ResourceNotFoundException("Semester progress not found", "sem id",semId));

            if (progress.getStudentSemester().getSemesterId().equals(semId)) {
                studentSemesterProgressRepo.delete(progress);
                log.info("Successfully deleted semester progress: {}", semId);
                return new ApiResponse("Successfully deleted semester progress:" + rollNo + " / " + semId, true);
            } else {
                log.info("Semester progress not found: {}", semId);
                return new ApiResponse("Semester progress not found !!", false);
            }
        } catch (Exception ex) {
            log.error("Error during semester progress deletion: {}", ex.getMessage());
            throw new DataOperationException("Error during semester progress deletion", ex);
        }
    }

    @Override
    public List<StudentSemesterProgressDTO> getAll(String rollNo) {
        log.info("Processing to get all semester progress list registered with the student.");
        try {
            StudentDetail student = studentDetailRepo.findByRollNo(rollNo);
            if (student==null) {
                throw new ResourceNotFoundException("Course not found", "RollNo: "+rollNo,0L);
            }

            List<StudentSemesterProgress> progressList = studentSemesterProgressRepo.findByStudentDetail(student);
            List<StudentSemesterProgressDTO> res = progressList.stream()
                    .map(p -> modelMapper.map(p, StudentSemesterProgressDTO.class))
                    .collect(Collectors.toList());

            log.info("Successfully retrieved semester progress list.");
            return res;
        } catch (Exception ex) {
            log.error("Error during getting semester progress list: {}", ex.getMessage());
            throw new DataOperationException("Error during getting semester progress list", ex);
        }
    }
}
