package ait.cohort60.student.service;

import ait.cohort60.student.dto.ScoreDto;
import ait.cohort60.student.dto.StudentCredentialsDto;
import ait.cohort60.student.dto.StudentDto;
import ait.cohort60.student.dto.StudentUpdateDto;

import java.util.List;
import java.util.Set;

public interface StudentService {
    Boolean addStudent(StudentCredentialsDto studentCredentialsDto);

    StudentDto findStudentById(Long id);
    StudentDto removeStudentById(Long id);
    StudentCredentialsDto updateStudent(Long id, StudentUpdateDto studentUpdateDto);
    Boolean addScore(Long score, ScoreDto scoreDto);
    List<StudentDto> findStudentsByName(String name);
    Long countStudentsByNames(Set<String> name);
    List<StudentDto> findStudentsByExamNameMinScore(String examName, Integer minScore);

}