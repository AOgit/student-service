package ait.cohort60.student.service;

import ait.cohort60.student.dao.StudentRepository;
import ait.cohort60.student.dto.ScoreDto;
import ait.cohort60.student.dto.StudentCredentialsDto;
import ait.cohort60.student.dto.StudentDto;
import ait.cohort60.student.dto.StudentUpdateDto;
import ait.cohort60.student.dto.exceptions.NotFoundException;
import ait.cohort60.student.model.Student;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
//    @Autowired
    private final StudentRepository studentRepository;
    private final ModelMapper modelMapper;


    @Override
    public Boolean addStudent(StudentCredentialsDto studentCredentialsDto) {
        if(studentRepository.findById(studentCredentialsDto.getId()).isPresent()){
           return false;
        }
//        Student student = new Student(
//                studentCredentialsDto.getId(),
//                studentCredentialsDto.getName(),
//                studentCredentialsDto.getPassword()
//                );
        Student student = modelMapper.map(studentCredentialsDto, Student.class);
        studentRepository.save(student);
        return true;
    }

    @Override
    public StudentDto findStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundException::new);
        return modelMapper.map(student, StudentDto.class);
    }

    @Override
    public StudentDto removeStudentById(Long id) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundException::new);
        studentRepository.deleteById(student.getId());
        return modelMapper.map(student, StudentDto.class);
    }


    @Override
    public StudentCredentialsDto updateStudent(Long id, StudentUpdateDto studentUpdateDto) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundException::new);
        student.setName(studentUpdateDto.getName());
        student.setPassword(studentUpdateDto.getPassword());
        studentRepository.save(student);
        return modelMapper.map(student, StudentCredentialsDto.class);
    }

    @Override
    public Boolean addScore(Long id, ScoreDto scoreDto) {
        Student student = studentRepository.findById(id).orElseThrow(NotFoundException::new);
        boolean result = student.addScore(scoreDto.getExamName(), scoreDto.getScore());
        studentRepository.save(student);
        return result;
    }

    @Override
    public List<StudentDto> findStudentsByName(String name) {
        return studentRepository.findByNameIgnoreCase(name)
                .map(s ->modelMapper.map(s, StudentDto.class))
                .toList();
    }

    @Override
    public Long countStudentsByNames(Set<String> names) {
        return studentRepository.countByNameIn(names);
    }

    @Override
    public List<StudentDto> findStudentsByExamNameMinScore(String examName, Integer minScore) {

        return studentRepository.findByExamAndScoresGreaterThan(examName, minScore)
                .map(s ->modelMapper.map(s, StudentDto.class))
                .toList();
    }
}
