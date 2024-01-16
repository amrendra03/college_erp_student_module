package com.server.service.impl.course;

import com.server.dto.course.CourseDetailDTO;
import com.server.dto.course.SemesterDTO;
import com.server.dto.student.StudentCourseDetailDTO;
import com.server.dto.student.StudentSemesterDTO;
import com.server.entities.course.CourseDetail;
import com.server.entities.course.Semester;
import com.server.entities.student.StudentCourseDetail;
import com.server.entities.student.StudentSemester;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.course.CourseDetailRepo;
import com.server.repository.course.SemesterRepo;
import com.server.repository.course.StudentCourseDetailRepo;
import com.server.repository.course.StudentSemesterRepo;
import com.server.service.course.StudentCourseService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentCourseServiceImpl implements StudentCourseService {

    @Autowired
    private StudentCourseDetailRepo courseDetailRepo;

    @Autowired
    private StudentSemesterRepo semesterRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public StudentCourseDetailDTO create(StudentCourseDetailDTO courseDetailDTO) {
        StudentCourseDetail course = this.modelMapper.map(courseDetailDTO, StudentCourseDetail.class);
        this.courseDetailRepo.save(course);
        return this.modelMapper.map(this.courseDetailRepo.save(course), StudentCourseDetailDTO.class);
    }

    @Override
    public StudentCourseDetailDTO update(StudentCourseDetailDTO x, Long id) {
        StudentCourseDetail  y = this.courseDetailRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course id not found !.", "Course id", id));

        if (x.getCourseName() != y.getCourseName())
            y.setCourseName(x.getCourseName());
        if (x.getDuration() != y.getDuration())
            y.setDuration(x.getDuration());

        return this.modelMapper.map(this.courseDetailRepo.save(y), StudentCourseDetailDTO.class);
    }

    @Override
    public StudentCourseDetailDTO get(Long id) {
        StudentCourseDetail course = this.courseDetailRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course id not found !.", "Course id", id));

        return this.modelMapper.map(course, StudentCourseDetailDTO.class);

    }

    @Override
    public List<StudentCourseDetailDTO> getAll() {
        List<StudentCourseDetail> list = this.courseDetailRepo.findAll();

        List<StudentCourseDetailDTO> dtoList = list.stream().map((x) -> this.modelMapper.map(x, StudentCourseDetailDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public void delete(Long id) {
        StudentCourseDetail course = this.courseDetailRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found !.", "Course Id", id));
        this.courseDetailRepo.delete(course);
    }

    @Override
    public StudentSemesterDTO addSem(Long id, StudentSemesterDTO sem) {

        StudentSemester x = this.semesterRepo.save(this.modelMapper.map(sem, StudentSemester.class));

        return this.modelMapper.map(x, StudentSemesterDTO.class);

    }

    @Override
    public void deleteSem(Long id, Long semId) {
        StudentSemester course = this.semesterRepo.findById(semId)
                .orElseThrow(() -> new ResourceNotFoundException("Semester not found", "Course id ", id));

        this.semesterRepo.delete(course);

    }

}
