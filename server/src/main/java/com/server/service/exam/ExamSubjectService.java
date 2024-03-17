package com.server.service.exam;

import com.server.dto.exam.ExamSubjectDTO;

import java.util.List;

public interface ExamSubjectService {


    List<ExamSubjectDTO> getAllSubjects();
    ExamSubjectDTO getSubjectById(Long id);

    ExamSubjectDTO addSubject(ExamSubjectDTO subjectDTO);

    List<ExamSubjectDTO> saveSubjects(List<ExamSubjectDTO> subjectDTOList);


    ExamSubjectDTO updateSubject(Long id, ExamSubjectDTO subjectDTO);
    void deleteSubject(Long id);


}
