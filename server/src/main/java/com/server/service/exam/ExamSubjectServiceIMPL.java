package com.server.service.exam;

import com.server.dto.exam.ExamSubjectDTO;
import com.server.entities.exam.ExamSubject;
import com.server.repository.exam.ExamSubjectRepo;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ExamSubjectServiceIMPL implements ExamSubjectService {
    private final ExamSubjectRepo examSubjectRepo;
    private final ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(ExamSubjectServiceIMPL.class);

    @Autowired
    public ExamSubjectServiceIMPL(ExamSubjectRepo examSubjectRepo, ModelMapper modelMapper) {
        this.examSubjectRepo = examSubjectRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ExamSubjectDTO> getAllSubjects() {
        List<ExamSubject> subjects = examSubjectRepo.findAll();
        logger.info("Retrieved all subjects from the database.");
        return subjects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExamSubjectDTO getSubjectById(Long id) {
        Optional<ExamSubject> optionalSubject = examSubjectRepo.findById(id);
        if (optionalSubject.isPresent()) {
            ExamSubject subject = optionalSubject.get();
            logger.info("Retrieved subject with id: {}", id);
            return convertToDTO(subject);
        } else {
            logger.warn("Subject with id {} not found.", id);
            return null;
        }
    }

    @Override
    public ExamSubjectDTO addSubject(ExamSubjectDTO subjectDTO) {
        ExamSubject subject = convertToEntity(subjectDTO);
        subject = examSubjectRepo.save(subject);
        logger.info("Added new subject with id: {}", subject.getId());
        return convertToDTO(subject);
    }

    @Override
    public List<ExamSubjectDTO> saveSubjects(List<ExamSubjectDTO> subjectDTOList) {
        List<ExamSubject> subjects = subjectDTOList.stream()
                .map(this::convertToEntity)
                .collect(Collectors.toList());
        subjects = examSubjectRepo.saveAll(subjects);
        logger.info("Saved {} subjects.", subjects.size());
        return subjects.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ExamSubjectDTO updateSubject(Long id, ExamSubjectDTO subjectDTO) {
        Optional<ExamSubject> optionalSubject = examSubjectRepo.findById(id);
        if (optionalSubject.isPresent()) {
            ExamSubject subject = optionalSubject.get();
            subject.setSubjectCode(subjectDTO.getSubjectCode());
            subject.setSubjectName(subjectDTO.getSubjectName());
            subject.setCost(subjectDTO.getCost());
            examSubjectRepo.save(subject);
            logger.info("Updated subject with id: {}", id);
            return convertToDTO(subject);
        } else {
            logger.warn("Subject with id {} not found. Update failed.", id);
            return null;
        }
    }

    @Override
    public void deleteSubject(Long id) {
        if (examSubjectRepo.existsById(id)) {
            examSubjectRepo.deleteById(id);
            logger.info("Deleted subject with id: {}", id);
        } else {
            logger.warn("Subject with id {} not found. Deletion failed.", id);
        }
    }

    private ExamSubjectDTO convertToDTO(ExamSubject subject) {
        return modelMapper.map(subject, ExamSubjectDTO.class);
    }

    private ExamSubject convertToEntity(ExamSubjectDTO subjectDTO) {
        return modelMapper.map(subjectDTO, ExamSubject.class);
    }

}
