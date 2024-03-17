package com.server.service.reponse;

import com.server.controller.response.ResponseStudentDataController;
import com.server.dto.StudentResponseDto;
import com.server.entities.enums.SemesterStatus;
import com.server.entities.student.*;
import com.server.exception.ResourceNotFoundException;
import com.server.repository.course.SemesterSubjectRepo;
import com.server.repository.course.StudentCourseDetailRepo;
import com.server.repository.course.StudentSemesterRepo;
import com.server.repository.student.StudentCourseRegistrationRepo;
import com.server.repository.student.StudentDetailRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class ResponseStudentServiceIMPL implements ResponseStudentDataService {

    private final Logger log = LoggerFactory.getLogger(ResponseStudentDataController.class);
    @Autowired
    private StudentDetailRepo studentDetailRepo;

    @Autowired
    private StudentCourseRegistrationRepo studentCourseRegistrationRepo;

    @Autowired
    private StudentCourseDetailRepo studentCourseDetailRepo;

    @Autowired
    private StudentSemesterRepo studentSemesterRepo;

    @Autowired
    private SemesterSubjectRepo semesterSubjectRepo;

    @Override
    public StudentResponseDto getStudentData(String email) {
        log.info("Processing the fetching student data...");

        try {
            StudentResponseDto res = new StudentResponseDto();
            log.info("fetching student data");
            StudentDetail student = studentDetailRepo.findByEmail(email);
            List<StudentCourseRegistration> registeredCourse = null;
            StudentCourseDetail studentCourseDetail=null;
            StudentSemester semData = null;
            List<StudentSubject> subject=null;

            log.info("fetching the registered course list");
            if (student != null) {
                // course registration list
                registeredCourse = sortByPriority(studentCourseRegistrationRepo.findByRollNo(student.getRollNo()));
                for (StudentCourseRegistration i : registeredCourse) {
                    i.getStudentCourseDetail().setStudentSemesters(null);
                    log.info("{}", i.getType());
                }
                if (registeredCourse.get(0)!=null){
                    // course high priority data
                    log.info("fetching the course data");
                    studentCourseDetail = registeredCourse.get(0).getStudentCourseDetail();
                    log.info("course id: {}, name: {}", studentCourseDetail.getCourseId(), studentCourseDetail.getCourseName());

                    if(studentCourseDetail!=null){
                        // semester list
                        log.info("fetching the semester list");
                        List<StudentSemester> semester = studentSemesterRepo.findByStudentCourseDetail(studentCourseDetail);
                        for (StudentSemester sem : semester) {
                            log.info("sem status: {}", sem.getStatus());
                            if (sem.getStatus() == SemesterStatus.ACTIVE) {
                                semData = sem;
                                break;
                            }
                        }
                        res.setTotalSem(semester.size());
                        log.info("sem: {}", semData);
                        if(semData!=null){
                            // active semester data
                            log.info("fetching the subject list");
                            subject = semesterSubjectRepo.findAllByCourseIdAndSemesterNumber(studentCourseDetail.getCourseId(), semData.getSemesterId());

                        }
                    }
                }
            }

            // ready to response
            log.info("creating the response data structure.");

            res.setName(student.getName());
            res.setPhoneNo(student.getPhone());
            res.setCourse(studentCourseDetail.getCourseName());
            res.setRollNo(student.getRollNo());
            res.setRegistrationDate(registeredCourse.get(0).getDate());
            res.setEnrollNo(student.getEnrollmentNo());
            res.setTotalSubject(subject.size());
            res.setDuration(studentCourseDetail.getDuration());
            res.setCompleted(String.valueOf((semData.getSemesterNumber() - 1)));
            res.setCurrent(String.valueOf(semData.getSemesterNumber()));
            res.setCourseId(studentCourseDetail.getCourseId());

//            log.info("successfully fetched data: {}", res);
            return res;
        } catch (Exception ex) {
//            log.info("Exception: {}", ex.getMessage());
            throw new ResourceNotFoundException(ex.getMessage(), "no data", 0L);
        }
    }

    public List<StudentCourseRegistration> sortByPriority(List<StudentCourseRegistration> registrations) {
        Comparator<StudentCourseRegistration> priorityComparator = Comparator
                .comparing(StudentCourseRegistration::getType, Comparator.reverseOrder());

        // Sort the list using the priority comparator
        List<StudentCourseRegistration> sortedRegistrations = registrations.stream()
                .sorted(priorityComparator)
                .collect(Collectors.toList());

        return sortedRegistrations;
    }
}
