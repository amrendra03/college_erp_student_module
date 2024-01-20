package com.server.service.impl.course;

import com.server.dto.ApiResponse;
import com.server.dto.student.StudentSubjectDto;

import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentSemester;
import com.server.entities.student.StudentSubject;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.course.SemesterSubjectRepo;

import com.server.repository.course.StudentCourseDetailRepo;
import com.server.service.course.SemesterSubjectService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterSubjectServiceImpl implements SemesterSubjectService {

    @Autowired
    private SemesterSubjectRepo semesterSubjectRepo;


    @Autowired
    private StudentCourseDetailRepo studentCourseDetailRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentSubjectDto create(Long courseId, Long semId, StudentSubjectDto sub) {

        StudentCourseDetail course = this.studentCourseDetailRepo.findById(courseId).orElseThrow(()->new ResourceNotFoundException("Course not found ","course ID",courseId));
        StudentSubject subject =  this.modelMapper.map(sub,StudentSubject.class);
        StudentSubjectDto res=null;

        for(StudentSemester sem: course.getStudentSemesters()){
            if(sem.getSemesterId()==semId){
                subject.setStudentSemester(sem);
                subject.setCourseId(courseId);
                subject.setSemesterNumber(sem.getSemesterNumber());
               res = this.modelMapper.map(this.semesterSubjectRepo.save(subject),StudentSubjectDto.class);
            }
        }
        return res;
    }
    @Override
    public StudentSubjectDto update(Long courseId,int semId,Long subId,StudentSubjectDto studentSubjectDto) {
        // Find the existing entity by ID
        StudentSubject existingStudentSubject = null;
        StudentSubject res=null;
        try {
            existingStudentSubject = this.semesterSubjectRepo.findByCourseIdAndSemesterNumberAndSubjectId(courseId, semId, subId);

            if (existingStudentSubject != null) {
                // Update fields based on the DTO, individually for each attribute
                if (studentSubjectDto.getSubjectName() != null) {
                    existingStudentSubject.setSubjectName(studentSubjectDto.getSubjectName());
                }
                if (studentSubjectDto.getFacultyName() != null) {
                    existingStudentSubject.setFacultyName(studentSubjectDto.getFacultyName());
                }

                // You may choose to update semId and subId separately if needed
                existingStudentSubject.setSemesterNumber(semId);
                existingStudentSubject.setCourseId(courseId);

                res = this.semesterSubjectRepo.save(existingStudentSubject);

            }
        } catch (Exception ex) {
            // Handle exceptions (e.g., database errors)
            throw new ResourceNotFoundException("Error updating StudentSubject: " + ex.getMessage(), "subId", subId);
        }
        return this.modelMapper.map(res, StudentSubjectDto.class);
    }

    @Override
    public StudentSubjectDto get(Long courseId,int semId,Long subId) {

        StudentSubject res=this.semesterSubjectRepo.findByCourseIdAndSemesterNumberAndSubjectId(courseId,semId,subId);
        return this.modelMapper.map(res,StudentSubjectDto.class);
    }

    @Override
    public List<StudentSubjectDto> getAll(Long courseId,int semId) {

        List<StudentSubject> res = this.semesterSubjectRepo.findAllByCourseIdAndSemesterNumber(courseId,semId);

        return res.stream().map(x->this.modelMapper.map(x,StudentSubjectDto.class)).collect(Collectors.toList());

    }

    @Override
    public ApiResponse delete(Long courseId, int semId, Long subId) {
        StudentSubject res = this.semesterSubjectRepo.findByCourseIdAndSemesterNumberAndSubjectId(courseId,semId,subId);
        this.semesterSubjectRepo.delete(res);
        return new ApiResponse("Successfully deleted subId "+subId,true);
    }

}
