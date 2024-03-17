package com.server.service.student;

import com.server.dto.exam.ExamRecordDTO;

import java.util.List;

public interface ExamRecordService {

    /**
     * Retrieve all exam records.
     *
     * @return List of ExamRecordDTO objects representing all records.
     */
    List<ExamRecordDTO> getAllExamRecords(String email);

    /**
     * Retrieve an exam record by its ID.
     *
     * @param id The ID of the exam record to retrieve.
     * @return ExamRecordDTO object representing the record, or null if not found.
     */
    ExamRecordDTO getExamRecordById(Long id);

    /**
     * Add a new exam record.
     *
     * @param examRecordDTO ExamRecordDTO object representing the record to be added.
     * @return ExamRecordDTO object representing the added record.
     */
    ExamRecordDTO addExamRecord(ExamRecordDTO examRecordDTO);

    /**
     * Update an existing exam record.
     *
     * @param id            The ID of the exam record to update.
     * @param examRecordDTO ExamRecordDTO object representing the updated information.
     * @return ExamRecordDTO object representing the updated record.
     */
    ExamRecordDTO updateExamRecord(Long id, ExamRecordDTO examRecordDTO);

    /**
     * Delete an exam record by its ID.
     *
     * @param id The ID of the exam record to delete.
     */
    void deleteExamRecord(Long id);
}
