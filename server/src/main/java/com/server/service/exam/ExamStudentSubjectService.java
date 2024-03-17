package com.server.service.exam;

import com.server.dto.ApiResponse;
import com.server.dto.exam.ExamStudentSubjectDTO;

import java.util.List;
import java.util.Map;

public interface ExamStudentSubjectService {

    /**
     * Retrieve all exam student subjects.
     *
     * @return List of ExamStudentSubjectDTO objects representing all subjects.
     */
    List<ExamStudentSubjectDTO> getAllExamStudentSubjects();

    /**
     * Retrieve an exam student subject by its ID.
     *
     * @param id The ID of the exam student subject to retrieve.
     * @return ExamStudentSubjectDTO object representing the subject, or null if not found.
     */
    ExamStudentSubjectDTO getExamStudentSubjectById(Long id);

    /**
     * Add a new exam student subject.
     *
     * @param examStudentSubjectDTO ExamStudentSubjectDTO object representing the subject to be added.
     * @return ExamStudentSubjectDTO object representing the added subject.
     */
    ApiResponse addExamStudentSubject(Map<String,Boolean> requestBody,String token);

    /**
     * Update an existing exam student subject.
     *
     * @param id                     The ID of the exam student subject to update.
     * @param examStudentSubjectDTO ExamStudentSubjectDTO object representing the updated information.
     * @return ExamStudentSubjectDTO object representing the updated subject.
     */
    ExamStudentSubjectDTO updateExamStudentSubject(Long id, ExamStudentSubjectDTO examStudentSubjectDTO);

    /**
     * Delete an exam student subject by its ID.
     *
     * @param id The ID of the exam student subject to delete.
     */
    void deleteExamStudentSubject(Long id);

    List<ExamStudentSubjectDTO> saveExamStudentSubjects(List<ExamStudentSubjectDTO> examStudentSubjectDTOList);

}
